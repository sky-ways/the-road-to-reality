package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotations.platform.*;
import com.github.cao.awa.trtr.ref.block.trtr.slab.*;
import com.github.cao.awa.trtr.type.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.minecraft.client.render.entity.*;

@Client
public class TrtrClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TrtrScreenType.pre();
        BlockEntityRendererRegistry.register(
                TrtrBlockEntityType.SLAB_ENTITY,
                TrtrSlabBlockEntityRenderer::new
        );

        //        EntityRendererRegistry.register(TrtrEntityType.ELECTRIC_WIRE, ElectricWireRender::new);
    }
}
