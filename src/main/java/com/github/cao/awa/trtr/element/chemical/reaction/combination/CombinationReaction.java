package com.github.cao.awa.trtr.element.chemical.reaction.combination;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.reaction.*;
import com.github.cao.awa.trtr.element.chemical.reaction.action.*;
import com.github.cao.awa.trtr.element.chemical.reaction.type.*;
import it.unimi.dsi.fastutil.objects.*;

import java.util.*;
import java.util.function.*;

public abstract class CombinationReaction extends ChemicalReaction  {
    public final Map<ChemicalElement, Function<ChemicalElement, List<ReactionAction>>> actions = new Object2ObjectOpenHashMap<>();

    public CombinationReaction() {
        register();
    }

    public abstract void register();
}
