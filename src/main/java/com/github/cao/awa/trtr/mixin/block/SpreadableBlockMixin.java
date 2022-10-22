package com.github.cao.awa.trtr.mixin.block;

import com.github.cao.awa.trtr.ref.block.gentle.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(SpreadableBlock.class)
public class SpreadableBlockMixin {
    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    private static void canSurvive(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (state.getBlock() instanceof GentleBlock gentleBlock && !gentleBlock.willCrushSpreadableBlock()) {
            cir.setReturnValue(true);
        }
    }
}
