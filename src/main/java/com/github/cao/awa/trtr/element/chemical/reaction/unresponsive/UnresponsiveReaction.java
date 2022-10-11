package com.github.cao.awa.trtr.element.chemical.reaction.unresponsive;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.reaction.*;
import com.github.cao.awa.trtr.element.chemical.reaction.type.*;

public class UnresponsiveReaction extends ChemicalReaction {
    public static final UnresponsiveReaction UNRESPONSIVE = new UnresponsiveReaction();

    @Override
    public void reaction(ReactionInformation information) {
        // do not anything
    }
}
