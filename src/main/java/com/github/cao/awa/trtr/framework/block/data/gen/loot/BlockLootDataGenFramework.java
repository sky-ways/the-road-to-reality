package com.github.cao.awa.trtr.framework.block.data.gen.loot;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.framework.accessor.data.gen.loot.LootDataGeneratorAccessor;
import com.github.cao.awa.trtr.framework.accessor.data.gen.loot.LootFactory;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.context.LootContextTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class BlockLootDataGenFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("Trtr/BlockLootDataGenFramework");
    private final BlockFramework blockFramework;
    private final List<LootFactory<?>> factories = ApricotCollectionFactor.newArrayList();

    public BlockLootDataGenFramework(BlockFramework blockFramework) {
        this.blockFramework = blockFramework;
    }

    public void loot(FabricDataGenerator generator) {
        this.blockFramework.dumpBlocks()
                           .stream()
                           .filter(this :: match)
                           .filter(this :: verify)
                           .map(this :: instance)
                           .filter(Objects :: nonNull)
                           .forEach(this.factories :: add);
        done(generator);
    }

    private boolean match(Block clazz) {
        return true;
    }

    private void done(FabricDataGenerator generator) {
        generator.createPack()
                 .addProvider((o, f) -> new FrameworkLootProvider(o,
                                                                  this.factories
                 ));
    }

    private LootFactory<?> instance(Block block) {
        // Construct the block loot provider..
        LOGGER.info("Constructing loot provider for block '{}'",
                    block.getClass()
                         .getName()
        );

        LootFactory<?> factory = create(block);

        if (factory == null) {
            LOGGER.error("Failed construct the data provider for '{}', if this block using LootFactory<T>, then maybe type has been erasure by java",
                         block.getClass()
                              .getName()
            );
            return null;
        }
        return factory;
    }

    private boolean verify(Block block) {
        final List<String> missing = ApricotCollectionFactor.newArrayList();

        // Check indispensable fields, cannot register if missing.
        if (! LootDataGeneratorAccessor.ACCESSOR.has(block)) {
            missing.add("LOOT or LOOT_PROVIDER");
        }
        return checkFields(block.getClass()
                                .getName(),
                           missing
        );
    }

    private LootFactory<?> create(Block block) {
        try {
            LootFactory<?> factory = LootDataGeneratorAccessor.ACCESSOR.get(block);
            if (factory == null) {
                Class<? extends SimpleFabricLootTableProvider> clazz = LootDataGeneratorAccessor.ACCESSOR.getType(block);
                factory = output -> EntrustEnvironment.cast(instance(clazz,
                                                                     output,
                                                                     block
                ));
            }
            return factory;
        } catch (Exception e) {
            return null;
        }
    }

    private FabricLootTableProvider instance(Class<? extends SimpleFabricLootTableProvider> clazz, FabricDataOutput output, Block block) {
        return EntrustEnvironment.trys(
                () -> clazz
                        .getConstructor(FabricDataOutput.class)
                        .newInstance(output),
                () -> EntrustEnvironment.trys(
                        () -> clazz
                                .getConstructor(FabricDataOutput.class,
                                                Block.class
                                )
                                .newInstance(output,
                                             block
                                ),
                        () -> clazz.getConstructor(FabricDataOutput.class,
                                                   LootContextType.class
                                   )
                                   .newInstance(output,
                                                LootContextTypes.BLOCK
                                   )
                )
        );
    }
}
