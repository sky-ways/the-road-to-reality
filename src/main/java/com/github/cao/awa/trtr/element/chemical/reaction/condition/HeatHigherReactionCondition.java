package com.github.cao.awa.trtr.element.chemical.reaction.condition;

public class HeatHigherReactionCondition extends ReactionCondition<Double> {
    public HeatHigherReactionCondition(Double value) {
        super(value);
    }

    @Override
    public boolean satisfy(Double value) {
        return value >= this.value;
    }
}
