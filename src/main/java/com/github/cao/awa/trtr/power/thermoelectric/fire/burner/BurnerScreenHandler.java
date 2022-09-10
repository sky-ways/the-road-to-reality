package com.github.cao.awa.trtr.power.thermoelectric.fire.burner;

import com.github.cao.awa.trtr.properties.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.*;

public class BurnerScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private static BlockEntityProperties<BurnerBlockEntity> properties;

    public BurnerScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(1),null);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public BlockEntityProperties<BurnerBlockEntity> getProperties() {
        return properties;
    }

    public BurnerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, BlockEntityProperties<BurnerBlockEntity> properties) {
        super(TrtrScreenHandlerType.BURNER, syncId);
        checkSize(inventory, 1);
        this.inventory = inventory;
        if (properties != null) {
            BurnerScreenHandler.properties = properties;
        }
        inventory.onOpen(playerInventory.player);

        int m;
        int l;
        for (m = 0; m < 3; ++ m) {
            for (l = 0; l < 9; ++ l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        for (m = 0; m < 9; ++ m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }

        this.addSlot(new Slot(inventory, 0, 80, 36));

    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}
