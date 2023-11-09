package com.github.cao.awa.trtr.item.part.stone;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.TrtrItem;
import net.minecraft.util.Identifier;

@Auto
public class StonePickaxePartItem extends TrtrItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:stone_pickaxe_part");

    @Auto
    public StonePickaxePartItem(Settings settings) {
        super(settings);
    }
}
