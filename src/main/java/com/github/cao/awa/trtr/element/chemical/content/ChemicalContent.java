package com.github.cao.awa.trtr.element.chemical.content;

import com.github.cao.awa.trtr.element.chemical.reaction.*;
import org.json.*;

public class ChemicalContent {
    private final ChemicalReactive element;
    private int value;
    private double percent;

    public ChemicalContent(ChemicalReactive element, double percent, int value) {
        this.element = element;
        this.percent = percent;
        this.value = value;
    }

    public ChemicalContent(ChemicalReactive element, double percent) {
        this.element = element;
        this.percent = percent;
        this.value = 0;
    }

    public ChemicalReactive getElement() {
        return element;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public JSONObject serialize() {
        JSONObject json = new JSONObject();
        json.put(
                "value",
                value
        );
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
        this.value = json.getInt("value");
        this.percent = json.getDouble("percent");
        return this;
    }
}
