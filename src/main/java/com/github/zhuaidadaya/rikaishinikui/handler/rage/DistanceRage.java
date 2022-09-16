package com.github.zhuaidadaya.rikaishinikui.handler.rage;

import org.jetbrains.annotations.*;

import java.util.*;

public class DistanceRage<P, T extends Number> extends NumberRage<P> {
    private final Random random;
    private final P product;
    private final T min;
    private final T max;
    private final boolean doRandom;

    public DistanceRage(P product, T min, T max) {
        this.product = product;
        this.min = min;
        this.max = max;
        this.random = null;
        doRandom = false;
    }

    public DistanceRage(P product, T min, T max, boolean doRandom) {
        this.product = product;
        this.min = min;
        this.max = max;
        this.doRandom = doRandom;
        this.random = doRandom ? new Random() : null;
    }

    public P getProduct() {
        return product;
    }

    public boolean approve(Number target) {
        if (inRage(target)) {
            if (target.intValue() == max.intValue()) {
                return true;
            }
            if (doRandom) {
                int range = max.intValue() - min.intValue();
                assert random != null;
                return random.nextInt(range) == range / 2;
            }
        }
        return false;
    }

    public boolean inRage(Number target) {
        return target.doubleValue() <= max.doubleValue() && target.doubleValue() >= min.doubleValue();
    }

    @Nullable
    public P product(Number target) {
        return approve(target) ? getProduct() : null;
    }
}
