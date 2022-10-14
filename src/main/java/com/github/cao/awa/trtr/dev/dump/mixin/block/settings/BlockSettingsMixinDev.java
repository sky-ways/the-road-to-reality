package com.github.cao.awa.trtr.dev.dump.mixin.block.settings;

import net.fabricmc.fabric.mixin.object.builder.*;
import net.minecraft.block.*;

public class BlockSettingsMixinDev {
    public static float appendResistance(AbstractBlock.Settings settings) {
        return ((AbstractBlockSettingsAccessor)settings).getMaterial() == Material.TNT ? 0 : 8;
    }
}
