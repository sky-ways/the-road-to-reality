package com.github.cao.awa.trtr.item.spear;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.TrtrItem;
import net.minecraft.util.Identifier;

@Auto
public class StoneSpearItem extends TrtrItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:stone_spear");

    @Auto
    public StoneSpearItem(Settings settings) {
        super(settings);
    }
}
