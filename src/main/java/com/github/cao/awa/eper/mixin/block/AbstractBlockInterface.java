package com.github.cao.awa.eper.mixin.block;

import net.minecraft.block.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin(AbstractBlock.class)
public interface AbstractBlockInterface {
    @Accessor("settings")
    AbstractBlock.Settings getSettings();
}
