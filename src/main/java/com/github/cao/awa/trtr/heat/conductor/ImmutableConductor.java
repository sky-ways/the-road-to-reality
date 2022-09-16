package com.github.cao.awa.trtr.heat.conductor;

import com.github.cao.awa.trtr.ref.block.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class ImmutableConductor extends HeatConductor {
    public ImmutableConductor(HeatConductiveBlockEntity conductive, int temperature) {
        super(conductive, temperature);
    }

    @Override
    public void endothermic(World world, BlockPos pos) {

    }

    @Override
    public void adaptive(World world) {

    }

    public void endothermic(World world) {
        for (HeatConductor conductor : getHeating()) {
            double t1 = conductor.getTemperature();
            double t2 = this.getTemperature();
            if (t1 > NORMAL_TEMPERATURE.get() && t1 > t2) {
                double t = (t1 - t2);
                double v = (t * conductor.fixCoefficient(t, this));
                conductor.setTemperature(t1 - v);
            }
        }

        getHeating().clear();
    }

    @Override
    public double fixCoefficient(double heat, HeatConductor conductor) {
        return HEAT_FIX_COEFFICIENT.apply(heat, conductor);
    }
}
