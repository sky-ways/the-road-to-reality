package com.github.cao.awa.trtr.block.stove.mud.render;

import com.github.cao.awa.apricot.anntation.Planning;
import com.github.cao.awa.apricot.anntation.Unsupported;
import com.github.cao.awa.trtr.block.stove.mud.MudStoveBlockEntity;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
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
        matrices.push();

        matrices.translate(0.5,
                           1.25,
                           0.5
        );

        EntrustEnvironment.operation(entity.getFuel(),
                                     item -> {
                                         if (item == null) {
                                             return;
                                         }
                                         MinecraftClient.getInstance()
                                                        .getItemRenderer()
                                                        .renderItem(
                                                                item,
                                                                ModelTransformationMode.GROUND,
                                                                light,
                                                                OverlayTexture.DEFAULT_UV,
                                                                matrices,
                                                                vertexConsumers,
                                                                entity.getWorld(),
                                                                0
                                                        );
                                     }
        );

        matrices.pop();
    }
}
