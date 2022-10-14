package com.github.cao.awa.trtr.ref.item.fire;

import com.github.cao.awa.trtr.explosion.vanilla.tnt.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.block.*;
import net.minecraft.item.*;

import java.util.*;

public class FireReacts {
    public static final Map<Item, FireReact> REACTS = new Object2ObjectOpenHashMap<>();

    public static void pre() {
        register(Blocks.TNT, ((world, pos, strength) -> {
            VanillaTntBlock.explode(world, pos, null);
        }));
    }

    public static void register(Item item, FireReact react) {
        REACTS.put(item, react);
    }

    public static void register(Block block, FireReact react) {
        register(block.asItem(), react);
    }
}
