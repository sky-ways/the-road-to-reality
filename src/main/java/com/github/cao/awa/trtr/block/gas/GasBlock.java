package com.github.cao.awa.trtr.block.gas;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.annotation.data.gen.NoModel;
import com.github.cao.awa.trtr.block.entity.TrtrBlockWithEntity;
import com.github.cao.awa.trtr.block.gas.entity.GasBlockEntity;
import com.github.cao.awa.trtr.constant.pressure.PressureConstants;
import com.github.cao.awa.trtr.pressure.pa.PaPressure;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

@Auto
@NoModel
public class GasBlock extends TrtrBlockWithEntity {
    private static final Direction[] HORIZONTAL = new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:awa");

    @Auto
    public static final AbstractBlock.Settings SETTINGS = AbstractBlock.Settings.create()
                                                                                .mapColor(MapColor.WATER_BLUE)
                                                                                .replaceable()
                                                                                .noCollision()
                                                                                .strength(100.0F)
                                                                                .dropsNothing()
                                                                                .liquid();

    @Auto
    public static GasBlockEntity ENTITY;

    @Auto
    public GasBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity living) {
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,
                                                            1,
                                                            2
            ));
        }
    }

    public void tryFlow(BlockState state, ServerWorld world, BlockPos pos) {
        PaPressure selfPressure = getPressure(world,
                                              pos
        );

        if (! flowToSide(Direction.UP,
                         world,
                         pos,
                         selfPressure,
                         2
        )) {
            List<Direction> flowToDirections = ApricotCollectionFactor.arrayList();

            for (Direction direction : HORIZONTAL) {
                if (canFlow(world,
                            pos,
                            direction
                )) {
                    flowToDirections.add(direction);
                }
            }

            boolean sideFlowed = false;

            if (flowToDirections.size() == 1) {
                sideFlowed = flowToSide(flowToDirections.get(0),
                                        world,
                                        pos,
                                        selfPressure,
                                        2
                );
            } else {
                for (Direction direction : flowToDirections) {
                    sideFlowed |= flowToSide(direction,
                                             world,
                                             pos,
                                             selfPressure,
                                             flowToDirections.size()
                    );
                }
            }

            if (! sideFlowed) {
                flowToSide(Direction.DOWN,
                           world,
                           pos,
                           selfPressure,
                           3
                );
            }
        }
    }

    public boolean canFlow(World world, BlockPos pos, Direction direction) {
        BlockPos targetPos = pos.offset(direction);

        BlockState targetState = world.getBlockState(targetPos);

        return targetState.isOf(this) || targetState.isAir();
    }

    public boolean flowToSide(Direction side, ServerWorld world, BlockPos selfPos, PaPressure selfPressure, int divider) {
        BlockPos targetPos = selfPos.offset(side);

        PaPressure targetPressure = getPressure(world,
                                                targetPos
        );

        long transferPressure = (selfPressure.value() - targetPressure.value()) / divider;

        if (transferPressure > 0) {
            if (! canFlow(world,
                          selfPos,
                          side
            )) {
                return false;
            }

            BlockState targetState = world.getBlockState(targetPos);
            if (targetState.isAir()) {
                world.setBlockState(targetPos,
                                    getDefaultState(),
                                    Block.NOTIFY_ALL
                );
            }

            setPressure(world,
                        targetPos,
                        targetPressure.value(selfPressure.value() - transferPressure)
            );

            setPressure(world,
                        selfPos,
                        selfPressure.value(selfPressure.value() - transferPressure)
            );

            return true;
        }

        return false;
    }

    public static void setPressure(World world, BlockPos pos, PaPressure pressure) {
        GasBlockEntity blockEntity = (GasBlockEntity) world.getBlockEntity(pos);
        if (blockEntity == null) {
            return;
        }

        blockEntity.pressure.value(pressure.value());
    }

    public static PaPressure getPressure(World world, BlockPos pos) {
        GasBlockEntity blockEntity = (GasBlockEntity) world.getBlockEntity(pos);
        if (blockEntity == null) {
            return PressureConstants.STANDARD_ATMOSPHERIC_PRESSURE.copy();
        }
        return blockEntity.pressure;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        GasBlockEntity blockEntity = (GasBlockEntity) world.getBlockEntity(pos);
        if (blockEntity != null) {
            blockEntity.pressure.value(blockEntity.pressure.value() + 1);
            System.out.println(blockEntity.pressure.value());
        }

        return ActionResult.SUCCESS;
    }

    public void flowingTick(BlockState state, ServerWorld world, BlockPos pos) {
        PaPressure selfPressure = getPressure(world,
                                              pos
        );

        if ((selfPressure.value() - 100) <= PressureConstants.STANDARD_ATMOSPHERIC_PRESSURE.value()) {
            world.setBlockState(pos,
                                Blocks.AIR.getDefaultState(),
                                Block.NOTIFY_ALL
            );
        } else {
            tryFlow(state,
                    world,
                    pos
            );
        }
    }
}
