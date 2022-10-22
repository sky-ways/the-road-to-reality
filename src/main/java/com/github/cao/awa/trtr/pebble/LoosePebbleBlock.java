package com.github.cao.awa.trtr.pebble;

import com.github.cao.awa.trtr.math.possibility.*;
import com.github.cao.awa.trtr.math.shape.*;
import com.github.cao.awa.trtr.ref.block.gentle.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.ref.fluid.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.block.*;
import net.minecraft.block.enums.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.player.*;
import net.minecraft.fluid.*;
import net.minecraft.item.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

import java.util.*;

public class LoosePebbleBlock extends TrtrOreBlock implements GentleBlock, Waterloggable, FluidAdvective {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = Properties.FACING;
    public static final Identifier IDENTIFIER = new Identifier("trtr:loose_pebble");
    private static final IntProperty TYPE = IntProperty.of(
            "pebble_type",
            0,
            2
    );
    private static final IntProperty SUB_TYPE = IntProperty.of(
            "pebble_sub_type",
            0,
            6
    );
    private static final Random random = new Random();
    private final PossibilitySteps typeOneSteps = EntrustParser.trying(() -> {
        PossibilitySteps steps = new PossibilitySteps();
        steps.append(
                0,
                1,
                2,
                3
        );
        steps.append(
                1,
                6,
                5
        );
        steps.append(
                2,
                6,
                4
        );
        steps.append(
                3,
                5,
                4
        );
        steps.append(
                4,
                - 1
        );
        steps.append(
                5,
                - 1
        );
        steps.append(
                6,
                - 1
        );
        return steps;
    });

    public LoosePebbleBlock() {
        super(FabricBlockSettings.of(
                                         Material.STONE,
                                         MapColor.GRAY
                                 )
                                 .nonOpaque());
        setDefaultState(getStateManager().getDefaultState()
                                         .with(
                                                 SUB_TYPE,
                                                 0
                                         )
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

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (! world.isClient) {
            if (state.get(TYPE) == 1) {
                int current = state.get(SUB_TYPE);
                int next = typeOneSteps.select(current);
                if (next == - 1) {
                    state = state.get(WATERLOGGED) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
                } else {
                    state = state.with(
                            SUB_TYPE,
                            next
                    );
                }
                world.setBlockState(
                        pos,
                        state,
                        3
                );
                world.spawnEntity(new ItemEntity(world,
                                                 pos.getX(),
                                                 pos.getY() + 0.5F,
                                                 pos.getZ(),
                                                 TrtrItems.PEBBLE.getDefaultStack()
                ));
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.SUCCESS;
    }

    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return ! state.get(WATERLOGGED);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(
                TYPE,
                SUB_TYPE,
                WATERLOGGED,
                FACING
        );
    }

    public Identifier identifier() {
        return IDENTIFIER;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(
                    pos,
                    Fluids.WATER,
                    Fluids.WATER.getTickRate(world)
            );
        }
        return direction == Direction.DOWN && ! this.canPlaceAt(
                state,
                world,
                pos
        ) ?
               super.getFluidState(state)
                    .getBlockState() :
               super.getStateForNeighborUpdate(
                       state,
                       direction,
                       neighborState,
                       world,
                       pos,
                       neighborPos
               );
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down())
                    .getMaterial()
                    .isSolid();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return PixelVoxelShapes.cuboid(
                2,
                0,
                2,
                14,
                state.get(TYPE) == 2 ? 3 : 2,
                14
        );
    }
}
