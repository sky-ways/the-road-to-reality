package com.github.cao.awa.trtr.ref.block.trtr.slab;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.*;
import net.minecraft.client.util.math.*;
import net.minecraft.item.*;

public class TrtrSlabBlockEntityRenderer implements BlockEntityRenderer<TrtrConventionalSlabEntity> {
    private static final ObjectArrayList<Item> BLOCK_EXCEPT = EntrustEnvironment.operation(new ObjectArrayList<>(), list -> {
        list.add(Items.COCOA_BEANS);
        list.add(Items.REDSTONE);
    });

    public TrtrSlabBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {

    }

    @Override
    public void render(TrtrConventionalSlabEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Item item = blockEntity.getItem().getItem();
        if (!BLOCK_EXCEPT.contains(item) && (item instanceof TrtrBlockItem || item instanceof BlockItem)) {
            TrtrBlockItem.slabRender(blockEntity, tickDelta, matrices, vertexConsumers, light, overlay);
            return;
        }
        TrtrItem.slabRender(blockEntity, tickDelta, matrices, vertexConsumers, light, overlay);
    }
}
