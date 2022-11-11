package com.github.cao.awa.trtr.database.properties.handler.integer;

import com.github.cao.awa.trtr.database.properties.handler.*;
import org.json.*;

public class LongPropertiesHandler extends PropertiesHandler<Long> {
    public LongPropertiesHandler() {
        super(
                "(L",
                Long.class
        );
    }

    @Override
    public Long getFromJSON(String key, JSONObject json) {
        return json.getLong(key);
    }

    @Override
    public void putToJSON(String key, Long target, JSONObject json) {
        json.put(
                key,
                target
        );
    }
}
