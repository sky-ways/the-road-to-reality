package com.github.cao.awa.trtr.block.entity;

import com.github.cao.awa.apricot.anntation.Auto;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

@Auto
public abstract class TrtrBlockEntity extends BlockEntity {
    public TrtrBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type,
              pos,
              state
        );
    }
}
