package com.github.cao.awa.trtr.ore.lead.galena.deepslate;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.ref.prototype.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

import java.util.*;

public class DeepslateGalenaBlockItem extends BlockItem {
    public DeepslateGalenaBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static void register(Block block) {
        Settings settings = new Settings();
        DeepslateGalenaBlockItem galena = new DeepslateGalenaBlockItem(block, settings);
        Registry.register(Registry.ITEM, DeepslateGalenaBlock.IDENTIFIER, galena);
        Item.BLOCK_ITEMS.put(block, galena);
    }
}