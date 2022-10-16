package com.github.zhuaidadaya.rikaishinikui.handler.range;

import org.jetbrains.annotations.*;

public class OutOfRange<P> extends NumberRange<P> {
    private final P product;
    private final Number min;
    private final Number max;

    public OutOfRange(P product, Number min, Number max) {
        this.product = product;
        this.min = min;
        this.max = max;
    }

    public P getProduct() {
        return product;
    }

    public boolean approve(Number target) {
        return target.doubleValue() > max.doubleValue() || target.doubleValue() < min.doubleValue();
    }

    public boolean inRage(Number target) {
        return target.doubleValue() <= max.doubleValue() && target.doubleValue() >= min.doubleValue();
    }

    @Nullable
    public P product(Number target) {
        return approve(target) ? getProduct() : null;
    }
}
