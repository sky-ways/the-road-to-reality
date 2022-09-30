package com.github.cao.awa.trtr.type;

import net.fabricmc.fabric.api.client.itemgroup.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class TrtrItemGroup {
    public static final ItemGroup ALL_OF_GROUP = FabricItemGroupBuilder.create(new Identifier("trtr:all")).icon(() -> TrtrItems.IRON_HAMMER.asItem().getDefaultStack()).appendItems(stacks -> {
        stacks.addAll(TrtrItemStacks.ORES);

        stacks.addAll(TrtrItemStacks.CRUSHED_ORES);

        stacks.addAll(TrtrItemStacks.ORE_POWDERS);

        stacks.addAll(TrtrItemStacks.TOOLS);

        stacks.addAll(TrtrItemStacks.ASSEMBLY);

        stacks.addAll(TrtrItemStacks.ASSEMBLY_BLOCK);
    }).build();

    public static final ItemGroup ORE_GROUP = FabricItemGroupBuilder.create(new Identifier("trtr:ore")).icon(() -> TrtrBlocks.ACANTHITE_BLOCK.asItem().getDefaultStack()).appendItems(stacks -> {
        stacks.addAll(TrtrItemStacks.ORES);
    }).build();

    public static final ItemGroup CRUSHED_ORE_GROUP = FabricItemGroupBuilder.create(new Identifier("trtr:crushed_ore")).icon(TrtrItems.CRUSHED_ACANTHITE::getDefaultStack).appendItems(stacks -> {
        stacks.addAll(TrtrItemStacks.CRUSHED_ORES);
    }).build();

    public static final ItemGroup ORE_POWDER_GROUP = FabricItemGroupBuilder.create(new Identifier("trtr:ore_powder")).icon(TrtrItems.ACANTHITE_POWDER::getDefaultStack).appendItems(stacks -> {
        stacks.addAll(TrtrItemStacks.ORE_POWDERS);
    }).build();

    public static final ItemGroup TOOL_GROUP = FabricItemGroupBuilder.create(new Identifier("trtr:tool")).icon(TrtrItems.IRON_HAMMER::getDefaultStack).appendItems(stacks -> {
        stacks.addAll(TrtrItemStacks.TOOLS);
    }).build();

    public static final ItemGroup ASSEMBLY_GROUP = FabricItemGroupBuilder.create(new Identifier("trtr:assembly")).icon(TrtrItems.IRON_HAMMER::getDefaultStack).appendItems(stacks -> {
        stacks.addAll(TrtrItemStacks.ASSEMBLY);
    }).build();

    public static final ItemGroup ASSEMBLY_BLOCK_GROUP = FabricItemGroupBuilder.create(new Identifier("trtr:assembly_block")).icon(TrtrItems.IRON_HAMMER::getDefaultStack).appendItems(stacks -> {
        stacks.addAll(TrtrItemStacks.ASSEMBLY_BLOCK);
    }).build();

    public static void pre() {

    }
}
