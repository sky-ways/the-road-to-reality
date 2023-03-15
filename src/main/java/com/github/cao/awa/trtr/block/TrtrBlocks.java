package com.github.cao.awa.trtr.block;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;

public class TrtrBlocks {
    public static final Map<Identifier, Block> BLOCKS = ApricotCollectionFactor.newHashMap();

    public static void register(Identifier identifier, Block block) {
        BLOCKS.put(identifier, block);
    }

    public static Block get(Identifier identifier) {
        return BLOCKS.get(identifier);
    }
}
