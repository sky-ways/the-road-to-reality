package com.github.cao.awa.trtr.properties.type;

import com.github.cao.awa.modmdo.annotations.*;
import com.github.cao.awa.trtr.properties.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.nbt.*;
import org.json.*;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.Function;

@Disposable
public final class NbtCompoundSerializer implements Serializable<NbtCompound> {
    private final NbtCompound nbt;
    private final String deserialize;

    public NbtCompoundSerializer(NbtCompound nbt, String deserialize) {
        this.nbt = nbt;
        this.deserialize = deserialize;
    }

    public NbtCompoundSerializer(String deserialize) {
        this(null, deserialize);
    }

    public NbtCompoundSerializer(NbtCompound nbt) {
        this(nbt, null);
    }

    @Override
    public String serialize() {
        JSONObject json = new JSONObject();
        Map<String, NbtElement> map = EntrustParser.trying(() -> {
            Method method = nbt.getClass().getDeclaredMethod("toMap");
            method.setAccessible(true);
            return (Map<String, NbtElement>) method.invoke(nbt);
        });
        if (map == null) {
            return "{}";
        }
        map.forEach((k, v) -> {
            JSONObject element = new JSONObject();
            element.put("type", InstanceProperties.TYPE_S.get(v.getClass()));
            element.put("info", InstanceProperties.SERIALIZERS.get(v.getClass()).apply(v));
            json.put(k, element);
        });
        return json.toString();
    }

    public NbtCompound deserialize() {
        if ("{}".equals(deserialize)) {
            return new NbtCompound();
        }
        JSONObject json = new JSONObject(deserialize);

        Map<String, NbtElement> map = new Object2ObjectOpenHashMap<>();

        json.keySet().forEach(key -> {
            JSONObject element = json.getJSONObject(key);
            Function<String , Object> function = InstanceProperties.TYPE_D.get(element.getString("type"));
            map.put(key, (NbtElement) function.apply(element.getString("info")));
        });

        return EntrustParser.trying(() -> {
            Constructor<NbtCompound> constructor = NbtCompound.class.getDeclaredConstructor(Map.class);
            constructor.setAccessible(true);
            return constructor.newInstance(map);
        });
    }

    public NbtCompound getNbt() {
        return nbt;
    }

    public String getDeserialize() {
        return deserialize;
    }

    public NbtCompound nbt() {
        return nbt;
    }
}
