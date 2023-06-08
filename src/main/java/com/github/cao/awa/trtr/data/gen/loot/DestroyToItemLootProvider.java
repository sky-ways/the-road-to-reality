package com.github.cao.awa.trtr.data.gen.loot;

import com.github.cao.awa.trtr.framework.accessor.data.gen.loot.TrtrLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class DestroyToItemLootProvider extends TrtrLootTableProvider {
    private final Block block;
    private final ItemConvertible itemConvertible;

    public DestroyToItemLootProvider(FabricDataOutput output, Block block, ItemConvertible itemConvertible) {
        super(output);
        this.block = block;
        this.itemConvertible = itemConvertible;
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {
        // Usually drop one item of this block after block destroy.
        identifierBuilderBiConsumer.accept(this.block.getLootTableId(),
                                           new LootTable.Builder().pool(new LootPool.Builder().with(ItemEntry.builder(this.itemConvertible.asItem())))
        );
    }
}
