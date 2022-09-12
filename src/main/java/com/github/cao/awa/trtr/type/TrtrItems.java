package com.github.cao.awa.trtr.type;

import com.github.cao.awa.trtr.debuger.*;
import com.github.cao.awa.trtr.power.storage.battery.*;
import com.github.cao.awa.trtr.tool.hammer.*;
import com.github.cao.awa.trtr.tool.hammer.iron.*;
import com.github.cao.awa.trtr.tool.hammer.stone.*;
import com.github.cao.awa.trtr.tool.hammer.wooden.*;
import net.minecraft.item.*;

public class TrtrItems {
    public static final Item BATTERY = Battery.register();
    public static final Item THERMOMETER = HandHoldThermometer.register();
    public static final Item IRON_HAMMER = IronHammer.register();
    public static final Item STONE_HAMMER = StoneHammer.register();
    public static final Item WOODEN_HAMMER = WoodenHammer.register();

    public static void pre() {

    }
}
