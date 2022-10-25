package com.github.cao.awa.trtr.element.chemical.properties;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.content.*;
import it.unimi.dsi.fastutil.objects.*;
import org.json.*;

import java.util.*;
import java.util.function.*;

public class ChemicalElementProperties {
    private final Map<ChemicalElement, ChemicalContent> contents;

    public ChemicalElementProperties() {
        this.contents = new Object2ObjectOpenHashMap<>();
    }

    public ChemicalElementProperties(Map<ChemicalElement, ChemicalContent> contents) {
        this.contents = contents;
    }

    public static ChemicalElementProperties deserialize(String deserialize) {
        JSONObject json = new JSONObject(deserialize);
        Map<ChemicalElement, ChemicalContent> contents = new Object2ObjectOpenHashMap<>();
        json.keySet().forEach(name -> {
            ChemicalElement element = ChemicalElements.get(name);
            ChemicalContent content = new ChemicalContent(element, - 1);
            contents.put(element, content.deserialize(json.getJSONObject(name)));
        });
        return new ChemicalElementProperties(contents);
    }

    public void put(ChemicalElement element, ChemicalContent content) {
        contents.put(element, content);
    }

    public void update(ChemicalElement element, Consumer<ChemicalContent> action) {
        ChemicalContent content = contents.get(element);
        if (content == null) {
            return;
        }
        action.accept(content);
    }

    public ChemicalContent get(ChemicalElement element) {
        return contents.get(element);
    }

    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        contents.forEach(((element, content) -> json.put(element.getName(), content.serialize())));
        return json;
    }
}
