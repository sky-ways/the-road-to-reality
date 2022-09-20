package com.github.cao.awa.trtr.ore.lead.galena.deepslate;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class DeepslateGalenaBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:deepslate_galena");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
