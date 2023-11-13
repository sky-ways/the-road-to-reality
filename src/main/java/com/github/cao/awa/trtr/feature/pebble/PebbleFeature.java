package com.github.cao.awa.trtr.feature.pebble;

import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.pebble.PebbleBlock;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class PebbleFeature extends Feature<PebbleFeatureConfig> {
    private static final Random RANDOM = new Random();
    private static final Block PEBBLE_BLOCK = TrtrBlocks.get(PebbleBlock.class);
    private static final Direction[] DIRECTIONS = new Direction[]{Direction.WEST, Direction.NORTH, Direction.SOUTH, Direction.EAST};

    public PebbleFeature(Codec<PebbleFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<PebbleFeatureConfig> context) {
        PebbleFeatureConfig config = context.getConfig();

        WorldAccess world = context.getWorld();

        BlockPos blockPos = context.getOrigin();

        // Generate on listed surface block.
        if (config.surfacePlaceable()
                  .contains(
                          // Get the identifier to match.
                          Registries.BLOCK.getId(
                                  world.getBlockState(blockPos.down())
                                       .getBlock()
                          )
                  )
        ) {
            BlockState blockState = PEBBLE_BLOCK.getDefaultState();

            if (! config.isDefaultState()) {
                int pebbleType = RANDOM.nextInt(1,
                                                4
                );
                int pebbleCount = RANDOM.nextInt(1,
                                                 4
                );

                pebbleCount = pebbleType == 1 ? Math.min(2,
                                                         pebbleCount
                ) : pebbleCount;

                blockState = blockState.with(PebbleBlock.FACING,
                                             EntrustEnvironment.select(DIRECTIONS,
                                                                       RANDOM
                                             )
                                       )
                                       .with(PebbleBlock.TYPE,
                                             pebbleType
                                       )
                                       .with(PebbleBlock.COUNT,
                                             pebbleCount
                                       );
            }
            world.setBlockState(blockPos,
                                blockState,
                                Block.NOTIFY_ALL
            );
        }

        return false;
    }
}
