package com.github.cao.awa.trtr.heat.conductor;

import com.github.cao.awa.trtr.ref.block.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.nbt.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;
import java.util.function.*;

import static com.github.cao.awa.trtr.TrtrMod.heatHandler;

public abstract class HeatConductor {
    public static final BiFunction<Double, HeatConductor, Double> HEAT_FIX_COEFFICIENT = (h, c) -> 0.001;
    public static final Supplier<Double> NORMAL_TEMPERATURE = () -> 30D;
    private final ObjectArrayList<HeatConductor> heating = new ObjectArrayList<>();
    private final HeatConductiveBlockEntity conductive;
    private double temperature;
    private int unloadCost = 6;

    public HeatConductor(HeatConductiveBlockEntity conductive) {
        this.temperature = 0;
        this.conductive = conductive;
    }

    public HeatConductor(HeatConductiveBlockEntity conductive, int temperature) {
        this.temperature = temperature;
        this.conductive = conductive;
    }

    public HeatConductiveBlockEntity getConductive() {
        return conductive;
    }

    public ObjectArrayList<HeatConductor> getHeating() {
        return heating;
    }

    public abstract void endothermic(World world, BlockPos pos);

    public abstract void adaptive(World world);

    public void heat(ImmutableConductor heatModel) {
        collect(heatModel);
        endothermic(null);
    }

    /**
     * definition ΣX<sub>i</sub> looks like:
     * <blockquote><pre>
     *    n
     *    Σ X<sub>i</sub>
     *   i=0
     * </pre>
     * </blockquote>
     *
     * n means list size of X <br>
     * then
     *
     * <blockquote><pre>
     * t = B<sub>i</sub> - T
     * V<sub>i</sub> = t * f(t)
     *
     * T -> ΣV<sub>i</sub>
     * B<sub>i</sub> -> -V<sub>i</sub>
     * </pre></blockquote>
     *
     *  <p>
     * T is self temperature <br>
     * t is temperature difference of T with B<sub>i</sub> <br>
     * the t is always higher than 0 <br>
     * <br>
     * and V<sub>i</sub> is temperature conduction speed for B<sub>i</sub> <br>
     * </p>
     * <br>
     * <p>
     * block T and ΣB<sub>i</sub> will be thermal equilibrium
     *
     */
    public void endothermic(World world) {
        unloadCost = heating.size();
        if (unloadCost == 0) {
            return;
        }
        double total = 0;
        for (HeatConductor conductor : heating) {
            if (conductor.temperature > NORMAL_TEMPERATURE.get() && conductor.temperature > this.temperature) {
                double t = (conductor.temperature - this.temperature);
                double v = (t * conductor.fixCoefficient(t, this));
                total += v;
                conductor.temperature -= v;
            } else {
                prepareUnload(world, getConductive().getPos());
            }
        }

        this.temperature += total;

        heating.clear();
    }

    public boolean shouldUnload(World world) {
//        boolean cannotUnload = false;
//        for (Direction direction : Direction.values()) {
//            HeatConductor conductor = heatHandler.getConductor(world, getConductive().getPos().offset(direction));
//            if (conductor == null) {
//                continue;
//            }
//            if (conductor.getTemperature() > temperature && conductor.temperature > NORMAL_TEMPERATURE.get()) {
//                cannotUnload = true;
//            }
//            if (cannotUnload) {
//                return null;
//            }
//        }
//        return getConductive();
        return true;
    }

    public abstract double fixCoefficient(double heat, HeatConductor conductor);

    public void collect(HeatConductor bl) {
        this.heating.add(bl);
    }

    public void prepare(World world, BlockPos pos) {
        if (heatHandler.isTicking(world, pos)) {
            return;
        }
        heatHandler.prepare(world, pos, () -> {
            if (world.getBlockEntity(pos) instanceof HeatConductiveBlockEntity entity) {
                entity.prepare(world, pos);
            }
        });
    }

    public void prepareUnload(World world, BlockPos pos) {
        unloadCost --;
        if (unloadCost == 0) {
            heatHandler.requireUnload(world, pos);
        }
    }

    public void readNbt(NbtCompound compound) {
        NbtCompound nbt = compound.getCompound("temperatureConductor");
        this.setTemperature(nbt.getDouble("temperature"));
    }

    public void writeNbt(NbtCompound compound) {
        NbtCompound nbt = new NbtCompound();
        nbt.putDouble("temperature", getTemperature());
        compound.put("temperatureConductor", nbt);
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
