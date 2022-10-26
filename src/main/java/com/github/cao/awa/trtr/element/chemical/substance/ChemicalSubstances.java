package com.github.cao.awa.trtr.element.chemical.substance;

import com.github.cao.awa.trtr.element.chemical.substance.compound.calcium.carbonate.*;
import com.github.cao.awa.trtr.element.chemical.substance.compound.carbon.dioxide.*;
import it.unimi.dsi.fastutil.objects.*;

import java.util.*;

public class ChemicalSubstances {
    public static final Map<String, ChemicalSubstance> NAME_TO_ELEMENTS = new Object2ObjectOpenHashMap<>();

//    public static final Nitrogen NITROGEN = register(new Nitrogen());

//    public static final Oxygen OXYGEN = register(new Oxygen());
    public static final CalciumCarbonate CALCIUM_CARBONATE = register(new CalciumCarbonate());
    public static final CarbonDioxide CARBON_DIOXIDE = register(new CarbonDioxide());

    public static ChemicalSubstance get(String name) {
        return NAME_TO_ELEMENTS.get(name);
    }

    public static  <T extends ChemicalSubstance> T register(T target) {
        NAME_TO_ELEMENTS.put(target.getName(), target);
        return target;
    }
}
