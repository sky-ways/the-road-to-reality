package com.github.cao.awa.trtr.block.stone.branch;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.data.gen.NoModel;
import com.github.cao.awa.trtr.annotation.property.AutoProperty;
import com.github.cao.awa.trtr.block.TrtrBlock;
import com.github.cao.awa.trtr.item.TrtrItems;
import com.github.cao.awa.trtr.item.branch.BranchItem;
import com.github.cao.awa.trtr.math.shape.PixelVoxelShapes;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SideShapeType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

@Auto
@NoModel
public class BranchBlock extends TrtrBlock {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:branch_block");

    @Auto
    public static final FabricBlockSettings SETTINGS = FabricBlockSettings.create()
                                                                          .mapColor(DyeColor.BROWN)
                                                                          .breakInstantly()
                                                                          .notSolid();

    @AutoProperty
    public static final DirectionProperty FACING = Properties.FACING;

    @Auto
    public static BlockItem ITEM;

    @Auto
    public static final ItemConvertible LOOT = TrtrItems.get(BranchItem.class);

    public static final VoxelShape OUTLINE_SHAPE = PixelVoxelShapes.cuboid(2,
                                                                           0,
                                                                           2,
                                                                           14,
                                                                           2,
                                                                           14
    );

    public BranchBlock(Settings settings) {
        super(settings);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && ! canPlaceAt(state,
                                                           world,
                                                           pos
        ) ? Blocks.AIR.getDefaultState() : state;
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        pos = pos.down();
        BlockState blockState = world.getBlockState(pos);
        return blockState.isSideSolid(world,
                                      pos,
                                      Direction.UP,
                                      SideShapeType.FULL
        );
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE_SHAPE;
    }

    public static boolean canPlace(BlockState state) {
        return state.isAir() || state.isIn(BlockTags.REPLACEABLE);
    }
}
