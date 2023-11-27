package com.github.cao.awa.trtr.mixin.server;

import com.github.cao.awa.trtr.gas.manager.WorldGasManager;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Inject(method = "tick", at = @At("RETURN"))
    public void tick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        WorldGasManager.GAS_MANAGER.tick();
    }
}
