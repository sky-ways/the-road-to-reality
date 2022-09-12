package com.github.cao.awa.trtr;

import com.github.cao.awa.trtr.ref.block.trtr.slab.*;
import com.github.cao.awa.trtr.type.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.fabricmc.fabric.impl.client.rendering.*;
import net.minecraft.block.entity.*;
import net.minecraft.client.font.*;
import net.minecraft.client.render.block.*;
import net.minecraft.client.render.block.entity.*;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.render.item.*;
import net.minecraft.util.registry.*;

public class TrtrClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TrtrScreenType.pre();
        BlockEntityRendererRegistry.register(TrtrBlockEntityType.SLAB_ENTITY, TrtrSlabBlockEntityRenderer::new);
    }
}
