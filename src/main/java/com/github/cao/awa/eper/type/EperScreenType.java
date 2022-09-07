package com.github.cao.awa.eper.type;

import com.github.cao.awa.eper.power.thermoelectric.fire.burner.*;
import net.minecraft.client.gui.screen.*;

public class EperScreenType<T extends Screen> {
    public static void pre() {
        BurnerScreen.register();
    }
}
