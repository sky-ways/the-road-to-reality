package com.github.cao.awa.trtr.ore.silver.acanthite;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.table.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

import java.util.*;

public class AcanthiteBlockItem extends BlockItem {
    public AcanthiteBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static void register(Block block) {
        Settings settings = new Settings();
        AcanthiteBlockItem acanthite = new AcanthiteBlockItem(block, settings);
        Registry.register(Registry.ITEM, AcanthiteBlock.IDENTIFIER, acanthite);
        Item.BLOCK_ITEMS.put(block, acanthite);
    }
}