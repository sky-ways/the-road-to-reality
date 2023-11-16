package com.github.cao.awa.trtr.item.flint;

import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

public class FlintItem extends CraftingItem {
    public static final Identifier IDENTIFIER = Identifier.tryParse("minecraft:flint");

    public FlintItem(Settings settings) {
        super(settings);
    }
}
