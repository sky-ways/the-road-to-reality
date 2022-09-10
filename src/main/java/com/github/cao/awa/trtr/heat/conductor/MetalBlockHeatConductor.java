package com.github.cao.awa.trtr.heat.conductor;

import com.github.cao.awa.trtr.ref.block.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class MetalBlockHeatConductor extends HeatConductor {
    public MetalBlockHeatConductor() {
        super();
    }

    public MetalBlockHeatConductor(int temperature) {
        super(temperature);
    }

    public int getTemperature() {
        return super.getTemperature();
    }

    public void setTemperature(int temperature) {
        super.setTemperature(temperature);
    }

    public void endothermic(World world, BlockPos pos, BlockState state) {
        for (Direction direction : Direction.values()) {
            BlockPos targetPos = pos.offset(direction);
            BlockEntity targetEntity = world.getBlockEntity(targetPos);
            BlockState targetState = world.getBlockState(targetPos);
            if (state.getOrEmpty(HeatConductionBlock.TEMPERATURE).isPresent()) {
                if (this.getTemperature() > NORMAL_TEMPERATURE.get()) {
                    if (prepare(world, targetPos, targetState)) {
                        targetEntity = world.getBlockEntity(targetPos);
                    }
                }
                if (targetEntity instanceof HeatConductive heatConduction) {
                    collect(heatConduction.getConductor());
                }
            }
        }
        endothermic();
    }
}
