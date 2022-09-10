package com.github.cao.awa.trtr.heat.conductor;

import net.minecraft.block.*;
import net.minecraft.nbt.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class ImmutableConductor extends HeatConductor {
    public ImmutableConductor(int temperature) {
        super(temperature);
    }

    @Override
    public void endothermic(World world, BlockPos pos, BlockState state) {

    }

    public void endothermic() {
        for (HeatConductor bl : getHeating()) {
            if (bl.getTemperature() > getTemperature()) {
                double tc = bl.getTemperature() - getTemperature();
                double v = Math.ceil((tc * HEAT_FIX_COEFFICIENT.apply(tc)));
                bl.setTemperature((int) (bl.getTemperature() - v));
            }
        }

        getHeating().clear();
    }

}
