package com.github.cao.awa.trtr.database.properties;

import com.github.cao.awa.modmdo.security.*;
import com.github.cao.awa.trtr.database.properties.pool.*;
import com.github.cao.awa.trtr.database.properties.stack.*;
import com.github.cao.awa.trtr.database.properties.type.*;
import com.github.cao.awa.trtr.element.chemical.properties.*;
import com.github.cao.awa.trtr.math.*;
import com.github.cao.awa.trtr.util.uuid.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import org.jetbrains.annotations.*;
import org.json.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

import static com.github.cao.awa.trtr.TrtrMod.propertiesDatabase;

public class InstanceProperties {
    public static final Map<Class<?>, Function<Object, String>> SERIALIZERS = new ConcurrentHashMap<>();
    public static final Map<Class<?>, Function<Object, JSONObject>> JSON_SERIALIZERS = new ConcurrentHashMap<>();
    public static final Map<Class<?>, String> TYPE_S = new ConcurrentHashMap<>();
    public static final Map<String, Function<String, Object>> TYPE_D = new ConcurrentHashMap<>();

    static {
        setHandler(
                Integer.class,
                "(I",
                Numerical::parseInt
        );
        setHandler(
                Double.class,
                "(D",
                Numerical::parseDouble
        );
        setHandler(
                Float.class,
                "(F",
                Numerical::parseFloat
        );
        setHandler(
                Short.class,
                "(S",
                Numerical::parseShort
        );
        setHandler(
                Byte.class,
                "(Byt",
                Numerical::parseByte
        );
        setHandler(
                Long.class,
                "(Byt",
                Numerical::parseLong
        );
        setHandler(
                Boolean.class,
                "(I",
                Boolean::parseBoolean
        );
        setHandler(
                Character.class,
                "(C",
                s -> (char) Numerical.parseInt(s)
        );
        setHandler(
                String.class,
                "[STR",
                s -> s
        );

        // ChemicalElementProperties
        setHandler(
                ChemicalElementProperties.class,
                "*CE",
                ChemicalElementProperties::deserialize,
                properties -> properties.serialize()
                                        .toString()
        );

        setJSONObjectHandler(
                ChemicalElementProperties.class,
                ChemicalElementProperties::serialize
        );

        setHandler(
                AppointedPropertiesStack.class,
                "*STACK",
                AppointedPropertiesStack::deserialize,
                AppointedPropertiesStack::serialize
        );
        setHandler(
                NbtCompound.class,
                "[Nbt[C",
                NbtCompoundSerializer::deserialize,
                NbtCompoundSerializer::serialize
        );
        setHandler(
                NbtString.class,
                "[Nbt[S",
                NbtString::of,
                NbtString::asString
        );
        setHandler(
                NbtInt.class,
                "[Nbt(I",
                value -> NbtInt.of(Numerical.parseInt(value))
        );
        setHandler(
                NbtDouble.class,
                "[Nbt(D",
                value -> NbtDouble.of(Numerical.parseDouble(value))
        );
        setHandler(
                NbtFloat.class,
                "[Nbt(F",
                value -> NbtFloat.of(Numerical.parseFloat(value))
        );
        setHandler(
                NbtShort.class,
                "[Nbt(S",
                value -> NbtShort.of(Numerical.parseShort(value))
        );
        setHandler(
                NbtByte.class,
                "[Nbt(B",
                value -> NbtByte.of(Numerical.parseByte(value))
        );
        setHandler(
                NbtLong.class,
                "[Nbt(L",
                value -> NbtLong.of(Numerical.parseLong(value))
        );
        setHandler(
                PropertiesList.class,
                "*LIST",
                PropertiesList::deserialize,
                PropertiesList::serialize
        );

        setHandler(
                ItemStack.class,
                "[Item[Sta",
                value -> ItemStack.fromNbt(NbtCompoundSerializer.deserialize(value)),
                stack -> {
                    NbtCompound compound = new NbtCompound();
                    stack.writeNbt(compound);
                    return compound.asString();
                }
        );
    }

    private final Map<String, Object> map = new ConcurrentHashMap<>();
    private final boolean safe;
    private String access = null;

    public InstanceProperties() {
        this.safe = true;
    }

    public InstanceProperties(boolean isSafe) {
        this.safe = isSafe;
    }

    public static <T> void setHandler(@NotNull Class<T> target, @NotNull String serial, @NotNull Function<String, T> deserializer) {
        setHandler(
                target,
                serial,
                deserializer,
                null
        );
    }

    public static <T> void setHandler(@NotNull Class<T> target, @NotNull String serial, @NotNull Function<String, T> deserializer, @Nullable Function<T, Object> serializer) {
        TYPE_S.put(
                target,
                serial
        );
        TYPE_D.put(
                serial,
                (Function<String, Object>) deserializer
        );
        SERIALIZERS.put(
                target,
                serializer == null ?
                Object::toString :
                s -> serializer.apply((T) s)
                               .toString()
        );
    }

    public static <T> void setJSONObjectHandler(@NotNull Class<T> target, @Nullable Function<T, JSONObject> serializer) {
        JSON_SERIALIZERS.put(
                target,
                (Function<Object, JSONObject>) serializer
        );
    }

    public <X> void update(String key, Function<X, X> function) {
        put(
                key,
                function.apply(get(key))
        );
    }

    /**
     * Get target, throw exception when convert failed
     * If not present, will get the null
     *
     * @param key
     *         target
     * @param <X>
     *         target type
     * @return result
     */
    public <X> X get(String key) {
        return safe ? safeGet(key) : (X) map.get(key);
    }

    /**
     * Get target, handle exception when convert failed
     * If not present, will get the null
     *
     * @param key
     *         target
     * @param <X>
     *         target type
     * @return result
     */
    private <X> X safeGet(String key) {
        return EntrustParser.trying(() -> (X) map.get(key));
    }

    /**
     * Put target
     *
     * @param key
     *         target key
     * @param value
     *         target value
     */
    public void put(String key, Object value) {
        map.put(
                key,
                value
        );
    }

    @Deprecated
    public <X> void update(String key, Function<X, X> function, X defaultValue) {
        put(
                key,
                function.apply(getOrDefault(
                        key,
                        defaultValue
                ))
        );
    }

    /**
     * Get target from properties, and get default when failed
     *
     * @param key
     *         target
     * @param defaultValue
     *         result when failed get
     * @param <X>
     *         target type
     * @return result
     */
    public <X> X getOrDefault(String key, X defaultValue) {
        X x = get(key);
        return x == null ? defaultValue : x;
    }

    /**
     * Calculate using properties
     *
     * @param key
     *         target
     * @param predicate
     *         ensure target predicate
     * @param function
     *         calculate function
     * @param defaultValue
     *         when failed ensure
     * @param <X>
     *         target type
     * @return calculate result
     */
    public <X> X calculate(String key, Function<X, Boolean> predicate, Function<X, X> function, X defaultValue) {
        X target = getOrDefault(
                key,
                defaultValue
        );
        return predicate.apply(target) ? function.apply(target) : defaultValue;
    }

    /**
     * Write access token to NbtCompound
     *
     * @param key
     *         short uuid key
     */
    public void access(String key) {
        InstanceProperties properties = propertiesDatabase.get(key);
        this.access = key;

        if (properties == null) {
            return;
        }

        this.map.clear();

        this.map.putAll(properties.map);
    }

    public NbtCompound getAccessNbtCompound() {
        NbtCompound nbt = new NbtCompound();
        if (access != null) {
            nbt.putString(
                    "acs",
                    access
            );
            return nbt;
        }
        return nbt;
    }

    /**
     * Write access token to NbtCompound
     *
     * @param compound
     *         NbtCompound instance
     */
    public void createAccess(NbtCompound compound) {
        if (propertiesDatabase == null) {
            return;
        }
        access = access == null || access.equals("") ? RandomIdentifier.noLrIdentifier(16) : access;
        propertiesDatabase.put(access, this);

        compound.putString(
                "acs",
                access
        );
    }

    /**
     * Write access token to JSONObject
     *
     * @param json
     *         JSOBObject instance
     */
    public void createAccess(JSONObject json) {
        String id = access == null ? ShortUuidUtil.randomShortUuid() : access;
        json.put(
                "acs",
                id
        );
        propertiesDatabase.put(
                id,
                this
        );
    }

    /**
     * Read data from NbtCompound
     *
     * @param compound
     *         NbtCompound instance
     */
    public void readNbt(@NotNull NbtCompound compound) {
        NbtCompound nbt = compound.getCompound("properties");
        for (String key : nbt.getKeys()) {
            NbtCompound element = nbt.getCompound(key);
            String type = element.getString("type");
            put(
                    key,
                    TYPE_D.get(type)
                          .apply(element.getString("info"))
            );

        }
    }

    /**
     * Read data from JSONObject
     *
     * @param json
     *         JSONObject instance
     */
    public void readJSONObject(@NotNull JSONObject json) {
        JSONObject nbt = json.getJSONObject("properties");
        for (String key : nbt.keySet()) {
            JSONObject element = nbt.getJSONObject(key);
            String type = element.getString("type");
            EntrustExecution.tryTemporary(
                    () -> {
                        put(
                                key,
                                TYPE_D.get(type)
                                      .apply(element.getString("info"))
                        );
                    },
                    () -> {
                        put(
                                key,
                                TYPE_D.get(type)
                                      .apply(element.getJSONObject("info")
                                                    .toString())
                        );
                    }
            );
        }
    }

    /**
     * Operation(stack in) to a properties stack
     * <p>
     * target must be an AppointedPropertiesStack or not present
     * if not satisfy requires, will fail operation
     */
    public <X> void stack(String key, X x) {
        AppointedPropertiesStack<X> list = safeGet(key);
        if (list == null) {
            list = new AppointedPropertiesStack<>();
            put(
                    key,
                    list
            );
        }
        list.stack(x);
    }

    /**
     * Operation(pop out) to a properties stack
     * <p>
     * target must be an AppointedPropertiesStack or not present
     * if not satisfy requires, will fail operation
     *
     * @param key
     *         target
     * @return popped target
     */
    public <X> X pop(String key) {
        AppointedPropertiesStack<X> list = safeGet(key);
        if (list == null) {
            return null;
        }
        return list.pop();
    }

    /**
     * Operation(pop out) to a properties stack
     * <p>
     * target must be an AppointedPropertiesStack or not present
     * if not satisfy requires, will fail operation
     *
     * @param key
     *         target
     * @return value
     */
    public <X> PropertiesList<X> list(String key) {
        PropertiesList<X> list = safeGet(key);
        if (list == null) {
            list = new PropertiesList<>();
            put(
                    key,
                    list
            );
        }
        return list;
    }

    /**
     * Operation(pop out all) to a properties stack
     * <p>
     * target must be an AppointedPropertiesStack or not present
     * if not satisfy requires, will fail operation
     */
    public <X> List<X> pops(String key) {
        AppointedPropertiesStack<X> list = safeGet(key);
        if (list == null) {
            return null;
        }
        return list.pops();
    }

    /**
     * Destroy an item in properties, and give it back as that sample
     *
     * @param key
     *         target
     * @param <X>
     *         target type
     * @return destroyed item
     */
    public <X> X destroy(String key) {
        X x = safeGet(key);
        remove(key);
        return x;
    }

    /**
     * Remove element from properties
     *
     * @param key
     *         target
     */
    public void remove(String key) {
        map.remove(key);
    }

    public NbtCompound toNbtCompound() {
        NbtCompound nbt = new NbtCompound();
        writeNbt(nbt);
        return nbt;
    }

    /**
     * Write data to NbtCompound
     *
     * @param compound
     *         NbtCompound instance
     */
    public void writeNbt(NbtCompound compound) {
        NbtCompound nbt = new NbtCompound();
        map.forEach((k, v) -> {
            Class<?> clazz = v.getClass();
            String type = TYPE_S.get(clazz);
            if (type == null) {
                return;
            }
            NbtCompound element = new NbtCompound();
            element.putString(
                    "type",
                    type
            );

            element.putString(
                    "info",
                    SERIALIZERS.get(clazz)
                               .apply(v)
            );
            nbt.put(
                    k,
                    element
            );
        });
        compound.put(
                "properties",
                nbt
        );
    }

    public JSONObject toJSONObject() {
        JSONObject nbt = new JSONObject();
        writeJSONObject(nbt);
        return nbt;
    }

    /**
     * Write data to JSONObject
     *
     * @param json
     *         JSONObject instance
     */
    public void writeJSONObject(JSONObject json) {
        JSONObject nbt = new JSONObject();
        map.forEach((k, v) -> {
            Class<?> clazz = v.getClass();
            if (TYPE_S.containsKey(clazz)) {
                String type = TYPE_S.get(clazz);
                JSONObject element = new JSONObject();
                element.put(
                        "type",
                        type
                );
                if (JSON_SERIALIZERS.containsKey(clazz)) {
                    element.put(
                            "info",
                            JSON_SERIALIZERS.get(clazz)
                                            .apply(v)
                    );
                } else {
                    element.put(
                            "info",
                            SERIALIZERS.get(clazz)
                                       .apply(v)
                    );
                }
                nbt.put(
                        k,
                        element
                );
            }
        });
        json.put(
                "properties",
                nbt
        );
    }

    public void readProperties(InstanceProperties properties) {
        this.access = properties.access;
        this.map.clear();
        this.map.putAll(properties.map);
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }
}