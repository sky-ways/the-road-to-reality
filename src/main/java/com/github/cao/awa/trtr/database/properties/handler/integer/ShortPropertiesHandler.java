package com.github.cao.awa.trtr.database.properties.handler.integer;

import com.github.cao.awa.trtr.database.properties.handler.*;
import com.github.cao.awa.trtr.math.*;
import org.json.*;

public class ShortPropertiesHandler extends PropertiesHandler<Short> {
    public ShortPropertiesHandler() {
        super(
                "(S",
                Short.class
        );
    }

    @Override
    public Short getFromJSON(String key, JSONObject json) {
        return Numerical.parseShort(json.get(key).toString());
    }

    @Override
    public void putToJSON(String key, Short target, JSONObject json) {
        json.put(
                key,
                target
        );
    }
}
