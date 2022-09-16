package com.github.cao.awa.trtr.ore.feldspar.orthoclase;

import com.github.cao.awa.trtr.ore.silver.acanthite.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class OrthoclaseBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:orthoclase");

    public OrthoclaseBlock(Settings settings) {
        super(settings);
    }

    public static Block register() {
        Settings settings = Settings.of(Material.METAL, MapColor.WHITE).hardness(4F).requiresTool();
        OrthoclaseBlock bauxite = new OrthoclaseBlock(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, bauxite);
        OrthoclaseBlockItem.register(bauxite);
        return bauxite;
    }
}
