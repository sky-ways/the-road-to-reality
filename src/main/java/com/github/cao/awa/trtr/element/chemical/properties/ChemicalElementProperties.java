package com.github.cao.awa.trtr.element.chemical.properties;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.content.*;
import com.github.cao.awa.trtr.element.chemical.reaction.*;
import it.unimi.dsi.fastutil.objects.*;
import org.json.*;

import java.util.*;
import java.util.function.*;

public class ChemicalElementProperties {
    private final Map<ChemicalReactive, ChemicalContent> contents;

    public ChemicalElementProperties() {
        this.contents = new Object2ObjectOpenHashMap<>();
    }

    public ChemicalElementProperties(Map<ChemicalReactive, ChemicalContent> contents) {
        this.contents = contents;
    }

    public static ChemicalElementProperties deserialize(String deserialize) {
        JSONObject json = new JSONObject(deserialize);
        Map<ChemicalReactive, ChemicalContent> contents = new Object2ObjectOpenHashMap<>();
        json.keySet().forEach(name -> {
            ChemicalReactive element = ChemicalElements.get(name);
            ChemicalContent content = new ChemicalContent(element, - 1);
            contents.put(element, content.deserialize(json.getJSONObject(name)));
        });
        return new ChemicalElementProperties(contents);
    }

    public void put(ChemicalReactive element, ChemicalContent content) {
        contents.put(element, content);
    }

    public void update(ChemicalReactive element, Consumer<ChemicalContent> action) {
        ChemicalContent content = contents.get(element);
        if (content == null) {
            return;
        }
        action.accept(content);
    }

    public ChemicalContent get(ChemicalReactive element) {
        return contents.get(element);
    }

    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        contents.forEach(((element, content) -> json.put(element.getName(), content.serialize())));
        return json;
    }
}
