package com.github.cao.awa.trtr.ore.nuclear.uranium.pitchblende;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public class PitchblendeBlock extends TrtrBlockWithEntity<PitchblendeBlockEntity> {
    public static final Identifier IDENTIFIER = new Identifier("trtr:pitchblende");

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PitchblendeBlockEntity(pos, state);
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, PitchblendeBlockEntity blockEntity) {
        blockEntity.tick(world, pos, state);
    }

    @Override
    public BlockEntityType<PitchblendeBlockEntity> blockEntityType() {
        return TrtrBlockEntityType.PITCHBLENDE;
    }
}
