package com.github.cao.awa.trtr.item.craft;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public abstract class CraftingItem extends Item {
    public CraftingItem(Settings settings) {
        super(settings);
    }

    public abstract TypedActionResult<ItemStack> craft(World world, PlayerEntity user, ItemStack craftingStack, ItemStack targetStack);

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 16;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
