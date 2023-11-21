package com.github.cao.awa.trtr.item.plank;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class SprucePlankItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:spruce_plank");

    @Auto
    public SprucePlankItem(Settings settings) {
        super(settings);
    }
}
