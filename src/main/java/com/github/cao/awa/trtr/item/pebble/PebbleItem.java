package com.github.cao.awa.trtr.item.pebble;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.stone.pebble.PebbleBlock;
import com.github.cao.awa.trtr.dev.InventoryUtil;
import com.github.cao.awa.trtr.dev.OffhandUtil;
import com.github.cao.awa.trtr.item.TrtrItem;
import com.github.cao.awa.trtr.item.TrtrItems;
import com.github.cao.awa.trtr.item.branch.BranchItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.function.Consumer;
import java.util.function.Function;

@Auto
public class PebbleItem extends TrtrItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:pebble");

    @Auto
    public PebbleItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Consumer<ItemStack> action = useItem -> {
            NbtCompound nbt = useItem.getOrCreateNbt();
            int carved = nbt.getInt("carve");
            if (carved == 4) {
                useItem.decrement(1);
                nbt.putInt("carve",
                           0
                );
                ItemStack stick = new ItemStack(Items.STICK);
                InventoryUtil.insertOrDrop(user,
                                           world,
                                           stick
                );
                return;
            }
            nbt.putInt("carve",
                       carved + 1
            );
            useItem.setNbt(nbt);
        };

        if (hand == Hand.MAIN_HAND) {
            OffhandUtil.useOff(user.getMainHandStack(),
                               user.getOffHandStack(),
                               (item1, item2) -> ! item2.isEmpty() && item2.getItem() == TrtrItems.get(BranchItem.class),
                               action
            );
        } else {
            OffhandUtil.useMain(user.getMainHandStack(),
                                user.getOffHandStack(),
                                (item1, item2) -> ! item1.isEmpty() && item1.getItem() == TrtrItems.get(BranchItem.class),
                                action
            );
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();

        BlockState placeSource = world.getBlockState(blockPos);

        Function<BlockPos, ActionResult> placeFunction = pos -> {
            world.setBlockState(pos,
                                TrtrBlocks.get(PebbleBlock.class)
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

        if (PebbleBlock.canPlace(placeSource)) {
            return placeFunction.apply(blockPos);
        } else {
            BlockPos blockPos2 = blockPos.offset(context.getSide());
            placeSource = world.getBlockState(blockPos2);
            if (PebbleBlock.canPlace(placeSource)) {
                return placeFunction.apply(blockPos2);
            }
        }
        return ActionResult.PASS;
    }
}
