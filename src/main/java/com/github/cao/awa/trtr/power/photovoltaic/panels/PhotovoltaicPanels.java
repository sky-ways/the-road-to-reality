package com.github.cao.awa.trtr.power.photovoltaic.panels;

import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

// TODO waiting for Si
public class PhotovoltaicPanels extends TrtrBlockWithEntity<PhotovoltaicPanelsBlockEntity> {
    public static final Identifier IDENTIFIER = new Identifier("trtr:photovoltaic_panels");

    public PhotovoltaicPanels(Settings settings) {
        super(settings);
    }

    public static Block register() {
        Settings settings = Settings.of(Material.METAL, MapColor.BLUE).hardness(4F).requiresTool();
        PhotovoltaicPanels panels = new PhotovoltaicPanels(settings);
        Registry.register(Registry.BLOCK, IDENTIFIER, panels);
        PhotovoltaicPanelsItem.register(panels);
        return panels;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof PhotovoltaicPanelsBlockEntity blockEntity) {
            player.sendMessage(Text.of("Ticked: " + blockEntity.getTicked()));
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);
        if (blockEntity instanceof PhotovoltaicPanelsBlockEntity panelsBlock) {
            System.out.println(panelsBlock.getTicked());
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PhotovoltaicPanelsBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return ! world.isClient && world.getDimension().hasSkyLight() ? checkType(type, TrtrBlockEntityType.PHOTOVOLTAIC_PANELS, this::tick) : null;
    }

    public void tick(World world, BlockPos pos, BlockState state, PhotovoltaicPanelsBlockEntity blockEntity) {
        blockEntity.tick(world, pos, state, blockEntity);
    }

    @Override
    public BlockEntityType<PhotovoltaicPanelsBlockEntity> blockEntityType() {
        return TrtrBlockEntityType.PHOTOVOLTAIC_PANELS;
    }
}
