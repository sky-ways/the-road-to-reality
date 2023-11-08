package com.github.cao.awa.trtr.item.branch;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.NoFloatingBlock;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.branch.BranchBlock;
import com.github.cao.awa.trtr.item.TrtrItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.function.Function;

@Auto
public class BranchItem extends TrtrItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:branch");

    @Auto
    public BranchItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();

        BlockState placeSource = world.getBlockState(blockPos);

        Function<BlockPos, ActionResult> placeFunction = pos -> {
            world.setBlockState(pos,
                                TrtrBlocks.get(BranchBlock.class)
                                          .getDefaultState(),
                                11
            );
            world.emitGameEvent(playerEntity,
                                GameEvent.BLOCK_PLACE,
                                pos
            );
            ItemStack itemStack = context.getStack();
            if (playerEntity instanceof ServerPlayerEntity) {
                itemStack.decrement(1);
            }

            return ActionResult.success(world.isClient());
        };

        return NoFloatingBlock.place(context,
                                     world,
                                     blockPos,
                                     placeSource,
                                     placeFunction
        );
    }
}
