package com.github.cao.awa.trtr.mixin.item;

import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.server.network.*;
import net.minecraft.util.math.random.*;
import org.jetbrains.annotations.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "damage(ILnet/minecraft/util/math/random/Random;Lnet/minecraft/server/network/ServerPlayerEntity;)Z", at = @At("HEAD"))
    public void test(int amount, Random random, ServerPlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        System.out.println("az");
    }
}
