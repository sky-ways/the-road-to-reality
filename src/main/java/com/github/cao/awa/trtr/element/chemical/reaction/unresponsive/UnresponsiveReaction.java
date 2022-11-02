package com.github.cao.awa.trtr.element.chemical.reaction.unresponsive;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.reaction.*;
import com.github.cao.awa.trtr.element.chemical.reaction.type.*;
import com.github.cao.awa.trtr.element.chemical.result.*;

public class UnresponsiveReaction extends ChemicalReaction {
    public static final UnresponsiveReaction UNRESPONSIVE = new UnresponsiveReaction();

    @Override
    public ReactionResult reaction(ReactionInformation information) {
        return ReactionResult.NOT_REACH;
    }
}
