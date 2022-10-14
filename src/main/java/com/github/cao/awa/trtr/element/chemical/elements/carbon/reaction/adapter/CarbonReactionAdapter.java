package com.github.cao.awa.trtr.element.chemical.elements.carbon.reaction.adapter;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.adapter.*;
import com.github.cao.awa.trtr.element.chemical.reaction.*;
import com.github.cao.awa.trtr.element.chemical.reaction.type.*;

public class CarbonReactionAdapter extends ReactionAdapter {
    public CarbonReactionAdapter() {
        super(ChemicalElements.ELEMENT_CARBON);
    }

    @Override
    public ChemicalReaction permit(ReactionInformation information) {
        if (information.temperature() > 114514) {
            return CombinationReactions.reactions.get(getInstance());
        }
        return ChemicalReaction.unresponsive();
    }
}
