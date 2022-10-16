package com.github.zhuaidadaya.rikaishinikui.handler.range;

import java.util.*;

public class AbsoluteRange<P> extends NumberRange<P> {
    private final P product;
    private final Number value;

    public AbsoluteRange(P product, Number value) {
        this.product = product;
        this.value = value;
    }

    public P getProduct() {
        return product;
    }

    @Override
    public boolean inRage(Number target) {
        return Objects.equals(target, value);
    }

    @Override
    public boolean approve(Number target) {
        return Objects.equals(target, value);
    }

    @Override
    public P product(Number target) {
        return approve(target) ? getProduct() : null;
    }
}
