package com.github.cao.awa.trtr.element.chemical;

import com.github.cao.awa.trtr.element.chemical.elements.aluminum.*;
import com.github.cao.awa.trtr.element.chemical.elements.carbon.*;
import com.github.cao.awa.trtr.element.chemical.elements.flerovium.*;
import com.github.cao.awa.trtr.element.chemical.elements.iron.*;
import com.github.cao.awa.trtr.element.chemical.elements.oxygen.*;
import it.unimi.dsi.fastutil.objects.*;

import java.util.*;

public class ChemicalElements {
    public static final Map<String, ChemicalElement> NAME_TO_ELEMENTS = new Object2ObjectOpenHashMap<>();

    public static final OxygenElement OXYGEN = register(new OxygenElement());
    public static final IronElement IRON = register(new IronElement());
    public static final CarbonElement CARBON = register(new CarbonElement());
    public static final FleroviumElement FLEROVIUM = register(new FleroviumElement());
    public static final AluminumElement ALUMINUM = register(new AluminumElement());

    public static ChemicalElement get(String name) {
        return NAME_TO_ELEMENTS.get(name);
    }

    public static  <T extends ChemicalElement> T register(T target) {
        NAME_TO_ELEMENTS.put(target.getName(), target);
        return target;
    }
}
