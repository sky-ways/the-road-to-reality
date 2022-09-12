package com.github.cao.awa.trtr.power.photovoltaic.panels;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

public class PhotovoltaicPanelsItem extends TrtrBlockItem {
    public PhotovoltaicPanelsItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static void register(Block block) {
        Settings settings = new Settings();
        PhotovoltaicPanelsItem panels = new PhotovoltaicPanelsItem(block, settings);
        Registry.register(Registry.ITEM, PhotovoltaicPanels.IDENTIFIER, panels);
    }
}