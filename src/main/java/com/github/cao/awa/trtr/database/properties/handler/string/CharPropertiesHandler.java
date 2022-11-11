package com.github.cao.awa.trtr.database.properties.handler.string;

import com.github.cao.awa.trtr.database.properties.handler.*;
import com.github.cao.awa.trtr.math.*;
import org.json.*;

public class CharPropertiesHandler extends PropertiesHandler<Character> {
    public CharPropertiesHandler() {
        super(
                "(D",
                Character.class
        );
    }

    @Override
    public Character getFromJSON(String key, JSONObject json) {
        return Numerical.parseChar(json.get(key).toString());
    }

    @Override
    public void putToJSON(String key, Character target, JSONObject json) {
        json.put(
                key,
                target
        );
    }
}
