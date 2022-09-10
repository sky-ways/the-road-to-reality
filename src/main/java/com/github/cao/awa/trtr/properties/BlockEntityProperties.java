package com.github.cao.awa.trtr.properties;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import com.mojang.datafixers.types.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.nbt.*;

import java.util.*;
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

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public void writeNbt(NbtCompound compound) {
        NbtCompound nbt = new NbtCompound();
        map.forEach((k, v) -> {
            if (TYPE_S.containsKey(v.getClass())) {
                String type = TYPE_S.get(v.getClass());
                NbtCompound element = new NbtCompound();
                element.putString("type", type);
                element.putString(k, v.toString());
                nbt.put(k, element);
            }
        });
        compound.put("properties", nbt);
    }

    public void readNbt(NbtCompound compound) {
        NbtCompound nbt = compound.getCompound("properties");
        for (String key : nbt.getKeys()) {
            NbtCompound element = nbt.getCompound(key);
            put(key, TYPE_D.get(key).apply(element.getString(key)));
        }
    }

    public static final Map<Class<?>, String> TYPE_S = EntrustParser.operation(new Object2ObjectOpenHashMap<>(), map -> {
        map.put(Integer.class, "(I");
        map.put(Double.class, "(D");
        map.put(Float.class, "(F");
        map.put(Short.class, "(S");
        map.put(Byte.class, "(Byt");
        map.put(Character.class, "(C");
//        map.put(NbtCompound.class,"[N:Cpd");
        map.put(String.class, "[STR");
    });

    public static final Map<String, Function<String, Object>> TYPE_D = EntrustParser.operation(new Object2ObjectOpenHashMap<>(), map -> {
        map.put("(I", Integer::parseInt);
        map.put( "(D", Double::parseDouble);
        map.put("(F", Float::parseFloat);
        map.put("(S", Short::parseShort);
        map.put("(Byt", Byte::parseByte);
        map.put("(C", s -> (char)Integer.parseInt(s));
        map.put( "[STR", s -> s);
    });
}
