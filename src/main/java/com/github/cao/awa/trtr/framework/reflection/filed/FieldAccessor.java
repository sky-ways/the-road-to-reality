package com.github.cao.awa.trtr.framework.reflection.filed;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;

import java.lang.reflect.Field;
import java.util.Arrays;

public interface FieldAccessor {
    default <T> T get(Class<?> clazz, String field) {
        return EntrustEnvironment.cast(EntrustEnvironment.trys(() -> clazz.getField(field)
                                                                          .get(null)));
    }

    default <T> Class<T> type(Class<?> clazz, String field) {
        if (get(clazz,
                field
        ) instanceof Class c) {
            return c;
        }
        return EntrustEnvironment.cast(EntrustEnvironment.trys(() -> clazz.getField(field)
                                                                          .getType()));
    }

    default boolean has(Class<?> clazz, String field) {
        return Arrays.stream(clazz.getFields())
                     .map(Field :: getName)
                     .toList()
                     .contains(field);
    }
}
