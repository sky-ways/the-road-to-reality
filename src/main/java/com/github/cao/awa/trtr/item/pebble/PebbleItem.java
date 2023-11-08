package com.github.cao.awa.trtr.item.pebble;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.NoFloatingBlock;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.pebble.PebbleBlock;
import com.github.cao.awa.trtr.dev.InventoryUtil;
import com.github.cao.awa.trtr.item.TrtrItems;
import com.github.cao.awa.trtr.item.branch.BranchItem;
import com.github.cao.awa.trtr.item.craft.CraftingItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.function.Function;

@Auto
public class PebbleItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:pebble");

    @Auto
    public PebbleItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> craft(World world, PlayerEntity user, ItemStack craftingStack, ItemStack targetStack) {
        if (targetStack.getItem() == TrtrItems.get(BranchItem.class)) {
            InventoryUtil.insertOrDrop(
                    user,
                    world,
                    new ItemStack(
                            Items.STICK,
                            1
                    )
            );
            targetStack.decrement(1);
            return TypedActionResult.success(targetStack);
        }
        return TypedActionResult.pass(targetStack);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
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
}
