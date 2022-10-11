package com.github.cao.awa.trtr.element.chemical.reaction;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.reaction.type.*;
import com.github.cao.awa.trtr.element.chemical.reaction.unresponsive.*;

public abstract class ChemicalReaction {
    public static ChemicalReaction unresponsive() {
        return UnresponsiveReaction.UNRESPONSIVE;
    }

    public abstract void reaction(ReactionInformation information);
}
