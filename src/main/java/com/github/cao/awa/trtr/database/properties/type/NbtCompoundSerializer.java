package com.github.cao.awa.trtr.database.properties.type;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.nbt.*;
import org.json.*;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.Function;

public final class NbtCompoundSerializer {
    public static String serialize(NbtCompound nbt) {
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

    public static NbtCompound deserialize(String deserialize) {
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
}
