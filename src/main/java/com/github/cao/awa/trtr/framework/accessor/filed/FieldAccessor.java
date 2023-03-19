package com.github.cao.awa.trtr.framework.accessor.filed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Implement for access to special static fields in class.
 *
 * @author cao_awa
 * @since 1.0.0
 */
public interface FieldAccessor {
    Logger LOGGER = LogManager.getLogger("FieldAccessor");

    default <T> T get(Class<?> clazz, String field) {
        // Calls util.
        return FieldAccess.get(clazz,
                               field
        );
    }

    default <T> Class<T> type(Class<?> clazz, String field) {
        // Calls util.
        return FieldAccess.type(clazz,
                                field
        );
    }

    default Field field(Class<?> clazz, String field) {
        // Calls util.
        return FieldAccess.getField(clazz,
                                    field
        );
    }

    default Field field(Object obj, String field) {
        // Calls util.
        return FieldAccess.getField(obj.getClass(),
                                    field
        );
    }

    default boolean has(Class<?> clazz, String field) {
        // Get all fields, to find target in these fields.
        return Arrays.stream(clazz.getFields())
                     .map(Field :: getName)
                     .toList()
                     .contains(field);
    }
}
