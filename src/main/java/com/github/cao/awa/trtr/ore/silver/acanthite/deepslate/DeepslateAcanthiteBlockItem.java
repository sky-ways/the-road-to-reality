package com.github.cao.awa.trtr.ore.silver.acanthite.deepslate;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

public class DeepslateAcanthiteBlockItem extends BlockItem {
    public DeepslateAcanthiteBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static void register(Block block) {
        Settings settings = new Settings();
        DeepslateAcanthiteBlockItem bauxite = new DeepslateAcanthiteBlockItem(block, settings);
        Registry.register(Registry.ITEM, DeepslateAcanthiteBlock.IDENTIFIER, bauxite);
    }
}