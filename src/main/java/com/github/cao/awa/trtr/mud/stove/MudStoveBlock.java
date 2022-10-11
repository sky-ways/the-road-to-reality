package com.github.cao.awa.trtr.mud.stove;

import com.github.cao.awa.trtr.math.shape.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.item.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

public class MudStoveBlock extends TrtrBlockWithEntity<MudStoveBlockEntity> {
    public static final Identifier IDENTIFIER = new Identifier("trtr:mud_stove");
    public static final BooleanProperty PIPE_CONNECTED = BooleanProperty.of("pipe_connected");
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public MudStoveBlock() {
        super(Settings.of(Material.SOIL));
        this.setDefaultState(this.stateManager.getDefaultState()
                                              .with(PIPE_CONNECTED, false)
                                              .with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return PixelVoxelShapes.union(PixelVoxelShapes.cuboid(1, 0, 1, 15, 16, 15)
                //                PixelVoxelShapes.cuboid(0, 0 ,4, 16, 16,14)
        );
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, MudStoveBlockEntity blockEntity) {

    }

    @Override
    public BlockEntityType<MudStoveBlockEntity> blockEntityType() {
        return TrtrBlockEntityType.MUD_STOVE;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PIPE_CONNECTED, FACING);
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
