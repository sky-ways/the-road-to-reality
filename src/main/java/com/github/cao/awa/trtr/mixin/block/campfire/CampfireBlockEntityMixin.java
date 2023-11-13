package com.github.cao.awa.trtr.mixin.block.campfire;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.share.SharedObjectData;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(CampfireBlockEntity.class)
public abstract class CampfireBlockEntityMixin {
    @Unique
    private final List<ItemStack> fuels = ApricotCollectionFactor.arrayList();

    @Inject(method = "litServerTick", at = @At("RETURN"))
    private static void tick(World world, BlockPos pos, BlockState state, CampfireBlockEntity campfire, CallbackInfo ci) {
        CampfireBlockEntityMixin mixin = castToMixin(campfire);

        if (mixin.fuels.size() == 0) {
            world.setBlockState(pos,
                                state.with(CampfireBlock.LIT,
                                           false
                                ),
                                Block.NOTIFY_ALL
            );
        } else {
            List<ItemStack> removes = ApricotCollectionFactor.arrayList();

            for (ItemStack fuel : mixin.fuels) {
                NbtCompound stackCompound = fuel.getOrCreateNbt();
                int leftTime = stackCompound.getInt("CampfireFuelTimeLeft");

                if (leftTime != 0) {
                    stackCompound.putInt("CampfireFuelTimeLeft",
                                         leftTime - 1
                    );
                    fuel.setNbt(stackCompound);
                } else {
                    removes.add(fuel);
                }
            }

            mixin.fuels.removeAll(removes);

            campfire.markDirty();
        }
    }

    @Inject(method = "readNbt", at = @At("RETURN"))
    private void readNbt(NbtCompound nbt, CallbackInfo ci) {
        for (NbtElement element : nbt.getList("FuelInventory",
                                              10
        )) {
            NbtCompound compound = (NbtCompound) element;

            this.fuels.add(ItemStack.fromNbt(compound));
        }

        SharedObjectData.set(this,
                             "FuelList",
                             this.fuels
        );
    }

    @Inject(method = "writeNbt", at = @At("RETURN"))
    private void writeNbt(NbtCompound nbt, CallbackInfo ci) {
        NbtList list = new NbtList();

        for (ItemStack stack : this.fuels) {
            NbtCompound stackCompound = new NbtCompound();
            stack.writeNbt(stackCompound);
            list.add(stackCompound);
        }

        nbt.put("FuelInventory",
                list
        );
    }

    @Unique
    private static CampfireBlockEntityMixin castToMixin(CampfireBlockEntity entity) {
        return (CampfireBlockEntityMixin) (Object) entity;
    }

    private CampfireBlockEntity castToEntity() {
        return (CampfireBlockEntity) (Object) this;
    }
}
