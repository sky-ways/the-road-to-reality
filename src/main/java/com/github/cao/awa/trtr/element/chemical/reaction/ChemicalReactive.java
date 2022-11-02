package com.github.cao.awa.trtr.element.chemical.reaction;

import com.github.cao.awa.trtr.element.chemical.reaction.type.*;
import com.github.cao.awa.trtr.element.chemical.result.*;

public interface ChemicalReactive {
    ReactionResult reaction(ReactionInformation information);

    String getName();
}
