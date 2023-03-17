package com.github.cao.awa.trtr.block.example.simple.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class SimpleExampleBlockEntity extends BlockEntity {
    public SimpleExampleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type,
              pos,
              state
        );
    }
}
