package com.github.cao.awa.trtr.ore.aluminum.bauxite.powder;

import com.github.cao.awa.trtr.debuger.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class BauxitePowder extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:bauxite_powder");

    public BauxitePowder(Settings settings) {
        super(settings);
    }

    public static Item register() {
        Settings settings = new Settings();
        BauxitePowder bauxitePowder = new BauxitePowder(settings);
        Registry.register(Registry.ITEM, IDENTIFIER, bauxitePowder);
        return bauxitePowder;
    }
}
