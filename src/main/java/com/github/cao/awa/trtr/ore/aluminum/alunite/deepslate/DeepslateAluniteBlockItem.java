package com.github.cao.awa.trtr.ore.aluminum.alunite.deepslate;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

public class DeepslateAluniteBlockItem extends BlockItem {
    public DeepslateAluniteBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static void register(Block block) {
        Settings settings = new Settings();
        DeepslateAluniteBlockItem alunite = new DeepslateAluniteBlockItem(block, settings);
        Registry.register(Registry.ITEM, DeepslateAluniteBlock.IDENTIFIER, alunite);
    }
}