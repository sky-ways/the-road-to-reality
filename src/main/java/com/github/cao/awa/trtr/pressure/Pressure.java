package com.github.cao.awa.trtr.pressure;

/**
 * Pressure interface.
 *
 * @author cao_awa
 * @since 1.0.0
 */
public abstract class Pressure {
    private double value;

    /**
     * Create a pressure.
     *
     * @param value Pressure
     */
    public Pressure(double value) {
        this.value = value;
    }

    /**
     * Get pressure.
     *
     * @return Pressure
     *
     * @author cao_awa
     * @since 1.0.0
     */
    public double get() {
        return this.value;
    }

    /**
     * Set pressure.
     *
     * @author cao_awa
     * @since 1.0.0
     */
    public void set(double value) {
        this.value = value;
    }

    /**
     * Get pressure name.
     *
     * @return Pressure name
     *
     * @author cao_awa
     * @since 1.0.0
     */
    public abstract String getName();
}
