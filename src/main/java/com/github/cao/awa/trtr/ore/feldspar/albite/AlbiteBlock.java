package com.github.cao.awa.trtr.ore.feldspar.albite;

import com.github.cao.awa.trtr.ore.generic.*;
import com.github.cao.awa.trtr.ore.silver.acanthite.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.data.client.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class AlbiteBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:albite");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
