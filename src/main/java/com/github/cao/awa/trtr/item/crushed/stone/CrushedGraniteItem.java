package com.github.cao.awa.trtr.item.crushed.stone;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class CrushedGraniteItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:crushed_granite");

    @Auto
    public CrushedGraniteItem(Settings settings) {
        super(settings);
    }
}
