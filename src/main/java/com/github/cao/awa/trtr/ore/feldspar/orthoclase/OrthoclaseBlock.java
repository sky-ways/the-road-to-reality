package com.github.cao.awa.trtr.ore.feldspar.orthoclase;

import com.github.cao.awa.trtr.ore.silver.acanthite.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class OrthoclaseBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:orthoclase");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
