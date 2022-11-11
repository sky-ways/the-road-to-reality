package com.github.cao.awa.trtr.database.properties.handler;

import de.javagl.obj.*;
import org.json.*;

public abstract class PropertiesHandler<T> {
    private final String name;
    private final Class<T> clazz;

    public PropertiesHandler(String name, Class<T> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public String getName() {
        return name;
    }

    public abstract T getFromJSON(String key, JSONObject json);

    public void postJSON(String key, Object target, JSONObject json) {
        if (target.getClass()
                  .equals(clazz)) {
            putToJSON(
                    key,
                    clazz.cast(target),
                    json
            );
        }
    }

    public abstract void putToJSON(String key, T target, JSONObject json);
}
