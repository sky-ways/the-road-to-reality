package com.github.zhuaidadaya.rikaishinikui.handler.range.table;

import com.github.zhuaidadaya.rikaishinikui.handler.range.*;
import it.unimi.dsi.fastutil.objects.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

public class RangeTable<P, R extends NumberRange<P>> {
    private final List<R> rages = new ObjectArrayList<>();

    @SafeVarargs
    private RangeTable(R... rages) {
        this.rages.addAll(List.of(rages));
    }

    @SafeVarargs
    public static <T, R extends NumberRange<T>> RangeTable<T, R> of(R... rages) {
        return new RangeTable<>(rages);
    }

    @Nullable
    public NumberRange<P> first() {
        return rages.size() > 0 ? rages.get(0) : null;
    }

    public void approve(Number target, Consumer<P> action) {
        for (R rage : rages) {
            P product = rage.product(target);
            if (product == null) {
                continue;
            }
            action.accept(product);
        }
    }

    public int size() {
        return rages.size();
    }

    public List<R> getRages() {
        return rages;
    }

    public void merge(RangeTable<P, R> table) {
        rages.addAll(table.rages);
    }
}
