package com.github.cao.awa.trtr.type;

import com.github.zhuaidadaya.rikaishinikui.handler.range.*;
import com.github.zhuaidadaya.rikaishinikui.handler.range.table.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.item.*;

import java.util.*;

public class TrtrHammerables {
    public static final Object2ObjectOpenHashMap<Item, RangeTable<Item, NumberRange<Item>>> hammerables = new Object2ObjectOpenHashMap<>();

    public static void register(Set<Item> items, RangeTable<Item, NumberRange<Item>> products) {
        for (Item item : items) {
            if (!hammerables.containsKey(item)) {
                hammerables.put(item, products);
            } else {
                hammerables.get(item).merge(products);
            }
        }
    }
}
