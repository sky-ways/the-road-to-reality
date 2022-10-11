package com.github.cao.awa.trtr.element.chemical.reaction.action;

import com.github.cao.awa.trtr.element.chemical.*;

public class GenerateChemicalElement extends ReactionAction {
    private final ChemicalElement element;

    public GenerateChemicalElement(ChemicalElement element) {
        this.element = element;
    }

    public ChemicalElement getElement() {
        return element;
    }
}
