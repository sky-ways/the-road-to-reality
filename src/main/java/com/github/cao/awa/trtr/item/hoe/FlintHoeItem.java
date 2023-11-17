package com.github.cao.awa.trtr.item.hoe;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.TrtrItem;
import net.minecraft.util.Identifier;

@Auto
public class FlintHoeItem extends TrtrItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:flint_hoe");

    @Auto
    public FlintHoeItem(Settings settings) {
        super(settings);
    }
}
