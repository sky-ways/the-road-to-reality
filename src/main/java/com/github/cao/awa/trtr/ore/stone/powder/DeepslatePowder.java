package com.github.cao.awa.trtr.ore.stone.powder;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class DeepslatePowder extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:deepslate_powder");

    public DeepslatePowder(Settings settings) {
        super(settings);
    }

    public static Item register() {
        Settings settings = new Settings();
        DeepslatePowder deepslatePowder = new DeepslatePowder(settings);
        Registry.register(Registry.ITEM, IDENTIFIER, deepslatePowder);
        return deepslatePowder;
    }
}
