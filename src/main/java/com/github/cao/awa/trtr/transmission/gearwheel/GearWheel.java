package com.github.cao.awa.trtr.transmission.gearwheel;

import com.github.cao.awa.trtr.air.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public class GearWheel extends TrtrBlockWithEntity<GearWheelBlockEntity> {
    public static final Identifier IDENTIFIER = new Identifier("trtr:gearwheel");

    protected GearWheel(Settings settings) {
        super(settings);
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, GearWheelBlockEntity blockEntity) {
        blockEntity.tick(world, pos, state);
    }

    public static Block register() {
        Settings settings = Settings.of(Material.WOOD, MapColor.BROWN);
        GearWheel gearWheel = new GearWheel(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, gearWheel);
        return gearWheel;
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
        return new GearWheelBlockEntity(pos, state);
    }
}
