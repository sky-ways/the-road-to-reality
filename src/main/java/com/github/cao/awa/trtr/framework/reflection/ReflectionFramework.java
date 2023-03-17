package com.github.cao.awa.trtr.framework.reflection;

import com.github.cao.awa.trtr.framework.loader.JarSearchLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.io.File;
import java.util.List;

public abstract class ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("Trtr/ReflectionFramework");
    private static final Reflections REFLECTIONS = new Reflections(new ConfigurationBuilder().addUrls(JarSearchLoader.load(new File("mods")))
                                                                                             .addUrls(ClasspathHelper.forPackage(""))
                                                                                             .addScanners(Scanners.TypesAnnotated));

    public Reflections getReflection() {
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
}
