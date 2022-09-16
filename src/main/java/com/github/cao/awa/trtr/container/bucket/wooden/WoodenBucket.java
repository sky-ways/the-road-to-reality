package com.github.cao.awa.trtr.container.bucket.wooden;

import com.github.cao.awa.trtr.type.*;
import net.minecraft.advancement.criterion.*;
import net.minecraft.block.*;
import net.minecraft.block.cauldron.*;
import net.minecraft.entity.player.*;
import net.minecraft.fluid.*;
import net.minecraft.item.*;
import net.minecraft.server.network.*;
import net.minecraft.stat.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import net.minecraft.world.event.*;

public class WoodenBucket extends BucketItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:wooden_bucket");
    public static final Identifier WATER_IDENTIFIER = new Identifier("trtr:water_wooden_bucket");
    private final Fluid fluid;

    public WoodenBucket(Fluid fluid, Settings settings) {
        super(fluid, settings);
        this.fluid = fluid;
    }

    public static Item register(Identifier identifier, Fluid fluid) {
        Settings settings = new Settings().maxCount(fluid == Fluids.EMPTY ? 16 : 1);
        WoodenBucket bucket = new WoodenBucket(fluid, settings);
        Registry.register(Registry.ITEM, identifier, bucket);
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(Items.WATER_BUCKET, CauldronBehavior.FILL_WITH_WATER);
        return bucket;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = raycast(world, user, this.fluid == Fluids.EMPTY ? RaycastContext.FluidHandling.SOURCE_ONLY : RaycastContext.FluidHandling.NONE);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        } else if (blockHitResult.getType() != HitResult.Type.BLOCK) {
            return TypedActionResult.pass(itemStack);
        } else {
            BlockPos blockPos = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getSide();
            BlockPos blockPos2 = blockPos.offset(direction);
            if (world.canPlayerModifyAt(user, blockPos) && user.canPlaceOn(blockPos2, direction, itemStack)) {
                BlockState blockState;
                if (this.fluid == Fluids.EMPTY) {
                    blockState = world.getBlockState(blockPos);
                    if (blockState.getBlock() instanceof FluidDrainable fluidDrainable) {
                        ItemStack itemStack2 = getBucket(blockState.getFluidState().getFluid());
                        if (itemStack2.isEmpty()) {
                            itemStack.setCount(itemStack.getCount() - 1);
                            return TypedActionResult.pass(itemStack);
                        }
                        fluidDrainable.tryDrainFluid(world, blockPos, blockState);
                        user.incrementStat(Stats.USED.getOrCreateStat(this));
                        fluidDrainable.getBucketFillSound().ifPresent((sound) -> {
                            user.playSound(sound, 1.0F, 1.0F);
                        });
                        world.emitGameEvent(user, GameEvent.FLUID_PICKUP, blockPos);
                        ItemStack itemStack3 = ItemUsage.exchangeStack(itemStack, user, itemStack2);
                        if (! world.isClient) {
                            Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity) user, itemStack2);
                        }

                        return TypedActionResult.success(itemStack3, world.isClient());
                    }

                    return TypedActionResult.fail(itemStack);
                } else {
                    blockState = world.getBlockState(blockPos);
                    BlockPos blockPos3 = blockState.getBlock() instanceof FluidFillable && this.fluid == Fluids.WATER ? blockPos : blockPos2;
                    if (this.placeFluid(user, world, blockPos3, blockHitResult)) {
                        this.onEmptied(user, world, itemStack, blockPos3);
                        if (user instanceof ServerPlayerEntity) {
                            Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity) user, blockPos3, itemStack);
                        }

                        user.incrementStat(Stats.USED.getOrCreateStat(this));
                        return TypedActionResult.success(getEmptiedStack(itemStack, user), world.isClient());
                    } else {
                        return TypedActionResult.fail(itemStack);
                    }
                }
            } else {
                return TypedActionResult.fail(itemStack);
            }
        }
    }

    public static ItemStack getEmptiedStack(ItemStack stack, PlayerEntity player) {
        return !player.getAbilities().creativeMode ? new ItemStack(TrtrItems.WOODEN_BUCKET) : stack;
    }

    public static ItemStack getBucket(Fluid fluid) {
        if (fluid instanceof WaterFluid) {
            return TrtrItems.WATER_WOODEN_BUCKET.getDefaultStack();
        } else if (fluid instanceof EmptyFluid) {

        }
        //        else if (fluid instanceof LavaFluid) {
        //        }
        return ItemStack.EMPTY;
    }
}
