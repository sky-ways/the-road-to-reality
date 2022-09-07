package com.github.zhuaidadaya.rikaishinikui.handler.universal.operational;

import java.util.function.*;

public abstract class Operational<T> {
    protected Consumer<T> callback = t -> {};

    public abstract T get();
    public abstract T set(T target);
    public abstract Operational<T> callback(Consumer<T> action);
}
