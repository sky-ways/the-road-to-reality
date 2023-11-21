package com.github.cao.awa.trtr.block.gas.entity;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.serializer.AutoNbt;
import com.github.cao.awa.trtr.block.entity.TrtrBlockEntity;
import com.github.cao.awa.trtr.block.gas.GasBlock;
import com.github.cao.awa.trtr.constant.pressure.PressureConstants;
import com.github.cao.awa.trtr.pressure.pa.PaPressure;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Auto
public class GasBlockEntity extends TrtrBlockEntity {
    @Auto
    @AutoNbt("layer")
    public PaPressure pressure = new PaPressure(PressureConstants.STANDARD_ATMOSPHERIC_PRESSURE.value());

    @Auto
    public GasBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type,
              pos,
              state
        );
    }

    @Auto
    public void tick(World world, BlockPos pos, BlockState state) {
        if (state.getBlock() instanceof GasBlock gasBlock && world instanceof ServerWorld serverWorld) {
            gasBlock.flowingTick(state,
                                 serverWorld,
                                 pos
            );
        }
    }
}
