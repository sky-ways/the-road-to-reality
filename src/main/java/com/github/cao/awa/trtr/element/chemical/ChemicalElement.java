package com.github.cao.awa.trtr.element.chemical;

import com.github.cao.awa.trtr.element.chemical.adapter.*;
import com.github.cao.awa.trtr.element.chemical.reaction.*;
import com.github.cao.awa.trtr.element.chemical.reaction.type.*;
import com.github.cao.awa.trtr.element.chemical.result.*;

public abstract class ChemicalElement implements ChemicalReactive {
    private final ReactionAdapter adapter;

    public ChemicalElement(ReactionAdapter adapter) {
        this.adapter = adapter;
    }

    public ReactionResult reaction(ReactionInformation information) {
       return getAdapter().adapter(information);
    }

    public ReactionAdapter getAdapter() {
        return adapter;
    }

    public abstract int getAtomicNumber();
}
