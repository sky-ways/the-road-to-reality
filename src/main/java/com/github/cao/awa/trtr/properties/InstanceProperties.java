package com.github.cao.awa.trtr.properties;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import net.minecraft.nbt.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class InstanceProperties<T> {
    private static final Map<Class<?>, Function<Object, String>> SERIALIZERS = new ConcurrentHashMap<>();
    private static final Map<Class<?>, String> TYPE_S = new ConcurrentHashMap<>();
    private static final Map<String, Function<String, Object>> TYPE_D = new ConcurrentHashMap<>();

    static {
        addHandler(Integer.class, "(I", Integer::parseInt);
        addHandler(Double.class, "(D", Double::parseDouble);
        addHandler(Float.class, "(F", Float::parseFloat);
        addHandler(Short.class, "(S", Short::parseShort);
        addHandler(Byte.class, "(Byt", Byte::parseByte);
        addHandler(Boolean.class, "(I", Boolean::parseBoolean);
        addHandler(Character.class, "(C", s -> (char) Integer.parseInt(s));
        addHandler(String.class, "[STR", s -> s);
    }

    private final Map<String, Object> map = new ConcurrentHashMap<>();
    private final T instance;

    public InstanceProperties(T instance) {
        this.instance = instance;
    }

    public static void addHandler(@NotNull Class<?> target, @NotNull String serial, @NotNull Function<String, Object> deserializer) {
        addHandler(target, serial, deserializer, null);
    }

    public static void addHandler(@NotNull Class<?> target, @NotNull String serial, @NotNull Function<String, Object> deserializer, @Nullable Function<Object, String> serializer) {
        TYPE_S.put(target, serial);
        TYPE_D.put(serial, deserializer);
        SERIALIZERS.put(target, serializer == null ? Object::toString : serializer);
    }

    public T getInstance() {
        return instance;
    }
    public int getIntOrDefault(String key, int defaultValue) {
        if (! map.containsKey(key)) {
            return defaultValue;
        }
        return EntrustParser.trying(() -> (int) map.get(key), e -> defaultValue);
    }

    public void updateInt(String key, Function<Integer, Integer> function) {
        putInt(key, function.apply(getIntOrDefault(key, 0)));
    }

    public void putInt(String key, int value) {
        map.put(key, value);
    }

    public double getDoubleOrDefault(String key, double defaultValue) {
        if (! map.containsKey(key)) {
            return defaultValue;
        }
        return EntrustParser.trying(() -> (double) map.get(key), () -> defaultValue);
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

    public void writeNbt(NbtCompound compound) {
        NbtCompound nbt = new NbtCompound();
        map.forEach((k, v) -> {
            if (TYPE_S.containsKey(v.getClass())) {
                String type = TYPE_S.get(v.getClass());
                NbtCompound element = new NbtCompound();
                element.putString("type", type);
                element.putString(k, SERIALIZERS.get(v.getClass()).apply(v));
                nbt.put(k, element);
            }
        });
        compound.put("properties", nbt);
    }

    public void readNbt(NbtCompound compound) {
        NbtCompound nbt = compound.getCompound("properties");
        for (String key : nbt.getKeys()) {
            NbtCompound element = nbt.getCompound(key);
            String des = element.getString("type");
            put(key, TYPE_D.get(des).apply(element.getString(key)));
        }
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }
}
