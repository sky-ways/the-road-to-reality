package com.github.cao.awa.trtr.block.pebble;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.annotation.property.AutoProperty;
import com.github.cao.awa.trtr.block.NoFloatingBlock;
import com.github.cao.awa.trtr.block.TrtrBlock;
import com.github.cao.awa.trtr.dev.InventoryUtil;
import com.github.cao.awa.trtr.item.TrtrItems;
import com.github.cao.awa.trtr.item.pebble.StonePebbleItem;
import com.github.cao.awa.trtr.math.shape.PixelVoxelShapes;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.List;
import java.util.Map;

@Auto
public class PebbleBlock extends TrtrBlock {
    public static final Map<Integer, Integer> TYPE_MAX_COUNT = EntrustEnvironment.operation(ApricotCollectionFactor.hashMap(),
                                                                                            map -> {
                                                                                                map.put(1,
                                                                                                        2
                                                                                                );
                                                                                                map.put(2,
                                                                                                        3
                                                                                                );
                                                                                                map.put(3,
                                                                                                        3
                                                                                                );
                                                                                            }
    );

    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:pebble_block");

    @Auto
    public static final FabricBlockSettings SETTINGS = FabricBlockSettings.create()
                                                                          .mapColor(MapColor.GRAY)
                                                                          .breakInstantly()
                                                                          .replaceable()
                                                                          .notSolid();

    @Auto
    @AutoProperty
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    @Auto
    @AutoProperty
    public static final IntProperty TYPE = IntProperty.of("type",
                                                          1,
                                                          3
    );
    @Auto
    @AutoProperty
    public static final IntProperty COUNT = IntProperty.of("count",
                                                           1,
                                                           3
    );

    @Auto
    public static final ItemConvertible LOOT = TrtrItems.get(StonePebbleItem.class);

    @Auto
    public static final Class<PebbleBlockModel> MODEL = PebbleBlockModel.class;

    public static final VoxelShape OUTLINE_SHAPE = PixelVoxelShapes.cuboid(2,
                                                                           0,
                                                                           2,
                                                                           14,
                                                                           2,
                                                                           14
    );

    @Auto
    public PebbleBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                                         .with(TYPE,
                                               3
                                         )
                                         .with(COUNT,
                                               3
                                         )
        );
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && ! canPlaceAt(state,
                                                           world,
                                                           pos
        ) ? Blocks.AIR.getDefaultState() : state;
    }


    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        List<ItemStack> stacks = super.getDroppedStacks(state,
                                                        builder
        );
        int currentLeftCount = state.get(COUNT) - 1;

        if (currentLeftCount > 0) {
            stacks.add(new ItemStack(TrtrItems.get(StonePebbleItem.class),
                                     currentLeftCount
            ));
        }

        return stacks;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int currentCount = state.get(COUNT);

        if (currentCount == 1) {
            world.setBlockState(pos,
                                Blocks.AIR.getDefaultState(),
                                Block.NOTIFY_ALL
            );
        } else {
            world.setBlockState(pos,
                                state.with(COUNT,
                                           currentCount - 1
                                ),
                                Block.NOTIFY_ALL
            );
        }

        InventoryUtil.insertOrDrop(player,
                                   world,
                                   new ItemStack(TrtrItems.get(StonePebbleItem.class))
        );

        return ActionResult.SUCCESS;
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return NoFloatingBlock.canPlace(state,
                                        world,
                                        pos
        );
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE_SHAPE;
    }
}
