package com.github.cao.awa.trtr.block.pan.frying.renderer;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.anntation.Planning;
import com.github.cao.awa.apricot.anntation.Unsupported;
import com.github.cao.awa.trtr.block.pan.frying.entity.FryingPanBlockEntity;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Random;

@Auto
@Planning
@Unsupported
public class FryingPanRenderer implements BlockEntityRenderer<FryingPanBlockEntity> {
    private static final Random RANDOM = new Random();

    public FryingPanRenderer(BlockEntityRendererFactory.Context ctx) {

    }

    @Override
    public void render(FryingPanBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

    }
}
