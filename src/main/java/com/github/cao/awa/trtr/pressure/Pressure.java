package com.github.cao.awa.trtr.pressure;

public abstract class Pressure {
    private double value;

    /**
     * Get pressure.
     *
     * @return Pressure
     */
    public double get() {
        return value;
    }

    /**
     * Set pressure.
     */
    public void set(double value) {
        this.value = value;
    }

    /**
     * Get pressure name.
     *
     * @return Pressure name
     */
    public abstract String getName();
}
