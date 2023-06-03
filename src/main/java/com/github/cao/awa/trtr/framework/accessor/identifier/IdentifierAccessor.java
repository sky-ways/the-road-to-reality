package com.github.cao.awa.trtr.framework.accessor.identifier;

import com.github.cao.awa.trtr.framework.accessor.filed.FieldAccessor;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IdentifierAccessor implements FieldAccessor {
    private static final Logger LOGGER = LogManager.getLogger("IdentifierAccessor");
    public static final IdentifierAccessor ACCESSOR = new IdentifierAccessor();

    public Identifier get(Class<?> clazz) {
        Object fieldResult = get(clazz,
                                 "IDENTIFIER"
        );
        if (fieldResult instanceof Identifier identifier) {
            return identifier;
        }
        LOGGER.warn("Access 'IDENTIFIER' in {} as identifier failed, trying parse with string",
                    clazz.getName()
        );

        Identifier identifier;
        try {
            String[] paths = fieldResult.toString()
                                        .split(":");

            identifier = Identifier.of(paths[0],
                                       paths[1]
            );
        } catch (Exception e) {
            identifier = null;
        }

        if (identifier == null) {
            LOGGER.error("Access 'IDENTIFIER' in '{}' failed parse to identifier with string",
                         clazz.getName()
            );
        }
        return identifier;
    }

    public Identifier get(Object o) {
        return get(o.getClass());
    }

    public boolean has(Class<?> clazz) {
        return has(clazz,
                   "IDENTIFIER"
        );
    }

    public boolean has(Object block) {
        return has(block.getClass());
    }
}
