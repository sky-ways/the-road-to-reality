package bot.inker.inkrender.render;

import bot.inker.inkrender.util.Matrix4Util;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.model.ModelHelper;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.List;
import java.util.function.Supplier;

public class InkRenderBakedModel implements BakedModel, FabricBakedModel {
    private static final Vector3f VEC3F = new Vector3f();
    private final Mesh mesh;
    private final ModelTransformation transformation;
    private final Sprite sprite;

    public InkRenderBakedModel(Mesh mesh, ModelTransformation transformation, Sprite sprite) {
        this.mesh = mesh;
        this.transformation = transformation;
        this.sprite = sprite;
    }

    @Override
    public List<BakedQuad> getQuads(BlockState blockState, Direction direction, Random random) {
        List<BakedQuad>[] bakedQuads = ModelHelper.toQuadLists(mesh);
        return bakedQuads[direction == null ? 6 : direction.getId()];
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean hasDepth() {
        return true;
    }

    @Override
    public boolean isSideLit() {
        return false;
    }

    @Override
    public boolean isBuiltin() {
        return false;
    }

    @Override
    public Sprite getParticleSprite() {
        return this.sprite;
    }

    @Override
    public ModelTransformation getTransformation() {
        return transformation;
    }

    @Override
    public ModelOverrideList getOverrides() {
        return ModelOverrideList.EMPTY;
    }

    @Override
    public boolean isVanillaAdapter() {
        return false;
    }

    @Override
    public void emitBlockQuads(BlockRenderView blockRenderView, BlockState blockState, BlockPos blockPos, Supplier<Random> supplier, RenderContext context) {
        if (mesh == null) {
            return;
        }
        context.pushTransform(quad -> {
            // var rad = Math.toRadians(supplier.get().nextInt(360));
            Matrix4f mat = Matrix4Util.identityMatrix();
            Matrix4Util.multiply(mat, Matrix4Util.HALF_OF_XYZ_TRANSLATE);
            Matrix4Util.multiply(mat, Matrix4Util.ROTATE_Y_OF_QUARTER_PI);
            // mat.multiply(Matrix4Util.translate(-0.5f,-0.5f, -0.5f));
            // mat.multiply(Matrix4Util.translate(0.5f,0f, 0.5f));
            for (int i = 0; i < 4; i++) {
                quad.copyPos(
                        i,
                        VEC3F
                );
                quad.pos(
                        i,
                        Matrix4Util.transform(
                                mat,
                                VEC3F
                        )
                );
            }
            return true;
        });
        context.meshConsumer()
               .accept(mesh);
        context.popTransform();
    }

    @Override
    public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
        if (mesh == null) {
            return;
        }
        context.meshConsumer()
               .accept(mesh);
    }
}
