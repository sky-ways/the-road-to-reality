package com.github.cao.awa.trtr.database.properties.handler.string;

import com.github.cao.awa.trtr.database.properties.handler.*;
import com.github.cao.awa.trtr.math.*;
import org.json.*;

public class BytePropertiesHandler extends PropertiesHandler<Byte> {
    public BytePropertiesHandler() {
        super(
                "(Byt",
                Byte.class
        );
    }

    @Override
    public Byte getFromJSON(String key, JSONObject json) {
        return Numerical.parseByte(json.get(key).toString());
    }

    @Override
    public void putToJSON(String key, Byte target, JSONObject json) {
        json.put(
                key,
                target
        );
    }
}
