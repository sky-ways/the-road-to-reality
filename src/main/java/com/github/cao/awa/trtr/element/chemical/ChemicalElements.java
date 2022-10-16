package com.github.cao.awa.trtr.element.chemical;

import com.github.cao.awa.trtr.element.chemical.elements.aluminum.*;
import com.github.cao.awa.trtr.element.chemical.elements.calcium.carbonate.*;
import com.github.cao.awa.trtr.element.chemical.elements.carbon.*;
import com.github.cao.awa.trtr.element.chemical.elements.flerovium.*;
import com.github.cao.awa.trtr.element.chemical.elements.iron.*;
import com.github.cao.awa.trtr.element.chemical.elements.nitrogen.*;
import com.github.cao.awa.trtr.element.chemical.elements.oxygen.*;
import it.unimi.dsi.fastutil.objects.*;

import java.util.*;

public class ChemicalElements {
    public static final Map<String, ChemicalElement> NAME_TO_ELEMENTS = new Object2ObjectOpenHashMap<>();

    public static final OxygenElement ELEMENT_OXYGEN = register(new OxygenElement());
    public static final IronElement ELEMENT_IRON = register(new IronElement());
    public static final CarbonElement ELEMENT_CARBON = register(new CarbonElement());
    public static final FleroviumElement ELEMENT_FLEROVIUM = register(new FleroviumElement());
    public static final AluminumElement ELEMENT_ALUMINUM = register(new AluminumElement());
    public static final NitrogenElement ELEMENT_NITROGEN = register(new NitrogenElement());

    public static final Oxygen OXYGEN = register(new Oxygen());
    public static final Nitrogen NITROGEN = register(new Nitrogen());

    public static final CalciumCarbonate CALCIUM_CARBONATE = register(new CalciumCarbonate());

    public static ChemicalElement get(String name) {
        return NAME_TO_ELEMENTS.get(name);
    }

    public static  <T extends ChemicalElement> T register(T target) {
        NAME_TO_ELEMENTS.put(target.getName(), target);
        return target;
    }
}
