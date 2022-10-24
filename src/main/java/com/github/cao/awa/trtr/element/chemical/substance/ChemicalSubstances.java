package com.github.cao.awa.trtr.element.chemical.substance;

import com.github.cao.awa.trtr.element.chemical.elements.*;
import com.github.cao.awa.trtr.element.chemical.elements.compound.calcium.carbonate.*;
import com.github.cao.awa.trtr.element.chemical.elements.compound.carbon.dioxide.*;
import com.github.cao.awa.trtr.element.chemical.elements.nitrogen.*;
import com.github.cao.awa.trtr.element.chemical.elements.oxygen.*;
import com.github.cao.awa.trtr.element.chemical.grouped.oxygen.cycle.two.oxygen.*;
import it.unimi.dsi.fastutil.objects.*;

import java.util.*;

public class ChemicalSubstances {
    public static final Map<String, ChemicalElement> NAME_TO_ELEMENTS = new Object2ObjectOpenHashMap<>();

    public static final Nitrogen NITROGEN = register(new Nitrogen());

    public static final Oxygen OXYGEN = register(new Oxygen());
    public static final CalciumCarbonate CALCIUM_CARBONATE = register(new CalciumCarbonate());
    public static final CarbonDioxide CARBON_DIOXIDE = register(new CarbonDioxide());

    public static ChemicalElement get(String name) {
        return NAME_TO_ELEMENTS.get(name);
    }

    public static  <T extends ChemicalElement> T register(T target) {
        NAME_TO_ELEMENTS.put(target.getName(), target);
        return target;
    }
}
