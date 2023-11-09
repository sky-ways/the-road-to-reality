package com.github.cao.awa.trtr.block;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.TrtrMod;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@Auto
public abstract class TrtrBlockWithEntity extends BlockWithEntity {
    protected TrtrBlockWithEntity(Settings settings) {
        super(settings);
    }

    @Auto
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return TrtrMod.BLOCK_FRAMEWORK.createBlockEntity(this,
                                                         pos,
                                                         state
        );
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Auto
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type,
                              TrtrMod.BLOCK_FRAMEWORK.entityType(this.getClass()),
                              TrtrMod.BLOCK_FRAMEWORK :: entityTick
        );
    }

    // Append properties to state builder.
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        TrtrMod.BLOCK_FRAMEWORK.properties(this,
                                           builder
        );
    }

    public static <T extends BlockEntity> T getBlockEntity(World world, BlockPos pos) {
        return EntrustEnvironment.cast(world.getBlockEntity(pos));
    }
}
