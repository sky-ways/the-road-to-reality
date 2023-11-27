package com.github.cao.awa.trtr.gas;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.annotation.serializer.AutoNbt;
import com.github.cao.awa.trtr.constant.pressure.PressureConstants;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializable;
import com.github.cao.awa.trtr.gas.manager.WorldGasManager;
import com.github.cao.awa.trtr.pressure.pa.PaPressure;
import com.github.cao.awa.trtr.random.Randoms;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.List;

@Auto
public class BlockGas implements NbtSerializable {
    private static final Direction[] HORIZONTAL = new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

    @Auto
    @AutoNbt
    public PaPressure pressure;

    public boolean tryFlow(ServerWorld world, BlockPos pos, boolean force) {
        boolean flowResult = flowToSide(Direction.UP,
                                        world,
                                        pos,
                                        force
        );

//        if (Randoms.b()) {
        List<Direction> flowToDirections = ApricotCollectionFactor.arrayList();

        for (Direction direction : HORIZONTAL) {
            if (WorldGasManager.GAS_MANAGER.canFlow(
                    pos,
                    direction
            )) {
                flowToDirections.add(direction);
            }
        }

        boolean sideFlowed = false;

//            if (flowToDirections.size() == 1) {
//                sideFlowed = flowToSide(flowToDirections.get(0),
//                                        world,
//                                        pos,
//                                        2
//                );
//
//                flowResult |= sideFlowed;
//            } else {
        for (Direction direction : flowToDirections) {
            sideFlowed |= flowToSide(direction,
                                     world,
                                     pos,
                                     force
            );

            flowResult |= sideFlowed;
        }
//            }

        if (! sideFlowed || Randoms.b()) {
            flowResult = flowToSide(Direction.DOWN,
                                    world,
                                    pos,
                                    force
            );
        }
//        }
        return flowResult;
    }

    public boolean flowToSide(Direction side, ServerWorld world, BlockPos selfPos, boolean force) {
        BlockPos targetPos = selfPos.offset(side);

        PaPressure selfPressure = getPressure(selfPos);
        PaPressure targetPressure = getPressure(targetPos);

        if ((! force && ! shouldTransfer(
                selfPressure,
                targetPressure
        )) || ! WorldGasManager.GAS_MANAGER.canFlow(
                selfPos,
                side
        )) {
            return false;
        }

        long transferPressure = (selfPressure.value() - targetPressure.value()) / 2;

        if (force) {
            transferPressure = Math.max(
                    transferPressure,
                    1
            );
        }

        if (transferPressure > 0) {
            setPressure(
                    targetPos,
                    targetPressure.value(targetPressure.value() + transferPressure)
            );

            setPressure(
                    selfPos,
                    selfPressure.value(selfPressure.value() - transferPressure)
            );

            BlockState selfState = world.getBlockState(selfPos);

            if (selfState.getBlock() instanceof GasPassable passable) {
                passable.onGasPass(
                        world,
                        selfPos,
                        selfState,
                        side.getOpposite()
                );
            }

            return true;
        }

        return false;
    }

    public static void setPressure(BlockPos pos, PaPressure pressure) {
        BlockGas gas = WorldGasManager.GAS_MANAGER.getGas(pos);
        if (gas == null) {
            gas = new BlockGas();
            gas.pressure = new PaPressure();
        }

        gas.pressure.value(pressure.value());

        WorldGasManager.GAS_MANAGER.updateGas(pos,
                                              gas
        );
    }

    public static boolean shouldTransfer(PaPressure selfPressure, PaPressure targetPressure) {
        return selfPressure.value() > targetPressure.value();
    }

    public static PaPressure getPressure(BlockPos pos) {
        BlockGas gas = WorldGasManager.GAS_MANAGER.getGas(pos);
        if (gas == null) {
            return PressureConstants.STANDARD_ATMOSPHERIC_PRESSURE.copy();
        }
        return gas.pressure;
    }

    public boolean softTick(ServerWorld world, BlockPos pos) {
        return tryFlow(
                world,
                pos,
                false
        );
    }

    public boolean forceTick(ServerWorld world, BlockPos pos) {
        return tryFlow(
                world,
                pos,
                true
        );
    }
}
