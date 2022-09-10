package com.github.cao.awa.trtr.ore.aluminum.bauxite.deepslate;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class DeepslateBauxiteBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:deepslate_bauxite");

    public DeepslateBauxiteBlock(Settings settings) {
        super(settings);
    }

    public static Block register() {
        Settings settings = Settings.of(Material.METAL, MapColor.WHITE).hardness(4F).requiresTool();
        DeepslateBauxiteBlock bauxite = new DeepslateBauxiteBlock(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, bauxite);
        DeepslateBauxiteBlockItem.register(bauxite);
        return bauxite;
    }
}
