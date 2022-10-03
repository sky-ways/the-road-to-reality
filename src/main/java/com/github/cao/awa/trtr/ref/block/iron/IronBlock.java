package com.github.cao.awa.trtr.ref.block.iron;
import com.github.cao.awa.trtr.ref.block.*;
import com.github.cao.awa.trtr.register.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import static com.github.cao.awa.trtr.TrtrMod.heatHandler;

public class IronBlock extends HeatConductionBlock<IronBlockEntity> {
    public static final Identifier IDENTIFIER = new Identifier("minecraft:iron_block");

    public IronBlock(Settings settings) {
        super(settings, new TrtrBlockRegister().registerBlock(true));
        setDefaultState(getStateManager().getDefaultState());
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new IronBlockEntity(pos, state);
    }

    public BlockEntityType<IronBlockEntity> blockEntityType() {
        return TrtrBlockEntityType.IRON_BLOCK;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
