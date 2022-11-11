package com.github.cao.awa.trtr.database.properties.handler.decimal;

import com.github.cao.awa.trtr.database.properties.handler.*;
import org.json.*;

public class FloatPropertiesHandler extends PropertiesHandler<Float> {
    public FloatPropertiesHandler() {
        super(
                "(F",
                Float.class
        );
    }

    @Override
    public Float getFromJSON(String key, JSONObject json) {
        return json.getFloat(key);
    }

    @Override
    public void putToJSON(String key, Float target, JSONObject json) {
        json.put(
                key,
                target
        );
    }
}
