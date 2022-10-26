package com.github.cao.awa.trtr.element.chemical.reaction;

import com.github.cao.awa.trtr.element.chemical.reaction.type.*;

public interface ChemicalReactive {
    void reaction(ReactionInformation information);

    String getName();
}
