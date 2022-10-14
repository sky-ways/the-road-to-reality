package com.github.cao.awa.trtr.pressure;

public class PressureKpa extends Pressure {
    public PressureKpa(double value) {
        set(value);
    }

    public void set(double value) {
        super.set(value);
    }

    @Override
    public PressurePa toPa() {
        return new PressurePa(get() * 1000);
    }

    public double get() {
        return super.get();
    }
}
