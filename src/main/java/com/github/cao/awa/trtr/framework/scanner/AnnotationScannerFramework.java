package com.github.cao.awa.trtr.framework.scanner;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.TrtrMod;
import com.github.cao.awa.trtr.annotation.property.AutoProperty;
import com.github.cao.awa.trtr.annotation.serializer.AutoNbt;
import com.github.cao.awa.trtr.constant.trtr.TrtrConstants;
import com.github.cao.awa.trtr.framework.exception.NotPreparedException;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class AnnotationScannerFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("AnnotationScannerFramework");

    public void work() {
        // Working stream...
        if (reflection().getTypesAnnotatedWith(Auto.class)
                        .stream()
                        .filter(this :: match)
                        .map(this :: verify)
                        .reduce(false,
                                (b1, b2) -> b1 || b2
                        )) {
            throw new NotPreparedException("Missing auto annotation in fields or methods");
        }
    }

    private boolean match(Class<?> clazz) {
        // Framework will not process the unsupported class.
        boolean unsupported = unsupported(clazz);
        boolean dev = dev(clazz);

        // The abstract class cannot be instanced, filter it out.
        boolean abs = Modifier.isAbstract(clazz.getModifiers());

        // Notice the unsupported class.
        if (unsupported) {
            LOGGER.warn("Class '{}' is unsupported, ignored it",
                        clazz.getName()
            );
        }

        // Notice development class.
        if (dev && ! TrtrMod.DEV_MODE) {
            LOGGER.warn("Class '{}' is only available in development environment, ignored it",
                        clazz.getName()
            );
        }

        // Combine conditions.
        return
                // Ignored dev check when dev mode enabled.
                (TrtrMod.DEV_MODE || ! dev) &&
                        // Unsupported class will not be proxy.
                        ! unsupported &&
                        // Abstract class will not be proxy.
                        ! abs &&
                        shouldLoad(TrtrConstants.getLoadingSide(clazz));
    }

    private boolean verify(Class<?> block) {
        boolean breakFlag = false;

        for (Field field : block.getDeclaredFields()) {
            // Must present the @Auto annotation, else then cannot be proxy and will breaks game.
            if (! field.isAnnotationPresent(Auto.class)) {
                // Check auto property.
                if (field.isAnnotationPresent(AutoProperty.class)) {
                    LOGGER.error("The auto property field '{}' are missing auto annotation",
                                 block.getName() + "#" + field.getName()
                    );
                    breakFlag = true;
                }

                // Check auto nbt.
                if (field.isAnnotationPresent(AutoNbt.class)) {
                    LOGGER.error("The auto nbt field '{}' are missing auto annotation",
                                 block.getName() + "#" + field.getName()
                    );
                    breakFlag = true;
                }
            }
        }

        return breakFlag;
    }
}
