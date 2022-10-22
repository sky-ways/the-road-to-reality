package com.github.cao.awa.trtr.ref.block.air;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.content.*;
import com.github.cao.awa.trtr.element.chemical.properties.*;
import com.github.cao.awa.trtr.pressure.*;
import com.github.cao.awa.trtr.ref.block.air.vanilla.*;
import com.github.cao.awa.trtr.register.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public class TrtrDumpAirBlock extends TrtrAirBlock implements ChemicalElemental<AirBlockEntity> {
    public static final Identifier IDENTIFIER = new Identifier("trtr:air");

    public TrtrDumpAirBlock(Settings settings) {
        super(
                settings,
                new TrtrBlockRegister().registerBlock(true)
        );
    }

    @Override
    public @Nullable <E extends BlockEntity> BlockEntityTicker<E> getTicker(World world, BlockState state, BlockEntityType<E> type) {
        return null;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, AirBlockEntity blockEntity) {

    }

    @Override
    public BlockEntityType<AirBlockEntity> blockEntityType() {
        return TrtrBlockEntityType.AIR;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AirBlockEntity(
                pos,
                state,
                this
        );
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public void generateElements(World world, BlockPos pos, ChemicalElementProperties properties) {
        properties.put(
                ChemicalElements.OXYGEN,
                new ChemicalContent(
                        ChemicalElements.OXYGEN,
                        random.nextDouble(
                                19,
                                24
                        ),
                        0
                )
        );

        properties.put(
                ChemicalElements.NITROGEN,
                new ChemicalContent(
                        ChemicalElements.NITROGEN,
                        random.nextDouble(
                                0,
                                5
                        ),
                        0
                )
        );

        properties.put(
                ChemicalElements.ELEMENT_CARBON,
                new ChemicalContent(
                        ChemicalElements.ELEMENT_CARBON,
                        11.4,
                        0
                )
        );
    }

    @Override
    public void generatePressures(World world, BlockPos pos, InstanceProperties properties) {
        properties.put(
                "pressure",
                new PressureKpa(101.3)
        );
    }
}
