package com.github.cao.awa.trtr.mud.blower;

import com.github.cao.awa.trtr.math.shape.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.block.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

public class MudBlowerBlock extends TrtrBasedBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:mud_blower");
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public MudBlowerBlock() {
        super(FabricBlockSettings.of(Material.SOIL));
        this.setDefaultState(this.stateManager.getDefaultState()
                                              .with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return PixelVoxelShapes.cuboid(2, 0, 2, 14, 4, 14);
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
