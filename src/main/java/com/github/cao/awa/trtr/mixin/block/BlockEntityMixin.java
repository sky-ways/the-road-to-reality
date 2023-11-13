package com.github.cao.awa.trtr.mixin.block;

import com.github.cao.awa.trtr.share.SharedObjectData;
import net.minecraft.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntity.class)
public class BlockEntityMixin {
    @Inject(method = "markRemoved", at = @At("HEAD"))
    public void markRemoved(CallbackInfo ci) {
        SharedObjectData.remove(this);
    }
}
