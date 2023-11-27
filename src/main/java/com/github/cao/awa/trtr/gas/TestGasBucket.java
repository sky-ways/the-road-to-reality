package com.github.cao.awa.trtr.gas;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.constant.pressure.PressureConstants;
import com.github.cao.awa.trtr.gas.manager.WorldGasManager;
import com.github.cao.awa.trtr.item.TrtrItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
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

        if (world.isClient()) {
            return ActionResult.PASS;
        }

        BlockPos pos = context.getBlockPos();
        BlockPos placePos = pos.offset(context.getSide());

        BlockState placeState = world.getBlockState(placePos);

        if (placeState.isAir()) {
            BlockGas gas = WorldGasManager.GAS_MANAGER.getGas(placePos);
            if (gas != null) {
                gas.pressure.value(gas.pressure.value() + 1000);

                WorldGasManager.GAS_MANAGER.updateGas(placePos,
                                                      gas
                );
            } else {
                gas = new BlockGas();

                gas.pressure = PressureConstants.STANDARD_ATMOSPHERIC_PRESSURE.copy();

                WorldGasManager.GAS_MANAGER.updateGas(placePos,
                                                      gas
                );
            }

            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
