package com.github.cao.awa.trtr.power.thermoelectric.fire.burner;

import com.github.cao.awa.trtr.ref.block.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public class BurnerBlock extends TrtrBlockWithEntity<BurnerBlockEntity> {
    public static final Identifier IDENTIFIER = new Identifier("trtr:burner");
    public static final BooleanProperty BURNING = BooleanProperty.of("burning");
    public static final DirectionProperty FACING = Properties.FACING;
    public static final IntProperty TEMPERATURE = HeatConductionBlock.TEMPERATURE;

    public BurnerBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(BURNING, false).with(TEMPERATURE, 1).with(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(1F / 16F, 0f, 1F / 16F, 15F / 16F, 1.0f, 15F / 16F);
    }

    @Override
    public BlockEntityType<BurnerBlockEntity> blockEntityType() {
        return TrtrBlockEntityType.BURNER;
    }

    public static Block register() {
        Settings settings = Settings.of(Material.METAL, MapColor.WHITE).hardness(4F).requiresTool();
        BurnerBlock burner = new BurnerBlock(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, burner);
        BurnerBlockItem.register(burner);
        return burner;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BurnerBlockEntity(pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state, BurnerBlockEntity blockEntity) {
        blockEntity.tick(world, pos, state, blockEntity);
    }

    public void tickClient(World world, BlockPos pos, BlockState state, BurnerBlockEntity blockEntity) {
        blockEntity.tickClient(world, pos, state, blockEntity);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
        if (world.getBlockEntity(pos) instanceof BurnerBlockEntity burnerBlockEntity) {
            burnerBlockEntity.onSteppedOn(world, pos, state, entity);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(BURNING, TEMPERATURE, FACING);
    }



    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (! state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BurnerBlockEntity burnerBlockEntity) {
                ItemStack stack = burnerBlockEntity.getInvStackList().get(0);
                ItemEntity entity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                entity.setVelocity(world.random.nextTriangular(0.0D, 0.11485000171139836D), world.random.nextTriangular(0.2D, 0.11485000171139836D), world.random.nextTriangular(0.0D, 0.11485000171139836D));
                world.spawnEntity(entity);
                world.updateComparators(pos, this);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return super.canReplace(state, context);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
            return ActionResult.CONSUME;
        }
    }
}
