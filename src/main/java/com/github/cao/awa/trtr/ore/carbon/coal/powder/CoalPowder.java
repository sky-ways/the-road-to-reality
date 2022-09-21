package com.github.cao.awa.trtr.ore.carbon.coal.powder;

import com.github.cao.awa.trtr.ore.aluminum.bauxite.powder.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class CoalPowder extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:coal_powder");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
