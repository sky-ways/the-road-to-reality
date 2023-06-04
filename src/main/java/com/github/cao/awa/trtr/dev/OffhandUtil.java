package com.github.cao.awa.trtr.dev;

import net.minecraft.item.ItemStack;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class OffhandUtil {
    public static void useMain(ItemStack mainHand, ItemStack offHand, BiFunction<ItemStack, ItemStack, Boolean> predicate, Consumer<ItemStack> mainHandAction) {
        if (predicate.apply(mainHand,
                            offHand
        )) {
            mainHandAction.accept(mainHand);
        }
    }

    public static void useOff(ItemStack mainHand, ItemStack offHand, BiFunction<ItemStack, ItemStack, Boolean> predicate, Consumer<ItemStack> offHandAction) {
        if (predicate.apply(mainHand,
                            offHand
        )) {
            offHandAction.accept(offHand);
        }
    }

    public static void use(ItemStack mainHand, ItemStack offHand, BiFunction<ItemStack, ItemStack, ItemStack> switcher, Consumer<ItemStack> action) {
        action.accept(switcher.apply(mainHand,
                                     offHand
        ));
    }
}
