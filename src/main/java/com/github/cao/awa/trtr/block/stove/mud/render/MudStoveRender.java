package com.github.cao.awa.trtr.block.stove.mud.render;

import com.github.cao.awa.apricot.anntation.Planning;
import com.github.cao.awa.apricot.anntation.Unsupported;
import com.github.cao.awa.trtr.block.stove.mud.MudStoveBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.TestOnly;

import java.util.Random;

@Planning
@Unsupported
public class MudStoveRender implements BlockEntityRenderer<MudStoveBlockEntity> {
    private static final Random RANDOM = new Random();

    public MudStoveRender(BlockEntityRendererFactory.Context ctx) {

    }

    @TestOnly
    @Override
    public void render(MudStoveBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
//        matrices.push();
//
//        // 计算当前y值的偏移
//        double offset = Math.sin(RANDOM.nextDouble()) / 4.0;
//        // 移动物品
//        matrices.translate(0.5,
//                           1.25 + offset,
//                           0.5
//        );
//
//        // 旋转物品
//        matrices.multiply(new Quaternionf(RANDOM.nextDouble(),
//                                          RANDOM.nextDouble(),
//                                          RANDOM.nextDouble(),
//                                          RANDOM.nextDouble()
//        ));
//
//        MinecraftClient.getInstance()
//                       .getItemRenderer()
//                       .renderItem(
//                               Blocks.BEDROCK.asItem()
//                                             .getDefaultStack(),
//                               ModelTransformationMode.GROUND,
//                               light,
//                               OverlayTexture.DEFAULT_UV,
//                               matrices,
//                               vertexConsumers,
//                               entity.getWorld(),
//                               0
//                       );
//
//        matrices.pop();
    }
}
