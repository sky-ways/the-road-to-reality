package com.github.cao.awa.trtr.framework.block.data.gen.tag;

import com.github.cao.awa.trtr.framework.accessor.data.gen.tag.TrtrTagFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FrameworkTagProvider extends FabricTagProvider<RegistryKey<?>> {
    private static final Logger LOGGER = LogManager.getLogger("FrameworkTagProvider");
    private final List<? extends FabricTagProvider<?>> factories;

    public FrameworkTagProvider(FabricDataOutput output, RegistryKey<? extends Registry<RegistryKey<?>>> registryKey, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, List<TrtrTagFactory> factories) {
        super(output,
              registryKey,
              registriesFuture
        );
        this.factories = factories.stream()
                                  .map(factory -> factory.apply(output,
                                                                registryKey,
                                                                registriesFuture
                                  ))
                                  .toList();
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        for (FabricTagProvider<?> provider : this.factories) {
            try {
                Method method = provider.getClass()
                                        .getDeclaredMethod("configure",
                                                           RegistryWrapper.WrapperLookup.class
                                        );
                method.trySetAccessible();
                method.invoke(provider,
                              arg
                );
            } catch (Exception e) {
                LOGGER.warn(e);
            }
        }
    }
}