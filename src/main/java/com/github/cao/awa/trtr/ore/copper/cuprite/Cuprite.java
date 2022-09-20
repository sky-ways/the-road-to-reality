package com.github.cao.awa.trtr.ore.copper.cuprite;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;

public class Cuprite extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:cuprite");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
