package com.github.zhuaidadaya.rikaishinikui.handler.universal.operational;

import java.util.function.*;

public class OperationalDouble extends Operational<Double> {
    private Double doubleValue;

    public OperationalDouble(Long base) {
        doubleValue = Double.valueOf(base);
    }

    public OperationalDouble(Integer base) {
        doubleValue = Double.valueOf(base);
    }

    public OperationalDouble() {
        doubleValue = 0D;
    }

    public Double get() {
        return doubleValue;
    }

    public Double set(Double value) {
        doubleValue = value;
        callback(doubleValue);
        return value;
    }

    public OperationalDouble callback(Consumer<Double> callback) {
        this.callback = callback;
        return this;
    }

    private void callback(Double LongValue) {
        callback.accept(LongValue);
    }

    public Double add() {
        callback(doubleValue++);
        return doubleValue;
    }

    public Double add(Double value, Double amplifier) {
        return add(value * amplifier);
    }

    public Double add(Double value) {
        doubleValue += value;
        callback(doubleValue);
        return doubleValue;
    }

    public Double reduce(Double value, Double amplifier) {
        return reduce(value * amplifier);
    }

    public Double reduce(Double value) {
        doubleValue -= value;
        callback(doubleValue);
        return doubleValue;
    }

    public Double add(Integer value) {
        doubleValue += value;
        callback(doubleValue);
        return doubleValue;
    }

    public Double reduce() {
        callback(doubleValue--);
        return doubleValue;
    }

    public Double multiply(Double value) {
        callback(doubleValue *= value);
        return doubleValue;
    }

    public Double divide(Double value) {
        callback(doubleValue /= value);
        return doubleValue;
    }
}
