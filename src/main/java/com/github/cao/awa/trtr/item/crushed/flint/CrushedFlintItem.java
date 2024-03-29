package com.github.cao.awa.trtr.item.crushed.flint;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class CrushedFlintItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:crushed_flint");

    @Auto
    public CrushedFlintItem(Settings settings) {
        super(settings);
    }
}
