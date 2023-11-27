package com.github.cao.awa.trtr.gas;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public interface GasPassable {
    boolean canGasPass(BlockState state, Direction direction);

    void onGasPass(World world, BlockPos pos, BlockState state, Direction from);
}
