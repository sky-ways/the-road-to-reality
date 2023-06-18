package com.github.cao.awa.trtr.dev;

import com.github.cao.awa.trtr.random.Randoms;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class InventoryUtil {
    public static void insertOrDrop(PlayerEntity player, World world, ItemStack stack) {
        if (! player.getInventory()
                    .insertStack(stack)) {
            world.spawnEntity(new ItemEntity(world,
                                             player.getX(),
                                             player.getY(),
                                             player.getZ(),
                                             stack,
                                             Randoms.d(0.1),
                                             Randoms.d(0.05),
                                             Randoms.d(0.1)
            ));
        }
    }
}
