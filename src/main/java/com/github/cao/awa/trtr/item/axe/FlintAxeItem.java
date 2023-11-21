package com.github.cao.awa.trtr.item.axe;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.material.TrtrMaterial;
import net.minecraft.item.AxeItem;
import net.minecraft.util.Identifier;

@Auto
public class FlintAxeItem extends AxeItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:flint_axe");

    @Auto
    public FlintAxeItem(Settings settings) {
        super(
                TrtrMaterial.FLINT,
                - 1,
                - 3,
                settings
        );
    }
}
