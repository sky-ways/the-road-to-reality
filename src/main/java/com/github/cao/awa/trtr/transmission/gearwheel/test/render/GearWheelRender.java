package com.github.cao.awa.trtr.transmission.gearwheel.test.render;

import com.github.cao.awa.trtr.transmission.gearwheel.test.*;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.util.math.*;
import net.minecraft.util.*;

public class GearWheelRender extends EntityRenderer<GearWheelEntity> {
    public GearWheelRender(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(GearWheelEntity entity) {
        return null;
    }

    @Override
    public void render(GearWheelEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
