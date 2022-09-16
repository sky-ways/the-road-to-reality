package com.github.cao.awa.trtr.ore.feldspar.albite;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

public class AlbiteBlockItem extends BlockItem {
    public AlbiteBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static void register(Block block) {
        Settings settings = new Settings();
        AlbiteBlockItem bauxite = new AlbiteBlockItem(block, settings);
        Registry.register(Registry.ITEM, AlbiteBlock.IDENTIFIER, bauxite);
    }
}