package com.github.cao.awa.trtr.cooking.container.pot;

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
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

// todo
public class PotBlock extends TrtrBlockWithEntity<PotBlockEntity> {
    public static final Identifier IDENTIFIER = new Identifier("trtr:pot");

    public PotBlock() {
        super(FabricBlockSettings.of(Material.METAL));
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, PotBlockEntity blockEntity) {
        blockEntity.tick(world, pos, state);
    }

    @Override
    public BlockEntityType<PotBlockEntity> blockEntityType() {
        return TrtrBlockEntityType.POT;
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
        if (entity instanceof PotBlockEntity pot) {
            if (stack.isEmpty()) {
                return pot.pop(world, pos, player) == null ? ActionResult.SUCCESS : ActionResult.PASS;
            }
            pot.stack(world, pos, player, stack);
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    //    @Override
    //    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
    //        super.onEntityCollision(state, world, pos, entity);
    //    }
}
