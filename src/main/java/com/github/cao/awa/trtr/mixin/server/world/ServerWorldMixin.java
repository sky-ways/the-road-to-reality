package com.github.cao.awa.trtr.mixin.server.world;

import com.github.cao.awa.trtr.gas.GasPassable;
import com.github.cao.awa.trtr.gas.manager.WorldGasManager;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
    @Inject(method = "tick", at = @At("RETURN"))
    public void tick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        WorldGasManager.GAS_MANAGER.tick();
    }

    @Inject(method = "onBlockChanged", at = @At("HEAD"))
    public void onBlockChanged(BlockPos pos, BlockState oldBlock, BlockState newBlock, CallbackInfo ci) {
        if (! newBlock.isAir() && ! (newBlock.getBlock() instanceof GasPassable)) {
            WorldGasManager.GAS_MANAGER.removeGas(pos);
        }
    }
}
