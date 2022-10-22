package com.github.cao.awa.trtr.dev.dump.mixin.player.inventory;

import com.github.cao.awa.trtr.database.properties.*;

public class PlayerInventoryMixinDev {
    public static void mergeData(InstanceProperties properties) {
        System.out.println(properties.toJSONObject().toString());
    }
}
