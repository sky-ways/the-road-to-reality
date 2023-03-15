package com.github.cao.awa.trtr.framework.data.gen.model;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.ItemModelGenerator;

public abstract class TrtrBlockModelProvider extends FabricModelProvider {
    public TrtrBlockModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public final void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
