package com.github.cao.awa.trtr.block.pan.frying.entity;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.entity.TrtrBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

@Auto
public class FryingPanBlockEntity extends TrtrBlockEntity {
    public FryingPanBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type,
              pos,
              state
        );
    }
}
