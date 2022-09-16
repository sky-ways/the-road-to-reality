package com.github.cao.awa.trtr.ore.silver.acanthite.powder;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class AcanthitePowder extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:acanthite_powder");

    public AcanthitePowder(Settings settings) {
        super(settings);
    }

    public static Item register() {
        Settings settings = new Settings();
        AcanthitePowder acanthitePowder = new AcanthitePowder(settings);
        Registry.register(Registry.ITEM, IDENTIFIER, acanthitePowder);
        return acanthitePowder;
    }
}
