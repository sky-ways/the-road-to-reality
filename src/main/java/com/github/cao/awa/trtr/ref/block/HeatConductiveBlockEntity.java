package com.github.cao.awa.trtr.ref.block;

import net.minecraft.block.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public interface HeatConductiveBlockEntity extends HeatConductive {
    BlockPos getPos();
    void prepare(World world, BlockPos pos);
    void init(World world);
    default boolean isOf(HeatConductiveBlockEntity entity) {
        return entity != null;
    }
    default boolean isOf(BlockEntity entity) {
        return entity instanceof HeatConductionBlockEntity<?>;
    }
    default boolean participateUnload() {
        return false;
    }
}
