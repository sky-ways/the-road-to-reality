package com.github.cao.awa.trtr.mixin.server;

import com.github.cao.awa.trtr.debuger.performance.tracker.*;
import com.github.cao.awa.trtr.heat.handler.*;
import net.minecraft.server.*;
import net.minecraft.server.world.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;
import java.util.function.*;

import static com.github.cao.awa.trtr.TrtrMod.heatHandler;
import static com.github.cao.awa.trtr.TrtrMod.timeTracker;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Shadow
    @Final
    private Map<RegistryKey<World>, ServerWorld> worlds;

    @Redirect(method = "tickWorlds", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;tick(Ljava/util/function/BooleanSupplier;)V"))
    public void tickWorlds(ServerWorld instance, BooleanSupplier shouldKeepTicking) {
        heatHandler.tick(instance);
        instance.tick(shouldKeepTicking);
    }

    @Inject(method = "stop", at = @At("HEAD"))
    public void stop(boolean bl, CallbackInfo ci) {
        for (World world : worlds.values()) {
            heatHandler.unloadHandler(world);
        }
    }

    @Inject(method = "runServer", at = @At("HEAD"))
    public void start(CallbackInfo ci) {
        heatHandler = new HeatHandler();
        timeTracker = new SubmitTimeTracker();
    }
}
