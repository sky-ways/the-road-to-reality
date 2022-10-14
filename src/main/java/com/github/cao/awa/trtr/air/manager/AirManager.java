package com.github.cao.awa.trtr.air.manager;

import com.github.cao.awa.trtr.air.manager.handler.*;
import com.github.cao.awa.trtr.heat.handler.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.nbt.*;
import net.minecraft.server.world.*;
import net.minecraft.world.*;

import java.util.*;

public class AirManager {
    private final Map<World, WorldAirHandler> handlers = new Object2ObjectOpenHashMap<>();

    public void add(World world, WorldAirHandler handler) {
        if (handler == null) {
            return;
        }
        handlers.put(world, handler);
    }

    public void tick(World world) {
        WorldAirHandler handler = handlers.get(world);
        if (handler == null) {
            return;
        }
        handler.tick();
    }
}
