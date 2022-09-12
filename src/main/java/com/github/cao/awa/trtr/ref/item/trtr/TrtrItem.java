package com.github.cao.awa.trtr.ref.item.trtr;

import com.github.cao.awa.trtr.ref.block.trtr.slab.*;
import net.minecraft.client.*;
import net.minecraft.client.render.*;
import net.minecraft.client.render.model.json.*;
import net.minecraft.client.util.math.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;

public class TrtrItem extends Item {
    public TrtrItem(Settings settings) {
        super(settings);
    }

    public static void slabRender(TrtrConventionalSlabEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        matrices.translate(0.47, 0.52, 0.37);
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90));

        int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos());
        MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getItem(), ModelTransformation.Mode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 0);

        matrices.pop();
    }
}
