package com.github.cao.awa.trtr.ore.lead.galena;

import com.github.cao.awa.trtr.ore.silver.acanthite.*;
import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.ref.prototype.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

import java.util.*;

public class GalenaBlockItem extends BlockItem {
    public GalenaBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static void register(Block block) {
        Settings settings = new Settings();
        GalenaBlockItem galena = new GalenaBlockItem(block, settings);
        Registry.register(Registry.ITEM, GalenaBlock.IDENTIFIER, galena);
        Item.BLOCK_ITEMS.put(block, galena);
    }
}