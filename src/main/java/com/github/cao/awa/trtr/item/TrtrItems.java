package com.github.cao.awa.trtr.item;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.framework.accessor.identifier.IdentifierAccessor;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Map;

public class TrtrItems {
    public static final Map<Identifier, Item> ITEMS = ApricotCollectionFactor.newHashMap();

    public static void register(Identifier identifier, Item item) {
        ITEMS.put(identifier,
                  item
        );
    }

    public static Identifier getIdentifier(Item item) {
        // Access identifier of item.
        return IdentifierAccessor.ACCESSOR.get(item);
    }

    public static Item get(Identifier identifier) {
        return ITEMS.get(identifier);
    }

    public static Item get(Class<? extends Item> clazz) {
        return get(IdentifierAccessor.ACCESSOR.get(clazz));
    }
}
