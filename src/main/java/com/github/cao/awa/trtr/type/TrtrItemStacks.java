package com.github.cao.awa.trtr.type;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.item.*;

public class TrtrItemStacks {
    public static final ObjectArrayList<ItemStack> ORES = EntrustParser.operation(new ObjectArrayList<>(), stacks -> {
        stacks.add(TrtrBlocks.ACANTHITE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.BAUXITE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.GALENA_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.CHALCOPYRITE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.CUPRITE_BLOCK.asItem().getDefaultStack());

        stacks.add(TrtrBlocks.DEEPSLATE_ACANTHITE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.DEEPSLATE_BAUXITE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.DEEPSLATE_GALENA_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.DEEPSLATE_CHALCOPYRITE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.DEEPSLATE_CUPRITE_BLOCk.asItem().getDefaultStack());

        stacks.add(TrtrBlocks.PITCHBLENDE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.DEEPSLATE_PITCHBLENDE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.CARNOTITE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.DEEPSLATE_CARNOTITE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.AUTUNITE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.DEEPSLATE_AUTUNITE_BLOCK.asItem().getDefaultStack());

        stacks.add(TrtrBlocks.MALACHITE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.ALUNITE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.ALBITE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.ORTHOCLASE_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.ANORTHITE_BLOCK.asItem().getDefaultStack());
    });

    public static final ObjectArrayList<ItemStack> CRUSHED_ORES = EntrustParser.operation(new ObjectArrayList<>(), stacks -> {
        stacks.add(TrtrItems.CRUSHED_STONE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_ACANTHITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_GALENA.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_COAL.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_BAUXITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_HEMATITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_CHALCOPYRITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_CUPRITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_PITCHBLENDE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_PITCHBLENDE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_CARNOTITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_AUTUNITE.getDefaultStack());

        stacks.add(TrtrItems.CRUSHED_DEEPSLATE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_DEEPSLATE_COAL.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_DEEPSLATE_BAUXITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_DEEPSLATE_GALENA.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_DEEPSLATE_ACANTHITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_DEEPSLATE_HEMATITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_DEEPSLATE_CHALCOPYRITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_DEEPSLATE_CUPRITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_DEEPSLATE_PITCHBLENDE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_DEEPSLATE_PITCHBLENDE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_DEEPSLATE_CARNOTITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_DEEPSLATE_AUTUNITE.getDefaultStack());

        stacks.add(TrtrItems.CRUSHED_MALACHITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_ALUNITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_ALBITE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_ORTHOCLASE.getDefaultStack());
        stacks.add(TrtrItems.CRUSHED_ANORTHITE.getDefaultStack());
    });

    public static final ObjectArrayList<ItemStack> ORE_POWDERS = EntrustParser.operation(new ObjectArrayList<>(), stacks -> {
        stacks.add(TrtrItems.STONE_POWDER.getDefaultStack());
        stacks.add(TrtrItems.DEEPSLATE_POWDER.getDefaultStack());
        stacks.add(TrtrItems.ACANTHITE_POWDER.getDefaultStack());
        stacks.add(TrtrItems.BAUXITE_POWDER.getDefaultStack());
        stacks.add(TrtrItems.GALENA_POWDER.getDefaultStack());
        stacks.add(TrtrItems.HEMATITE_POWDER.getDefaultStack());
        stacks.add(TrtrItems.COAL_POWDER.getDefaultStack());
        stacks.add(TrtrItems.CHALCOPYRITE_POWDER.getDefaultStack());
        stacks.add(TrtrItems.CUPRITE_POWDER.getDefaultStack());
        stacks.add(TrtrItems.PITCHBLENDE_POWDER.getDefaultStack());
        stacks.add(TrtrItems.CARNOTITE_POWDER.getDefaultStack());
        stacks.add(TrtrItems.AUTUNITE_POWDER.getDefaultStack());

        stacks.add(TrtrItems.MALACHITE_POWDER.getDefaultStack());
        stacks.add(TrtrItems.ALUNITE_POWDER.getDefaultStack());
        stacks.add(TrtrItems.ALBITE_POWDER.getDefaultStack());
        stacks.add(TrtrItems.ORTHOCLASE_POWDER.getDefaultStack());
        stacks.add(TrtrItems.ANORTHITE_POWDER.getDefaultStack());
    });

    public static final ObjectArrayList<ItemStack> TOOLS = EntrustParser.operation(new ObjectArrayList<>(), stacks -> {
        stacks.add(TrtrItems.WOODEN_HAMMER.getDefaultStack());
        stacks.add(TrtrItems.STONE_HAMMER.getDefaultStack());
        stacks.add(TrtrItems.IRON_HAMMER.getDefaultStack());
        stacks.add(TrtrItems.THERMOMETER.getDefaultStack());
    });

    public static final ObjectArrayList<ItemStack> ASSEMBLY_BLOCK = EntrustParser.operation(new ObjectArrayList<>(), stacks -> {
        stacks.add(TrtrBlocks.LARGE_GEAR_WHEEL_BLOCK.asItem().getDefaultStack());
        stacks.add(TrtrBlocks.MEDIUM_GEAR_WHEEL_BLOCK.asItem().getDefaultStack());
    });

    public static final ObjectArrayList<ItemStack> ASSEMBLY = EntrustParser.operation(new ObjectArrayList<>(), stacks -> {
    });

    public static final ObjectArrayList<ItemStack> FOOD_MATERIALS = EntrustParser.operation(new ObjectArrayList<>(), stacks -> {
        stacks.add(TrtrItems.COCOA_POWDER.getDefaultStack());
    });

    public static final ObjectArrayList<ItemStack> COOKING_TOOL = EntrustParser.operation(new ObjectArrayList<>(), stacks -> {
        stacks.add(TrtrBlocks.POT.asItem().getDefaultStack());
    });
}
