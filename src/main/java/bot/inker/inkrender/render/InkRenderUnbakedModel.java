package bot.inker.inkrender.render;

import bot.inker.inkrender.api.InkResourceLoader;
import bot.inker.inkrender.MemoryResourcePack;
import bot.inker.inkrender.util.UncheckUtil;
import com.mojang.datafixers.util.Pair;
import de.javagl.obj.FloatTuple;
import de.javagl.obj.Mtl;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjSplitting;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Function;

public class InkRenderUnbakedModel implements UnbakedModel {
    public static final SpriteIdentifier DEFAULT_SPRITE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, null);
    private static final Logger logger = LogManager.getLogger();
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
        this.transform = transform == null ? ModelTransformation.NONE : transform;

        Mtl mtl = mtls.get("sprite");
        if (mtls.size() == 0) {
            this.sprite = DEFAULT_SPRITE;
        } else {
            Mtl spriteMtl = (mtl == null ? mtls.values().iterator().next() : mtl);
            this.sprite = spriteMtl.getMapKd() != null ? new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(spriteMtl.getMapKd())) : DEFAULT_SPRITE;
        }

        this.resourceIdentifier = new HashMap<>();
        for (Mtl material : mtls.values()) {
            if (material.getMapKd() == null) {
                continue;
            }
            resourceIdentifier.computeIfAbsent(material.getMapKd(), str -> MemoryResourcePack.INSTANCE.put(() -> resourceLoader.openTexture(material.getMapKd())));
        }
    }


    @Override
    public Collection<Identifier> getModelDependencies() {
        return Collections.emptySet();
    }

    @Override
    public Collection<SpriteIdentifier> getTextureDependencies(Function<Identifier, UnbakedModel> unbakedModelGetter, Set<Pair<String, String>> unresolvedTextureReferences) {
        return resourceIdentifier.values().stream().map(it -> new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, it)).toList();
    }

    @Override
    public BakedModel bake(ModelLoader loader, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings bakeSettings, Identifier modelId) {
        try {
            return bakeImpl(textureGetter, bakeSettings);
        } catch (Exception e) {
            logger.error("Failed to bake obj model", e);
            return UncheckUtil.uncheck(e);
        }
    }

    public BakedModel bakeImpl(Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings bakeSettings) {
        Renderer renderer = RendererAccess.INSTANCE.getRenderer();
        Mesh mesh = null;

        if (renderer != null) {
            Map<String, Obj> materialGroups = ObjSplitting.splitByMaterialGroups(obj);
            MeshBuilder builder = renderer.meshBuilder();
            QuadEmitter emitter = builder.getEmitter();

            materialGroups.forEach((matName, matGroupObj) -> {
                Mtl mtl = mtls.get(matName);

                Sprite sprite = textureGetter.apply(DEFAULT_SPRITE);

                if (mtl != null && mtl.getMapKd() != null) {
                    sprite = textureGetter.apply(new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, resourceIdentifier.get(mtl.getMapKd())));
                }

                for (int i = 0; i < matGroupObj.getNumFaces(); i++) {
                    for (int v = 0; v < matGroupObj.getFace(i).getNumVertices(); v++) {
                        FloatTuple floatTuple = matGroupObj.getVertex(matGroupObj.getFace(i).getVertexIndex(v));
                        Vec3f vertex = new Vec3f(floatTuple.getX(), floatTuple.getY(), floatTuple.getZ());

                        addVertex(i, v, v, vertex, emitter, matGroupObj);

                        if (v == 2 && matGroupObj.getFace(i).getNumVertices() == 3) {
                            addVertex(i, 3, 2, vertex, emitter, matGroupObj);
                        }
                    }

                    emitter.spriteColor(0, - 1, - 1, - 1, - 1);
                    emitter.material(RendererAccess.INSTANCE.getRenderer().materialFinder().find());
                    emitter.nominalFace(emitter.lightFace());
                    emitter.spriteBake(0, sprite, MutableQuadView.BAKE_NORMALIZED | (bakeSettings.isUvLocked() ? MutableQuadView.BAKE_LOCK_UV : 0));

                    emitter.emit();
                }
            });

            mesh = builder.build();
        }

        return new InkRenderBakedModel(mesh, transform, textureGetter.apply(this.sprite));
    }

    private void addVertex(int faceIndex, int vertIndex, int realIndex, Vec3f vertex, QuadEmitter emitter, Obj matGroup) {
        emitter.pos(vertIndex, vertex.getX(), vertex.getY(), vertex.getZ());

        if (matGroup.getFace(faceIndex).containsTexCoordIndices()) {
            FloatTuple text = matGroup.getTexCoord(matGroup.getFace(faceIndex).getTexCoordIndex(realIndex));
            emitter.sprite(vertIndex, 0, text.getX(), text.getY());
        }

        if (matGroup.getFace(faceIndex).containsNormalIndices()) {
            FloatTuple normal = matGroup.getNormal(matGroup.getFace(faceIndex).getNormalIndex(realIndex));
            emitter.normal(vertIndex, normal.getX(), normal.getY(), normal.getZ());
            emitter.nominalFace(Direction.getFacing(normal.getX(), normal.getY(), normal.getZ()));
        }
    }
}
