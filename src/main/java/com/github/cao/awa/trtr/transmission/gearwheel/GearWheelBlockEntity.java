package com.github.cao.awa.trtr.transmission.gearwheel;

import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class GearWheelBlockEntity extends BlockEntity {
    public GearWheelBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.GEAR_WHEEL, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {

    }
}
