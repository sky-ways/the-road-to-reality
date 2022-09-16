package com.github.zhuaidadaya.rikaishinikui.handler.rage;

import java.util.*;

public abstract class NumberRage<P> {
    public abstract boolean inRage(Number target);
    public abstract boolean approve(Number target);
    public abstract P product(Number target);

    public static <Y, X extends Number> NumberRage<Y> create(Y product, X min, X max) {
        return Objects.equals(min, max) ? new AbsoluteRage<>(product, min.intValue()) : new DistanceRage<>(product, min, max);
    }

    public static <Y, X extends Number> AbsoluteRage<Y> absolute(Y product, Integer value) {
        return new AbsoluteRage<>(product, value);
    }

    public static <Y, X extends Number> DistanceRage<Y, X> distance(Y product, X min, X max) {
        return new DistanceRage<>(product, min, max);
    }
}
