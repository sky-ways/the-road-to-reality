package com.github.cao.awa.trtr.framework.accessor.filed;

import com.github.cao.awa.trtr.framework.exception.NotStaticFieldException;
import com.github.cao.awa.trtr.framework.exception.SetFinalFieldException;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldAccess {
    private static final Logger LOGGER = LogManager.getLogger("FieldAccessor");

    public static <T> T get(Class<?> clazz, String fieldName) {
        FieldAccess.ensureAccessible(clazz,
                                     fieldName
        );
        return EntrustEnvironment.cast(EntrustEnvironment.trys(() -> clazz.getField(fieldName)
                                                                          .get(null)));
    }

    public static <T> Class<T> type(Class<?> clazz, String fieldName) {
        FieldAccess.ensureAccessible(clazz,
                                     fieldName
        );
        return EntrustEnvironment.cast(EntrustEnvironment.trys(() -> {
            if (get(clazz,
                    fieldName
            ) instanceof Class<?> c) {
                return c;
            }
            return clazz.getField(fieldName)
                        .getType();
        }));
    }

    public static Field set(Field field, Object value) {
        int modifiers = field.getModifiers();
        if (! Modifier.isStatic(modifiers)) {
            throw new NotStaticFieldException();
        } else if (Modifier.isFinal(modifiers)) {
            throw new SetFinalFieldException();
        }
        EntrustEnvironment.trys(() -> {
            field.set(null,
                      value
            );
        });
        return field;
    }

    public static Field set(Class<?> clazz, String fieldName, Object value) {
        try {
            Field field = clazz.getField(fieldName);
            return set(field,
                       value
            );
        } catch (Exception e) {
            return null;
        }
    }

    public static Field set(Class<?> clazz, String fieldName, Object obj, Object value) {
        try {
            Field field = clazz.getField(fieldName);
            return set(field,
                       obj,
                       value
            );
        } catch (Exception e) {
            return null;
        }
    }

    public static Field set(Field field, Object obj, Object value) {
        int modifiers = field.getModifiers();
        if (Modifier.isFinal(modifiers)) {
            throw new SetFinalFieldException();
        }
        EntrustEnvironment.trys(() -> {
            field.set(obj,
                      value
            );
        });
        return field;
    }

    public static void ensureAccessible(Class<?> clazz, String field) throws NotStaticFieldException {
        Field f = EntrustEnvironment.trys(() -> clazz.getField(field));
        if (f == null) {
            return;
        }
        FieldAccess.ensureAccessible(f);
    }

    public static void ensureAccessible(Field field) throws NotStaticFieldException {
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
}
