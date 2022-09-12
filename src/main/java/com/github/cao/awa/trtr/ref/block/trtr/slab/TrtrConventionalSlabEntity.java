package com.github.cao.awa.trtr.ref.block.trtr.slab;

import com.github.cao.awa.trtr.tool.hammer.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.util.collection.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class TrtrConventionalSlabEntity extends TrtrSlabBlockEntity {
    public TrtrConventionalSlabEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.SLAB_ENTITY, pos, state);
    }

    @Override
    public void thump(World world, BlockPos pos, BlockState state, ItemStack tool, PlayerEntity player) {
        tool.postMine(world, state, pos, player);
        if (tool.getItem() instanceof Hammer hammer) {
            hammer.thump(world, player, Hand.MAIN_HAND);
        }
    }
}
