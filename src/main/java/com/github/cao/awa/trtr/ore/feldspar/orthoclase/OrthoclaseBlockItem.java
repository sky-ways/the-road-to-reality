package com.github.cao.awa.trtr.ore.feldspar.orthoclase;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

public class OrthoclaseBlockItem extends BlockItem {
    public OrthoclaseBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static void register(Block block) {
        Settings settings = new Settings();
        OrthoclaseBlockItem bauxite = new OrthoclaseBlockItem(block, settings);
        Registry.register(Registry.ITEM, OrthoclaseBlock.IDENTIFIER, bauxite);
    }
}