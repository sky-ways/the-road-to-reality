package com.github.cao.awa.trtr.framework.accessor.data.gen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public interface TrtrTagFactory {
    FabricTagProvider<?> apply(FabricDataOutput output, RegistryKey<? extends Registry<RegistryKey<?>>> registryKey, CompletableFuture<RegistryWrapper.WrapperLookup> future);
}
