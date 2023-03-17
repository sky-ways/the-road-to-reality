package com.github.cao.awa.trtr.framework.accessor.data.gen.model;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;

public abstract class TrtrItemModelProvider extends FabricModelProvider {
    public TrtrItemModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public final void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }
}
