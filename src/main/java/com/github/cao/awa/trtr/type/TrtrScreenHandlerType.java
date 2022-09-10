package com.github.cao.awa.trtr.type;

import com.github.cao.awa.trtr.power.thermoelectric.fire.burner.*;
import net.minecraft.screen.*;
import net.minecraft.util.registry.*;

public class TrtrScreenHandlerType<T extends ScreenHandler> {
    public static final ScreenHandlerType<BurnerScreenHandler> BURNER = register("trtr:burner_1x1", BurnerScreenHandler::new);

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registry.SCREEN_HANDLER, id, new ScreenHandlerType<>(factory));
    }

    public static void pre() {

    }
}
