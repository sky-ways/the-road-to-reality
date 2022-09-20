package com.github.cao.awa.trtr;

import com.github.cao.awa.trtr.ref.block.trtr.slab.*;
import com.github.cao.awa.trtr.type.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;

public class TrtrClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TrtrScreenType.pre();
        BlockEntityRendererRegistry.register(TrtrBlockEntityType.SLAB_ENTITY, TrtrSlabBlockEntityRenderer::new);
    }
}
