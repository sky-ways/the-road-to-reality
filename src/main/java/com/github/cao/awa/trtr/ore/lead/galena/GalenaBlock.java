package com.github.cao.awa.trtr.ore.lead.galena;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class GalenaBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:galena");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
