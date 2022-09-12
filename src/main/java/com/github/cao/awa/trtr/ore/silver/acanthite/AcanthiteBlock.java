package com.github.cao.awa.trtr.ore.silver.acanthite;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class AcanthiteBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:acanthite");

    public AcanthiteBlock(Settings settings) {
        super(settings);
    }

    public static Block register() {
        Settings settings = Settings.of(Material.METAL, MapColor.WHITE).hardness(4F).requiresTool();
        AcanthiteBlock bauxite = new AcanthiteBlock(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, bauxite);
        AcanthiteBlockItem.register(bauxite);
        return bauxite;
    }
}
