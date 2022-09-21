package com.github.cao.awa.trtr.ref.item.bowl;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.cauldron.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.sound.*;
import net.minecraft.stat.*;
import net.minecraft.tag.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import net.minecraft.world.event.*;

public class BowlItem extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("minecraft:bowl");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    public void register() {
        super.register();
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(this, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(TrtrItems.WOODEN_BOWL_WATERED)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
            }
            return ActionResult.success(world.isClient);
        });
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        //        List<AreaEffectCloudEntity> list = world.getEntitiesByClass(AreaEffectCloudEntity.class, user.getBoundingBox().expand(2.0D), (entity) -> entity != null && entity.isAlive() && entity.getOwner() instanceof EnderDragonEntity);
        ItemStack itemStack = user.getStackInHand(hand);
        //        if (!list.isEmpty()) {
        //            AreaEffectCloudEntity areaEffectCloudEntity = list.get(0);
        //            areaEffectCloudEntity.setRadius(areaEffectCloudEntity.getRadius() - 0.5F);
        //            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        //            world.emitGameEvent(user, GameEvent.FLUID_PICKUP, user.getPos());
        //            return TypedActionResult.success(this.fill(itemStack, user, new ItemStack(Items.DRAGON_BREATH)), world.isClient());
        //        } else {
        BlockHitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
        if (hitResult.getType() != HitResult.Type.MISS) {
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos blockPos = hitResult.getBlockPos();
                if (! world.canPlayerModifyAt(user, blockPos)) {
                    return TypedActionResult.pass(itemStack);
                }

                if (world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
                    world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    world.emitGameEvent(user, GameEvent.FLUID_PICKUP, blockPos);
                    return TypedActionResult.success(this.fill(itemStack, user, new ItemStack(TrtrItems.WOODEN_BOWL_WATERED)), world.isClient());
                }
            }
        }
        return TypedActionResult.pass(itemStack);
        //        }
    }

    protected ItemStack fill(ItemStack stack, PlayerEntity player, ItemStack outputStack) {
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return ItemUsage.exchangeStack(stack, player, outputStack);
    }
}
