package com.github.cao.awa.trtr.framework.data.gen.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public abstract class TrtrLootTableProvider extends SimpleFabricLootTableProvider {
    public TrtrLootTableProvider(FabricDataOutput output) {
        super(output,
              LootContextTypes.BLOCK
        );
    }
}
