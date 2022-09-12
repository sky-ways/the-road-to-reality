package com.github.cao.awa.trtr.mixin.item;

import net.minecraft.item.*;
import net.minecraft.nbt.*;
import org.jetbrains.annotations.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "getMaxDamage", at = @At("HEAD"), cancellable = true)
    public void getMaxDamage(CallbackInfoReturnable<Integer> cir) {
//        NbtCompound nbt = getNbt();
//        if (nbt != null) {
//            int max = nbt.getInt("maxDamage");
//            if (max > 0) {
//                cir.setReturnValue(max);
//            } else {
//                createMaxDamage(nbt);
//            }
//        } else {
//            createMaxDamage(getOrCreateNbt());
//        }
    }

    public void createMaxDamage(NbtCompound nbt) {
//        nbt.putInt("maxDamage", 114);
    }

    @Shadow
    @Nullable
    public abstract NbtCompound getNbt();

    @Shadow
    public abstract NbtCompound getOrCreateNbt();

    @Inject(method = "isDamageable", at = @At("HEAD"), cancellable = true)
    public void isDamageable(CallbackInfoReturnable<Boolean> cir) {
//        NbtCompound nbt = getNbt();
//        if (nbt != null) {
//            int max = nbt.getInt("maxDamage");
//            cir.setReturnValue(max > 0);
//        }
    }
}
