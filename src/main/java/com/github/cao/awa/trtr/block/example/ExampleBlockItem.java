package com.github.cao.awa.trtr.block.example;

import com.github.cao.awa.apricot.anntations.Auto;
import com.github.cao.awa.trtr.block.TrtrBlockItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;

@Auto
public class ExampleBlockItem extends TrtrBlockItem {
    @Auto
    public static final FabricItemSettings SETTINGS = new FabricItemSettings();

    @Auto
    public ExampleBlockItem(Block block, Settings settings) {
        super(block,
              settings
        );
    }
}
