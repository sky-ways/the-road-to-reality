package com.github.cao.awa.trtr.item.pebble;

import com.github.cao.awa.trtr.block.NoFloatingBlock;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.pebble.PebbleBlock;
import com.github.cao.awa.trtr.item.SneakingPlaceBlock;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.function.Function;

public abstract class PebbleItem extends CraftingItem {
    public PebbleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ActionResult result = SneakingPlaceBlock.place(context,
                                                       4,
                                                       getPlacingBlock()
        );

        if (result == ActionResult.SUCCESS) {
            return result;
        }

        PlayerEntity playerEntity = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();

        BlockState placeSource = world.getBlockState(blockPos);

        if (placeSource.getBlock() == TrtrBlocks.get(PebbleBlock.class)) {
            int placedCurrentCount = placeSource.get(PebbleBlock.COUNT);
            int placedType = placeSource.get(PebbleBlock.TYPE);

            if (PebbleBlock.TYPE_MAX_COUNT.get(placedType) != placedCurrentCount) {
                world.setBlockState(blockPos,
                                    placeSource.with(PebbleBlock.COUNT,
                                                     placedCurrentCount + 1
                                    ),
                                    Block.NOTIFY_ALL
                );

                context.getStack()
                       .decrement(1);

                return ActionResult.SUCCESS;
            }
        } else {
            Function<BlockPos, ActionResult> placeFunction = pos -> {
                world.setBlockState(pos,
                                    TrtrBlocks.get(PebbleBlock.class)
                                              .getDefaultState()
                                              .with(PebbleBlock.TYPE,
                                                    3
                                              )
                                              .with(PebbleBlock.COUNT,
                                                    1
                                              )
                                              .with(PebbleBlock.FACING,
                                                    context.getHorizontalPlayerFacing()
                                              ),
                                    Block.NOTIFY_ALL
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

        return ActionResult.PASS;
    }

    public abstract Block getPlacingBlock();

    @Override
    public int maxCraftTime() {
        return 171;
    }
}
