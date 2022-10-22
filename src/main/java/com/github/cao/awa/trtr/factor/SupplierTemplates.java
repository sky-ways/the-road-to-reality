package com.github.cao.awa.trtr.factor;

import java.util.function.*;

public final class SupplierTemplates {
    private static final Supplier<String> EMPTY_STRING = () -> "";

    public static Supplier<String> emptyString() {
        return EMPTY_STRING;
    }

    public static <T> Supplier<T> createNull() {
        return () -> null;
    }
}
