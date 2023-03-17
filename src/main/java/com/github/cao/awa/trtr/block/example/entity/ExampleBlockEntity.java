package com.github.cao.awa.trtr.block.example.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class ExampleBlockEntity extends BlockEntity {
    public ExampleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type,
              pos,
              state
        );
    }


}
