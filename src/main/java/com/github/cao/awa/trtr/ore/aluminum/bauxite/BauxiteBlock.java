package com.github.cao.awa.trtr.ore.aluminum.bauxite;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class BauxiteBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:bauxite");

    public BauxiteBlock(Settings settings) {
        super(settings);
    }

    public static Block register() {
        Settings settings = Settings.of(Material.METAL, MapColor.WHITE).hardness(4F).requiresTool();
        BauxiteBlock bauxite = new BauxiteBlock(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, bauxite);
        BauxiteBlockItem.register(bauxite);
        return bauxite;
    }
}
