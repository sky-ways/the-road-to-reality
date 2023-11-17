package com.github.cao.awa.trtr.item.flint.sharp;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class SharpFlintItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:sharp_flint");

    @Auto
    public SharpFlintItem(Settings settings) {
        super(settings);
    }
}
