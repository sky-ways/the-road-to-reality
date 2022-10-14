package com.github.cao.awa.trtr.mixin.block.settings;

import com.github.cao.awa.trtr.dev.dump.mixin.block.settings.*;
import net.minecraft.block.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(AbstractBlock.Settings.class)
public class BlockSettingsMixin {
    @Shadow private float resistance;

    @Inject(method = "resistance", at = @At("RETURN"))
    public void resistance(float resistance, CallbackInfoReturnable<AbstractBlock.Settings> cir) {
        this.resistance = resistance + BlockSettingsMixinDev.appendResistance(cir.getReturnValue());
    }
}
