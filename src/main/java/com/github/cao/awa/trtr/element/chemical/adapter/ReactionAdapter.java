package com.github.cao.awa.trtr.element.chemical.adapter;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.reaction.*;
import com.github.cao.awa.trtr.element.chemical.reaction.action.*;
import com.github.cao.awa.trtr.element.chemical.reaction.type.*;

public abstract class ReactionAdapter {
    private final ChemicalElement instance;

    public ReactionAdapter(ChemicalElement instance) {
        this.instance = instance;
    }

    public ChemicalElement getInstance() {
        return instance;
    }

    public abstract ChemicalReaction permit(ReactionInformation information);

    public void adapter(ReactionInformation information) {
        permit(information).reaction(information);
    }
}
