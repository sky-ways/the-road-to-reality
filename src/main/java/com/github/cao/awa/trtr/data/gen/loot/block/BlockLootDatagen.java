package com.github.cao.awa.trtr.data.gen.loot.block;

import com.github.cao.awa.trtr.type.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.data.server.*;
import net.minecraft.loot.*;
import net.minecraft.loot.context.*;
import net.minecraft.loot.provider.number.*;
import net.minecraft.util.*;

import java.util.function.*;

public class BlockLootDatagen extends SimpleFabricLootTableProvider {
    public BlockLootDatagen(FabricDataGenerator dataGenerator) {
        super(
                dataGenerator,
                LootContextTypes.BLOCK
        );
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
        biConsumer.accept(
                new Identifier(
                        "trtr",
                        "pebble_block"
                ),
                BlockLootTableGenerator.drops(TrtrBlocks.LOOSE_PEBBLE_BLOCK,
                                              TrtrItems.CRUSHED_STONE,
                                              UniformLootNumberProvider.create(1.0F, 3.0F)
                )
        );
    }
}