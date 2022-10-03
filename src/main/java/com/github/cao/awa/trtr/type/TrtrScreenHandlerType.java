package com.github.cao.awa.trtr.type;

import net.minecraft.screen.*;
import net.minecraft.util.registry.*;

public class TrtrScreenHandlerType<T extends ScreenHandler> {
    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registry.SCREEN_HANDLER, id, new ScreenHandlerType<>(factory));
    }

    public static void pre() {

    }
}
