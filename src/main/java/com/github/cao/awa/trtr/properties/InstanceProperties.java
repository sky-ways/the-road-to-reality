package com.github.cao.awa.trtr.properties;

import com.github.cao.awa.trtr.math.*;
import com.github.cao.awa.trtr.properties.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import net.minecraft.nbt.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class InstanceProperties<T> {
    public static final Map<Class<?>, Function<Object, String>> SERIALIZERS = new ConcurrentHashMap<>();
    public static final Map<Class<?>, String> TYPE_S = new ConcurrentHashMap<>();
    public static final Map<String, Function<String, Object>> TYPE_D = new ConcurrentHashMap<>();

    static {
        setHandler(Integer.class, "(I", Numerical::parseInt);
        setHandler(Double.class, "(D", Numerical::parseDouble);
        setHandler(Float.class, "(F", Numerical::parseFloat);
        setHandler(Short.class, "(S", Numerical::parseShort);
        setHandler(Byte.class, "(Byt", Numerical::parseByte);
        setHandler(Long.class, "(Byt", Numerical::parseLong);
        setHandler(Boolean.class, "(I", Boolean::parseBoolean);
        setHandler(Character.class, "(C", s -> (char) Numerical.parseInt(s));
        setHandler(String.class, "[STR", s -> s);
        setHandler(AppointedPropertiesStack.class, "*L", AppointedPropertiesStack::parse);
        setHandler(NbtCompound.class, "[Nbt[C", s -> new NbtCompoundSerializer(s).deserialize(), x -> {
            if (x instanceof NbtCompound nbt) {
                return new NbtCompoundSerializer(nbt).serialize();
            }
            return "{}";
        });
        setHandler(NbtString.class, "[Nbt[S", NbtString::of, x -> {
            if (x instanceof NbtString string) {
                return string.asString();
            }
            return "";
        });
        setHandler(NbtInt.class, "[Nbt(I", value -> NbtInt.of(Numerical.parseInt(value)));
        setHandler(NbtDouble.class, "[Nbt(D", value -> NbtDouble.of(Numerical.parseDouble(value)));
        setHandler(NbtFloat.class, "[Nbt(F", value -> NbtFloat.of(Numerical.parseFloat(value)));
        setHandler(NbtShort.class, "[Nbt(S", value -> NbtShort.of(Numerical.parseShort(value)));
        setHandler(NbtByte.class, "[Nbt(B", value -> NbtByte.of(Numerical.parseByte(value)));
        setHandler(NbtLong.class, "[Nbt(L", value -> NbtLong.of(Numerical.parseLong(value)));
    }

    private final Map<String, Object> map = new ConcurrentHashMap<>();
    private final T instance;
    private final boolean safe;

    public InstanceProperties(T instance) {
        this.instance = instance;
        this.safe = false;
    }

    public InstanceProperties(T instance, boolean isSafe) {
        this.instance = instance;
        this.safe = isSafe;
    }

    public static void setHandler(@NotNull Class<?> target, @NotNull String serial, @NotNull Function<String, Object> deserializer) {
        setHandler(target, serial, deserializer, null);
    }

    public static void setHandler(@NotNull Class<?> target, @NotNull String serial, @NotNull Function<String, Object> deserializer, @Nullable Function<Object, String> serializer) {
        TYPE_S.put(target, serial);
        TYPE_D.put(serial, deserializer);
        SERIALIZERS.put(target, serializer == null ? Object::toString : serializer);
    }

    public T getInstance() {
        return instance;
    }

    public <X> void update(String key, Function<X, X> function) {
        put(key, function.apply(get(key)));
    }

    public <X> void update(String key, Function<X, X> function, X defaultValue) {
        put(key, function.apply(getOrDefault(key, defaultValue)));
    }

    public <X> X calculate(String key, Function<X, Boolean> predicate, Function<X, X> function, X defaultValue) {
        X target = getOrDefault(key, defaultValue);
        return predicate.apply(target) ? function.apply(target) : defaultValue;
    }

    public <X> X getOrDefault(String key, X defaultValue) {
        X x = get(key);
        return x == null ? defaultValue : x;
    }

    public <X> X get(String key) {
        return safe ? safeGet(key) : (X) map.get(key);
    }

    private <X> X safeGet(String key) {
        return EntrustParser.trying(() -> (X) map.get(key));
    }

    public void writeNbt(NbtCompound compound) {
        NbtCompound nbt = new NbtCompound();
        map.forEach((k, v) -> {
            if (TYPE_S.containsKey(v.getClass())) {
                String type = TYPE_S.get(v.getClass());
                NbtCompound element = new NbtCompound();
                element.putString("type", type);
                element.putString("info", SERIALIZERS.get(v.getClass()).apply(v));
                nbt.put(k, element);
            }
        });
        compound.put("properties", nbt);
    }

    public void readNbt(NbtCompound compound) {
        NbtCompound nbt = compound.getCompound("properties");
        for (String key : nbt.getKeys()) {
            NbtCompound element = nbt.getCompound(key);
            String type = element.getString("type");
            put(key, TYPE_D.get(type).apply(element.getString("info")));
        }
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public <X> void stack(String key, X x) {
        AppointedPropertiesStack<X> list = safeGet(key);
        if (list == null) {
            put(key, new AppointedPropertiesStack<>());
            list = safeGet(key);
        }
        list.stack(x);
    }

    public <X> X pop(String key) {
        AppointedPropertiesStack<X> list = safeGet(key);
        if (list == null) {
            return null;
        }
        return list.pop();
    }
}
