package com.github.cao.awa.trtr.mixin.world.chunk.selection;

import net.minecraft.block.*;
import net.minecraft.fluid.*;
import net.minecraft.world.chunk.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ChunkSection.class)
public class ChunkSelectionMixin {
    @Shadow
    @Final
    private PalettedContainer<BlockState> blockStateContainer;

    @Shadow
    private short randomTickableBlockCount;

    @Shadow
    private short nonEmptyBlockCount;

    @Shadow
    private short nonEmptyFluidCount;

    @Redirect(method = "setBlockState(IIILnet/minecraft/block/BlockState;)Lnet/minecraft/block/BlockState;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/ChunkSection;setBlockState(IIILnet/minecraft/block/BlockState;Z)Lnet/minecraft/block/BlockState;"))
    public BlockState setState(ChunkSection instance, int x, int y, int z, BlockState state, boolean lock) {
        return this.setState(
                x,
                y,
                z,
                state,
                lock
        );
    }

    public BlockState setState(int x, int y, int z, BlockState state, boolean lock) {
        BlockState blockState = lock ? this.blockStateContainer.swap(
                x,
                y,
                z,
                state
        ) : this.blockStateContainer.swapUnsafe(
                x,
                y,
                z,
                state
        );

        if (! blockState.isAir()) {
            -- this.nonEmptyBlockCount;
            if (blockState.hasRandomTicks()) {
                -- this.randomTickableBlockCount;
            }
        }

        FluidState fluidState = blockState.getFluidState();
        if (! fluidState.isEmpty()) {
            -- this.nonEmptyFluidCount;
        }

        if (! state.isAir()) {
            ++ this.nonEmptyBlockCount;
            if (state.hasRandomTicks()) {
                ++ this.randomTickableBlockCount;
            }
        }

        FluidState fluidState2 = state.getFluidState();
        if (! fluidState2.isEmpty()) {
            ++ this.nonEmptyFluidCount;
        }

        return blockState;
    }
}
