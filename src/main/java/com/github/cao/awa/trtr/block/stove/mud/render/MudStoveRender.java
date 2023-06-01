package com.github.cao.awa.trtr.block.stove.mud.render;

import com.github.cao.awa.apricot.anntation.Planning;
import com.github.cao.awa.apricot.anntation.Unsupported;
import com.github.cao.awa.trtr.block.stove.mud.MudStoveBlockEntity;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Random;

// TODO Waiting for plan 'Smelting Process'
@Planning
@Unsupported
public class MudStoveRender implements BlockEntityRenderer<MudStoveBlockEntity> {
    private static final Random RANDOM = new Random();

    public MudStoveRender(BlockEntityRendererFactory.Context ctx) {

    }

    @Override
    public void render(MudStoveBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (entity.getFuel() > 0) {
            matrices.push();

            matrices.translate(0.125,
                               0.12,
                               0.125
            );

            matrices.scale(0.75F,
                           (float) Math.max(entity.getFuel() * 0.6,
                                            (entity.getFuel() - 0.35)
                           ),
                           0.75F
            );

            MinecraftClient.getInstance()
                           .getBlockRenderManager()
                           .renderBlockAsEntity(Blocks.COAL_BLOCK.getDefaultState(),
                                                matrices,
                                                vertexConsumers,
                                                light,
                                                overlay
                           );

            matrices.pop();
        }
    }
}
