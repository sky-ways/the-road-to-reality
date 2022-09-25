package com.github.cao.awa.trtr.heat.handler;

import com.github.cao.awa.trtr.heat.conductor.*;
import com.github.cao.awa.trtr.mixin.world.manager.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.receptacle.*;
import net.minecraft.block.entity.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.chunk.*;
import org.jetbrains.annotations.*;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

import static com.github.cao.awa.trtr.TrtrMod.heatHandler;
import static com.github.cao.awa.trtr.TrtrMod.timeTracker;

public class WorldHeatHandler {
    private static final Method UNLOAD_CHECKER = EntrustParser.trying(() -> {
        Method method = ServerChunkManager.class.getDeclaredMethod("getChunkHolder", long.class);
        method.setAccessible(true);
        return method;
    });
    private final Map<BlockPos, HeatConductor> conductors = new ConcurrentHashMap<>();
    private final Map<BlockPos, Runnable> prepare = new ConcurrentHashMap<>();
    private final Map<BlockPos, HeatConductor> ticking = new ConcurrentHashMap<>();
    private boolean unloaded = false;

    public WorldHeatHandler() {
    }

    public boolean isTicking(BlockPos pos) {
        return ticking.containsKey(pos);
    }

    public void prepare(BlockPos pos, Runnable action) {
        prepare.put(pos, action);
    }

    public void requireTick(BlockPos pos) {
        HeatConductor conductor = conductors.get(pos);
        if (conductor == null) {
            return;
        }
        ticking.put(pos, conductor);
    }

    public void unregister(BlockPos pos, HeatConductor conductor) {
        conductors.remove(pos, conductor);
    }

    public boolean hasRegistered(BlockPos pos) {
        return conductors.containsKey(pos);
    }

    public boolean isRegistered(HeatConductor conductor) {
        return conductors.containsValue(conductor);
    }

    public @Nullable HeatConductor getConductor(BlockPos pos) {
        return conductors.get(pos);
    }

    public HeatConductor getOrReplace(BlockPos pos, Supplier<HeatConductor> creator) {
        HeatConductor conductor = conductors.get(pos);
        HeatConductor created = creator.get();
        if (conductor == null) {
            register(pos, created);
        } else if (conductor.getConductive().isOf(created.getConductive())) {
            return conductor;
        } else {
            register(pos, created);
        }
        return created;
    }

    public void register(BlockPos pos, @NotNull HeatConductor conductor) {
        conductors.put(pos, conductor);
    }

    public <T extends HeatConductor> void replace(BlockPos pos, Supplier<T> creator) {
        register(pos, creator.get());
    }

    public void tick(World world) {
        timeTracker.start(this);
        ticking.values().parallelStream().forEach(conductor -> {
            conductor.adaptive(world);
            timeTracker.count(this);
        });

        ticking.forEach((pos, conductor) -> {
            BlockEntity entity = world.getBlockEntity(pos);

            if (entity != null) {
                if (shouldUnload(world, pos) || conductor.getConductive().isOf(entity)) {
                    return;
                } else if (conductor.getConductive().participateUnload()) {
                    unload(pos);
                }
            }
            ticking.remove(pos);
        });

        prepare.forEach((t, runnable) -> runnable.run());
        prepare.clear();

        timeTracker.done(this);
    }

    public boolean shouldUnload(World world, BlockPos pos) {
        ChunkManager manager = world.getChunkManager();
        return EntrustParser.trying(() -> {
            assert UNLOAD_CHECKER != null;
            return ! ((ChunkHolder) UNLOAD_CHECKER.invoke(manager, ChunkPos.toLong(pos))).getLevelType().isAfter(ChunkHolder.LevelType.TICKING);
        }, () -> true);
    }

    public void unload(BlockPos pos) {
        requireUnload(pos);
        unregister(pos);
    }

    public void unregister(BlockPos pos) {
        conductors.remove(pos);
    }

    public void requireUnload(BlockPos pos) {
        ticking.remove(pos);
    }

    public boolean isUnloaded() {
        return unloaded;
    }

    public void unloadHandler(World world) {
        unloaded = true;
        //        for (HeatConductor conductor : conductors.values()) {
        //            BlockPos pos = conductor.getConductive().getPos();
        //            BlockEntity entity = world.getBlockEntity(pos);
        //            if (entity instanceof HeatConductiveBlockEntity conduction) {
        //                if (! conduction.participateUnload()) {
        //                    continue;
        //                }
        //            }
        //            BlockState state = world.getBlockState(pos);
        //            Block block = state.getBlock();
        //            if (entity == null) {
        //                if (block instanceof TrtrBlockWithEntity blockWithEntity) {
        //                    world.addBlockEntity(blockWithEntity.createBlockEntity(pos, state));
        //                }
        //            }
        //        }
    }
}
