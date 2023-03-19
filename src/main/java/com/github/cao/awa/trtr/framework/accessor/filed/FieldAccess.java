package com.github.cao.awa.trtr.framework.accessor.filed;

import com.github.cao.awa.trtr.framework.exception.NotStaticFieldException;
import com.github.cao.awa.trtr.framework.exception.SetFinalFieldException;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Access the static fields in class.
 *
 * @author cao_awa
 * @author 草二号机
 * @since 1.0.0
 */
public class FieldAccess {
    private static final Logger LOGGER = LogManager.getLogger("FieldAccessor");

    public static <T> T get(Class<?> clazz, String fieldName) {
        // Make the field be accessible.
        ensureAccessible(clazz,
                         fieldName
        );
        // Access the field and cast it to result type.
        // The cast is necessary.
        return EntrustEnvironment.cast(EntrustEnvironment.trys(() -> clazz.getField(fieldName)
                                                                          .get(null)));
    }

    public static Field getField(Class<?> clazz, String fieldName) {
        // Make the field be accessible.
        ensureAccessible(clazz,
                         fieldName
        );
        // Access the field.
        return EntrustEnvironment.trys(() -> clazz.getField(fieldName));
    }

    public static <T> Class<T> type(Class<?> clazz, String fieldName) {
        // Make the field be accessible.
        ensureAccessible(clazz,
                         fieldName
        );
        // Access the field and cast it to result type.
        // The cast is necessary.
        return EntrustEnvironment.cast(EntrustEnvironment.trys(() -> {
            // We need a type, so directly return it if is.
            if (get(clazz,
                    fieldName
            ) instanceof Class<?> c) {
                return c;
            }
            // If not, get the field and access it type to return.
            return clazz.getField(fieldName)
                        .getType();
        }));
    }

    public static Field set(Field field, Object value) {
        // Cannot access not static field.
        // And cannot set final field.
        int modifiers = field.getModifiers();
        if (! Modifier.isStatic(modifiers)) {
            throw new NotStaticFieldException();
        } else if (Modifier.isFinal(modifiers)) {
            throw new SetFinalFieldException();
        }

        EntrustEnvironment.trys(() -> {
            // The field is static modified, so obj should null.
            field.set(null,
                      value
            );
        });
        return field;
    }

    public static Field set(Class<?> clazz, String fieldName, Object value) {
        try {
            // Get the field and set it.
            Field field = clazz.getField(fieldName);
            return set(field,
                       value
            );
        } catch (Exception e) {
            return null;
        }
    }

    public static void ensureAccessible(Class<?> clazz, String field) throws NotStaticFieldException {
        // Get the field, and ensure it present for next step access;
        Field f = EntrustEnvironment.trys(() -> clazz.getField(field));
        if (f == null) {
            return;
        }
        ensureAccessible(f);
    }

    public static void ensureAccessible(Field field) throws NotStaticFieldException {
        // Cannot access not static field.
        if (! Modifier.isStatic(field.getModifiers())) {
            LOGGER.warn("The field '{}' in '{}' type is missing static modifier, ignored",
                        field.getName(),
                        field.getType()
            );
            throw new NotStaticFieldException("The field with @Auto automatic IoC should be static");
        }
        // Modifier maybe private or without declarations.
        // Need to make it be accessible.
        // If unable to access, then throw an exception for notice this error.
        if (! field.canAccess(null)) {
            if (field.trySetAccessible()) {
                return;
            }
            throw new IllegalStateException("The field '" + field.getName() + "' with @Auto automatic IoC is not accessible");
        }
    }
}
