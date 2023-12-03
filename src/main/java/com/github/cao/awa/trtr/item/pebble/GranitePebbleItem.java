package com.github.cao.awa.trtr.item.pebble;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.cobbled.CobbledGranite;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

@Auto
public class GranitePebbleItem extends PebbleItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:granite_pebble");

    @Auto
    public GranitePebbleItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public Block getPlacingBlock() {
        return TrtrBlocks.get(CobbledGranite.IDENTIFIER);
    }
}
