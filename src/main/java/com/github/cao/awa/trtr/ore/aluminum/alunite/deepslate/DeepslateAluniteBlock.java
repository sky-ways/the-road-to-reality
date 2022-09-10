package com.github.cao.awa.trtr.ore.aluminum.alunite.deepslate;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class DeepslateAluniteBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:deepslate_alunite");

    public DeepslateAluniteBlock(Settings settings) {
        super(settings);
    }

    public static Block register() {
        Settings settings = Settings.of(Material.METAL, MapColor.WHITE).hardness(4F).requiresTool();
        DeepslateAluniteBlock alunite = new DeepslateAluniteBlock(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, alunite);
        DeepslateAluniteBlockItem.register(alunite);
        return alunite;
    }
}
