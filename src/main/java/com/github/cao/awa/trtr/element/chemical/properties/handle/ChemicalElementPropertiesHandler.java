package com.github.cao.awa.trtr.element.chemical.properties.handle;

import com.github.cao.awa.trtr.database.properties.handler.*;
import com.github.cao.awa.trtr.element.chemical.properties.*;
import com.github.cao.awa.trtr.math.*;
import org.json.*;

public class ChemicalElementPropertiesHandler extends PropertiesHandler<ChemicalElementProperties> {
    public ChemicalElementPropertiesHandler() {
        super(
                "*CE",
                ChemicalElementProperties.class
        );
    }

    @Override
    public ChemicalElementProperties getFromJSON(String key, JSONObject json) {
        return ChemicalElementProperties.deserialize(json.getJSONObject(key).toString());
    }

    @Override
    public void putToJSON(String key, ChemicalElementProperties target, JSONObject json) {
        json.put(
                key,
                target.serialize()
        );
    }
}
