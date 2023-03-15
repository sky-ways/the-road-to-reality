package com.github.cao.awa.trtr.block.example;

import com.github.cao.awa.trtr.block.TrtrBlockItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;

public class ExampleBlockItem extends TrtrBlockItem {
    public static final FabricItemSettings SETTINGS = new FabricItemSettings();

    public ExampleBlockItem(Block block, Settings settings) {
        super(block,
              settings
        );
    }
}
