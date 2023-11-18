package com.github.cao.awa.trtr.item.part.stone;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class StoneShovelPartItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:stone_shovel_part");

    @Auto
    public StoneShovelPartItem(Settings settings) {
        super(settings);
    }
}
