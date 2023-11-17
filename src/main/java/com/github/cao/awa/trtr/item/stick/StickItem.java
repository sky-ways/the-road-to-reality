package com.github.cao.awa.trtr.item.stick;

import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.util.Identifier;

public class StickItem extends CraftingItem {
    public static final Identifier IDENTIFIER = Identifier.tryParse("minecraft:stick");

    public StickItem(Settings settings) {
        super(settings);
    }
}
