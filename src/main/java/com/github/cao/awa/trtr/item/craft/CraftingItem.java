package com.github.cao.awa.trtr.item.craft;

import com.github.cao.awa.trtr.dev.InventoryUtil;
import com.github.cao.awa.trtr.item.TrtrItem;
import com.github.cao.awa.trtr.recipe.handing.HandCraftingRecipe;
import com.github.cao.awa.trtr.recipe.handing.inventory.HandCraftingInventory;
import com.github.cao.awa.trtr.recipe.type.TrtrRecipeType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

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

        HandCraftingInventory inventory = HandCraftingInventory.create(user,
                                                                       usedTime(remainingUseTicks),
                                                                       remainingUseTicks
        );

        Optional<RecipeEntry<HandCraftingRecipe>> optional = world.getServer()
                                                                  .getRecipeManager()
                                                                  .getFirstMatch(TrtrRecipeType.HAND_CRAFTING,
                                                                                 inventory,
                                                                                 world
                                                                  );
        if (optional.isPresent()) {
            HandCraftingRecipe recipe = optional.get()
                                                .value();

            ItemStack resultStack = recipe.craft(inventory,
                                                 world.getRegistryManager()
            );

            InventoryUtil.insertOrDrop(
                    user,
                    world,
                    resultStack
            );

            if (recipe.doMainConsume() && recipe.doOffConsume()) {
                return TypedActionResult.success(resultStack);
            } else if (recipe.doMainConsume()) {
                return TypedActionResult.consume(craftingStack);
            } else {
                return TypedActionResult.consume(targetStack);
            }
        }

        return TypedActionResult.pass(targetStack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (world.isClient()) {
            return;
        }

        if (user instanceof PlayerEntity player) {
            HandCraftingInventory inventory = HandCraftingInventory.create(player,
                                                                           usedTime(remainingUseTicks),
                                                                           remainingUseTicks
            );

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
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return ANTI_ACCIDENTAL_TOUCH_TIME + maxCraftTime();
    }

    public int maxCraftTime() {
        return 16;
    }

    public int usedTime(int remainingUseTicks) {
        return maxCraftTime() - remainingUseTicks;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
