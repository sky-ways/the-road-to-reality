package com.github.cao.awa.trtr.block.example.tag;

import com.github.cao.awa.trtr.framework.accessor.data.gen.tag.TrtrBlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ExampleBlockTag extends TrtrBlockTagProvider {
    public ExampleBlockTag(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output,
              registriesFuture
        );
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        System.out.println("Custom block tag generator");
    }
}
