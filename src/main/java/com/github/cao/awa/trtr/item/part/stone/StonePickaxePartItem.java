package com.github.cao.awa.trtr.item.part.stone;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class StonePickaxePartItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:stone_pickaxe_part");

    @Auto
    public StonePickaxePartItem(Settings settings) {
        super(settings);
    }
}
