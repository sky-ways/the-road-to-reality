package com.github.cao.awa.trtr.item.pebble;

import com.github.cao.awa.apricot.anntation.Auto;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

@Auto
public class DeepslatePebbleItem extends PebbleItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:deepslate_pebble");

    @Auto
    public DeepslatePebbleItem(Settings settings) {
        super(settings);
    }

    @Override
    public Block getPlacingBlock() {
        return Blocks.COBBLED_DEEPSLATE;
    }
}
