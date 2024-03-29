package com.github.cao.awa.trtr.mixin.block.campfire;

import com.github.cao.awa.trtr.constant.campfire.CampfireConstants;
import com.github.cao.awa.trtr.share.SharedObjectData;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

@Mixin(CampfireBlock.class)
public abstract class CampfireBlockMixin extends BlockWithEntity {
    private static final Random RANDOM = new Random();
    @Shadow
    @Final
    public static BooleanProperty LIT;

    @Shadow
    @Final
    public static BooleanProperty SIGNAL_FIRE;

    @Shadow
    @Final
    public static BooleanProperty WATERLOGGED;

    @Shadow
    @Final
    public static DirectionProperty FACING;

    @Shadow
    protected abstract boolean isSignalFireBaseBlock(BlockState state);

    protected CampfireBlockMixin(Settings settings) {
        super(settings);
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/CampfireBlock;setDefaultState(Lnet/minecraft/block/BlockState;)V"))
    private void init(CampfireBlock instance, BlockState state) {
        setDefaultState(state
                                .with(LIT,
                                      false
                                )
                                .with(SIGNAL_FIRE,
                                      false
                                )
                                .with(WATERLOGGED,
                                      false
                                )
                                .with(FACING,
                                      Direction.NORTH
                                ));
    }

    @Inject(method = "getPlacementState", at = @At("RETURN"), cancellable = true)
    public void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        cir.setReturnValue(cir.getReturnValue()
                              .with(LIT,
                                    false
                              ));
    }

    @Inject(method = "onUse", at = @At("RETURN"), cancellable = true)
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (world.isClient()) {
            return;
        }

        ItemStack mainStack = player.getStackInHand(Hand.MAIN_HAND);
        ItemStack offStack = player.getStackInHand(Hand.OFF_HAND);

        if (mainStack.getItem() == Items.FLINT && offStack.getItem() == Items.FLINT) {
            fire(world,
                 pos
            );

            cir.setReturnValue(ActionResult.SUCCESS);
        } else {
            if (addFuel(world,
                        pos,
                        player,
                        hand
            )) {
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }

    @Unique
    private void fire(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);

        if (state.get(WATERLOGGED)) {
            return;
        }

        if (RANDOM.nextInt(0,
                           10
        ) == 9) {
            BlockEntity entity = world.getBlockEntity(pos);

            List<ItemStack> list = SharedObjectData.get(entity,
                                                        "FuelList"
            );

            if (list == null) {
                return;
            }

            if (list.size() == 0) {
                ItemStack fuelStack = new ItemStack(Items.AIR);
                fuelStack.setCount(1);
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putInt("CampfireFuelTimeLeft",
                                   20
                );
                fuelStack.setNbt(nbtCompound);

                list.add(fuelStack);
            }

            world.setBlockState(pos,
                                state.with(LIT,
                                           true
                                ),
                                Block.NOTIFY_ALL
            );
        }
    }

    @Unique
    private boolean addFuel(World world, BlockPos pos, PlayerEntity player, Hand hand) {
        BlockEntity entity = world.getBlockEntity(pos);
        List<ItemStack> list = SharedObjectData.get(entity,
                                                    "FuelList"
        );

        if (list == null || list.size() == 16) {
            return false;
        }

        ItemStack stack = player.getStackInHand(hand);

        int fuelTime = CampfireConstants.fuels.getOrDefault(stack.getItem(),
                                                            - 1
        );
        if (fuelTime != - 1) {
            ItemStack fuelStack = new ItemStack(Items.AIR);
            fuelStack.setCount(1);
            NbtCompound nbtCompound = new NbtCompound();
            nbtCompound.putInt("CampfireFuelTimeLeft",
                               fuelTime
            );
            fuelStack.setNbt(nbtCompound);

            list.add(fuelStack);

            stack.decrement(1);

            return true;
        }
        return false;
    }
}
