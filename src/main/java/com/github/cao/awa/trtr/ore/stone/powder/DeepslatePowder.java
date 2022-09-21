package com.github.cao.awa.trtr.ore.stone.powder;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class DeepslatePowder extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:deepslate_powder");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
