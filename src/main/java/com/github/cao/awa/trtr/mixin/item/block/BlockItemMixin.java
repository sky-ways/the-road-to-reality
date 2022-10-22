package com.github.cao.awa.trtr.mixin.item.block;

import com.github.cao.awa.trtr.database.properties.*;
import net.minecraft.advancement.criterion.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.server.network.*;
import net.minecraft.sound.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.event.*;
import org.jetbrains.annotations.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(BlockItem.class)
public abstract class BlockItemMixin {
    @Shadow @Nullable public abstract ItemPlacementContext getPlacementContext(ItemPlacementContext context);

    @Shadow @Nullable protected abstract BlockState getPlacementState(ItemPlacementContext context);

    @Shadow protected abstract BlockState placeFromNbt(BlockPos pos, World world, ItemStack stack, BlockState state);

    @Shadow protected abstract SoundEvent getPlaceSound(BlockState state);

    @Shadow protected abstract boolean postPlacement(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack stack, BlockState state);

    @Shadow protected abstract boolean place(ItemPlacementContext context, BlockState state);
}
