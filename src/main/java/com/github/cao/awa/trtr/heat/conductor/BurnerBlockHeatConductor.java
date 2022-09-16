package com.github.cao.awa.trtr.heat.conductor;

import com.github.cao.awa.trtr.ref.block.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.function.*;

import static com.github.cao.awa.trtr.TrtrMod.heatHandler;

public class BurnerBlockHeatConductor extends HeatConductor {
    public static final BiFunction<Double, HeatConductor, Double> BURNER_HEAT_FIX_COEFFICIENT = (h, c) -> 0.0007;

    public BurnerBlockHeatConductor(HeatConductiveBlockEntity conductive, int temperature) {
        super(conductive, temperature);
    }

    public BurnerBlockHeatConductor(HeatConductiveBlockEntity conductive) {
        super(conductive);
    }

    public void endothermic(World world, BlockPos pos) {
        BlockPos targetPos = pos.up();
        HeatConductor conductor = heatHandler.getConductor(world, targetPos);
        if (conductor == null) {
            if (this.getTemperature() > NORMAL_TEMPERATURE.get()) {
                prepare(world, targetPos);
            }
        } else {
            collect(conductor);
        }
        endothermic(world);
    }

    public double fixCoefficient(double heat, HeatConductor conductor) {
        return BURNER_HEAT_FIX_COEFFICIENT.apply(heat, conductor);
    }

    @Override
    public void adaptive(World world) {
        endothermic(world, getConductive().getPos());
    }

    public double getTemperature() {
        return super.getTemperature();
    }

    public void setTemperature(int temperature) {
        super.setTemperature(temperature);
    }
}
