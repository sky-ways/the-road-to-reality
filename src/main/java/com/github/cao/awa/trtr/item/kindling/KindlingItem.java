package com.github.cao.awa.trtr.item.kindling;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.dev.InventoryUtil;
import com.github.cao.awa.trtr.item.TrtrItem;
import com.github.cao.awa.trtr.item.TrtrItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Auto
public class KindlingItem extends TrtrItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:kindling");
    @Auto
    public static final FabricItemSettings SETTINGS = new FabricItemSettings().maxCount(1);

    @Auto
    public KindlingItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();

        assert player != null;
        ItemStack kindlingStack = player.getStackInHand(context.getHand());

        BlockState state = world.getBlockState(pos);

        boolean toLit =
                // Is fire then can be lit.
                state.getBlock() == Blocks.FIRE ||
                        // If not fire then should be campfire, need lit state to lit the kindling.
                        (state.getBlock() == Blocks.CAMPFIRE && state.get(CampfireBlock.LIT));

        if (toLit) {
            ItemStack litKindling = new ItemStack(TrtrItems.get(LitKindlingItem.class));
            InventoryUtil.insertOrDrop(player,
                                       world,
                                       litKindling
            );

            kindlingStack.decrement(1);

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
