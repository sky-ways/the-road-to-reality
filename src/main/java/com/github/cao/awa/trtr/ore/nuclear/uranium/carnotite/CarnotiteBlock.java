package com.github.cao.awa.trtr.ore.nuclear.uranium.carnotite;

import com.github.cao.awa.trtr.ore.nuclear.uranium.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public class CarnotiteBlock extends TrtrBlockWithEntity<UraniumBlockEntity> {
    public static final Identifier IDENTIFIER = new Identifier("trtr:carnotite");

    public CarnotiteBlock() {
        super(Settings.of(Material.METAL));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new UraniumBlockEntity(pos, state);
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, UraniumBlockEntity blockEntity) {
        blockEntity.tick(world, pos, state);
    }

    @Override
    public BlockEntityType<UraniumBlockEntity> blockEntityType() {
        return TrtrBlockEntityType.URANIUM;
    }
}
