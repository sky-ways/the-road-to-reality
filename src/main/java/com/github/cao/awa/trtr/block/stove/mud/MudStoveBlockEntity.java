package com.github.cao.awa.trtr.block.stove.mud;

import com.github.cao.awa.apricot.anntation.Auto;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

@Auto
public class MudStoveBlockEntity extends BlockEntity {
    public MudStoveBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type,
              pos,
              state
        );
    }
}
