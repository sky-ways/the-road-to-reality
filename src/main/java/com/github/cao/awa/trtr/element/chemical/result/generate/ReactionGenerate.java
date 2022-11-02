package com.github.cao.awa.trtr.element.chemical.result.generate;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.reaction.*;
import it.unimi.dsi.fastutil.objects.*;

import java.util.*;

public class ReactionGenerate {
    public static final ReactionGenerate NOT_GENERATE = new ReactionGenerate();
    private final List<ChemicalReactive> generated;

    public ReactionGenerate() {
        this.generated = Collections.emptyList();
    }

    public ReactionGenerate(List<ChemicalReactive> generated) {
        this.generated = generated;
    }
}
