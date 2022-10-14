package com.github.cao.awa.trtr.ref.block.iron;

import com.github.cao.awa.trtr.heat.conductor.*;
import com.github.cao.awa.trtr.ref.block.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;

import static com.github.cao.awa.trtr.TrtrMod.heatManager;

public class IronBlockEntity extends HeatConductionBlockEntity<IronBlockEntity> {
    public IronBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.IRON_BLOCK, pos, state);
    }

    @Override
    public void setConductor(HeatConductor conductor) {
        if (conductor instanceof MetalBlockHeatConductor heatConductor) {
            heatManager.getOrReplace(world, pos, () -> heatConductor);
        }
    }

    @Override
    public int thermalConductivity() {
        return 78;
    }
}
