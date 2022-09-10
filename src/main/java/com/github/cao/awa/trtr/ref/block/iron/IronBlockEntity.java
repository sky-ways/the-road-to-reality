package com.github.cao.awa.trtr.ref.block.iron;

import com.github.cao.awa.trtr.ref.block.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class IronBlockEntity extends HeatConductionBlockEntity<IronBlockEntity> {
    public IronBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.IRON_BLOCK, pos, state);
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, IronBlockEntity blockEntity) {
        super.tick(world, pos, state, blockEntity);
    }

    @Override
    public int thermalConductivity() {
        return 78;
    }
}
