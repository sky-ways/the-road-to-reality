package com.github.cao.awa.trtr.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SideShapeType;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.function.Function;

public class NoFloatingBlock {
    public static boolean canPlace(BlockState state, WorldView world, BlockPos pos) {
        pos = pos.down();
        BlockState blockState = world.getBlockState(pos);
        if (blockState.isIn(BlockTags.UNSTABLE_BOTTOM_CENTER)) {
            return false;
        }
        return (state.isAir() || state.isIn(BlockTags.REPLACEABLE)) && blockState.isSideSolid(world,
                                                                                              pos,
                                                                                              Direction.UP,
                                                                                              SideShapeType.CENTER
        );
    }

    public static ActionResult place(ItemUsageContext context, World world, BlockPos blockPos, BlockState placeSource, Function<BlockPos, ActionResult> placeFunction) {
//        if (NoFloatingBlock.canPlace(placeSource, world, blockPos)) {
//            return placeFunction.apply(blockPos);
//        } else {
        BlockPos blockPos2 = blockPos.offset(context.getSide());
        placeSource = world.getBlockState(blockPos2);
        if (NoFloatingBlock.canPlace(placeSource,
                                     world,
                                     blockPos2
        )) {
            return placeFunction.apply(blockPos2);
        }
//        }
        return ActionResult.PASS;
    }
}
