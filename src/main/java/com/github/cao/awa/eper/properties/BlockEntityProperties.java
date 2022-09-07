package com.github.cao.awa.eper.properties;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import it.unimi.dsi.fastutil.objects.*;

import java.util.function.*;

public class BlockEntityProperties<T> {
    private final Object2ObjectOpenHashMap<String, Object> map = new Object2ObjectOpenHashMap<>();
    private final T instance;

    public T getInstance() {
        return instance;
    }

    public BlockEntityProperties(T instance) {
        this.instance = instance;
    }

    public int calculateInt(String key, Function<Integer, Integer> function) {
        return function.apply(getIntOrDefault(key, 0));
    }

    public int getIntOrDefault(String key, int defaultValue) {
        if (! map.containsKey(key)) {
            return defaultValue;
        }
        return EntrustParser.trying(() -> (int) map.get(key), () -> defaultValue);
    }

    public int calculateInt(String key, Function<Integer, Boolean> predicate, Function<Integer, Integer> function, int defaultValue) {
        Integer integer = getIntOrDefault(key, 0);
        return predicate.apply(integer) ? function.apply(integer) : defaultValue;
    }

    public void updateInt(String key, Function<Integer, Integer> function) {
        putInt(key, function.apply(getIntOrDefault(key, 0)));
    }

    public void putInt(String key, int value) {
        map.put(key, value);
    }

    public double calculateDouble(String key, Function<Double, Double> function) {
        return function.apply(getDoubleOrDefault(key, 0D));
    }

    public double getDoubleOrDefault(String key, double defaultValue) {
        if (! map.containsKey(key)) {
            return defaultValue;
        }
        return EntrustParser.trying(() -> (double) map.get(key), () -> defaultValue);
    }

    public double calculateDouble(String key, Function<Double, Boolean> predicate, Function<Double, Double> function, double defaultValue) {
        Double integer = getDoubleOrDefault(key, 0);
        return predicate.apply(integer) ? function.apply(integer) : defaultValue;
    }

    public void updateDouble(String key, Function<Double, Double> function) {
        putDouble(key, function.apply(getDoubleOrDefault(key, 0D)));
    }

    public void putDouble(String key, double value) {
        map.put(key, value);
    }

    public float calculateFloat(String key, Function<Float, Float> function) {
        return function.apply(getFloatOrDefault(key, 0F));
    }

    public float getFloatOrDefault(String key, float defaultValue) {
        if (! map.containsKey(key)) {
            return defaultValue;
        }
        return EntrustParser.trying(() -> Float.parseFloat(String.valueOf(map.get(key))), () -> defaultValue);
    }

    public float calculateFloat(String key, Function<Float, Boolean> predicate, Function<Float, Float> function, float defaultValue) {
        Float integer = getFloatOrDefault(key, 0);
        return predicate.apply(integer) ? function.apply(integer) : defaultValue;
    }

    public void updateFloat(String key, Function<Float, Float> function) {
        putFloat(key, function.apply(getFloatOrDefault(key, 0F)));
    }

    public void putFloat(String key, float value) {
        map.put(key, value);
    }
}
