package com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.function.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.receptacle.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

/**
 * Entrust to a no exception environment.
 *
 * @author cao_awa
 * @since 1.0.0
 */
public class EntrustEnvironment {
    public static <T> T getNotNull(T target, @NotNull T defaultValue) {
        return target == null ? defaultValue : target;
    }

    public static <T> void ifNotNull(T target, Consumer<T> action) {
        if (target != null) {
            action.accept(target);
        }
    }

    public static <T> void ifNull(T target, Consumer<T> action) {
        if (target == null) {
            action.accept(null);
        }
    }

    public static <T> T operation(T target, Consumer<T> action) {
        action.accept(target);
        return target;
    }

    public static <T> T trying(ExceptingSupplier<T> action) {
        try {
            return action.get();
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T trying(ExceptingSupplier<T> action, Supplier<T> actionWhenException) {
        try {
            return action.get();
        } catch (Exception e) {
            return actionWhenException.get();
        }
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

    /**
     * Entrust for supplier.
     *
     * @param input
     *         Source supplier
     * @param <R>
     *         Result type
     * @return Result
     *
     * @author cao_awa
     * @since 1.0.0
     */
    public static <R> R result(ExceptingSupplier<R> input) {
        return tryTemporary(input);
    }

    public static <T> T tryTemporary(ExceptingSupplier<T> action) {
        try {
            return action.get();
        } catch (Throwable e) {
            return null;
        }
    }

    /**
     * Entrust for function.
     *
     * @param input
     *         Source function
     * @param <I>
     *         Input type
     * @param <R>
     *         Result type
     * @return No exception environment
     *
     * @author cao_awa
     * @since 1.0.0
     */
    public static <I, R> Function<I, R> function(ExceptingFunction<I, R> input) {
        return s -> tryTemporary(() -> input.apply(s));
    }

    /**
     * Compare objects, do action if equals.
     *
     * @param source
     *         Source
     * @param target
     *         Target
     * @param action
     *         Action when equals
     * @author cao_awa
     * @since 1.0.0
     */
    public static void equals(Object source, Object target, ExceptingTemporary action) {
        if (Objects.equals(
                source,
                target
        )) {
            tryTemporary(action);
        }
    }

    public static <T> void tryTemporary(ExceptingTemporary action) {
        try {
            action.apply();
        } catch (Throwable e) {

        }
    }

    /**
     * Compare objects, do action if equals.
     *
     * @param source
     *         Source
     * @param target
     *         Target
     * @param action
     *         Action when equals
     * @param orElse
     *         Action when no equals
     * @author cao_awa
     * @since 1.0.0
     */
    public static void equals(Object source, Object target, ExceptingTemporary action, ExceptingTemporary orElse) {
        if (Objects.equals(
                source,
                target
        )) {
            tryTemporary(action);
        } else {
            tryTemporary(orElse);
        }
    }

    /**
     * Compare objects, do action if equals.
     *
     * @param source
     *         Source
     * @param target1
     *         Target 1
     * @param action1
     *         Action when equals target 1
     * @param target2
     *         Target 2
     * @param action2
     *         Action when equals target 2
     * @author cao_awa
     * @since 1.0.0
     */
    public static void equals(Object source, Object target1, ExceptingTemporary action1, Object target2, ExceptingTemporary action2) {
        if (Objects.equals(
                source,
                target1
        )) {
            tryTemporary(action1);
        } else if (Objects.equals(
                source,
                target2
        )) {
            tryTemporary(action2);
        }
    }

    /**
     * Compare objects, do action if equals.
     *
     * @param source
     *         Source
     * @param target1
     *         Target 1
     * @param action1
     *         Action when equals target 1
     * @param target2
     *         Target 2
     * @param action2
     *         Action when equals target 2
     * @param orElse
     *         Action when no equals
     * @author cao_awa
     * @since 1.0.0
     */
    public static void equals(Object source, Object target1, ExceptingTemporary action1, Object target2, ExceptingTemporary action2, ExceptingTemporary orElse) {
        if (Objects.equals(
                source,
                target1
        )) {
            tryTemporary(action1);
        } else if (Objects.equals(
                source,
                target2
        )) {
            tryTemporary(action2);
        } else {
            tryTemporary(orElse);
        }
    }

    public static <T> T select(T[] array, int index) {
        return array.length > index ? array[index] : array[array.length - 1];
    }

    public static <T> T select(T[] array, Random random) {
        return array[random.nextInt(array.length)];
    }

    public static <T> T select(List<T> list, Random random) {
        return list.get(random.nextInt(list.size()));
    }

    public static <T> T desert(List<T> list, Random random) {
        T result = list.get(random.nextInt(list.size()));
        list.remove(result);
        return result;
    }

    /**
     * Create a receptacle, do something operation and return value of this receptacle.
     *
     * @param action
     *         Operation
     * @param <T>
     *         Type
     * @return Value of receptacle
     *
     * @author cao_awa
     * @since 1.0.0
     */
    public static <T> T receptacle(ExceptingConsumer<Receptacle<T>> action) {
        Receptacle<T> receptacle = Receptacle.of();
        tryTemporary(() -> action.accept(receptacle));
        return receptacle.get();
    }
}

