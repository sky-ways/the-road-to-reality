package com.github.cao.awa.eper.heat;

import com.github.cao.awa.eper.ref.block.*;
import com.github.cao.awa.eper.ref.block.iron.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;

public class HeatConductor {
    private final int thermalConductivity;
    /**
     * Celsius
     */
    private float selfHeat = 27;
    private int ticked = 0;
    private int conductionTick = 10;

    public HeatConductor(int thermalConductivity) {
        this.thermalConductivity = thermalConductivity;
    }

    public int getTicked() {
        return ticked;
    }

    public void setTicked(int ticked) {
        this.ticked = ticked;
    }

    public int getConductionTick() {
        return conductionTick;
    }

    public void setConductionTick(int conductionTick) {
        this.conductionTick = conductionTick;
    }

    public void heat(World world, BlockPos pos, BlockState state, HeatConductionType type) {
        if (this.selfHeat > 200) {
            for (Direction direction : Direction.values()) {
                BlockPos targetPos = pos.offset(direction);
                BlockState targetConductor = world.getBlockState(targetPos);
                if (targetConductor.getBlock() instanceof HeatConductionBlock<?>) {
                    Optional<Integer> property = targetConductor.getOrEmpty(IronBlock.TEMPERATURE);
                    if (property.isPresent()) {
                        int temperature = property.get();
                        HeatConductionBlockEntity<?> conductionEntity = null;
                        if (temperature == 1) {
                            world.setBlockState(targetPos, targetConductor.with(IronBlock.TEMPERATURE, 2), 3);
                        }
                        if (world.getBlockEntity(targetPos) instanceof HeatConductionBlockEntity<?> conductionBlockEntity) {
                            conductionEntity = conductionBlockEntity;
                        }
                        if (conductionEntity != null) {
                            heat(conductionEntity.getHeatConductor(), type);
                        }
                    }
                } else if (targetConductor.isAir()) {
                    radiating(world, pos, state);
                }
            }
        }
    }

    public void heat(HeatConductor conductor, HeatConductionType type) {
        if (! (selfHeat > conductor.getSelfHeat())) {
            return;
        }
        conductionTick = (int) Math.max(4, Math.min(10, ((thermalConductivity * 10) / selfHeat) * 2));
        ticked++;
        if (ticked > conductionTick - 1) {
            ticked = 0;
            type.apply(this, conductor);
        }
    }

    public void radiating(World world, BlockPos pos, BlockState state) {

    }

    public float getSelfHeat() {
        return selfHeat;
    }

    public void setSelfHeat(float selfHeat) {
        this.selfHeat = selfHeat;
    }

    public float calculateMaxConduction(HeatConductor target) {
        return getThermalConductivity();
    }

    public int getThermalConductivity() {
        return thermalConductivity;
    }

    public float calculateAttenuation(float maxConductivity, float maxHeat, float currentHeat) {
        return Math.max(maxConductivity, maxHeat - currentHeat);
    }

    public float calculateMax() {
        return getSelfHeat() - getThermalConductivity() - (getSelfHeat() * 0.3F);
    }

    public void heating(float max, float heating) {
        if (selfHeat + heating < max) {
            selfHeat += heating;
        } else {
            selfHeat = max;
        }
    }
}
