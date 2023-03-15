package com.github.cao.awa.trtr.framework.data.gen.model;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

public interface TrtrModelFactory {
    FabricModelProvider apply(FabricDataOutput output);
}
