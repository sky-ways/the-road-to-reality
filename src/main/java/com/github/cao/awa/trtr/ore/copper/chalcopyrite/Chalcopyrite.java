package com.github.cao.awa.trtr.ore.copper.chalcopyrite;

import com.github.cao.awa.trtr.ore.feldspar.albite.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class Chalcopyrite extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:chalcopyrite");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
