package com.github.cao.awa.trtr.mixin.world;

import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.util.profiler.*;
import net.minecraft.world.*;
import net.minecraft.world.chunk.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(World.class)
public abstract class WorldMixin implements WorldAccess {
    @Shadow public abstract void onBlockChanged(BlockPos pos, BlockState oldBlock, BlockState newBlock);

    @Shadow public abstract void updateListeners(BlockPos pos, BlockState oldState, BlockState newState, int flags);

    @Shadow public abstract boolean isDebugWorld();

    @Shadow @Final public boolean isClient;

    @Shadow public abstract WorldChunk getWorldChunk(BlockPos pos);

    @Shadow public abstract Profiler getProfiler();

    @Shadow public abstract void scheduleBlockRerenderIfNeeded(BlockPos pos, BlockState old, BlockState updated);

    @Shadow public abstract void updateComparators(BlockPos pos, Block block);

    @Inject(method = "setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;II)Z", at = @At("HEAD"), cancellable = true)
    public void setAirState(BlockPos pos, BlockState state, int flags, int maxUpdateDepth, CallbackInfoReturnable<Boolean> cir) {
        if (state.isOf(TrtrBlocks.AIR)) {
            cir.setReturnValue(setBlockState0(pos, TrtrBlocks.DUMP_AIR.getDefaultState(), flags, maxUpdateDepth));
        }
    }

    public boolean setBlockState0(BlockPos pos, BlockState state, int flags, int maxUpdateDepth) {
        if (this.isOutOfHeightLimit(pos)) {
            return false;
        } else if (!this.isClient && this.isDebugWorld()) {
            return false;
        } else {
            WorldChunk worldChunk = this.getWorldChunk(pos);
            Block block = state.getBlock();
            BlockState blockState = worldChunk.setBlockState(pos, state, (flags & 64) != 0);
            if (blockState == null) {
                return false;
            } else {
                BlockState blockState2 = this.getBlockState(pos);
                if ((flags & 128) == 0 && blockState2 != blockState && (blockState2.getOpacity(this, pos) != blockState.getOpacity(this, pos) || blockState2.getLuminance() != blockState.getLuminance() || blockState2.hasSidedTransparency() || blockState.hasSidedTransparency())) {
                    this.getProfiler().push("queueCheckLight");
                    this.getChunkManager().getLightingProvider().checkBlock(pos);
                    this.getProfiler().pop();
                }

                if (blockState2 == state) {
                    if (blockState != blockState2) {
                        this.scheduleBlockRerenderIfNeeded(pos, blockState, blockState2);
                    }

                    if ((flags & 2) != 0 && (!this.isClient || (flags & 4) == 0) && (this.isClient || worldChunk.getLevelType() != null && worldChunk.getLevelType().isAfter(ChunkHolder.LevelType.TICKING))) {
                        this.updateListeners(pos, blockState, state, flags);
                    }

                    if ((flags & 1) != 0) {
                        this.updateNeighbors(pos, blockState.getBlock());
                        if (!this.isClient && state.hasComparatorOutput()) {
                            this.updateComparators(pos, block);
                        }
                    }

                    if ((flags & 16) == 0 && maxUpdateDepth > 0) {
                        int i = flags & -34;
                        blockState.prepare(this, pos, i, maxUpdateDepth - 1);
                        state.updateNeighbors(this, pos, i, maxUpdateDepth - 1);
                        state.prepare(this, pos, i, maxUpdateDepth - 1);
                    }

                    this.onBlockChanged(pos, blockState, blockState2);
                }

                return true;
            }
        }
    }
}
