package com.github.cao.awa.trtr.ore.lead.galena.powder;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class GalenaPowder extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:galena_powder");

    public GalenaPowder(Settings settings) {
        super(settings);
    }

    public static Item register() {
        Settings settings = new Settings();
        GalenaPowder galenaPowder = new GalenaPowder(settings);
        Registry.register(Registry.ITEM, IDENTIFIER, galenaPowder);
        return galenaPowder;
    }
}
