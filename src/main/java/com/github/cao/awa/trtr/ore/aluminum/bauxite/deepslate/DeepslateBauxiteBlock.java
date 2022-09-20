package com.github.cao.awa.trtr.ore.aluminum.bauxite.deepslate;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class DeepslateBauxiteBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:deepslate_bauxite");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
