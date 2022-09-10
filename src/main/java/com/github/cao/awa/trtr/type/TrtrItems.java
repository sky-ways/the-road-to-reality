package com.github.cao.awa.trtr.type;

import com.github.cao.awa.trtr.debuger.*;
import com.github.cao.awa.trtr.power.storage.battery.*;
import net.minecraft.item.*;

public class TrtrItems {
    public static final Item BATTERY = Battery.register();
    public static final Item THERMOMETER = HandHoldThermometer.register();

    public static void pre() {

    }
}
