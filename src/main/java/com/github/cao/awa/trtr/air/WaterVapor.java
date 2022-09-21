package com.github.cao.awa.trtr.air;

import com.github.cao.awa.trtr.ore.feldspar.orthoclase.*;
import com.github.cao.awa.trtr.power.photovoltaic.panels.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public class WaterVapor extends TrtrBlockWithEntity<WaterVaporBlockEntity> {
    public static final Identifier IDENTIFIER = new Identifier("trtr:water_vapor");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, WaterVaporBlockEntity blockEntity) {
        blockEntity.tick(world, pos, state);
    }

    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }

    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public BlockEntityType<WaterVaporBlockEntity> blockEntityType() {
        return TrtrBlockEntityType.WATER_VAPOR;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WaterVaporBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) || super.isSideInvisible(state, stateFrom, direction);
    }
}
