package com.github.cao.awa.trtr.pressure;

public class PressureKpa extends Pressure {
    public PressureKpa(double value) {
        super(value);
    }

    @Override
    public String getName() {
        return "kPa";
    }
}
