package com.github.cao.awa.trtr.item.knife.stone;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.TrtrItem;
import net.minecraft.util.Identifier;

@Auto
public class StoneKnifeItem extends TrtrItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:stone_knife");

    @Auto
    public StoneKnifeItem(Settings settings) {
        super(settings);
    }
}
