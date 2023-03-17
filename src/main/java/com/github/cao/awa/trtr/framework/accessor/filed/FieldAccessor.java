package com.github.cao.awa.trtr.framework.accessor.filed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Arrays;

public interface FieldAccessor {
    Logger LOGGER = LogManager.getLogger("FieldAccessor");

    default <T> T get(Class<?> clazz, String field) {
        return FieldAccess.get(clazz,
                               field
        );
    }

    default <T> Class<T> type(Class<?> clazz, String field) {
        return FieldAccess.type(clazz,
                                field
        );
    }

    default boolean has(Class<?> clazz, String field) {
        return Arrays.stream(clazz.getFields())
                     .map(Field :: getName)
                     .toList()
                     .contains(field);
    }
}
