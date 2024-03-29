package bot.inker.inkrender.render;

import bot.inker.inkrender.MemoryResourcePack;
import bot.inker.inkrender.render.resource.loader.InkResourceLoader;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import de.javagl.obj.FloatTuple;
import de.javagl.obj.Mtl;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjSplitting;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class InkRenderUnbakedModel implements UnbakedModel {
    public static final SpriteIdentifier DEFAULT_SPRITE = new SpriteIdentifier(
            SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE,
            null
    );
    public static final Function<Identifier, SpriteIdentifier> DEFAULT_SPRITE_IDENTIFIED = identifier -> new SpriteIdentifier(
            SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE,
            identifier
    );
    private static final Logger LOGGER = LogManager.getLogger("InkRenderUnbakedModel");
    private final InkResourceLoader resourceLoader;
    private final Obj obj;
    private final Map<String, Mtl> mtls;
    private final Map<String, Identifier> resourceIdentifier;
    private final ModelTransformation transform;
    private final SpriteIdentifier sprite;

    public InkRenderUnbakedModel(InkResourceLoader resourceLoader, Obj obj, Map<String, Mtl> mtls, ModelTransformation transform) {
        this.resourceLoader = resourceLoader;
        this.obj = obj;
        this.mtls = mtls;
        this.transform = EntrustEnvironment.nonnull(
                transform,
                ModelTransformation.NONE
        );

        if (mtls.size() == 0) {
            this.sprite = DEFAULT_SPRITE;
        } else {
            Mtl mtl = mtls.get("sprite");
            String mapKd = (mtl == null ?
                    mtls.values()
                        .iterator()
                        .next() :
                    mtl).getMapKd();
            this.sprite = mapKd == null ? DEFAULT_SPRITE : DEFAULT_SPRITE_IDENTIFIED.apply(new Identifier(mapKd));
        }

        this.resourceIdentifier = new Object2ObjectOpenHashMap<>();
        mtls.values()
            .parallelStream()
            .map(Mtl :: getMapKd)
            .filter(Objects :: nonNull)
            .forEach(mapKd -> this.resourceIdentifier.computeIfAbsent(
                    mapKd,
                    str -> MemoryResourcePack.INSTANCE.put(() -> resourceLoader.openTexture(mapKd))
            ));
    }


    @Override
    public Collection<Identifier> getModelDependencies() {
        return Collections.emptySet();
    }

    @Override
    public void setParents(Function<Identifier, UnbakedModel> modelLoader) {
    }

    @Override
    public BakedModel bake(Baker baker, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings bakeSettings, Identifier modelId) {
        try {
            Renderer renderer = RendererAccess.INSTANCE.getRenderer();
            Mesh mesh = null;

            if (renderer != null) {
                Map<String, Obj> materialGroups = ObjSplitting.splitByMaterialGroups(this.obj);
                MeshBuilder builder = renderer.meshBuilder();
                QuadEmitter emitter = builder.getEmitter();

                materialGroups.forEach((matName, matGroupObj) -> {
                    Mtl mtl = this.mtls.get(matName);

                    Sprite sprite = textureGetter.apply(DEFAULT_SPRITE);

                    if (! (mtl == null || mtl.getMapKd() == null)) {
                        sprite = textureGetter.apply(DEFAULT_SPRITE_IDENTIFIED.apply(this.resourceIdentifier.get(mtl.getMapKd())));
                    }

                    for (int i = 0; i < matGroupObj.getNumFaces(); i++) {
                        for (int v = 0; v < matGroupObj.getFace(i)
                                                       .getNumVertices(); v++) {
                            FloatTuple floatTuple = matGroupObj.getVertex(matGroupObj.getFace(i)
                                                                                     .getVertexIndex(v));
                            Vec3d vertex = new Vec3d(
                                    floatTuple.getX(),
                                    floatTuple.getY(),
                                    floatTuple.getZ()
                            );

                            addVertex(
                                    i,
                                    v,
                                    v,
                                    vertex,
                                    emitter,
                                    matGroupObj
                            );

                            if (v == 2 && matGroupObj.getFace(i)
                                                     .getNumVertices() == 3) {
                                addVertex(
                                        i,
                                        3,
                                        2,
                                        vertex,
                                        emitter,
                                        matGroupObj
                                );
                            }
                        }

                        emitter.spriteColor(
                                0,
                                - 1,
                                - 1,
                                - 1,
                                - 1
                        );
                        emitter.material(RendererAccess.INSTANCE.getRenderer()
                                                                .materialFinder()
                                                                .find());
                        emitter.nominalFace(emitter.lightFace());
                        emitter.spriteBake(
                                0,
                                sprite,
                                bakeSettings.isUvLocked() ?
                                        // Check uv lock.
                                        36 :
                            /* If locked.
                            36: (32 | 4) -> (
                                MutableQuadView.BAKE_NORMALIZED |
                                MutableQuadView.BAKE_LOCK_UV
                            )
                             */
                                        32
                            /* If not locked.
                            32:
                                MutableQuadView.BAKE_NORMALIZED
                             */
                        );

                        emitter.emit();
                    }
                });

                mesh = builder.build();
            }

            return new InkRenderBakedModel(
                    mesh,
                    this.transform,
                    textureGetter.apply(this.sprite)
            );
        } catch (Exception e) {
            LOGGER.error(
                    "Failed to bake obj model",
                    e
            );
            // TODO
//            return UncheckUtil.uncheck(e);
            return null;
        }
    }

    private void addVertex(int faceIndex, int vertIndex, int realIndex, Vec3d vertex, QuadEmitter emitter, Obj matGroup) {
        emitter.pos(
                vertIndex,
                (float) vertex.getX(),
                (float) vertex.getY(),
                (float) vertex.getZ()
        );

        if (matGroup.getFace(faceIndex)
                    .containsTexCoordIndices()) {
            FloatTuple text = matGroup.getTexCoord(matGroup.getFace(faceIndex)
                                                           .getTexCoordIndex(realIndex));
            emitter.sprite(
                    vertIndex,
                    0,
                    text.getX(),
                    text.getY()
            );
        }

        if (matGroup.getFace(faceIndex)
                    .containsNormalIndices()) {
            FloatTuple normal = matGroup.getNormal(matGroup.getFace(faceIndex)
                                                           .getNormalIndex(realIndex));
            emitter.normal(
                    vertIndex,
                    normal.getX(),
                    normal.getY(),
                    normal.getZ()
            );
            emitter.nominalFace(Direction.getFacing(
                    normal.getX(),
                    normal.getY(),
                    normal.getZ()
            ));
        }
    }
}
