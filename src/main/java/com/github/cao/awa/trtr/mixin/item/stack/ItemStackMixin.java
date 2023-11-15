package com.github.cao.awa.trtr.mixin.item.stack;

import com.github.cao.awa.trtr.item.craft.CraftingItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    private static final Logger LOGGER = LogManager.getLogger("ItemStackMixin");

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (hand == Hand.OFF_HAND) {
            ItemStack offItem = user.getOffHandStack();
            if (offItem.getItem() instanceof CraftingItem craftingItem) {
                user.setCurrentHand(Hand.OFF_HAND);
                cir.setReturnValue(TypedActionResult.consume(offItem));
            }
        }
    }

    @Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
    public void finishUsing(World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (user instanceof PlayerEntity player) {
            ItemStack mainItem = user.getMainHandStack();
            ItemStack offItem = user.getOffHandStack();
            if (getItem() instanceof CraftingItem craftingItem && offItem.getItem() == getItem()) {
                craftingItem.craft(
                        world,
                        player,
                        offItem,
                        mainItem,
                        0
                );
            }
        }
    }
}
