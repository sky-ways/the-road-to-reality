package com.github.cao.awa.trtr.element.chemical.content;

import com.github.cao.awa.trtr.element.chemical.reaction.*;
import org.json.*;

public class ChemicalContent {
    private final ChemicalReactive element;
    private double percent;

    public ChemicalContent(ChemicalReactive element, double percent) {
        this.element = element;
        this.percent = percent;
    }

    public ChemicalReactive getElement() {
        return element;
    }

    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put(
                "percent",
                percent
        );
        return json;
    }

    public double getPercent() {
        return percent;
    }

    public ChemicalContent deserialize(String str) {
        return deserialize(new JSONObject(str));
    }

    public ChemicalContent deserialize(JSONObject json) {
        this.percent = json.getDouble("percent");
        return this;
    }
}
