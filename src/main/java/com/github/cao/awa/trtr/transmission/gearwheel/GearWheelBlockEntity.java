package com.github.cao.awa.trtr.transmission.gearwheel;

import com.github.cao.awa.trtr.properties.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class GearWheelBlockEntity extends BlockEntity {
    private final InstanceProperties<GearWheelBlockEntity> properties = new InstanceProperties<>(this);

    public GearWheelBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.GEAR_WHEEL, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        properties.readNbt(nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        properties.writeNbt(nbt);
    }

    public void tick(World world, BlockPos pos, BlockState state) {

    }
}
