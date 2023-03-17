package com.github.cao.awa.trtr.block.example.simple.loot;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.framework.accessor.data.gen.loot.TrtrLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

@Auto
public class SimpleExampleLoot extends TrtrLootTableProvider {
    @Auto
    public SimpleExampleLoot(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
        System.out.println("Custom simple loot generator...");
    }
}
