package com.github.cao.awa.trtr.database.properties.handler.logic;

import com.github.cao.awa.trtr.database.properties.handler.*;
import org.json.*;

public class BooleanPropertiesHandler extends PropertiesHandler<Boolean> {
    public BooleanPropertiesHandler() {
        super(
                "(B",
                Boolean.class
        );
    }

    @Override
    public Boolean getFromJSON(String key, JSONObject json) {
        return json.getBoolean(key);
    }

    @Override
    public void putToJSON(String key, Boolean target, JSONObject json) {
        json.put(
                key,
                target
        );
    }
}
