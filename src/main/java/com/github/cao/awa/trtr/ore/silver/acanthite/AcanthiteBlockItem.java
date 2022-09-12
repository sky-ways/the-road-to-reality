package com.github.cao.awa.trtr.ore.silver.acanthite;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

public class AcanthiteBlockItem extends BlockItem {
    public AcanthiteBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static void register(Block block) {
        Settings settings = new Settings();
        AcanthiteBlockItem bauxite = new AcanthiteBlockItem(block, settings);
        Registry.register(Registry.ITEM, AcanthiteBlock.IDENTIFIER, bauxite);
    }
}