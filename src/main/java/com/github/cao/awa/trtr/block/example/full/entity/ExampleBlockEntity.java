package com.github.cao.awa.trtr.block.example.full.entity;

import com.github.cao.awa.apricot.anntation.Auto;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

@Auto
public class ExampleBlockEntity extends BlockEntity {
    public ExampleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type,
              pos,
              state
        );
    }
}
