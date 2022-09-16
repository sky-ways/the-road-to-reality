package com.github.cao.awa.trtr.ore.lead.galena;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class GalenaBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:galena");

    public GalenaBlock(Settings settings) {
        super(settings);
    }

    public static Block register() {
        Settings settings = Settings.of(Material.METAL, MapColor.GRAY).hardness(4F).requiresTool();
        GalenaBlock bauxite = new GalenaBlock(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, bauxite);
        GalenaBlockItem.register(bauxite);
        return bauxite;
    }
}
