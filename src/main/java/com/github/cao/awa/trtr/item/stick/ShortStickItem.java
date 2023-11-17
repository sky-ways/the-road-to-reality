package com.github.cao.awa.trtr.item.stick;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class ShortStickItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:short_stick");

    public ShortStickItem(Settings settings) {
        super(settings);
    }
}
