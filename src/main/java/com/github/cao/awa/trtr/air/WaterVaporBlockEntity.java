package com.github.cao.awa.trtr.air;

import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class WaterVaporBlockEntity extends BlockEntity {
    public WaterVaporBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.WATER_VAPOR, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
    }
}