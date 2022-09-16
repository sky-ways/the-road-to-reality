package com.github.cao.awa.trtr.ref.block;

import com.github.cao.awa.trtr.heat.conductor.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.ref.block.iron.*;
import com.github.zhuaidadaya.rikaishinikui.handler.affair.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import static com.github.cao.awa.trtr.TrtrMod.heatHandler;

public abstract class HeatConductionBlock<T extends HeatConductionBlockEntity<T>> extends TrtrBlockWithEntity<T> {
    private BlockEntityTicker<? super T> ticker = this::tick;

    protected HeatConductionBlock(Settings settings) {
        super(settings);
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
