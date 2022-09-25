package com.github.cao.awa.trtr.heat.conductor;

import com.github.cao.awa.trtr.ref.block.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import static com.github.cao.awa.trtr.TrtrMod.heatHandler;

public class MetalBlockHeatConductor extends HeatConductor {
    public MetalBlockHeatConductor(HeatConductiveBlockEntity conductive) {
        super(conductive);
    }

    public MetalBlockHeatConductor(HeatConductiveBlockEntity conductive, int temperature) {
        super(conductive, temperature);
    }

    public double getTemperature() {
        return super.getTemperature();
    }

    public void setTemperature(double temperature) {
        super.setTemperature(temperature);
    }

    public void endothermic(World world, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockPos targetPos = pos.offset(direction);
            HeatConductor conductor = heatHandler.getConductor(world, targetPos);
            if (conductor == null) {
                if (this.getTemperature() > NORMAL_TEMPERATURE.get()) {
                    prepare(world, targetPos);
                }
                continue;
            }
            collect(conductor);
        }
        endothermic(world);
    }

    @Override
    public void adaptive(World world) {
        endothermic(world, getConductive().getPos());
    }

    @Override
    public double fixCoefficient(double heat, HeatConductor conductor) {
        return HEAT_FIX_COEFFICIENT.apply(heat, conductor);
    }
}
