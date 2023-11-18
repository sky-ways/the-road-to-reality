package com.github.cao.awa.trtr.item.knife.flint;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.material.TrtrMaterial;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;

@Auto
public class FlintKnifeItem extends SwordItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:flint_knife");

    @Auto
    public FlintKnifeItem(Settings settings) {
        super(
                TrtrMaterial.FLINT,
                - 6,
                - 2,
                settings
        );
    }
}
