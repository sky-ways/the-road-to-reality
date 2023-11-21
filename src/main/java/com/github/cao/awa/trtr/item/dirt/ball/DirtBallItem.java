package com.github.cao.awa.trtr.item.dirt.ball;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class DirtBallItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:dirt_ball");

    @Auto
    public DirtBallItem(Settings settings) {
        super(settings);
    }
}
