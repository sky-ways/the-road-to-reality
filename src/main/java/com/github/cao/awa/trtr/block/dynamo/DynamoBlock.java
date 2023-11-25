package com.github.cao.awa.trtr.block.dynamo;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.data.gen.DataGen;
import com.github.cao.awa.trtr.annotation.data.gen.NoModel;
import com.github.cao.awa.trtr.block.TrtrBlock;
import com.github.cao.awa.trtr.data.gen.loot.GenericBlockLootProvider;
import com.github.cao.awa.trtr.math.shape.PixelVoxelShapes;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

@Auto
@NoModel
public class DynamoBlock extends TrtrBlock {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:dynamo");


    @Auto
    public static final FabricBlockSettings SETTINGS = FabricBlockSettings.create()
                                                                          .mapColor(MapColor.RED)
                                                                          .requiresTool()
                                                                          .strength(1.5F,
                                                                                    6.0F
                                                                          );

    @Auto
    public static BlockItem ITEM;

    @Auto
    @DataGen
    public static GenericBlockLootProvider LOOT;

    @Auto
    public DynamoBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

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
}
