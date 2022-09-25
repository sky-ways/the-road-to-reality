package com.github.cao.awa.trtr.heat.handler;

import com.github.cao.awa.trtr.heat.conductor.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.client.world.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

public class HeatHandler {
    private final Map<World, WorldHeatHandler> handler = new Object2ObjectOpenHashMap<>();

    public void ensureWorld(World world) {
        if (handler.containsKey(world)) {
            return;
        }
        handler.put(world, new WorldHeatHandler());
    }

    public boolean isTicking(World world, BlockPos pos) {
        ensureWorld(world);
        return handler.get(world).isTicking(pos);
    }

    public void prepare(World world, BlockPos pos, Runnable action) {
        ensureWorld(world);
        handler.get(world).prepare(pos, action);
    }

    public void requireTick(World world, BlockPos pos) {
        ensureWorld(world);
        handler.get(world).requireTick(pos);
    }

    public void requireUnload(World world, BlockPos pos) {
        ensureWorld(world);
        handler.get(world).requireUnload(pos);
    }

    public void unregister(World world, BlockPos pos) {
        ensureWorld(world);
        handler.get(world).unregister(pos);
    }

    public void unregister(World world, BlockPos pos, HeatConductor conductor) {
        ensureWorld(world);
        handler.get(world).unregister(pos, conductor);
    }

    public boolean hasRegistered(World world, BlockPos pos) {
        ensureWorld(world);
        return handler.get(world).hasRegistered(pos);
    }

    public boolean isRegistered(World world, HeatConductor conductor) {
        ensureWorld(world);
        return handler.get(world).isRegistered(conductor);
    }

    public @Nullable HeatConductor getConductor(World world, BlockPos pos) {
        ensureWorld(world);
        if (world instanceof ClientWorld) {
            return null;
        }
        return handler.get(world).getConductor(pos);
    }

    public HeatConductor getOrReplace(World world, BlockPos pos, Supplier<HeatConductor> creator) {
        ensureWorld(world);
        return handler.get(world).getOrReplace(pos, creator);
    }

    public <T extends HeatConductor> void replace(World world, BlockPos pos, Supplier<T> creator) {
        ensureWorld(world);
        handler.get(world).replace(pos, creator);
    }

    public void register(World world, BlockPos pos, @NotNull HeatConductor conductor) {
        ensureWorld(world);
        handler.get(world).register(pos, conductor);
    }

    public void tick(World world) {
        ensureWorld(world);
        handler.get(world).tick(world);
    }

    public void unloadHandler(World world) {
        ensureWorld(world);
        handler.get(world).unloadHandler(world);
    }

    public boolean isUnloaded(World world) {
        ensureWorld(world);
        return handler.get(world).isUnloaded();
    }

    public void unload(World world, BlockPos pos) {
        ensureWorld(world);
        handler.get(world).unload(pos);
    }
}
