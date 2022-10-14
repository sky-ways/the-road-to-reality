package com.github.cao.awa.trtr.air.manager.handler;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.database.properties.pool.*;
import com.github.cao.awa.trtr.mixin.world.manager.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.runnable.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.block.*;
import net.minecraft.nbt.*;
import net.minecraft.server.network.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.chunk.*;

import java.util.*;

import static com.github.cao.awa.trtr.TrtrMod.counter;
import static com.github.cao.awa.trtr.TrtrMod.timeTracker;

public class OverworldAirHandler extends PersistentState implements WorldAirHandler {
    private final InstanceProperties<OverworldAirHandler> properties = new InstanceProperties<>(this);
    private final ServerWorld world;
    private final Thread thread = new Thread(() -> {
    });

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
//        List<ServerPlayerEntity> players = world.getPlayers();
//        for (ServerPlayerEntity player : players) {
//            int posY = player.getBlockPos()
//                             .getY();
//
//            int expectY = posY + 16 * 2;
//            int expectStartY = posY - 16 * 2;
//
//            int chunkX = ChunkSectionPos.getSectionCoord(player.getBlockPos()
//                                                               .getX());
//            int chunkZ = ChunkSectionPos.getSectionCoord(player.getBlockPos()
//                                                               .getZ());
//
////            timeTracker.start(this);
////            counter.start(this);
//            for (ChunkSection chunkSection : world.getChunk(
//                                                          chunkX,
//                                                          chunkZ
//                                                  )
//                                                  .getSectionArray()) {
//                                    if (expectStartY > chunkSection.getYOffset()) {
//                                        continue;
//                                    }
//                                    if (expectY < chunkSection.getYOffset()) {
//                                        break;
//                                    }
//                //                    PropertiesList<ChunkSection> list = properties.list("exclude");
//                //                    if (list.contains(chunkSection)) {
//                //                        continue;
//                //                    }
//                //                    list.add(chunkSection);
//                for (int x = 0; x < 16; x++) {
//                    for (int y = 0; y < 16; y++) {
//                        for (int z = 0; z < 16; z++) {
//                            BlockPos pos = new BlockPos(
//                                    chunkX * 16 + x,
//                                    chunkSection.getYOffset() + y,
//                                    chunkZ * 16 + z
//                            );
//                            if (chunkSection.getBlockState(
//                                                    x,
//                                                    y,
//                                                    z
//                                            )
//                                            .isAir()) {
//                                world.setBlockState(
//                                        pos,
//                                        TrtrBlocks.DUMP_AIR.getDefaultState(),
//                                        3
//                                );
//                            }
////                            InstanceProperties<?> properties = world.getBlockEntity(pos) instanceof PropertiesAccessible accessible ?
////                                                               accessible.properties() :
////                                                               null;
////                            if (properties != null) {
////                                counter.count(this,
////                                              properties.getAccessNbtCompound()
////                                                        .toString()
////                                                        .length()
////                                );
////                            }
//                        }
//                    }
//                }
//            }
////            counter.done(this);
////            timeTracker.done(this);
//        }
    }
}
