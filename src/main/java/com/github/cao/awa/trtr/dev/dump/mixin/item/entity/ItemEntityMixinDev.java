package com.github.cao.awa.trtr.dev.dump.mixin.item.entity;

import com.github.cao.awa.trtr.ref.item.fire.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class ItemEntityMixinDev {
    public static void inLava(World world, BlockPos pos, ItemEntity entity) {
    }
    public static void inFire(World world, BlockPos pos, ItemEntity entity) {
        FireReacts.REACTS.getOrDefault(entity.getStack().getItem(), ((world1, pos1, strength) -> {})).react(world, pos, 114F);
    }

    public static void onFire(World world, BlockPos pos, ItemEntity entity) {
    }
}
