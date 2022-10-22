package com.github.cao.awa.trtr.branche.tree;

import com.github.cao.awa.trtr.math.shape.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.type.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

public class TreeBranchBlock extends TrtrOreBlock {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = Properties.FACING;
    public static final Identifier IDENTIFIER = new Identifier("trtr:tree_branch");
    private static final IntProperty TYPE = IntProperty.of(
            "branch_type",
            0,
            2
    );

    public TreeBranchBlock() {
        super(FabricBlockSettings.of(
                                         Material.STONE,
                                         MapColor.GRAY
                                 )
                                 .nonOpaque());
        setDefaultState(getStateManager().getDefaultState()
                                         .with(
                                                 TYPE,
                                                 1
                                         )
                                         .with(
                                                 WATERLOGGED,
                                                 false
                                         )
                                         .with(
                                                 FACING,
                                                 Direction.NORTH
                                         ));
    }

    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (! world.isClient) {
                world.setBlockState(
                        pos,
                        Blocks.AIR.getDefaultState(),
                        3
                );
                world.spawnEntity(new ItemEntity(world,
                                                 pos.getX(),
                                                 pos.getY() + 0.5F,
                                                 pos.getZ(),
                                                 TrtrItems.TREE_BRANCH.getDefaultStack()
                ));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return PixelVoxelShapes.cuboid(
                2,
                0,
                2,
                14,
                2,
                14
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(
                TYPE,
                WATERLOGGED,
                FACING
        );
    }
}
