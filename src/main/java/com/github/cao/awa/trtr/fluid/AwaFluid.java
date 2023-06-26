package com.github.cao.awa.trtr.fluid;

import com.github.cao.awa.trtr.TrtrMod;
import com.github.cao.awa.trtr.annotation.dev.DevOnly;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.item.TrtrItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

import java.util.Optional;

@DevOnly
public abstract class AwaFluid extends WaterFluid {
    public Fluid getFlowing() {
        return TrtrMod.FLOWING_AWA;
    }

    public Fluid getStill() {
        return TrtrMod.AWA;
    }

    public Item getBucketItem() {
        return TrtrItems.get(AwaBucket.class);
    }

    public int getFlowSpeed(WorldView world) {
        return 4;
    }

    public BlockState toBlockState(FluidState state) {
        return TrtrBlocks.get(AwaBlock.class)
                         .getDefaultState()
                         .with(FluidBlock.LEVEL,
                               getBlockStateLevel(state)
                         );
    }

    public boolean matchesType(Fluid fluid) {
        return fluid == TrtrMod.AWA || fluid == TrtrMod.FLOWING_AWA;
    }

    public boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && ! fluid.isIn(FluidTags.WATER);
    }

    protected float getBlastResistance() {
        return 100.0F;
    }

    public Optional<SoundEvent> getBucketFillSound() {
        return Optional.of(SoundEvents.ITEM_BUCKET_FILL);
    }

    public static class Flowing extends AwaFluid {
        public Flowing() {
        }

        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

        public boolean isStill(FluidState state) {
            return false;
        }
    }

    public static class Still extends AwaFluid {
        public Still() {
        }

        public int getLevel(FluidState state) {
            return 8;
        }

        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
