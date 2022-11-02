package com.github.cao.awa.trtr.element.chemical.adapter;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.reaction.*;
import com.github.cao.awa.trtr.element.chemical.reaction.type.*;
import com.github.cao.awa.trtr.element.chemical.result.*;

public abstract class ReactionAdapter {
    private final ChemicalElement instance;

    public ReactionAdapter(ChemicalElement instance) {
        this.instance = instance;
    }

    public ChemicalElement getInstance() {
        return instance;
    }

    public abstract ChemicalReaction permit(ReactionInformation information);

    public ReactionResult adapter(ReactionInformation information) {
        return permit(information).reaction(information);
    }
}