package com.github.cao.awa.trtr.mixin.block;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.element.generator.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;
import java.util.function.*;

import static com.github.cao.awa.trtr.TrtrMod.*;

@Mixin(Block.class)
public abstract class BlockMixin {
    private static void dropStack(World world, BlockPos pos, ItemStack stack, Consumer<ItemEntity> action) {
        float f = EntityType.ITEM.getHeight() / 2.0F;
        double d = (double)((float)pos.getX() + 0.5F) + MathHelper.nextDouble(world.random, -0.25D, 0.25D);
        double e = (double)((float)pos.getY() + 0.5F) + MathHelper.nextDouble(world.random, -0.25D, 0.25D) - (double)f;
        double g = (double)((float)pos.getZ() + 0.5F) + MathHelper.nextDouble(world.random, -0.25D, 0.25D);
        dropStack(world, () -> {
            return new ItemEntity(world, d, e, g, stack);
        }, stack, action);
    }

    @Shadow
    public static List<ItemStack> getDroppedStacks(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack) {
        return null;
    }

    @Inject(method = "onBreak", at = @At("HEAD"))
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        heatManager.unload(
                world,
                pos
        );
        BlockEntity entity = world.getBlockEntity(pos);
        if (entity instanceof ChemicalElementGenerator elemental) {
            elemental.generateElement();
        }
    }

    @Redirect(method = "afterBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V"))
    public void afterBreak(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack) {
        if (world instanceof ServerWorld serverWorld) {
            getDroppedStacks(state,serverWorld, pos, blockEntity, entity, stack).forEach((stackx) -> {
                dropStack(world, pos, stackx, itemEntity -> {
                    if (blockEntity instanceof ChemicalElementGenerator elementGenerator) {
                        elementGenerator.generateElement();
                    }
                    if (itemEntity instanceof PropertiesAccessible accessible) {
                        if (blockEntity instanceof PropertiesAccessible blockAccessible) {
                            accessible.setProperties(blockAccessible.getProperties());
                            accessible.getProperties().createAccess(itemEntity.getStack().getOrCreateNbt());
                        }
                    }
                });
            });
            state.onStacksDropped(serverWorld, pos, stack, true);
        }

    }

    private static void dropStack(World world, Supplier<ItemEntity> itemEntitySupplier, ItemStack stack, Consumer<ItemEntity> action) {
        if (!world.isClient && !stack.isEmpty() && world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS)) {
            ItemEntity itemEntity = itemEntitySupplier.get();
            action.accept(itemEntity);
            itemEntity.setToDefaultPickupDelay();
            world.spawnEntity(itemEntity);
        }
    }
}
