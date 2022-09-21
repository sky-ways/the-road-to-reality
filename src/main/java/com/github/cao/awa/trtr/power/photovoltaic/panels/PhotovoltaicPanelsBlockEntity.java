package com.github.cao.awa.trtr.power.photovoltaic.panels;

import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class PhotovoltaicPanelsBlockEntity extends BlockEntity implements BlockEntityTicker<PhotovoltaicPanelsBlockEntity> {
    public PhotovoltaicPanelsBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.PHOTOVOLTAIC_PANELS, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, PhotovoltaicPanelsBlockEntity blockEntity) {
    }
}
