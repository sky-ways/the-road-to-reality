package com.github.cao.awa.trtr.power.thermoelectric.fire.burner;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

public class BurnerBlockItem extends TrtrBlockItem {
    public BurnerBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static void register(Block block) {
        Settings settings = new Settings();
        BurnerBlockItem panels = new BurnerBlockItem(block, settings);
        Registry.register(Registry.ITEM, BurnerBlock.IDENTIFIER, panels);
    }
}