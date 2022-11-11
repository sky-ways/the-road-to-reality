package com.github.cao.awa.trtr.database.properties.handler.integer;

import com.github.cao.awa.trtr.database.properties.handler.*;
import org.json.*;

public class IntegerPropertiesHandler extends PropertiesHandler<Integer> {
    public IntegerPropertiesHandler() {
        super(
                "(I",
                Integer.class
        );
    }

    @Override
    public Integer getFromJSON(String key, JSONObject json) {
        return json.getInt(key);
    }

    @Override
    public void putToJSON(String key, Integer target, JSONObject json) {
        json.put(
                key,
                target
        );
    }
}
