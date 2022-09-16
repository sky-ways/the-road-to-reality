package com.github.cao.awa.trtr.ore.lead.galena.deepslate;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class DeepslateGalenaBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:deepslate_galena");

    public DeepslateGalenaBlock(Settings settings) {
        super(settings);
    }

    public static Block register() {
        Settings settings = Settings.of(Material.METAL, MapColor.GRAY).hardness(4F).requiresTool();
        DeepslateGalenaBlock bauxite = new DeepslateGalenaBlock(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, bauxite);
        DeepslateGalenaBlockItem.register(bauxite);
        return bauxite;
    }
}
