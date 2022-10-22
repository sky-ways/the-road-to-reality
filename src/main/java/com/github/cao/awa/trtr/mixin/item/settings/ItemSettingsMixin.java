package com.github.cao.awa.trtr.mixin.item.settings;

import net.minecraft.item.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(Item.Settings.class)
public class ItemSettingsMixin {
    @Shadow
    int maxCount;

    @Inject(method = "maxCount", at = @At("RETURN"), cancellable = true)
    public void maxCount(int maxCount, CallbackInfoReturnable<Item.Settings> cir) {
        this.maxCount = maxCount > 7 ? 8 : 1;
        cir.setReturnValue(cir.getReturnValue());
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void cancelOneStack(CallbackInfo ci) {
        this.maxCount = 8;
    }
}
