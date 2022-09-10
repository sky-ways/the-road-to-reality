package com.github.cao.awa.trtr.heat.conductor;

import com.github.cao.awa.trtr.ref.block.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class BurnerBlockHeatConductor extends HeatConductor {
    public BurnerBlockHeatConductor(int temperature) {
        super(temperature);
    }

    public BurnerBlockHeatConductor() {
        super();
    }

    public void endothermic(World world, BlockPos pos, BlockState state) {
        Direction direction = Direction.UP;
        BlockPos targetPos = pos.offset(direction);
        BlockEntity targetEntity = world.getBlockEntity(targetPos);
        BlockState targetState = world.getBlockState(targetPos);
        if (targetState.getOrEmpty(HeatConductionBlock.TEMPERATURE).isPresent()) {
            if (this.getTemperature() > NORMAL_TEMPERATURE.get()) {
                if (prepare(world, targetPos, targetState)) {
                    targetEntity = world.getBlockEntity(targetPos);
                }
            }
            if (targetEntity instanceof HeatConductive heatConduction) {
                collect(heatConduction.getConductor());
            }
        }
        endothermic();
    }

    public int getTemperature() {
        return super.getTemperature();
    }

    public void setTemperature(int temperature) {
        super.setTemperature(temperature);
    }
}
