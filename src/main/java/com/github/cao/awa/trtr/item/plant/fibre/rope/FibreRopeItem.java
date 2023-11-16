package com.github.cao.awa.trtr.item.plant.fibre.rope;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class FibreRopeItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:fibre_rope");

    @Auto
    public FibreRopeItem(Settings settings) {
        super(settings);
    }
}
