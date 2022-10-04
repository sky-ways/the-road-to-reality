package com.github.cao.awa.trtr.properties;

import it.unimi.dsi.fastutil.objects.*;
import org.json.*;

import java.util.*;
import java.util.function.*;

public class AppointedPropertiesStack<T> {
    private final List<T> list;

    public AppointedPropertiesStack() {
        this.list = new ObjectArrayList<>();
    }

    public AppointedPropertiesStack(List<T> list) {
        this.list = list;
    }

    public static <T> AppointedPropertiesStack<T> parse(String s) {
        JSONObject json = new JSONObject(s);
        ObjectArrayList<T> list = new ObjectArrayList<>();
        Function<String, Object> function = InstanceProperties.TYPE_D.get(json.getString("type"));
        json.getJSONArray("list").forEach(t -> list.add((T) function.apply(t.toString())));
        return new AppointedPropertiesStack<>(list);
    }

    public void stack(T t) {
        list.add(t);
    }

    public T pop() {
        if (size() > 0) {
            T t = list.get(size() - 1);
            list.remove(t);
            return t;
        }
        return null;
    }

    public int size() {
        return list.size();
    }

    public String toString() {
        JSONArray array = new JSONArray();
        for (Object t : list) {
            array.put(t);
        }
        JSONObject info = new JSONObject();
        info.put("type", InstanceProperties.TYPE_S.get(array.get(0).getClass()));
        info.put("list", array);
        return info.toString();
    }
}
