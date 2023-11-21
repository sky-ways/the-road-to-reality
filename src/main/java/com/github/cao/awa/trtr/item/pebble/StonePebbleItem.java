package com.github.cao.awa.trtr.item.pebble;

import com.github.cao.awa.apricot.anntation.Auto;
import net.minecraft.util.Identifier;

@Auto
public class StonePebbleItem extends PebbleItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:stone_pebble");

    @Auto
    public StonePebbleItem(Settings settings) {
        super(settings);
    }
}
