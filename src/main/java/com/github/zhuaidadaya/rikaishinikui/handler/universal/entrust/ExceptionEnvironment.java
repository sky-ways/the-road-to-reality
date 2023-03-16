package com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.function.ExceptingRunnable;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.function.ExceptingSupplier;

public class ExceptionEnvironment {
    public static void unhandled(ExceptingRunnable runnable) {
        try {
            runnable.apply();
        } catch (Exception e) {
            if (e instanceof RuntimeException re) {
                throw re;
            }
            throw new IllegalStateException(e);
        }
    }

    public static <T> T unhandled(ExceptingSupplier<T> runnable) {
        try {
            return runnable.get();
        } catch (Exception e) {
            if (e instanceof RuntimeException re) {
                throw re;
            }
            throw new IllegalStateException(e);
        }
    }

    public static <T, E extends Exception> T nullWhen(ExceptingSupplier<T> supplier, Class<E> breakWhen) throws Exception {
        try {
            return supplier.get();
        } catch (Exception e) {
            if (breakWhen.isAssignableFrom(e.getClass())) {
                return null;
            }
            throw e;
        }
    }
}
