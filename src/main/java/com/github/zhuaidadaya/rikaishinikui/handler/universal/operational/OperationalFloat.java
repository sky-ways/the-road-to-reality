package com.github.zhuaidadaya.rikaishinikui.handler.universal.operational;

import java.util.function.*;

public class OperationalFloat extends Operational<Float> {
    private Float floatValue;

    public OperationalFloat(Long base) {
        floatValue = Float.valueOf(base);
    }

    public OperationalFloat(Integer base) {
        floatValue = Float.valueOf(base);
    }

    public OperationalFloat() {
        floatValue = 0F;
    }

    public Float get() {
        return floatValue;
    }

    public Float set(Float value) {
        floatValue = value;
        callback(floatValue);
        return value;
    }

    public OperationalFloat callback(Consumer<Float> callback) {
        this.callback = callback;
        return this;
    }

    private void callback(Float LongValue) {
        callback.accept(LongValue);
    }

    public Float add() {
        callback(floatValue++);
        return floatValue;
    }

    public Float add(Float value, Float amplifier) {
        return add(value * amplifier);
    }

    public Float add(Float value) {
        floatValue += value;
        callback(floatValue);
        return floatValue;
    }

    public Float reduce(Float value, Float amplifier) {
        return reduce(value * amplifier);
    }

    public Float reduce(Float value) {
        floatValue -= value;
        callback(floatValue);
        return floatValue;
    }

    public Float add(Integer value) {
        floatValue += value;
        callback(floatValue);
        return floatValue;
    }

    public Float reduce() {
        callback(floatValue--);
        return floatValue;
    }

    public Float multiply(Float value) {
        callback(floatValue *= value);
        return floatValue;
    }

    public Float divide(Float value) {
        callback(floatValue /= value);
        return floatValue;
    }
}
