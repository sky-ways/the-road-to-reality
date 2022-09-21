package com.github.cao.awa.trtr.ore.generic;

import com.github.cao.awa.trtr.ref.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class TrtrGenBlockItem extends TrtrBlockItem {
    public final TrtrBasedBlock block;

    public TrtrGenBlockItem(TrtrBasedBlock block, Item.Settings settings) {
        super(block, settings);
        this.block = block;
        register();
    }

    public TrtrGenBlockItem(TrtrBasedBlock block) {
        this(block, new Settings());
    }

    @Override
    public Identifier identifier() {
        return null;
    }

    public void register() {
        Registry.register(Registry.ITEM, block.identifier(), this);
        Item.BLOCK_ITEMS.put(block, this);
    }
}
