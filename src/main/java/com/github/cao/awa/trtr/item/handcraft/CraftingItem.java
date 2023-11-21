package com.github.cao.awa.trtr.item.handcraft;

import com.github.cao.awa.trtr.criteria.TrtrCriteria;
import com.github.cao.awa.trtr.dev.InventoryUtil;
import com.github.cao.awa.trtr.item.TrtrItem;
import com.github.cao.awa.trtr.recipe.handcraft.HandcraftingRecipe;
import com.github.cao.awa.trtr.recipe.handcraft.inventory.HandcraftingInventory;
import com.github.cao.awa.trtr.recipe.handcraft.util.HandcraftUtil;
import com.github.cao.awa.trtr.recipe.type.TrtrRecipeType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public abstract class CraftingItem extends TrtrItem {
    public static final int ANTI_ACCIDENTAL_TOUCH_TIME =
            // 1s used to anti-accidental touch.
            (20);

    public CraftingItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> craft(World world, PlayerEntity user, ItemStack craftingStack, ItemStack targetStack, int remainingUseTicks) {
        if (world.isClient()) {
            return TypedActionResult.pass(targetStack);
        }

        assert world.getServer() != null;

        HandcraftingInventory inventory = HandcraftingInventory.create(user,
                                                                       usedTime(remainingUseTicks),
                                                                       remainingUseTicks
        );

        Optional<RecipeEntry<HandcraftingRecipe>> optional = world.getServer()
                                                                  .getRecipeManager()
                                                                  .getFirstMatch(TrtrRecipeType.HAND_CRAFTING,
                                                                                 inventory,
                                                                                 world
                                                                  );
        if (optional.isPresent()) {
            HandcraftingRecipe recipe = optional.get()
                                                .value();

            List<ItemStack> resultStacks = recipe.craft(inventory);

            if (user instanceof ServerPlayerEntity serverPlayerEntity) {
                TrtrCriteria.HANDCRAFTED.trigger(serverPlayerEntity,
                                                 craftingStack,
                                                 targetStack,
                                                 resultStacks
                );
            }

            InventoryUtil.insertOrDrop(
                    user,
                    world,
                    resultStacks
            );

            targetStack.decrement(recipe.doMainConsume());
            craftingStack.decrement(recipe.doOffConsume());

            if (recipe.doMainConsume() != 0 && recipe.doOffConsume() != 0) {
                return TypedActionResult.success(craftingStack);
            } else if (recipe.doMainConsume() != 0) {
                return TypedActionResult.success(craftingStack);
            } else if (recipe.doOffConsume() != 0) {
                return TypedActionResult.success(targetStack);
            }
        }

        return TypedActionResult.pass(targetStack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (user instanceof PlayerEntity player) {
            HandcraftingInventory inventory = HandcraftingInventory.create(player,
                                                                           usedTime(remainingUseTicks),
                                                                           remainingUseTicks
            );

            if (world.isClient()) {
                if (usedTime(remainingUseTicks) > 0) {
                    HandcraftUtil.getMaxCrafting(world
                                                         .getRecipeManager(),
                                                 inventory,
                                                 world
                                 )
                                 .ifPresent(entry -> {
                                     float maxCraftTime = entry.value()
                                                               .range()
                                                               .max();

                                     player.addExperience(Integer.MIN_VALUE);
                                     player.addExperienceLevels(100);
                                     player.addExperience((int) (player.getNextLevelExperience() * (usedTime(remainingUseTicks) / maxCraftTime)));
                                 });
                }
            } else {
                assert world.getServer() != null;

                world.getServer()
                     .getRecipeManager()
                     .getFirstMatch(TrtrRecipeType.HAND_CRAFTING,
                                    inventory,
                                    world
                     )
                     .ifPresent(entry -> {
                         if (entry.value()
                                  .stopOnUsage()) {
                             user.stopUsingItem();
                         }
                     });
            }
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity player) {
            ItemStack mainItem = user.getMainHandStack();
            ItemStack offItem = user.getOffHandStack();
            if (offItem.getItem() == this) {
                craft(world,
                      player,
                      offItem,
                      mainItem,
                      remainingUseTicks
                );
            }

            player.addExperience(Integer.MIN_VALUE);
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            ItemStack mainItem = user.getMainHandStack();
            ItemStack offItem = user.getOffHandStack();
            if (stack.getItem() instanceof CraftingItem craftingItem && offItem.getItem() == stack.getItem()) {
                craftingItem.craft(
                        world,
                        player,
                        offItem,
                        mainItem,
                        0
                );
            }
        }
        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return ANTI_ACCIDENTAL_TOUCH_TIME + maxCraftTime();
    }

    public int maxCraftTime() {
        return 600;
    }

    public int usedTime(int remainingUseTicks) {
        return maxCraftTime() - remainingUseTicks;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
