package com.github.cao.awa.trtr.pressure;

public class PressurePa extends Pressure {
    public PressurePa(double value) {
        set(value);
    }

    public void set(double value) {
        super.set(value);
    }

    @Override
    public Pressure convert(Pressure pressure) {
        return this;
    }

    @Override
    public String getName() {
        return "Pa";
    }

    public double get() {
        return super.get();
    }
}
