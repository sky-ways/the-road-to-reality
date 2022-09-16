package com.github.cao.awa.trtr.ore.aluminum.bauxite;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.table.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

import java.util.*;

public class BauxiteBlockItem extends TrtrBlockItem {
    public BauxiteBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static void register(Block block) {
        Settings settings = new Settings();
        BauxiteBlockItem bauxite = new BauxiteBlockItem(block, settings);
        Registry.register(Registry.ITEM, BauxiteBlock.IDENTIFIER, bauxite);
        Item.BLOCK_ITEMS.put(block, bauxite);
    }
}