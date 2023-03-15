package com.github.cao.awa.trtr.framework.data.gen.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;

public interface LootFactory<T extends DataProvider> extends FabricDataGenerator.Pack.Factory<T> {
}
