package com.github.zhuaidadaya.rikaishinikui.handler.rage.table;

import com.github.zhuaidadaya.rikaishinikui.handler.rage.*;
import it.unimi.dsi.fastutil.objects.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

public class RageTable<P, R extends NumberRage<P>> {
    private final List<R> rages = new ObjectArrayList<>();

    @SafeVarargs
    private RageTable(R... rages) {
        this.rages.addAll(List.of(rages));
    }

    @SafeVarargs
    public static <T, R extends NumberRage<T>> RageTable<T, R> of(R... rages) {
        return new RageTable<>(rages);
    }

    @Nullable
    public NumberRage<P> first() {
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

    public void merge(RageTable<P, R> table) {
        rages.addAll(table.rages);
    }
}
