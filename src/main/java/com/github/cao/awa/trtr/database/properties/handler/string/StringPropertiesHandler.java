package com.github.cao.awa.trtr.database.properties.handler.string;

import com.github.cao.awa.trtr.database.properties.handler.*;
import com.github.cao.awa.trtr.math.*;
import org.json.*;

public class StringPropertiesHandler extends PropertiesHandler<String> {
    public StringPropertiesHandler() {
        super(
                "[STR",
                String.class
        );
    }

    @Override
    public String getFromJSON(String key, JSONObject json) {
        return json.getString(key);
    }

    @Override
    public void putToJSON(String key, String target, JSONObject json) {
        json.put(
                key,
                target
        );
    }
}
