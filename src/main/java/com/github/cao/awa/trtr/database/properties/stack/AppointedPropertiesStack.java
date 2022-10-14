package com.github.cao.awa.trtr.database.properties.stack;

import com.github.cao.awa.trtr.database.properties.*;
import org.json.*;

import java.util.*;
import java.util.function.*;

public class AppointedPropertiesStack<T> extends PropertiesStack<T> {
    public AppointedPropertiesStack() {
        super(new LinkedList<>());
    }

    public AppointedPropertiesStack(List<T> list) {
        super(list);
    }

    public static AppointedPropertiesStack<?> deserialize(String deserialize) {
        JSONObject json = new JSONObject(deserialize);
        LinkedList<Object> list = new LinkedList<>();
        Function<String, Object> function = InstanceProperties.TYPE_D.get(json.getString("type"));
        json.getJSONArray("list").forEach(t -> list.add(function.apply(t.toString())));
        return new AppointedPropertiesStack<>(list);
    }

    public JSONObject serialize() {
        JSONArray array = new JSONArray();
        T sample = null;
        for (T t : list) {
            array.put(InstanceProperties.SERIALIZERS.get(t.getClass()).apply(t));
            sample = t;
        }
        if (sample == null) {
            return new JSONObject();
        }
        JSONObject info = new JSONObject();
        if (array.length() > 0) {
            info.put("type", InstanceProperties.TYPE_S.get(sample.getClass()));
            info.put("list", array);
        }
        return info;
    }
}
