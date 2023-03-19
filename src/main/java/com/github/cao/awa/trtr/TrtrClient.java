package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotation.platform.Client;
import com.github.cao.awa.trtr.block.stove.mud.MudStove;
import com.github.cao.awa.trtr.block.stove.mud.MudStoveBlockEntity;
import com.github.cao.awa.trtr.block.stove.mud.render.MudStoveRender;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@Client
public class TrtrClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityType<MudStoveBlockEntity> type = TrtrMod.BLOCK_FRAMEWORK.entityType(MudStove.class);

        BlockEntityRendererFactories.register(type,
                                              MudStoveRender :: new
        );
    }
}
