package com.github.cao.awa.trtr.block.example.loot;

import com.github.cao.awa.apricot.anntations.Auto;
import com.github.cao.awa.trtr.framework.data.gen.loot.TrtrLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

@Auto
public class ExampleLoot extends SimpleFabricLootTableProvider {

    public ExampleLoot(FabricDataOutput output, LootContextType lootContextType) {
        super(output,
              lootContextType
        );
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
        System.out.println("Custom loot generator...");
    }
}
