package com.github.cao.awa.trtr.transmission.gearwheel.medium;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.transmission.gearwheel.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

public class MediumGearWheel extends TrtrBlockWithEntity<GearWheelBlockEntity> {
    public static final Identifier IDENTIFIER = new Identifier("trtr:medium_gearwheel");

    public MediumGearWheel() {
        super(Settings.of(Material.WOOD));
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, GearWheelBlockEntity blockEntity) {
        blockEntity.tick(world, pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(-16F, 0f, -16F, 32F, 1.0f,  32F);
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntityType<GearWheelBlockEntity> blockEntityType() {
        return TrtrBlockEntityType.GEAR_WHEEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GearWheelBlockEntity(pos, state, 1);
    }
}
