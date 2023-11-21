package com.github.cao.awa.trtr.block.stove.mud;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.anntation.Unsupported;
import com.github.cao.awa.trtr.annotation.data.gen.DataGen;
import com.github.cao.awa.trtr.annotation.dev.DevOnly;
import com.github.cao.awa.trtr.annotation.property.AutoProperty;
import com.github.cao.awa.trtr.block.entity.TrtrBlockWithEntity;
import com.github.cao.awa.trtr.block.stove.mud.entity.MudStoveBlockEntity;
import com.github.cao.awa.trtr.block.stove.mud.model.MudStoveModelProvider;
import com.github.cao.awa.trtr.block.stove.mud.renderer.MudStoveRenderer;
import com.github.cao.awa.trtr.data.gen.loot.GenericBlockLootProvider;
import com.github.cao.awa.trtr.math.shape.PixelVoxelShapes;
import com.github.cao.awa.trtr.renderer.block.BlockRendererProvider;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.function.Function;

// TODO Waiting for plan 'Smelting Process'
@Auto
@DevOnly
@Unsupported
public class MudStove extends TrtrBlockWithEntity implements BlockRendererProvider<MudStoveBlockEntity> {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:mud_stove");

    @Auto
    public static final FabricBlockSettings SETTINGS = FabricBlockSettings.create()
                                                                          .mapColor(MapColor.BROWN)
                                                                          .strength(1F,
                                                                                    4.0F
                                                                          );

    @Auto
    public static BlockItem ITEM;

    @Auto
    public static MudStoveBlockEntity ENTITY;

    @Auto
    @DataGen
    public static GenericBlockLootProvider LOOT;

    @Auto
    @DataGen
    public static MudStoveModelProvider MODEL;

    @Auto
    @AutoProperty
    public static final DirectionProperty FACING = Properties.FACING;

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return PixelVoxelShapes.cuboid(
                1,
                0,
                1,
                15,
                16,
                15
        );
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                   .with(FACING,
                         ctx.getHorizontalPlayerFacing()
                            .getOpposite()
                   );
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        MudStoveBlockEntity entity = getBlockEntity(world,
                                                    pos
        );
        if (entity == null) {
            return;
        }

        if (entity.getFuel() * 45 > 0) {
            world.spawnEntity(new ItemEntity(world,
                                             pos.getX(),
                                             pos.getY(),
                                             pos.getZ(),
                                             new ItemStack(Items.COAL,
                                                           (int) (entity.getFuel() * 45)
                                             ),
                                             0,
                                             0,
                                             0
            ));
        }

        super.onBreak(world,
                      pos,
                      state,
                      player
        );
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        MudStoveBlockEntity entity = getBlockEntity(world,
                                                    pos
        );
        if (entity == null) {
            return ActionResult.PASS;
        }

        ItemStack stack = player.getStackInHand(hand);

        if (stack.getCount() > 0) {
            entity.addFuel(stack);

            world.updateListeners(pos,
                                  state,
                                  state,
                                  NOTIFY_LISTENERS
            );

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Auto
    protected MudStove(Settings settings) {
        super(settings);
    }

    @Override
    public Function<BlockEntityRendererFactory.Context, BlockEntityRenderer<MudStoveBlockEntity>> renderer() {
        return MudStoveRenderer :: new;
    }
}
