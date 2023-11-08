package com.github.cao.awa.trtr.feature.pebble;

import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.stone.pebble.PebbleBlock;
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
        System.out.println(context.getOrigin());

        PebbleFeatureConfig config = context.getConfig();

        WorldAccess world = context.getWorld();

        BlockPos blockPos = context.getOrigin();

//        try (ChunkSectionCache chunkSectionCache = new ChunkSectionCache(world)) {
//            ChunkSection section = chunkSectionCache.getSection(blockPos);
//
//            int x = blockPos.getX();
//            int y = blockPos.getY();
//            int z = blockPos.getZ();
//
//            section.setBlockState(
//                    x,
//                    y,
//                    z,
//                    Registries.BLOCK.get(
//                                      Identifier.tryParse(
//                                              context.getConfig()
//                                                     .identifier()
//                                      )
//                              )
//                                    .getDefaultState()
//            );
//        }

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
                blockState = blockState.with(PebbleBlock.FACING,
                                             EntrustEnvironment.select(DIRECTIONS,
                                                                       RANDOM
                                             )
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
