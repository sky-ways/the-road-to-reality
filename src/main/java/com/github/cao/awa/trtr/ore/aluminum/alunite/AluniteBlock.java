package com.github.cao.awa.trtr.ore.aluminum.alunite;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class AluniteBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:alunite");

    public Identifier identifier() {
        return IDENTIFIER;
    }
}
