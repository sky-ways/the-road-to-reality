package com.github.cao.awa.trtr.kindling;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.event.*;

import java.util.*;

public class LitKindling extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:lit_kindling");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        boolean decrement = false;
        if (! CampfireBlock.canBeLit(blockState) && ! CandleBlock.canBeLit(blockState) && ! CandleCakeBlock.canBeLit(blockState)) {
            blockPos = blockPos.offset(context.getSide());
            if (AbstractFireBlock.canPlaceAt(
                    world,
                    blockPos,
                    context.getPlayerFacing()
            )) {
                world.setBlockState(
                        blockPos,
                        AbstractFireBlock.getState(
                                world,
                                blockPos
                        )
                );
                world.emitGameEvent(
                        context.getPlayer(),
                        GameEvent.BLOCK_PLACE,
                        blockPos
                );
                decrement = true;
            }
        } else {
            world.setBlockState(
                    blockPos,
                    blockState.with(
                            Properties.LIT,
                            true
                    )
            );
            world.emitGameEvent(
                    context.getPlayer(),
                    GameEvent.BLOCK_CHANGE,
                    blockPos
            );
            decrement = true;
        }

        if (decrement) {
            context.getStack()
                   .decrement(1);
            return ActionResult.success(world.isClient);
        } else {
            return ActionResult.FAIL;
        }
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        entity.setFireTicks(20);
        stack.decrement(1);
        return ActionResult.SUCCESS;
    }
}
