package com.github.cao.awa.trtr.kindling;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.fluid.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;

public class Kindling extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:kindling");
    private static final ObjectArrayList<Block> ACCEPT_BLOCK = EntrustEnvironment.operation(
            new ObjectArrayList<>(),
            list -> {
                list.add(Blocks.FIRE);
                list.add(Blocks.CAMPFIRE);
            }
    );

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();

        if (ACCEPT_BLOCK.contains(world.getBlockState(pos)
                                       .getBlock())) {

            ItemStack litKindling = TrtrItems.LIT_KINDLING.getDefaultStack();
            exchangeStack(
                    context.getStack(),
                    Objects.requireNonNull(context.getPlayer()),
                    litKindling
            );
        } else {
            System.out.println(world.getBlockState(pos));
        }
        return ActionResult.PASS;
    }
}
