package com.github.cao.awa.trtr.ore.aluminum.alunite;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class AluniteBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:alunite");

    public AluniteBlock(Settings settings) {
        super(settings);
    }

    public static Block register() {
        Settings settings = Settings.of(Material.METAL, MapColor.WHITE).hardness(4F).requiresTool();
        AluniteBlock alunite = new AluniteBlock(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, alunite);
        AluniteBlockItem.register(alunite);
        return alunite;
    }
}
