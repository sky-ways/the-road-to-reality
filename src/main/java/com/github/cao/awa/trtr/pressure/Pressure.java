package com.github.cao.awa.trtr.pressure;

public abstract class Pressure {
    private double value;

    public double get() {
        return value;
    }

    public void set(double value) {
        this.value = value;
    }

    public abstract PressurePa toPa();
}
