package com.github.cao.awa.trtr.block.pan.frying;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.data.gen.DataGen;
import com.github.cao.awa.trtr.annotation.property.AutoProperty;
import com.github.cao.awa.trtr.block.TrtrBlockWithEntity;
import com.github.cao.awa.trtr.block.pan.frying.entity.FryingPanBlockEntity;
import com.github.cao.awa.trtr.block.pan.frying.model.FryingPanModelProvider;
import com.github.cao.awa.trtr.block.pan.frying.renderer.FryingPanRenderer;
import com.github.cao.awa.trtr.data.gen.loot.GenericBlockLootProvider;
import com.github.cao.awa.trtr.math.shape.PixelVoxelShapes;
import com.github.cao.awa.trtr.renderer.block.BlockRendererProvider;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
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

@Auto
public class FryingPan extends TrtrBlockWithEntity implements BlockRendererProvider<FryingPanBlockEntity> {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:frying_pan");

    @Auto
    public static final FabricBlockSettings SETTINGS = FabricBlockSettings.create()
                                                                          .mapColor(MapColor.BROWN)
                                                                          .strength(1F,
                                                                                    4.0F
                                                                          );

    @Auto
    public static BlockItem ITEM;

    @Auto
    public static FryingPanBlockEntity ENTITY;

    @Auto
    @DataGen
    public static GenericBlockLootProvider LOOT;

    @Auto
    @DataGen
    public static FryingPanModelProvider MODEL;

//    @Auto
//    public static FryingPanRenderer RENDER;

    @Auto
    @AutoProperty
    public static final DirectionProperty FACING = Properties.FACING;

    @Auto
    protected FryingPan(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return PixelVoxelShapes.cuboid(
                1,
                0,
                1,
                15,
                3,
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

    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        return ActionResult.PASS;
    }

    @Override
    public Function<BlockEntityRendererFactory.Context, BlockEntityRenderer<FryingPanBlockEntity>> renderer() {
        return FryingPanRenderer :: new;
    }
}
