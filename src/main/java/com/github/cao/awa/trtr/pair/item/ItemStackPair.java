package com.github.cao.awa.trtr.pair.item;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;

public record ItemStackPair(ItemStack main, ItemStack off) {
    public void write(PacketByteBuf buf) {
        buf.writeItemStack(main());
        buf.writeItemStack(off());
    }

    public static ItemStackPair create(PacketByteBuf buf) {
        ItemStack mainStack = buf.readItemStack();
        ItemStack offStack = buf.readItemStack();

        return new ItemStackPair(
                mainStack,
                offStack
        );
    }
}