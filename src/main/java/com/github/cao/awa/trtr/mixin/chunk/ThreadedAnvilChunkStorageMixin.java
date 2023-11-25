package com.github.cao.awa.trtr.mixin.chunk;

import com.github.cao.awa.trtr.gas.WorldGasManager;
import net.minecraft.server.world.ChunkHolder;
import net.minecraft.server.world.ThreadedAnvilChunkStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ThreadedAnvilChunkStorage.class)
public class ThreadedAnvilChunkStorageMixin {
    @Inject(method = "setLevel", at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/longs/Long2ObjectLinkedOpenHashMap;put(JLjava/lang/Object;)Ljava/lang/Object;"))
    public void setLevel(long pos, int level, ChunkHolder holder, int i, CallbackInfoReturnable<ChunkHolder> cir) {
        if (holder.getWorldChunk() != null) {
            WorldGasManager.GAS_MANAGER.requestTicket(holder.getWorldChunk());
        }
    }

    @Inject(method = "tryUnloadChunk", at = @At("RETURN"))
    public void unloadChunk(long pos, ChunkHolder holder, CallbackInfo ci) {
        if (holder.getWorldChunk() != null) {
            WorldGasManager.GAS_MANAGER.expireChunkTicket(holder.getWorldChunk());
        }
    }
}
