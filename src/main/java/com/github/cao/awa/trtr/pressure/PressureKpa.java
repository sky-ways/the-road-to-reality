package com.github.cao.awa.trtr.pressure;

public class PressureKpa extends Pressure {
    public PressureKpa(double value) {
        set(value);
    }

    public void set(double value) {
        super.set(value);
    }

    @Override
    public String getName() {
        return "kPa";
    }

    public double get() {
        return super.get();
    }
}
