package com.github.cao.awa.trtr.mud.pipe;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;

public class MudPipeBlock extends TrtrBasedBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:mud_pipe");

    public MudPipeBlock() {
        super(Settings.of(Material.SOIL));
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
