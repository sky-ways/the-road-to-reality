package com.github.cao.awa.trtr.item.powder.stone;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class StonePowderItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:stone_powder");

    @Auto
    public StonePowderItem(Settings settings) {
        super(settings);
    }
}
