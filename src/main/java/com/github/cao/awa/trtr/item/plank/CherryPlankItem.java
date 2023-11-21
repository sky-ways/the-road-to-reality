package com.github.cao.awa.trtr.item.plank;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class CherryPlankItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:cherry_plank");

    @Auto
    public CherryPlankItem(Settings settings) {
        super(settings);
    }
}
