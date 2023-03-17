package com.github.cao.awa.trtr.data.gen.loot;

import com.github.cao.awa.trtr.framework.accessor.data.gen.loot.LootFactory;
import com.github.cao.awa.trtr.framework.accessor.data.gen.loot.TrtrLootTableProvider;
import com.github.cao.awa.trtr.framework.block.data.gen.loot.BlockLootDataGenFramework;
import com.github.cao.awa.trtr.framework.block.data.gen.loot.FrameworkLootProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

/**
 * Generate the loot table for blocks.
 *
 * @author cao_awa
 * @see BlockLootDataGenFramework
 * @see LootFactory
 * @see FrameworkLootProvider
 * @since 1.0.0
 */
public class GenericBlockLootProvider extends TrtrLootTableProvider {
    private final Block block;

    public GenericBlockLootProvider(FabricDataOutput output, Block block) {
        super(output);
        this.block = block;
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {
        // Usually drop one item of this block after block destroy.
        identifierBuilderBiConsumer.accept(this.block.getLootTableId(),
                                           new LootTable.Builder().pool(new LootPool.Builder().with(ItemEntry.builder(this.block.asItem())))
        );
    }
}
