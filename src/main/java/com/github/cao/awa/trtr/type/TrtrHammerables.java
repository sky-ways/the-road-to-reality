package com.github.cao.awa.trtr.type;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.table.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.item.*;

import java.util.*;

public class TrtrHammerables {
    public static final Object2ObjectOpenHashMap<Item, RageTable<Item, NumberRage<Item>>> hammerables = new Object2ObjectOpenHashMap<>();

    public static void register(Set<Item> items, RageTable<Item, NumberRage<Item>> products) {
        for (Item item : items) {
            if (!hammerables.containsKey(item)) {
                hammerables.put(item, products);
            } else {
                hammerables.get(item).merge(products);
            }
        }
    }
}
