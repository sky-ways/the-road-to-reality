package com.github.cao.awa.trtr.framework.nbt.serializer.item;

import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

public class NbtItemStackSerializer implements NbtSerializer<ItemStack> {
    @Override
    public NbtElement serialize(ItemStack stack) {
        return compound(stack :: writeNbt);
    }

    @Override
    public ItemStack deserialize(NbtElement element) {
        if (element.getNbtType() == NbtCompound.TYPE) {
            return ItemStack.fromNbt((NbtCompound) element);
        }
        return null;
    }

    @Override
    public ItemStack initializer() {
        return new ItemStack(Items.AIR);
    }
}
