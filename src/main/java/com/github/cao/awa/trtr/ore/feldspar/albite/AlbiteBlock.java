package com.github.cao.awa.trtr.ore.feldspar.albite;

import com.github.cao.awa.trtr.ore.silver.acanthite.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.data.client.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class AlbiteBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:albite");

    public AlbiteBlock(Settings settings) {
        super(settings);
    }

    public static Block register() {
        Settings settings = Settings.of(Material.METAL, MapColor.WHITE).hardness(4F).requiresTool();
        AcanthiteBlock bauxite = new AcanthiteBlock(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, bauxite);
        AlbiteBlockItem.register(bauxite);
        return bauxite;
    }
}
