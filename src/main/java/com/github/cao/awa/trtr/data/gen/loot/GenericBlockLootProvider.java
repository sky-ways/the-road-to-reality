package com.github.cao.awa.trtr.data.gen.loot;

import com.github.cao.awa.trtr.framework.data.gen.loot.TrtrLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class GenericBlockLootProvider extends TrtrLootTableProvider {
    private final Block block;

    public GenericBlockLootProvider(FabricDataOutput output, Block block) {
        super(output);
        this.block = block;
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {
        identifierBuilderBiConsumer.accept(this.block.getLootTableId(),
                                           new LootTable.Builder().pool(new LootPool.Builder().with(ItemEntry.builder(this.block.asItem())))
        );
    }
}
