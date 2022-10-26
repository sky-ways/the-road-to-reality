package com.github.cao.awa.trtr.element.chemical;

import com.github.cao.awa.trtr.element.chemical.adapter.*;
import com.github.cao.awa.trtr.element.chemical.reaction.*;
import com.github.cao.awa.trtr.element.chemical.reaction.type.*;

public abstract class ChemicalElement implements ChemicalReactive {
    private final ReactionAdapter adapter;

    public ChemicalElement(ReactionAdapter adapter) {
        this.adapter = adapter;
    }

    public void reaction(ReactionInformation information) {
        getAdapter().adapter(information);
    }

    public ReactionAdapter getAdapter() {
        return adapter;
    }

    public abstract int getAtomicNumber();
}
