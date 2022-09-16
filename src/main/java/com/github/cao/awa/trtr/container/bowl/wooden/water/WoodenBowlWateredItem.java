package com.github.cao.awa.trtr.container.bowl.wooden.water;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.advancement.criterion.*;
import net.minecraft.block.*;
import net.minecraft.block.cauldron.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.particle.*;
import net.minecraft.server.network.*;
import net.minecraft.server.world.*;
import net.minecraft.sound.*;
import net.minecraft.stat.*;
import net.minecraft.tag.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import net.minecraft.world.event.*;

public class WoodenBowlWateredItem extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:water_wooden_bowl");

    public WoodenBowlWateredItem(Settings settings) {
        super(settings);
    }

    public static Item register() {
        Settings settings = new Settings().maxCount(1);
        WoodenBowlWateredItem bowl = new WoodenBowlWateredItem(settings);
        Registry.register(Registry.ITEM, IDENTIFIER, bowl);
        CauldronBehavior behavior = (state, world, pos, player, hand, stack) -> {
            if (state.get(LeveledCauldronBlock.LEVEL) != 3) {
                if (! world.isClient) {
                    player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.BOWL)));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                    world.setBlockState(pos, state.cycle(LeveledCauldronBlock.LEVEL));
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        };
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(bowl, behavior);
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(bowl, behavior);
        return bowl;
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        ItemStack itemStack = context.getStack();
        BlockState blockState = world.getBlockState(blockPos);
        if (context.getSide() != Direction.DOWN && blockState.isIn(BlockTags.CONVERTABLE_TO_MUD)) {
            world.playSound(null, blockPos, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.PLAYERS, 1.0F, 1.0F);
            playerEntity.setStackInHand(context.getHand(), ItemUsage.exchangeStack(itemStack, playerEntity, new ItemStack(Items.BOWL)));
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(itemStack.getItem()));
            if (! world.isClient) {
                ServerWorld serverWorld = (ServerWorld) world;

                for (int i = 0; i < 5; ++ i) {
                    serverWorld.spawnParticles(ParticleTypes.SPLASH, blockPos.getX() + world.random.nextDouble(), blockPos.getY() + 1, blockPos.getZ() + world.random.nextDouble(), 1, 0.0D, 0.0D, 0.0D, 1.0D);
                }
            }

            world.playSound(null, blockPos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, blockPos);
            world.setBlockState(blockPos, Blocks.MUD.getDefaultState());
            return ActionResult.success(world.isClient);
        } else {
            return ActionResult.PASS;
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user instanceof PlayerEntity player ? player : null;
        if (playerEntity instanceof ServerPlayerEntity player) {
            Criteria.CONSUME_ITEM.trigger(player, stack);
        }
        //        if (!world.isClient) {
        //            List<StatusEffectInstance> list = PotionUtil.getPotionEffects(stack);
        //            for (StatusEffectInstance statusEffectInstance : list) {
        //                if (statusEffectInstance.getEffectType().isInstant()) {
        //                    statusEffectInstance.getEffectType().applyInstantEffect(playerEntity, playerEntity, user, statusEffectInstance.getAmplifier(), 1.0);
        //                    continue;
        //                }
        //                user.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
        //            }
        //        }
        if (playerEntity != null) {
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            if (! playerEntity.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }
        if (playerEntity == null || ! playerEntity.getAbilities().creativeMode) {
            if (stack.isEmpty()) {
                return new ItemStack(Items.BOWL);
            }
            if (playerEntity != null) {
                playerEntity.getInventory().insertStack(new ItemStack(Items.BOWL));
            }
        }
        user.emitGameEvent(GameEvent.DRINK);
        return stack;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }
}
