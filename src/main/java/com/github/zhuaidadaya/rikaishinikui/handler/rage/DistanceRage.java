package com.github.zhuaidadaya.rikaishinikui.handler.rage;

import org.jetbrains.annotations.*;

import java.util.*;

public class DistanceRage<P> extends NumberRage<P> {
    private final P product;
    private final Number min;
    private final Number max;

    public DistanceRage(P product, Number min, Number max) {
        this.product = product;
        this.min = min;
        this.max = max;
    }

    public P getProduct() {
        return product;
    }

    public boolean approve(Number target) {
        return inRage(target) && target.intValue() == max.intValue();
    }

    public boolean inRage(Number target) {
        return target.doubleValue() <= max.doubleValue() && target.doubleValue() >= min.doubleValue();
    }

    @Nullable
    public P product(Number target) {
        return approve(target) ? getProduct() : null;
    }
}
