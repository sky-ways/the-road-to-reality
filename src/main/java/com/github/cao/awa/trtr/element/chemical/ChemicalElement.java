package com.github.cao.awa.trtr.element.chemical;

import com.github.cao.awa.trtr.element.chemical.adapter.*;
import com.github.cao.awa.trtr.element.chemical.reaction.type.*;

public abstract class ChemicalElement {
    private final ReactionAdapter adapter;

    public ChemicalElement(ReactionAdapter adapter) {
        this.adapter = adapter;
    }

    public void reaction(ReactionInformation information) {
        getAdapter().adapter(information);
    }

    public abstract String getName();

    public ReactionAdapter getAdapter() {
        return adapter;
    }

    public abstract int getAtomicNumber();
}
