package com.github.cao.awa.trtr.mixin.server;

import com.github.cao.awa.trtr.air.manager.*;
import com.github.cao.awa.trtr.database.memory.*;
import com.github.cao.awa.trtr.database.oneness.*;
import com.github.cao.awa.trtr.debuger.performance.tracker.*;
import com.github.cao.awa.trtr.heat.handler.*;
import com.github.cao.awa.trtr.util.*;
import net.minecraft.server.*;
import net.minecraft.server.world.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;
import java.util.function.*;

import static com.github.cao.awa.trtr.TrtrMod.*;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Shadow
    @Final
    private Map<RegistryKey<World>, ServerWorld> worlds;

    @Redirect(method = "tickWorlds", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;tick(Ljava/util/function/BooleanSupplier;)V"))
    public void tickWorlds(ServerWorld instance, BooleanSupplier shouldKeepTicking) {
        heatManager.tick(instance);
        instance.tick(shouldKeepTicking);
        airManager.tick(instance);
    }

    @Inject(method = "stop", at = @At("HEAD"))
    public void stop(boolean bl, CallbackInfo ci) {
        for (World world : worlds.values()) {
            heatManager.unloadHandler(world);
        }
        propertiesDatabase.shutdown();
    }

    @Inject(method = "runServer", at = @At("HEAD"))
    public void start(CallbackInfo ci) {
        heatManager = new HeatManager();
        timeTracker = new SubmitTimeTracker();
        airManager = new AirManager();
//        propertiesDatabase = new MemoryFullCachedDatabase(LevelUtil.getServerLevelPath((MinecraftServer)(Object)this) + "/database/airy");
        propertiesDatabase = new OnenessFullCachedDatabase(LevelUtil.getServerLevelPath((MinecraftServer)(Object)this) + "/database/airy");
    }
}
