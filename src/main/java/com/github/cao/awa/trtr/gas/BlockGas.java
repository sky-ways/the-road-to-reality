package com.github.cao.awa.trtr.gas;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.annotation.serializer.AutoNbt;
import com.github.cao.awa.trtr.constant.pressure.PressureConstants;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializable;
import com.github.cao.awa.trtr.pressure.pa.PaPressure;
import com.github.cao.awa.trtr.random.Randoms;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.List;

@Auto
public class BlockGas implements NbtSerializable {
    private static final Direction[] HORIZONTAL = new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

    @Auto
    @AutoNbt
    public PaPressure pressure;

    public boolean tryFlow(BlockPos pos, boolean force) {
        boolean flowResult = flowToSide(Direction.UP,
                                        pos,
                                        2,
                                        force
        );

//        if (Randoms.b()) {
        List<Direction> flowToDirections = ApricotCollectionFactor.arrayList();

        for (Direction direction : HORIZONTAL) {
            if (canFlow(
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
                                     pos,
                                     2,
                                     force
            );

            flowResult |= sideFlowed;
        }
//            }

        if (! sideFlowed || Randoms.b()) {
            flowResult = flowToSide(Direction.DOWN,
                                    pos,
                                    2,
                                    force
            );
        }
//        }
        return flowResult;
    }

    public static boolean canFlow(BlockPos pos, Direction direction) {
        BlockPos targetPos = pos.offset(direction);

        return WorldGasManager.GAS_MANAGER.isValidGas(targetPos);
    }

    public boolean flowToSide(Direction side, BlockPos selfPos, int divider, boolean force) {
        BlockPos targetPos = selfPos.offset(side);

        PaPressure selfPressure = getPressure(selfPos);
        PaPressure targetPressure = getPressure(targetPos);

        long transferPressure = (selfPressure.value() - targetPressure.value()) / divider;

        if (force) {
            transferPressure = Math.max(
                    transferPressure,
                    1
            );
        }

        if (transferPressure > 0) {
            if (! canFlow(
                    selfPos,
                    side
            )) {
                return false;
            }

            setPressure(
                    targetPos,
                    targetPressure.value(targetPressure.value() + transferPressure)
            );

            setPressure(
                    selfPos,
                    selfPressure.value(selfPressure.value() - transferPressure)
            );

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

    public static PaPressure getPressure(BlockPos pos) {
        BlockGas gas = WorldGasManager.GAS_MANAGER.getGas(pos);
        if (gas == null) {
            return PressureConstants.STANDARD_ATMOSPHERIC_PRESSURE.copy();
        }
        return gas.pressure;
    }

    public boolean softTick(BlockPos pos) {
        if (! WorldGasManager.GAS_MANAGER.shouldFlowToAny(pos)) {
            return false;
        } else {
            return tryFlow(
                    pos,
                    false
            );
        }
    }

    public boolean forceTick(BlockPos pos) {
        return tryFlow(
                pos,
                true
        );
    }
}
