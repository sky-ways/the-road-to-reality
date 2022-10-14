package com.github.cao.awa.trtr.database.properties.pool;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.database.properties.stack.*;
import it.unimi.dsi.fastutil.objects.*;
import org.json.*;

import java.util.*;
import java.util.function.*;

public class PropertiesList<T> {
    private final List<T> elements;
    
    public PropertiesList(List<T> elements) {
        this.elements = elements;
    }
    
    public PropertiesList() {
        this.elements = new ObjectArrayList<>();
    }

    public void add(T value) {
        elements.add(value);
    }

    public T get(int index) {
        return elements.get(index);
    }

    public void remove(T value) {
        elements.remove(value);
    }

    public boolean contains(T value) {
        return elements.contains(value);
    }

    public void forEach(Consumer<T> action) {
        elements.forEach(action);
    }

    public static PropertiesList<?> deserialize(String deserialize) {
        JSONObject json = new JSONObject(deserialize);
        LinkedList<Object> list = new LinkedList<>();
        Function<String, Object> function = InstanceProperties.TYPE_D.get(json.getString("type"));
        json.getJSONArray("list").forEach(t -> list.add(function.apply(t.toString())));
        return new PropertiesList<>(list);
    }

    public String serialize() {
        JSONArray array = new JSONArray();
        T sample = null;
        for (T t : elements) {
            array.put(InstanceProperties.SERIALIZERS.get(t.getClass()).apply(t));
            sample = t;
        }
        if (sample == null) {
            return "{}";
        }
        JSONObject info = new JSONObject();
        if (array.length() > 0) {
            info.put("type", InstanceProperties.TYPE_S.get(sample.getClass()));
            info.put("list", array);
        }
        return info.toString();
    }
}
