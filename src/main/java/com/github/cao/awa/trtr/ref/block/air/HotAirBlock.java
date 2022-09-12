package com.github.cao.awa.trtr.ref.block.air;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.math.*;
import org.jetbrains.annotations.*;

public class HotAirBlock extends BlockWithEntity {
    public HotAirBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}
