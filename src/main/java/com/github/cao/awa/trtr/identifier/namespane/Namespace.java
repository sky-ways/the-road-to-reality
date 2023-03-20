package com.github.cao.awa.trtr.identifier.namespane;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class Namespace {
    public static Identifier subSpace(@Nullable Identifier identifier, String sub) {
        if (identifier == null) {
            return new Identifier("minecraft",
                                  sub
            );
        }
        return Identifier.tryParse(identifier.getNamespace() + ":" + sub + "/" + identifier.getPath());
    }
}
