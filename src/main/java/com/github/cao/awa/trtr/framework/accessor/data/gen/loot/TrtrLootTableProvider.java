package com.github.cao.awa.trtr.framework.accessor.data.gen.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.context.LootContextTypes;

public abstract class TrtrLootTableProvider extends SimpleFabricLootTableProvider {
    public TrtrLootTableProvider(FabricDataOutput output) {
        super(output,
              LootContextTypes.BLOCK
        );
    }
}
