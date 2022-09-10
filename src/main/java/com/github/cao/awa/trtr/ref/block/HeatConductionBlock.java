package com.github.cao.awa.trtr.ref.block;

import com.github.cao.awa.trtr.heat.conductor.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.ref.block.iron.*;
import com.github.zhuaidadaya.rikaishinikui.handler.affair.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public abstract class HeatConductionBlock<T extends HeatConductionBlockEntity<T>> extends TrtrBlockWithEntity<T> {
    public static final IntProperty TEMPERATURE = IntProperty.of("trtr_temperature", 0 ,2);

    protected HeatConductionBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return state.get(TEMPERATURE) == 1 ? null : new IronBlockEntity(pos, state);
        //        return new IronBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    public <E extends BlockEntity> BlockEntityTicker<E> getTicker(World world, BlockState state, BlockEntityType<E> type) {
        return checkType(type, blockEntityType(), (world1, pos, state1, blockEntity) -> Affair.of(() ->
                        tick(world1, pos, state1, blockEntity) // Do tick
                ).participateIf(() ->
                        world.isClient, // If is client, participate to affair
                        () -> tickClient(world1, pos, state1, blockEntity) // Do client tick
                ).done() // Done affair
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(TEMPERATURE));
    }

    public void tick(World world, BlockPos pos, BlockState state, T blockEntity) {
        blockEntity.tick(world, pos, state, blockEntity);
    }

    public void tickClient(World world, BlockPos pos, BlockState state, T blockEntity) {
        blockEntity.tick(world, pos, state, blockEntity);
    }
}
