package com.github.cao.awa.trtr.ore.iron.hematite.powder;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class HematitePowder extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:hematite_powder");

    public HematitePowder(Settings settings) {
        super(settings);
    }

    public static Item register() {
        Settings settings = new Settings();
        HematitePowder hematitePowder = new HematitePowder(settings);
        Registry.register(Registry.ITEM, IDENTIFIER, hematitePowder);
        return hematitePowder;
    }
}
