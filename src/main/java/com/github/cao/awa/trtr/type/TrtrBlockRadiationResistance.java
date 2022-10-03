package com.github.cao.awa.trtr.type;

import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.block.*;

public class TrtrBlockRadiationResistance {
    public static final Object2ObjectOpenHashMap<Block, Double> resistances = new Object2ObjectOpenHashMap<>();

    public static void register(Block block, double resistance) {
        resistances.put(block, resistance);
    }

    public static double get(Block block) {
        return resistances.getOrDefault(block, 1.0D);
    }

    public static double get(BlockState state) {
        return get(state.getBlock());
    }
}
