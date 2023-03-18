package com.github.cao.awa.trtr.block.example.simple.item;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.BlockBelong;
import com.github.cao.awa.trtr.block.item.TrtrBlockItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;

@Auto
@BlockBelong
public class SimpleExampleBlockItem extends TrtrBlockItem {
    @Auto
    public static final FabricItemSettings SETTINGS = new FabricItemSettings();

    @Auto
    public SimpleExampleBlockItem(Block block, Settings settings) {
        super(block,
              settings
        );
    }
}
