package com.github.cao.awa.eper.heat;

import java.util.function.*;

public enum HeatConductionType {
    TO_MAX((conductor, target) -> {
        float maxHeat = conductor.getSelfHeat();
        float currentHeat = target.getSelfHeat();
        float conduction = conductor.calculateAttenuation(conductor.calculateMaxConduction(target), maxHeat, currentHeat);
        target.heating(maxHeat,  conduction);
        conductor.setSelfHeat(conductor.getSelfHeat() - conduction);
    }),
    CALCULATE_MAX((conductor, target) -> {
        float maxHeat = conductor.calculateMax();
        float currentHeat = target.getSelfHeat();
        float conduction = conductor.calculateAttenuation(conductor.calculateMaxConduction(target), maxHeat, currentHeat);
        target.heating(maxHeat,  conduction);
        conductor.setSelfHeat(conductor.getSelfHeat() - conduction);
    });

    private final BiConsumer<HeatConductor, HeatConductor> action;

    HeatConductionType(BiConsumer<HeatConductor, HeatConductor> action) {
        this.action = action;
    }

    public void apply(HeatConductor conductor, HeatConductor target) {
        action.accept(conductor, target);
    }
}
