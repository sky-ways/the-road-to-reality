package com.github.cao.awa.trtr.ore.aluminum.bauxite;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

public class BauxiteBlockItem extends BlockItem {
    public BauxiteBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static void register(Block block) {
        Settings settings = new Settings();
        BauxiteBlockItem bauxite = new BauxiteBlockItem(block, settings);
        Registry.register(Registry.ITEM, BauxiteBlock.IDENTIFIER, bauxite);
    }
}