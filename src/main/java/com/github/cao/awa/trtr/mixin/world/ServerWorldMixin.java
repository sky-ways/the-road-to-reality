package com.github.cao.awa.trtr.mixin.world;

import com.github.cao.awa.trtr.*;
import com.github.cao.awa.trtr.air.manager.handler.*;
import net.minecraft.server.*;
import net.minecraft.server.world.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import net.minecraft.world.dimension.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.storage.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;
import java.util.concurrent.*;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
    @Shadow public abstract ServerWorld toServerWorld();

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(MinecraftServer server, Executor workerExecutor, LevelStorage.Session session, ServerWorldProperties properties, RegistryKey worldKey, DimensionOptions dimensionOptions, WorldGenerationProgressListener worldGenerationProgressListener, boolean debugWorld, long seed, List spawners, boolean shouldTickTime, CallbackInfo ci) {
        if (worldKey.equals(World.OVERWORLD)) {
            ServerWorld world = toServerWorld();
            OverworldAirHandler handler = world.getPersistentStateManager()
                                           .getOrCreate(
                                                   nbt -> OverworldAirHandler.fromNbt(nbt, world),
                                                   () -> new OverworldAirHandler(world),
                                                   "overworld_air_handler"
                                           );
            TrtrMod.airManager.add(toServerWorld(), handler);
        }
    }

    @Shadow
    public abstract PersistentStateManager getPersistentStateManager();
}
