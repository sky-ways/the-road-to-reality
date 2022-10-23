package com.github.cao.awa.trtr.cooking.container.grill;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.util.*;

// todo
public class GrillBlock extends TrtrBasedBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:grill");

    public GrillBlock(Settings settings) {
        super(settings);
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
