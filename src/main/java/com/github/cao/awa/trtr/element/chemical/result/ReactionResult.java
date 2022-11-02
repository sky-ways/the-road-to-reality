package com.github.cao.awa.trtr.element.chemical.result;

import com.github.cao.awa.trtr.element.chemical.result.generate.*;

public class ReactionResult {
    public static final ReactionResult NOT_REACH = new ReactionResult();
    private final ReactionGenerate generated;

    public ReactionResult() {
        this.generated = ReactionGenerate.NOT_GENERATE;
    }

    public ReactionResult(ReactionGenerate generated) {
        this.generated = generated;
    }

    public ReactionGenerate getGenerated() {
        return generated;
    }
}
