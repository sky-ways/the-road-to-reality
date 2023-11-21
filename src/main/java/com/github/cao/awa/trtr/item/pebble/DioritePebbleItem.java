package com.github.cao.awa.trtr.item.pebble;

import com.github.cao.awa.apricot.anntation.Auto;
import net.minecraft.util.Identifier;

@Auto
public class DioritePebbleItem extends PebbleItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:diorite_pebble");

    @Auto
    public DioritePebbleItem(Settings settings) {
        super(settings);
    }
}
