package com.github.cao.awa.eper.ref.block.eper;

import com.github.cao.awa.eper.power.thermoelectric.fire.burner.*;
import com.github.cao.awa.eper.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.affair.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public abstract class EperBlockWithEntity<T extends BlockEntity> extends BlockWithEntity {
    protected EperBlockWithEntity(Settings settings) {
        super(settings);
    }

    @Nullable
    public <E extends BlockEntity> BlockEntityTicker<E> getTicker(World world, BlockState state, BlockEntityType<E> type) {
        return checkType(type, blockEntityType(), (world1, pos, state1, blockEntity) -> Affair.of(
                        () -> tick(world1, pos, state1, blockEntity) // Do tick
                ).participateIf(
                        () -> world.isClient, // If is client, participate to affair
                        () -> tickClient(world1, pos, state1, blockEntity) // Do client tick
                ).done() // Done affair
        );
    }

    public abstract void tick(World world, BlockPos pos, BlockState state, T blockEntity);

    public void tickClient(World world, BlockPos pos, BlockState state, T blockEntity) {
    }

    public abstract BlockEntityType<T> blockEntityType();
}
