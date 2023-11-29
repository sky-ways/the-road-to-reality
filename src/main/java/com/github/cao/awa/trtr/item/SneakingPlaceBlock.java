package com.github.cao.awa.trtr.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SneakingPlaceBlock {
    public static ActionResult place(ItemUsageContext context, int consumeCount, Block placingBlock) {
        if (context.getPlayer() != null && context.getPlayer()
                                                  .isSneaking()) {
            World world = context.getWorld();
            ItemStack stack = context.getStack();

            BlockPos pos = context.getBlockPos()
                                  .offset(context.getSide());

            BlockState state = world.getBlockState(pos);

            if (state.isAir() && stack.getCount() >= consumeCount) {
                world.setBlockState(pos,
                                    placingBlock.getDefaultState(),
                                    Block.NOTIFY_ALL
                );

                if (context.getPlayer() instanceof ServerPlayerEntity player) {
                    stack.decrement(consumeCount);

                    Criteria.CONSUME_ITEM.trigger(player,
                                                  stack
                    );
                }

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }
}
