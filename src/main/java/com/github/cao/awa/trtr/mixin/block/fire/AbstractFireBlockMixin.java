package com.github.cao.awa.trtr.mixin.block.fire;

import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(AbstractFireBlock.class)
public abstract class AbstractFireBlockMixin {
    @Shadow
    public static BlockState getState(BlockView world, BlockPos pos) {
        return null;
    }

    @Shadow
    protected static boolean shouldLightPortalAt(World world, BlockPos pos, Direction direction) {
        return false;
    }

    @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
    private static void canPlaceAt(World world, BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.getBlock() != TrtrBlocks.TEST_AIR && ! blockState.isAir()) {
            cir.setReturnValue(false);
        } else {
            cir.setReturnValue(getState(
                    world,
                    pos
            ).canPlaceAt(
                    world,
                    pos
            ) || shouldLightPortalAt(
                    world,
                    pos,
                    direction
            ));
        }
    }
}
