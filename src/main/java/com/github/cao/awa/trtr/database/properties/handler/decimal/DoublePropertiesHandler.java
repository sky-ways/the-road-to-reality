package com.github.cao.awa.trtr.database.properties.handler.decimal;

import com.github.cao.awa.trtr.database.properties.handler.*;
import org.json.*;

public class DoublePropertiesHandler extends PropertiesHandler<Double> {
    public DoublePropertiesHandler() {
        super(
                "(D",
                Double.class
        );
    }

    @Override
    public Double getFromJSON(String key, JSONObject json) {
        return json.getDouble(key);
    }

    @Override
    public void putToJSON(String key, Double target, JSONObject json) {
        json.put(
                key,
                target
        );
    }
}
