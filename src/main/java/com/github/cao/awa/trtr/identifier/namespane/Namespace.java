package com.github.cao.awa.trtr.identifier.namespane;

import net.minecraft.util.Identifier;

public class Namespace {
    public static Identifier subSpace(Identifier identifier, String sub) {
        return Identifier.tryParse(identifier.getNamespace() + ":" + sub + "/" + identifier.getPath());
    }
}
