package com.github.cao.awa.trtr.item.pebble;

import com.github.cao.awa.apricot.anntation.Auto;
import net.minecraft.util.Identifier;

@Auto
public class AndesitePebbleItem extends PebbleItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:andesite_pebble");

    @Auto
    public AndesitePebbleItem(Settings settings) {
        super(settings);
    }
}
