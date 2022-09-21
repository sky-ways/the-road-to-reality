package com.github.cao.awa.trtr.ref.item.trtr;

import com.github.cao.awa.trtr.ore.copper.chalcopyrite.crushed.*;
import com.github.cao.awa.trtr.ref.*;
import com.github.cao.awa.trtr.ref.block.trtr.slab.*;
import net.minecraft.client.*;
import net.minecraft.client.render.*;
import net.minecraft.client.render.model.json.*;
import net.minecraft.client.util.math.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;

public abstract class TrtrItem extends Item implements RefRegister {
    public TrtrItem(Settings settings) {
        this(settings, false);
    }

    public TrtrItem(Settings settings, boolean doNotRegister) {
        super(settings);
        if (!doNotRegister) {
            register();
        }
    }

    public TrtrItem() {
        this(new Settings());
    }

    @Override
    public abstract Identifier identifier();

    @Override
    public void register() {
        Registry.register(Registry.ITEM, identifier(), this);
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
