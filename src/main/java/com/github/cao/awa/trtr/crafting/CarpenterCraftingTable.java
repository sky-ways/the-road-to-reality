package com.github.cao.awa.trtr.crafting;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import org.jetbrains.annotations.*;

public class CarpenterCraftingTable extends TrtrBasedBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:carpenter_crafting_table");

    public CarpenterCraftingTable(Settings settings) {
        super(settings);
    }
}
