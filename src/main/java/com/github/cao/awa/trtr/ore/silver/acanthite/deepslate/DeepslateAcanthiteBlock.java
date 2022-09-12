package com.github.cao.awa.trtr.ore.silver.acanthite.deepslate;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class DeepslateAcanthiteBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:deepslate_acanthite");

    public DeepslateAcanthiteBlock(Settings settings) {
        super(settings);
    }

    public static Block register() {
        Settings settings = Settings.of(Material.METAL, MapColor.WHITE).hardness(4F).requiresTool();
        DeepslateAcanthiteBlock bauxite = new DeepslateAcanthiteBlock(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, bauxite);
        DeepslateAcanthiteBlockItem.register(bauxite);
        return bauxite;
    }
}
