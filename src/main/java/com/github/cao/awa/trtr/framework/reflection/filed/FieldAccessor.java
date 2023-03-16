package com.github.cao.awa.trtr.framework.reflection.filed;

import com.github.cao.awa.trtr.framework.exception.NotStaticFieldException;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.ExceptionEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public interface FieldAccessor {
    Logger LOGGER = LogManager.getLogger("FieldAccessor");

    default <T> T get(Class<?> clazz, String field) {
        ensureAccessible(clazz,
                         field
        );
        return EntrustEnvironment.cast(EntrustEnvironment.trys(() -> clazz.getField(field)
                                                                          .get(null)));
    }

    default <T> Class<T> type(Class<?> clazz, String field) {
        ensureAccessible(clazz,
                         field
        );
        return EntrustEnvironment.cast(EntrustEnvironment.trys(() -> {
            if (get(clazz,
                    field
            ) instanceof Class<?> c) {
                return c;
            }
            return clazz.getField(field)
                        .getType();
        }));
    }

    default void ensureAccessible(Class<?> clazz, String field) throws NotStaticFieldException {
        Field f = EntrustEnvironment.trys(() -> clazz.getField(field));
        if (f == null) {
            return;
        }
        ensureAccessible(f);
    }

    default void ensureAccessible(Field field) throws NotStaticFieldException {
        if (! Modifier.isStatic(field.getModifiers())) {
                LOGGER.warn("The field '{}' in '{}' type is missing static modifier, ignored",
                            field.getName(),
                            field.getType()
                );
            throw new NotStaticFieldException("The field with @Auto automatic IoC should be static");
        }
        if (! field.canAccess(null)) {
            if (field.trySetAccessible()) {
                return;
            }
            throw new IllegalStateException("The field '" + field.getName() + "' with @Auto automatic IoC is not accessible");
        }
    }

    default boolean has(Class<?> clazz, String field) {
        return Arrays.stream(clazz.getFields())
                     .map(Field :: getName)
                     .toList()
                     .contains(field);
    }
}
