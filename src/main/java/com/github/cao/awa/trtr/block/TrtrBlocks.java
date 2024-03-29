package com.github.cao.awa.trtr.block;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.block.item.TrtrBlockItems;
import com.github.cao.awa.trtr.framework.accessor.identifier.IdentifierAccessor;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;

import java.util.Map;

public class TrtrBlocks {
    public static final Map<Identifier, Block> BLOCKS = ApricotCollectionFactor.hashMap();

    public static void register(Identifier identifier, Block block) {
        BLOCKS.put(identifier,
                   block
        );
    }

    public static Identifier getIdentifier(Block block) {
        // Access identifier of block.
        return IdentifierAccessor.ACCESSOR.get(block);
    }

    public static BlockItem getBlockItem(Block block) {
        return TrtrBlockItems.get(getIdentifier(block));
    }

    public static Block get(Identifier identifier) {
        return BLOCKS.get(identifier);
    }

    public static Block get(Class<? extends Block> clazz) {
        return get(IdentifierAccessor.ACCESSOR.get(clazz));
    }
}
