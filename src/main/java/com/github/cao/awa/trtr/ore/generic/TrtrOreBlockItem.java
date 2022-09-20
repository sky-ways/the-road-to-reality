package com.github.cao.awa.trtr.ore.generic;

import com.github.cao.awa.trtr.ore.aluminum.bauxite.*;
import com.github.cao.awa.trtr.ref.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

public class TrtrOreBlockItem extends TrtrBlockItem {
    public final Block block;

    public TrtrOreBlockItem(Block block, Item.Settings settings) {
        super(block, settings);
        this.block = block;
    }

    public TrtrOreBlockItem(Block block) {
        super(block, new Settings());
        this.block = block;
    }

    public void register() {
        if (block instanceof OreRegister register) {
            Registry.register(Registry.ITEM, register.identifier(), this);
            Item.BLOCK_ITEMS.put(block, this);
        }
    }
}
