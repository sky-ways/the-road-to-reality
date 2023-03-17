package com.github.cao.awa.trtr.block;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.framework.accessor.identifier.IdentifierAccessor;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;

import java.util.Map;

public class TrtrBlockItems {
    public static final Map<Identifier, BlockItem> ITEMS = ApricotCollectionFactor.newHashMap();

    public static void register(Identifier identifier, BlockItem item) {
        ITEMS.put(identifier,
                  item
        );
    }

    public static Identifier getIdentifier(BlockItem item) {
        // Access identifier of item.
        return IdentifierAccessor.ACCESSOR.get(item);
    }

    public static BlockItem get(Identifier identifier) {
        return ITEMS.get(identifier);
    }
}
