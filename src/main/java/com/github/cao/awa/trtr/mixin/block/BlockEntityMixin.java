package com.github.cao.awa.trtr.mixin.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;
import org.spongepowered.asm.mixin.*;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin {
    @Shadow @Nullable public abstract World getWorld();

    @Shadow @Final protected BlockPos pos;

    @Shadow @Nullable protected World world;

    @Shadow private BlockState cachedState;
}
