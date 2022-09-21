package com.github.cao.awa.trtr.ref.block;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.register.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public abstract class HeatConductionBlock<T extends HeatConductionBlockEntity<T>> extends TrtrBlockWithEntity<T> {
    private BlockEntityTicker<? super T> ticker = this::tick;

    public HeatConductionBlock(Settings settings) {
        super(settings);
    }

    public HeatConductionBlock(Settings settings, TrtrBlockRegister register) {
        super(settings, register);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    public <E extends BlockEntity> BlockEntityTicker<E> getTicker(World world, BlockState state, BlockEntityType<E> type) {
        return world.isClient ? null : checkType(type, blockEntityType(), ticker);
    }

    public void tick(World world, BlockPos pos, BlockState state, T blockEntity) {
//        if (heatHandler.hasRegistered(world, pos)) {
//            world.removeBlockEntity(pos);
//            ticker = null;
//        } else {
            blockEntity.tick(world, pos, state, blockEntity);
//        }
    }

    public void tickClient(World world, BlockPos pos, BlockState state, T blockEntity) {
    }
}
