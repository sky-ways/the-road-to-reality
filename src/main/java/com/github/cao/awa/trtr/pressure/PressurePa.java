package com.github.cao.awa.trtr.pressure;

public class PressurePa extends Pressure {
    public PressurePa(double value) {
        set(value);
    }

    public void set(double value) {
        super.set(value);
    }

    @Override
    public PressurePa toPa() {
        return this;
    }

    public double get() {
        return super.get();
    }
}
