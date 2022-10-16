package com.github.zhuaidadaya.rikaishinikui.handler.range;

import java.util.*;

public abstract class NumberRange<P> {
    public abstract boolean inRage(Number target);
    public abstract boolean approve(Number target);
    public abstract P product(Number target);

    public static <Y> NumberRange<Y> limit(Y product, Number min, Number max) {
        return Objects.equals(min, max) ? new AbsoluteRange<>(product, min.intValue()) : new DistanceRange<>(product, min, max);
    }

    public static <Y> AbsoluteRange<Y> absolute(Y product, Number value) {
        return new AbsoluteRange<>(product, value);
    }

    public static <Y> OutOfRange<Y> out(Y product, Number min, Number max) {
        return new OutOfRange<>(product, min, max);
    }

    public static <Y> OutOfRange<Y> over(Y product, Number max) {
        return new OutOfRange<>(product, 0, Math.abs(max.doubleValue()));
    }

    public static <Y> OutOfRange<Y> behind(Y product, Number min) {
        return new OutOfRange<>(product, -Math.abs(min.doubleValue()), 0);
    }

    public static <Y> DistanceRange<Y> distance(Y product, Number min, Number max) {
        return new DistanceRange<>(product, min, max);
    }
}
