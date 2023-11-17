package com.github.cao.awa.trtr.item.knife;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.TrtrItem;
import net.minecraft.util.Identifier;

@Auto
public class FlintKnifeItem extends TrtrItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:flint_knife");

    @Auto
    public FlintKnifeItem(Settings settings) {
        super(settings);
    }
}
