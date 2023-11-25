package com.github.cao.awa.trtr.gas;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.constant.pressure.PressureConstants;
import com.github.cao.awa.trtr.database.KeyValueBytesDatabase;
import com.github.cao.awa.trtr.gas.database.GasDatabase;
import com.github.cao.awa.trtr.pressure.pa.PaPressure;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public class WorldGasManager {
    private static final BlockGas DEFAULT_GAS = EntrustEnvironment.operation(
            new BlockGas(),
            gas -> {
                gas.pressure = PressureConstants.STANDARD_ATMOSPHERIC_PRESSURE.copy();
            }
    );
    public static final WorldGasManager GAS_MANAGER = new WorldGasManager();
    private GasDatabase database;
    private ServerWorld world;
    private final Map<ChunkPos, Set<BlockPos>> requiredTickets = ApricotCollectionFactor.concurrentHashMap();
    private final Map<ChunkPos, Set<BlockPos>> preparedTickets = ApricotCollectionFactor.concurrentHashMap();

    public WorldGasManager() {

    }

    public WorldGasManager setDatabase(KeyValueBytesDatabase database) {
        if (this.database != null) {
            this.database.close();
        }
        this.database = new GasDatabase(
                ApricotCollectionFactor :: hashMap,
                database
        );
        return this;
    }

    public void close() {
        if (this.database != null) {
            this.database.close();
        }
        this.database = null;
        this.world = null;
    }

    public WorldGasManager setWorld(ServerWorld world) {
        this.world = world;
        return this;
    }

    public void requestTicket(WorldChunk chunk) {
        // Prepare tickets.
        chunk.forEachBlockMatchingPredicate(
                AbstractBlock.AbstractBlockState :: isAir,
                (pos, state) -> {
                    pos = new BlockPos(pos);

                    BlockGas gas = this.database.get(pos);
                    if (gas == null) {
                        gas = new BlockGas();
                        gas.pressure = PressureConstants.STANDARD_ATMOSPHERIC_PRESSURE.copy();
                        updateGas(pos,
                                  gas,
                                  false
                        );
                    }

                    requestTicket(pos);
                }
        );
    }

    public void ensureTicket(BlockPos pos, boolean preparing) {
        ensureTicket(new ChunkPos(pos),
                     preparing
        );
    }

    public void ensureTicket(ChunkPos pos, boolean preparing) {
        (preparing ? this.preparedTickets : this.requiredTickets).compute(pos,
                                                                          (chunkPos, ticket) -> {
                                                                              if (ticket == null) {
                                                                                  ticket = ApricotCollectionFactor.concurrentHashSet();
                                                                              }
                                                                              return ticket;
                                                                          }
        );
    }

    public boolean isValidGas(BlockPos pos) {
        BlockState targetState = this.world.getBlockState(pos);

        return targetState.isAir() || targetState.isOf(Blocks.BEDROCK) || targetState.isOf(Blocks.ORANGE_WOOL);
    }

    public void requestTicket(BlockPos pos) {
        ensureTicket(pos,
                     true
        );

        @NotNull Set<BlockPos> tickets = this.preparedTickets.get(new ChunkPos(pos));

        requestTicket(pos,
                      tickets
        );
    }

    public void requestTicket(BlockPos pos, @NotNull Set<BlockPos> tickets) {
        BlockGas gas = this.database.get(pos);

        if (shouldTransfer(
                gas.pressure,
                PressureConstants.STANDARD_ATMOSPHERIC_PRESSURE
        )) {
            tickets.add(pos);
        }
    }

    public void expireChunkTicket(WorldChunk chunk) {
        this.requiredTickets.remove(chunk.getPos());
    }

    public void expireTicket(BlockPos pos) {
        this.requiredTickets.get(new ChunkPos(pos))
                            .remove(pos);
    }

    public void updateGas(BlockPos pos, BlockGas gas) {
        updateGas(
                pos,
                gas,
                true
        );
    }

    public void updateGas(BlockPos pos, BlockGas gas, boolean requestTicket) {
        this.database.put(
                pos,
                gas
        );

        if (requestTicket) {
            requestTicket(pos);
        }
    }

    public BlockGas getGas(BlockPos pos) {
        return this.database.get(pos);
    }

    public void tick() {
        // Let prepared tickets join to tick.
        this.preparedTickets.forEach(((chunkPos, tickets) -> {
            ensureTicket(chunkPos,
                         false
            );

            this.requiredTickets.get(chunkPos)
                                .addAll(tickets);
        }));

        this.preparedTickets.clear();


        Set<BlockPos> needRemoves = ApricotCollectionFactor.hashSet();

        this.requiredTickets.forEach((chunkPos, tickets) -> {
            tickets.forEach((ticket) -> {
                if (! isValidGas(ticket)) {
                    needRemoves.add(ticket);
                    expireTicket(ticket);

                    return;
                }

                BlockGas gas = this.database.get(ticket);

                if (! gas.softTick(ticket)) {
                    if (! shouldFlowToAny(ticket)) {
                        if (! shouldKeepTick(gas.pressure)) {
                            needRemoves.add(ticket);
                        } else {
                            gas.forceTick(ticket);
                            this.world.setBlockState(ticket,
                                                     Blocks.ORANGE_WOOL.getDefaultState(),
                                                     Block.NOTIFY_ALL
                            );
                        }
                    }
                } else {
                    this.world.setBlockState(ticket,
                                             Blocks.BEDROCK.getDefaultState(),
                                             Block.NOTIFY_ALL
                    );
                }
            });
        });

        for (BlockPos blockPos : needRemoves) {
            Set<BlockPos> tickets = this.requiredTickets.get(new ChunkPos(blockPos));
            if (tickets != null) {
                this.world.setBlockState(blockPos,
                                         Blocks.AIR.getDefaultState(),
                                         Block.NOTIFY_ALL
                );
                tickets.remove(blockPos);
            }
        }
    }

    public boolean isTicking(BlockPos pos) {
        return EntrustEnvironment.trys(
                () -> this.requiredTickets.get(new ChunkPos(pos))
                                          .contains(pos),
                () -> false
        );
    }

    public boolean shouldKeepTick(PaPressure selfPressure) {
        return selfPressure.value() - PressureConstants.STANDARD_ATMOSPHERIC_PRESSURE.value() > 25;
    }

    public boolean shouldTransfer(PaPressure self, PaPressure target) {
        long transferValue = self.value() - target.value();

        return transferValue / 2 > 0;
    }

    public boolean shouldFlowToAny(BlockPos selfPos) {
        boolean should = false;

        for (Direction direction : Direction.values()) {
            boolean canFlowToThisSide = BlockGas.canFlow(
                    selfPos,
                    direction
            );
            if (canFlowToThisSide) {
                should |= shouldTransfer(
                        this.database.get(selfPos).pressure,
                        EntrustEnvironment.nonnull(
                                this.database.get(selfPos.offset(direction)),
                                DEFAULT_GAS
                        ).pressure
                );
            }
        }

        return should;
    }
}
