package com.github.cao.awa.trtr.pressure;

public class PressurePa extends Pressure {
    public PressurePa(double value) {
        super(value);
    }

    @Override
    public String getName() {
        return "Pa";
    }
}
