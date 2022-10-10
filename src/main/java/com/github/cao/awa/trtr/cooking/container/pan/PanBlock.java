package com.github.cao.awa.trtr.cooking.container.pan;

import com.github.cao.awa.trtr.cooking.container.pot.*;
import com.github.cao.awa.trtr.math.shape.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.type.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public class PanBlock extends TrtrBlockWithEntity<PanBlockEntity> {
    public static final Identifier IDENTIFIER = new Identifier("trtr:pan");

    public PanBlock() {
        super(FabricBlockSettings.of(Material.METAL));
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, PanBlockEntity blockEntity) {
        blockEntity.tick(world, pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return PixelVoxelShapes.cuboid(2,2,2,14,5,14);
    }

    @Override
    public BlockEntityType<PanBlockEntity> blockEntityType() {
        return TrtrBlockEntityType.PAN;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PotBlockEntity(pos, state);
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity entity = world.getBlockEntity(pos);
        ItemStack stack = player.getMainHandStack();
        if (entity == null) {
            return ActionResult.PASS;
        }
        if (entity instanceof PanBlockEntity pot) {
            if (stack.isEmpty()) {
                return pot.take(world, pos, player) == null ? ActionResult.SUCCESS : ActionResult.PASS;
            }
            pot.put(world, pos, player, stack);
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    //    @Override
    //    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
    //        super.onEntityCollision(state, world, pos, entity);
    //    }
}
