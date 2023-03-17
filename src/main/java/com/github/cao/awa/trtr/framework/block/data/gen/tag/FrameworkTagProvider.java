package com.github.cao.awa.trtr.framework.block.data.gen.tag;

import com.github.cao.awa.trtr.framework.accessor.data.gen.tag.TrtrBlockTagProvider;
import com.github.cao.awa.trtr.framework.accessor.data.gen.tag.TrtrTagFactory;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
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

/**
 * Provide automatic tag generate by framework.
 *
 * @author cao_awa
 * @author 草二号机
 * @see TrtrTagFactory
 * @see TrtrBlockTagProvider
 * @since 1.0.0
 */
public class FrameworkTagProvider extends FabricTagProvider<RegistryKey<?>> {
    private static final Logger LOGGER = LogManager.getLogger("FrameworkTagProvider");
    private final List<? extends FabricTagProvider<?>> providers;

    public FrameworkTagProvider(FabricDataOutput output, RegistryKey<? extends Registry<RegistryKey<?>>> registryKey, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, List<TrtrTagFactory> factories) {
        super(output,
              registryKey,
              registriesFuture
        );
        // Convert the factories to providers.
        this.providers = factories.stream()
                                  .map(factory -> factory.apply(output,
                                                                registryKey,
                                                                registriesFuture
                                  ))
                                  .toList();
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        for (FabricTagProvider<?> provider : this.providers) {
            EntrustEnvironment.trys(() -> {
                // The method modifier of 'configure' is protected, so we cannot directly to calls.
                // Set this method be accessible and call it by Method.invoke() method.
                Method method = provider.getClass()
                                        .getDeclaredMethod("configure",
                                                           RegistryWrapper.WrapperLookup.class
                                        );
                method.trySetAccessible();
                method.invoke(provider,
                              arg
                );
            });
        }
    }
}