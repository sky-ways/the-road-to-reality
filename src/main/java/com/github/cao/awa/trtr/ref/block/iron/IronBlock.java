package com.github.cao.awa.trtr.ref.block.iron;

import com.github.cao.awa.trtr.ref.block.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;

public class IronBlock extends HeatConductionBlock<IronBlockEntity> {
    public static final Identifier IDENTIFIER = new Identifier("minecraft:iron_block");

    public IronBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(TEMPERATURE, 1));
    }

    public static Block register(Settings settings) {
        IronBlock ironBlock = new IronBlock(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, ironBlock);
        return ironBlock;
    }

    public BlockEntityType<IronBlockEntity> blockEntityType() {
        return TrtrBlockEntityType.IRON_BLOCK;
    }

    public void tick(World world, BlockPos pos, BlockState state, IronBlockEntity blockEntity) {
        blockEntity.tick(world, pos, state, blockEntity);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
    }
}
