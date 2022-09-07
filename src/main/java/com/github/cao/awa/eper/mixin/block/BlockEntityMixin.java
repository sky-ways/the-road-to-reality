package com.github.cao.awa.eper.mixin.block;

import com.github.cao.awa.eper.power.thermoelectric.fire.burner.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin {
    @Shadow @Nullable public abstract World getWorld();

    @Shadow @Final protected BlockPos pos;

    @Shadow @Nullable protected World world;

    @Shadow private BlockState cachedState;
}
