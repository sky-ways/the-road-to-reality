package com.github.cao.awa.trtr.item.plank;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class OakPlankItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:oak_plank");

    @Auto
    public OakPlankItem(Settings settings) {
        super(settings);
    }
}
