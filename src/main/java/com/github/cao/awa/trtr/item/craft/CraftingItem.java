package com.github.cao.awa.trtr.item.craft;

import com.github.cao.awa.trtr.item.TrtrItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public abstract class CraftingItem extends TrtrItem {
    public static final int ANTI_ACCIDENTAL_TOUCH_TIME =
            // 1s used to anti-accidental touch.
            (20);

    public CraftingItem(Settings settings) {
        super(settings);
    }

    public abstract TypedActionResult<ItemStack> craft(World world, PlayerEntity user, ItemStack craftingStack, ItemStack targetStack);

    public void scaledCraft(World world, PlayerEntity user, ItemStack craftingStack, ItemStack targetStack, int remainingUseTicks) {

    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity player) {
            ItemStack mainItem = user.getMainHandStack();
            ItemStack offItem = user.getOffHandStack();
            if (offItem.getItem() instanceof CraftingItem && offItem.getItem() == this) {
                scaledCraft(world,
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
        return 16;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
