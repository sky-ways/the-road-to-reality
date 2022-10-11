package com.github.cao.awa.trtr.element.chemical.elements.carbon;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.adapter.*;
import com.github.cao.awa.trtr.element.chemical.elements.carbon.reaction.adapter.*;
import com.github.cao.awa.trtr.element.chemical.elements.carbon.reaction.combination.*;
import com.github.cao.awa.trtr.element.chemical.reaction.*;
import com.github.cao.awa.trtr.element.chemical.reaction.type.*;

public class CarbonElement extends ChemicalElement {
    public CarbonElement() {
        super(new CarbonReactionAdapter());
    }

    @Override
    public String getName() {
        return "C";
    }
}
