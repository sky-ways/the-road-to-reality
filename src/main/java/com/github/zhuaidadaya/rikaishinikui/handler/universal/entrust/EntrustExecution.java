package com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.function.*;

import java.util.function.*;

/**
 * Entrust to a no exception environment.
 *
 * @author cao_awa
 * @since 1.0.0
 */
public class EntrustExecution {
    public static <T> void notNull(T target, Consumer<T> action) {
        if (target != null) {
            action.accept(target);
        }
    }

    public static <T> void ifNull(T target, Consumer<T> action) {
        if (target == null) {
            action.accept(null);
        }
    }

    public static <T> void operation(T target, Consumer<T> action) {
        action.accept(target);
    }

    public static <T> void tryTemporary(ExceptingTemporary action, Temporary whenException) {
        try {
            action.apply();
        } catch (Throwable e) {
            whenException.apply();
        }
    }

    public static <T> void tryTemporary(ExceptingTemporary action, Consumer<Throwable> whenException) {
        try {
            action.apply();
        } catch (Throwable e) {
            whenException.accept(e);
        }
    }

    public static <T> void tryTemporary(ExceptingTemporary action) {
        try {
            action.apply();
        } catch (Throwable e) {

        }
    }

    public static <T> T tryTemporary(ExceptingSupplier<T> action) {
        try {
            return action.get();
        } catch (Throwable e) {
            return null;
        }
    }

    /**
     * Entrust for supplier.
     *
     * @param input Source supplier
     * @param <R> Result type
     * @return Result
     */
    public static <R> R result(ExceptingSupplier<R> input) {
        return tryTemporary(input);
    }

    /**
     * Entrust for function.
     *
     * @param input Source function
     * @param <I> Input type
     * @param <R> Result type
     * @return No exception environment
     */
    public static <I, R> Function<I, R> function(ExceptingFunction<I, R> input) {
        return s -> tryTemporary(() -> input.apply(s));
    }
}

