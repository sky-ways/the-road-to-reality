package com.github.cao.awa.trtr.block.gas;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.gas.entity.GasBlockEntity;
import com.github.cao.awa.trtr.item.TrtrItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Auto
public class TestGasBucket extends TrtrItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:test_gas_bucket");

    @Auto
    public static final FabricItemSettings SETTINGS = new FabricItemSettings().maxCount(1);

    @Auto
    public TestGasBucket(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();

        BlockPos pos = context.getBlockPos();
        BlockPos placePos = pos.offset(context.getSide());

        BlockState placeState = world.getBlockState(placePos);

        if (placeState.isAir()) {
            world.setBlockState(placePos,
                                TrtrBlocks.get(GasBlock.class)
                                          .getDefaultState(),
                                Block.NOTIFY_ALL
            );

            GasBlockEntity entity = (GasBlockEntity) world.getBlockEntity(placePos);
            if (entity != null) {
                entity.pressure.value(entity.pressure.value() + 1000);
            }

            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
