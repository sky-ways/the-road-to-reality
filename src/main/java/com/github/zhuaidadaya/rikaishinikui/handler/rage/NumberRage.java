package com.github.zhuaidadaya.rikaishinikui.handler.rage;

import java.util.*;

public abstract class NumberRage<P> {
    public abstract boolean inRage(Number target);
    public abstract boolean approve(Number target);
    public abstract P product(Number target);

    public static <Y> NumberRage<Y> limit(Y product, Number min, Number max) {
        return Objects.equals(min, max) ? new AbsoluteRage<>(product, min.intValue()) : new DistanceRage<>(product, min, max);
    }

    public static <Y> AbsoluteRage<Y> absolute(Y product, Number value) {
        return new AbsoluteRage<>(product, value);
    }

    public static <Y> OutOfRage<Y> out(Y product, Number min, Number max) {
        return new OutOfRage<>(product, min, max);
    }

    public static <Y> OutOfRage<Y> over(Y product, Number max) {
        return new OutOfRage<>(product, 0, Math.abs(max.doubleValue()));
    }

    public static <Y> OutOfRage<Y> behind(Y product, Number min) {
        return new OutOfRage<>(product, -Math.abs(min.doubleValue()), 0);
    }

    public static <Y> DistanceRage<Y> distance(Y product, Number min, Number max) {
        return new DistanceRage<>(product, min, max);
    }
}
