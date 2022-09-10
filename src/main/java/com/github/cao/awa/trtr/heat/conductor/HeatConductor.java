package com.github.cao.awa.trtr.heat.conductor;

import com.github.cao.awa.trtr.ref.block.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.function.*;

public abstract class HeatConductor {
    public static final Function<Double, Double> HEAT_FIX_COEFFICIENT = (s) -> 0.001;
    public static final Supplier<Double> NORMAL_TEMPERATURE = () -> 30D;
    private final ObjectArrayList<HeatConductor> heating = new ObjectArrayList<>();
    private int temperature;

    public HeatConductor() {
        this.temperature = 0;
    }

    public HeatConductor(int temperature) {
        this.temperature = temperature;
    }

    public ObjectArrayList<HeatConductor> getHeating() {
        return heating;
    }

    public abstract void endothermic(World world, BlockPos pos, BlockState state);

    public void heat(ImmutableConductor heatModel) {
        collect(heatModel);
        endothermic();
    }

    /**
     * <blockquote><pre>
     * V = [t * f(t)]
     *      ∀t, V∈[0, T(A)]
     * T(A) -> +V
     * T(B) -> -V
     * </pre></blockquote>
     * <p>
     * t is temperature difference of blocks A and B <br>
     * the t is always higher than 0 <br>
     *
     * <br>
     * <p>
     * T(x) is temperature of a point time <br>
     */
    public void endothermic() {
        double cons = 0;
        for (HeatConductor bl : heating) {
            if (bl.temperature > temperature) {
                double t = (bl.temperature - temperature);
                double v = Math.ceil((t * HEAT_FIX_COEFFICIENT.apply(t)));
                cons += v;
                bl.temperature -= v;
            }
        }

        this.temperature += cons;

        heating.clear();
    }

    public void collect(HeatConductor bl) {
        this.heating.add(bl);
    }

    public boolean prepare(World world, BlockPos pos, BlockState state) {
        BlockEntity targetEntity = world.getBlockEntity(pos);
        if (state.getOrEmpty(HeatConductionBlock.TEMPERATURE).isPresent()) {
            if (targetEntity == null) {
                world.setBlockState(pos, state.with(HeatConductionBlock.TEMPERATURE, 2), 3);
            }
            return true;
        }
        return false;
    }

    public void readNbt(NbtCompound compound) {
        NbtCompound nbt = compound.getCompound("temperatureConductor");
        this.setTemperature(nbt.getInt("temperature"));
    }

    public void writeNbt(NbtCompound compound) {
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("temperature", getTemperature());
        compound.put("temperatureConductor", nbt);
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
