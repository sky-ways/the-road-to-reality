package com.github.cao.awa.trtr.element.chemical.result.generate;

import com.github.cao.awa.trtr.element.chemical.reaction.*;

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
