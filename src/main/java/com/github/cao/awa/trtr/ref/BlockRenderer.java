package com.github.cao.awa.trtr.ref;

import net.minecraft.block.entity.*;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.*;

public interface BlockRenderer<T extends BlockEntity> {
    void render(BlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay);

    default void instanceRender(T blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

    }
}
