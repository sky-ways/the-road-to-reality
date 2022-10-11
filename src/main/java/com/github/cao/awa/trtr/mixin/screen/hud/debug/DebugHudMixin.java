package com.github.cao.awa.trtr.mixin.screen.hud.debug;

import com.github.cao.awa.trtr.dev.dump.mixin.screen.hud.debug.*;
import net.minecraft.client.gui.hud.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

@Mixin(DebugHud.class)
public class DebugHudMixin {
    @Inject(method = "getRightText", at = @At(value = "RETURN"))
    public void appendDebugRightText(CallbackInfoReturnable<List<String>> cir) {
        DebugHudMixinDev.appendDebugRightText(cir.getReturnValue());
    }
}
