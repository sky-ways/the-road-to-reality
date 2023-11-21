package com.github.cao.awa.trtr.item.plank;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class JunglePlankItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:jungle_plank");

    @Auto
    public JunglePlankItem(Settings settings) {
        super(settings);
    }
}
