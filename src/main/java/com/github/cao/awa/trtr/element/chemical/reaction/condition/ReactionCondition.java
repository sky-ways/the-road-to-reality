package com.github.cao.awa.trtr.element.chemical.reaction.condition;

public abstract class ReactionCondition<T extends Number> {
    public final T value;

    public ReactionCondition(T value) {
        this.value = value;
    }

    public abstract boolean satisfy(T value);
}
