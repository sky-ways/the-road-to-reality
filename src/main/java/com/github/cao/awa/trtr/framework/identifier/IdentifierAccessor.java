package com.github.cao.awa.trtr.framework.identifier;

import com.github.cao.awa.trtr.framework.reflection.filed.FieldAccessor;
import de.javagl.obj.Obj;
import net.minecraft.util.Identifier;

public class IdentifierAccessor implements FieldAccessor {
    public static final IdentifierAccessor ACCESSOR = new IdentifierAccessor();

    public Identifier get(Class<?> clazz) {
        return get(clazz,
                   "IDENTIFIER"
        );
    }

    public Identifier get(Object o) {
        return get(o.getClass());
    }
}
