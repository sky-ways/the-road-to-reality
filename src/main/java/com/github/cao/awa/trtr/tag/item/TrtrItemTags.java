package com.github.cao.awa.trtr.tag.item;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TrtrItemTags {
    public static final TagKey<Item> PEBBLES = of("trtr:pebbles");

    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM,
                         new Identifier(id)
        );
    }

    public static void initialize() {

    }
}
