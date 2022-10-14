package com.github.cao.awa.trtr.ref.block.air.vanilla;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.content.*;
import com.github.cao.awa.trtr.element.chemical.properties.*;
import com.github.cao.awa.trtr.ref.block.air.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.register.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public class RefVanillaAirBlock extends TrtrAirBlock {
    public static final Identifier IDENTIFIER = new Identifier("minecraft:air");

    public RefVanillaAirBlock(Settings settings) {
        super(settings, new TrtrBlockRegister().registerBlock(true));
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, AirBlockEntity blockEntity) {
    }

    @Override
    public BlockEntityType<AirBlockEntity> blockEntityType() {
        return TrtrBlockEntityType.AIR;
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AirBlockEntity(pos, state, this);
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public void generateElements(World world, BlockPos pos, ChemicalElementProperties properties) {
    }

    @Override
    public void generatePressure(World world, BlockPos pos, InstanceProperties<AirBlockEntity> properties) {

    }
}
