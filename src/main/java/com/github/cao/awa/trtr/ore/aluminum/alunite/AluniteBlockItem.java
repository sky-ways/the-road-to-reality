package com.github.cao.awa.trtr.ore.aluminum.alunite;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

public class AluniteBlockItem extends BlockItem {
    public AluniteBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static void register(Block block) {
        Settings settings = new Settings();
        AluniteBlockItem alunite = new AluniteBlockItem(block, settings);
        Registry.register(Registry.ITEM, AluniteBlock.IDENTIFIER, alunite);
    }
}