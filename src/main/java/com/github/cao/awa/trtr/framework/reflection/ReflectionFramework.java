package com.github.cao.awa.trtr.framework.reflection;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.anntation.Unsupported;
import com.github.cao.awa.trtr.annotation.dev.DevOnly;
import com.github.cao.awa.trtr.constant.trtr.TrtrConstants;
import com.github.cao.awa.trtr.framework.accessor.method.MethodAccess;
import com.github.cao.awa.trtr.framework.exception.NoAutoAnnotationException;
import com.github.cao.awa.trtr.framework.loader.JarSearchLoader;
import com.github.cao.awa.trtr.framework.side.LoadingSide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.io.File;
import java.lang.reflect.*;
import java.util.List;

public abstract class ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("ReflectionFramework");
    private static final Reflections REFLECTIONS = new Reflections(new ConfigurationBuilder().addUrls(JarSearchLoader.load(new File("mods")))
                                                                                             .addUrls(ClasspathHelper.forPackage(""))
                                                                                             .addScanners(Scanners.TypesAnnotated));

    public Reflections reflection() {
        return REFLECTIONS;
    }

    public boolean checkFields(String target, List<String> field) {
        if (field.size() > 0) {
            LOGGER.error("'{}' has missing required field(s): {}",
                         target,
                         field
            );
            return false;
        } else {
            LOGGER.debug("'{}' has passed checking required field(s)",
                         target
            );
            return true;
        }
    }

    public static boolean dev(Class<?> clazz) {
        return clazz != null && clazz.isAnnotationPresent(DevOnly.class);
    }

    public static boolean dev(Field field) {
        return field != null && field.isAnnotationPresent(DevOnly.class);
    }

    public static boolean unsupported(Class<?> clazz) {
        return clazz != null && clazz.isAnnotationPresent(Unsupported.class);
    }

    public static boolean unsupported(Field field) {
        return field != null && field.isAnnotationPresent(Unsupported.class);
    }

    public static <T> Constructor<T> accessible(Constructor<T> constructor) {
        if (constructor.canAccess(null)) {
            return constructor;
        }
        constructor.trySetAccessible();
        return constructor;
    }

    @NotNull
    public static Method accessible(Method clazz) {
        if (clazz.isAnnotationPresent(Auto.class)) {
            return MethodAccess.ensureAccessible(clazz);
        }
        throw new NoAutoAnnotationException("Missing auto annotation");
    }

    @NotNull
    public static Method accessible(Method clazz, Object object) {
        if (autoAnnotated(clazz)) {
            return MethodAccess.ensureAccessible(clazz,
                                                 object
            );
        }
        throw new NoAutoAnnotationException("Missing auto annotation");
    }

    public static Field accessible(@NotNull Field field) {
        if (autoAnnotated(field)) {
            return accessible(field,
                              null
            );
        }
        throw new NoAutoAnnotationException("Missing auto annotation");
    }

    public static Field accessible(@NotNull Field field, @Nullable Object obj) {
        if (autoAnnotated(field)) {
            if (field.canAccess(Modifier.isStatic(field.getModifiers()) ? null : obj)) {
                return field;
            }
            field.trySetAccessible();
            return field;
        }
        throw new NoAutoAnnotationException("Missing auto annotation");
    }

    public static boolean autoAnnotated(AccessibleObject object) {
        return object.isAnnotationPresent(Auto.class);
    }

    public static boolean shouldLoad(LoadingSide side) {
        return switch (side) {
            case SERVER -> TrtrConstants.isServer;
            case CLIENT -> ! TrtrConstants.isServer;
            case BOTH -> true;
        };
    }
}
