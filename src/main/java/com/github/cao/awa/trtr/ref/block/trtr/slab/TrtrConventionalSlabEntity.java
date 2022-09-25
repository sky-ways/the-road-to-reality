package com.github.cao.awa.trtr.ref.block.trtr.slab;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.tool.hammer.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.table.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class TrtrConventionalSlabEntity extends TrtrSlabBlockEntity {
    public TrtrConventionalSlabEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.SLAB_ENTITY, pos, state);
    }

    @Override
    public void thump(World world, BlockPos pos, BlockState state, ItemStack tool, PlayerEntity player) {
        tool.postMine(world, state, pos, player);
        if (getItem().isEmpty()) {
            return;
        }
        if (tool.getItem() instanceof Hammer hammer) {
            hammer.thump(world, player, Hand.MAIN_HAND);

            ItemStack stack = getItem();
            RageTable<Item, NumberRage<Item>> products = stack.getItem() instanceof Hammerable hammerable ? hammerable.products() : TrtrHammerables.hammerables.get(stack.getItem());
            if (products != null) {
                NbtCompound nbt = stack.getOrCreateNbt();
                double crushed = nbt.getDouble("crushed") + hammer.getThumpEfficiency();
                nbt.putDouble("crushed", crushed);

                products.approve(crushed, item -> {
                    setItemStack(item.getDefaultStack());
                    if (products.size() == 1) {
                        take(world, pos, state, player);
                    }
                });
            }
        }
        markDirty();
    }

    @Override
    public void take(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        ItemStack result = getItem();
        setItemStack(ItemStack.EMPTY);

        ItemEntity entity = new ItemEntity(world, pos.getX(), pos.getY() + 0.5, pos.getZ(), result);
        world.spawnEntity(entity);

        markDirty();
    }
}
