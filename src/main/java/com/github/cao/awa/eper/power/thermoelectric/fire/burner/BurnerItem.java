package com.github.cao.awa.eper.power.thermoelectric.fire.burner;

import com.github.cao.awa.eper.power.photovoltaic.panels.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

public class BurnerItem extends BlockItem {
    public BurnerItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static void register(Block block) {
        Settings settings = new Settings();
        BurnerItem panels = new BurnerItem(block, settings);
        Registry.register(Registry.ITEM, Burner.IDENTIFIER, panels);
    }
}