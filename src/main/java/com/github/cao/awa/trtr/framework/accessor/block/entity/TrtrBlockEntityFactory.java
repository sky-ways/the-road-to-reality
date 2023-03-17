package com.github.cao.awa.trtr.framework.accessor.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public interface TrtrBlockEntityFactory {
    BlockEntity create(BlockPos pos, BlockState state);
}
