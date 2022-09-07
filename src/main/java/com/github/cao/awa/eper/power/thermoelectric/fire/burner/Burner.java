package com.github.cao.awa.eper.power.thermoelectric.fire.burner;

import com.github.cao.awa.eper.ref.block.eper.*;
import com.github.cao.awa.eper.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.affair.*;
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
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public class Burner extends EperBlockWithEntity<BurnerBlockEntity> {
    public static final Identifier IDENTIFIER = new Identifier("eper:burner");
    public static final BooleanProperty LIT = Properties.LIT;

    public Burner(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(LIT, false));
    }

    @Override
    public BlockEntityType<BurnerBlockEntity> blockEntityType() {
        return EperBlockEntityType.BURNER;
    }

    public static Block register() {
        Settings settings = Settings.of(Material.METAL, MapColor.WHITE).hardness(4F).requiresTool();
        Burner burner = new Burner(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, burner);
        BurnerItem.register(burner);
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
        stateManager.add(LIT);
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
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
