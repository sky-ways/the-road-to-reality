package com.github.cao.awa.trtr.mixin.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import static com.github.cao.awa.trtr.TrtrMod.heatHandler;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "onBreak", at = @At("HEAD"))
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        heatHandler.unload(world, pos);
    }
}
