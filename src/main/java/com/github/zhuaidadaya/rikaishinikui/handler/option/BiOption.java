package com.github.zhuaidadaya.rikaishinikui.handler.option;

public record BiOption<T>(T t1, T t2) {
    public static <X> BiOption<X> of(X x1, X x2) {
        return new BiOption<>(x1, x2);
    }
}
