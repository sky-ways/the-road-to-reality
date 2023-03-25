package com.github.cao.awa.trtr.identifier.namespane;

import com.github.cao.awa.trtr.util.string.StringConcat;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class Namespace {
    public static Identifier subSpace(@Nullable Identifier identifier, String sub) {
        if (identifier == null) {
            return new Identifier("minecraft",
                                  sub
            );
        }
        return Identifier.tryParse(StringConcat.concat(identifier.getNamespace(),
                                                       ":",
                                                       sub,
                                                       "/",
                                                       identifier.getPath()
        ));
    }
}
