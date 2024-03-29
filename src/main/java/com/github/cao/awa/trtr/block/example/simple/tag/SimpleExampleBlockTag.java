package com.github.cao.awa.trtr.block.example.simple.tag;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.framework.accessor.data.gen.tag.TrtrBlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

@Auto
public class SimpleExampleBlockTag extends TrtrBlockTagProvider {
    @Auto
    public SimpleExampleBlockTag(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output,
              registriesFuture
        );
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        System.out.println("Custom block tag generator");
    }
}
