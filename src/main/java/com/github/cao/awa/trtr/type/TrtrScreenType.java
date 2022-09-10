package com.github.cao.awa.trtr.type;

import com.github.cao.awa.trtr.power.thermoelectric.fire.burner.*;
import net.minecraft.client.gui.screen.*;

public class TrtrScreenType<T extends Screen> {
    public static void pre() {
        BurnerScreen.register();
    }
}
