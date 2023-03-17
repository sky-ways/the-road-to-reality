package com.github.cao.awa.trtr.framework.accessor.data.gen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public abstract class TrtrBlockTagProvider extends FabricTagProvider<Block> {
    public TrtrBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output,
              Registries.BLOCK.getKey(),
              registriesFuture
        );
    }
}
