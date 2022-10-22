package com.github.cao.awa.trtr.air.manager.handler;

import com.github.cao.awa.trtr.database.properties.*;
import net.minecraft.nbt.*;
import net.minecraft.server.world.*;
import net.minecraft.world.*;

public class OverworldAirHandler extends PersistentState implements WorldAirHandler {
    private final InstanceProperties properties = new InstanceProperties();
    private final ServerWorld world;

    public OverworldAirHandler(ServerWorld world) {
        this.world = world;
    }

    public static OverworldAirHandler fromNbt(NbtCompound nbt, ServerWorld world) {
        OverworldAirHandler airHandler = new OverworldAirHandler(world);
        airHandler.readNbt(nbt);
        return airHandler;
    }

    public void readNbt(NbtCompound nbt) {
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        return nbt;
    }

    @Override
    public void tick() {

    }
}
