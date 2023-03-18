package com.github.cao.awa.trtr.block.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public abstract class TrtrBlockItem extends BlockItem {
    public TrtrBlockItem(Block block, Settings settings) {
        super(block,
              settings
        );
    }
}
