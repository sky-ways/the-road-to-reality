package com.github.cao.awa.trtr.recipe.handcraft.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class HandcraftingInventory implements Inventory {
    private ItemStack mainStack;
    private ItemStack offStack;
    private final int usedTicks;
    private final int remainingTicks;

    public HandcraftingInventory(ItemStack mainStack, ItemStack offStack, int usedTicks, int remainingTicks) {
        this.mainStack = mainStack;
        this.offStack = offStack;
        this.usedTicks = usedTicks;
        this.remainingTicks = remainingTicks;
    }

    public int remainingTicks() {
        return this.remainingTicks;
    }

    public static HandcraftingInventory create(PlayerEntity player, int usedTicks, int remainingTicks) {
        return new HandcraftingInventory(player.getMainHandStack(),
                                         player.getOffHandStack(),
                                         usedTicks,
                                         remainingTicks
        );
    }

    public int usedTicks() {
        return this.usedTicks;
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return this.mainStack == null && this.offStack == null;
    }

    @Override
    public ItemStack getStack(int slot) {
        return slot == 0 ? this.mainStack : this.offStack;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack stack = slot == 0 ? this.mainStack : this.offStack;
        stack.decrement(amount);
        return stack;
    }

    public ItemStack mainStack() {
        return this.mainStack;
    }

    public void mainStack(ItemStack mainStack) {
        this.mainStack = mainStack;
    }

    public ItemStack offStack() {
        return this.offStack;
    }

    public void offStack(ItemStack offStack) {
        this.offStack = offStack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack stack = null;
        if (slot == 0) {
            stack = this.mainStack;
            this.mainStack = null;
        } else if (slot == 1) {
            stack = this.offStack;
            this.offStack = null;
        }
        return stack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (slot == 0) {
            this.mainStack = stack;
        } else if (slot == 1) {
            this.offStack = stack;
        }
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return false;
    }

    @Override
    public void clear() {
        this.mainStack = null;
        this.offStack = null;
    }
}
