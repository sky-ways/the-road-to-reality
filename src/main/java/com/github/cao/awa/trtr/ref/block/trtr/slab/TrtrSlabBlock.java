package com.github.cao.awa.trtr.ref.block.trtr.slab;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.register.*;
import com.github.cao.awa.trtr.tool.hammer.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.times.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.block.enums.*;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.player.*;
import net.minecraft.fluid.*;
import net.minecraft.item.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.tag.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public class TrtrSlabBlock extends TrtrBasedBlock implements Waterloggable {
    public static final ObjectArrayList<TrtrSlabBlock> SLABS = new ObjectArrayList<>();
    public static final EnumProperty<SlabType> TYPE = Properties.SLAB_TYPE;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty PLACED_ITEM = BooleanProperty.of("placed_item");
    protected static final VoxelShape BOTTOM_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape TOP_SHAPE = Block.createCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public TrtrSlabBlock(Settings settings) {
        super(settings, new TrtrBlockRegister());
        this.setDefaultState(this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, false).with(PLACED_ITEM, false));
        SLABS.add(this);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        if (blockState.isOf(this)) {
            return blockState.with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, false);
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
            BlockState blockState2 = this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
            Direction direction = ctx.getSide();
            return direction != Direction.DOWN && (direction == Direction.UP || ! (ctx.getHitPos().y - (double) blockPos.getY() > 0.5D)) ? blockState2 : blockState2.with(TYPE, SlabType.TOP);
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        take(world, pos, state, player);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED, PLACED_ITEM);
    }

    public boolean take(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        TrtrSlabBlockEntity slabBlockEntity = getSlabEntity(world, pos);
        if (slabBlockEntity == null) {
            return false;
        }
        slabBlockEntity.take(world, pos, state, player);
        cooling(slabBlockEntity);
        return true;
    }

    public boolean cooling(TrtrSlabBlockEntity slabBlockEntity) {
        boolean result = slabBlockEntity.getCooling() < TimeUtil.millions();
        if (result) {
            slabBlockEntity.setCooling(TimeUtil.millions() + 300);
            return false;
        }
        return true;
    }

    public TrtrSlabBlockEntity getSlabEntity(World world, BlockPos pos) {
        BlockEntity entity = world.getBlockEntity(pos);
        return entity instanceof TrtrSlabBlockEntity slab ? slab : null;
    }

    public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.get(TYPE) != SlabType.DOUBLE && ! state.get(Properties.WATERLOGGED) && fluid == Fluids.WATER;
    }

    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.get(TYPE) != SlabType.DOUBLE && tryFillWithFluid0(world, pos, state, fluidState);
    }

    boolean tryFillWithFluid0(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        if (! state.get(Properties.WATERLOGGED) && fluidState.getFluid() == Fluids.WATER) {
            if (! world.isClient()) {
                world.setBlockState(pos, state.with(Properties.WATERLOGGED, true), 3);
                world.createAndScheduleFluidTick(pos, fluidState.getFluid(), fluidState.getFluid().getTickRate(world));
            }
            return true;
        } else {
            return false;
        }
    }

    public ItemStack tryDrainFluid(WorldAccess world, BlockPos pos, BlockState state) {
        if (state.get(Properties.WATERLOGGED)) {
            world.setBlockState(pos, state.with(Properties.WATERLOGGED, false), 3);
            if (! state.canPlaceAt(world, pos)) {
                world.breakBlock(pos, true);
            }

            return new ItemStack(Items.WATER_BUCKET);
        } else {
            return ItemStack.EMPTY;
        }
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return type == NavigationType.WATER && world.getFluidState(pos).isIn(FluidTags.WATER);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public boolean hasSidedTransparency(BlockState state) {
        return state.get(TYPE) != SlabType.DOUBLE;
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        ItemStack itemStack = context.getStack();
        SlabType slabType = state.get(TYPE);
        if (slabType != SlabType.DOUBLE && itemStack.isOf(this.asItem())) {
            if (context.canReplaceExisting()) {
                boolean bl = context.getHitPos().y - (double) context.getBlockPos().getY() > 0.5D;
                Direction direction = context.getSide();
                if (slabType == SlabType.BOTTOM) {
                    return direction == Direction.UP || bl && direction.getAxis().isHorizontal();
                } else {
                    return direction == Direction.DOWN || ! bl && direction.getAxis().isHorizontal();
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        SlabType slabType = state.get(TYPE);
        return switch (slabType) {
            case DOUBLE -> VoxelShapes.fullCube();
            case TOP -> TOP_SHAPE;
            default -> BOTTOM_SHAPE;
        };
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return state.get(PLACED_ITEM) ? new TrtrConventionalSlabEntity(pos, state) : null;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
//        if (! (state.get(TYPE) == SlabType.BOTTOM)) {
//            return ActionResult.PASS;
//        }
//        if (world.getBlockEntity(pos) != null && ((player.getMainHandStack().isEmpty() && player.getOffHandStack().isEmpty()) || player.getMainHandStack().isEmpty())) {
//            if (take(world, pos, state, player)) {
//                if (world.getBlockEntity(pos) != null) {
//                    world.getChunk(pos).removeBlockEntity(pos);
//                }
//            }
//        } else {
//            if (world.getBlockEntity(pos) != null) {
//                if (player.getMainHandStack().getItem() instanceof Hammer) {
//                    if (player.getItemCooldownManager().isCoolingDown(player.getMainHandStack().getItem())) {
//                        return ActionResult.PASS;
//                    }
//                    thump(world, pos, state, player.getMainHandStack(), player);
//                } else if (player.getOffHandStack().getItem() instanceof Hammer) {
//                    if (player.getItemCooldownManager().isCoolingDown(player.getOffHandStack().getItem())) {
//                        return ActionResult.PASS;
//                    }
//                    thump(world, pos, state, player.getOffHandStack(), player);
//                }
//                return ActionResult.PASS;
//            } else {
//                boolean mainHand = player.getOffHandStack().isEmpty();
//                ItemStack stack = mainHand ? player.getMainHandStack() : player.getOffHandStack();
//                if (stack.isEmpty()) {
//                    return ActionResult.PASS;
//                }
//                world.setBlockState(pos, state.with(PLACED_ITEM, true), 3);
//                place(checkEntity(null, world, pos), world, pos, state, stack, player);
//                player.swingHand(mainHand ? Hand.MAIN_HAND : Hand.OFF_HAND);
//            }
//            return ActionResult.SUCCESS;
//        }
//        return ActionResult.PASS;
        return using(state, world, pos, player, hand, hit);
    }

    public ActionResult using(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (! (state.get(TYPE) == SlabType.BOTTOM)) {
            return ActionResult.PASS;
        }
        TrtrSlabBlockEntity blockEntity = null;
        if (world.getBlockEntity(pos) instanceof TrtrSlabBlockEntity slab) {
            blockEntity = slab;
        }
        if (blockEntity != null && !blockEntity.getItem().isEmpty()) {
            if (player.getMainHandStack().isEmpty()) {
                take(world, pos, state, player);
                return ActionResult.SUCCESS;
            }
            if (player.getMainHandStack().getItem() instanceof Hammer) {
                if (player.getItemCooldownManager().isCoolingDown(player.getMainHandStack().getItem())) {
                    return ActionResult.PASS;
                }
                thump(world, pos, state, player.getMainHandStack(), player);
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        } else {
            boolean mainHand = player.getOffHandStack().isEmpty();
            ItemStack stack = mainHand ? player.getMainHandStack() : player.getOffHandStack();
            if (stack.isEmpty()) {
                return ActionResult.PASS;
            }
            world.setBlockState(pos, state.with(PLACED_ITEM, true), 3);
            place(getSlabEntity(world, pos), world, pos, state, stack, player);
        }
        return ActionResult.SUCCESS;
    }

    public void thump(World world, BlockPos pos, BlockState state, ItemStack tool, PlayerEntity player) {
        TrtrSlabBlockEntity slabBlockEntity = getSlabEntity(world, pos);
        if (cooling(slabBlockEntity)) {
            return;
        }
        thump(slabBlockEntity, world, pos, state, tool, player);
        cooling(slabBlockEntity);
    }

    public void thump(TrtrSlabBlockEntity slabBlockEntity, World world, BlockPos pos, BlockState state, ItemStack tool, PlayerEntity player) {
        if (! state.get(PLACED_ITEM)) {
            place(slabBlockEntity, world, pos, state, player.getMainHandStack(), player);
            world.setBlockState(pos, state.with(PLACED_ITEM, true), 3);
        }
        slabBlockEntity.thump(world, pos, state, tool, player);
    }

    public void place(TrtrSlabBlockEntity slabBlockEntity, World world, BlockPos pos, BlockState state, ItemStack stack, PlayerEntity player) {
        if (cooling(slabBlockEntity)) {
            return;
        }
        ItemStack place = stack.copy();
        stack.setCount(stack.getCount() - 1);
        place.setCount(1);
        slabBlockEntity.setItemStack(place);
    }

    @Override
    public Identifier identifier() {
        return null;
    }
}
