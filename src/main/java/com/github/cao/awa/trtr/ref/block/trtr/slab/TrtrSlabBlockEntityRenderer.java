package com.github.cao.awa.trtr.ref.block.trtr.slab;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.block.entity.*;
import net.minecraft.client.*;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.*;
import net.minecraft.client.render.model.json.*;
import net.minecraft.client.util.math.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;

public class TrtrSlabBlockEntityRenderer implements BlockEntityRenderer<TrtrConventionalSlabEntity> {
    public TrtrSlabBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {

    }

    @Override
    public void render(TrtrConventionalSlabEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (blockEntity.getItem().getItem() instanceof TrtrBlockItem || blockEntity.getItem().getItem() instanceof BlockItem) {
            TrtrBlockItem.slabRender(blockEntity, tickDelta, matrices, vertexConsumers, light, overlay);
            return;
        }
        TrtrItem.slabRender(blockEntity, tickDelta, matrices, vertexConsumers, light, overlay);
    }
}
