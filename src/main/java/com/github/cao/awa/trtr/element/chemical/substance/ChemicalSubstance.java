package com.github.cao.awa.trtr.element.chemical.substance;

import com.github.cao.awa.trtr.element.chemical.adapter.*;
import com.github.cao.awa.trtr.element.chemical.reaction.*;
import com.github.cao.awa.trtr.element.chemical.reaction.type.*;

public abstract class ChemicalSubstance implements ChemicalReactive {
    private final ReactionAdapter adapter;

    public ChemicalSubstance(ReactionAdapter adapter) {
        this.adapter = adapter;
    }

    public void reaction(ReactionInformation information) {
        getAdapter().adapter(information);
    }

    public ReactionAdapter getAdapter() {
        return adapter;
    }
}
