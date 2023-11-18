package com.github.cao.awa.trtr.item.crushed.stone;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class CrushedDioriteItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:crushed_diorite");

    @Auto
    public CrushedDioriteItem(Settings settings) {
        super(settings);
    }
}
