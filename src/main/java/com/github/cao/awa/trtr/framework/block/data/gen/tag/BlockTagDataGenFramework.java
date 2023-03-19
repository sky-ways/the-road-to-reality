package com.github.cao.awa.trtr.framework.block.data.gen.tag;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.ModelDataGeneratorAccessor;
import com.github.cao.awa.trtr.framework.accessor.data.gen.tag.TagDataGeneratorAccessor;
import com.github.cao.awa.trtr.framework.accessor.data.gen.tag.TrtrTagFactory;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class BlockTagDataGenFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("Trtr/BlockTagDataGenFramework");
    private final BlockFramework blockFramework;
    private final List<TrtrTagFactory> factories = ApricotCollectionFactor.newArrayList();

    public BlockTagDataGenFramework(BlockFramework blockFramework) {
        this.blockFramework = blockFramework;
    }

    public void tag(FabricDataGenerator generator) {
        this.blockFramework.dumpBlocks()
                           .stream()
                           .filter(this :: match)
                           .filter(this :: verify)
                           .map(this :: instance)
                           .filter(Objects :: nonNull)
                           .forEach(this.factories :: add);
        done(generator);
    }

    private boolean match(Block block) {
        return checkDev(block.getClass());
    }

    private void done(FabricDataGenerator generator) {
        generator.createPack()
                 .addProvider((o, f) -> new FrameworkTagProvider(o,
                                                                 EntrustEnvironment.cast(Registries.BLOCK.getKey()),
                                                                 f,
                                                                 this.factories
                 ));
    }

    private TrtrTagFactory instance(Block block) {
        // Construct the block loot provider..
        LOGGER.info("Constructing loot provider for block '{}'",
                    block.getClass()
                         .getName()
        );

        TrtrTagFactory factory = create(block);

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
        if (! ModelDataGeneratorAccessor.ACCESSOR.has(block)) {
            LOGGER.info("Block '{}' has no tag provider, will no further action",
                        block.getClass()
                             .getName()
            );
        } else {
            LOGGER.info("Block '{}' has tag provider, will use to generate",
                        block.getClass()
                             .getName()
            );
        }

        return true;
    }

    private TrtrTagFactory create(Block block) {
        try {
            final TrtrTagFactory factory = (output, registryKey, future) -> TagDataGeneratorAccessor.ACCESSOR.get(block);
            if (EntrustEnvironment.trys(() -> factory.apply(null,
                                                            null,
                                                            null
            )) == null) {
                Class<? extends FabricTagProvider<?>> clazz = TagDataGeneratorAccessor.ACCESSOR.getType(block);
                return (output, registryKey, future) -> EntrustEnvironment.cast(instance(clazz,
                                                                                         output,
                                                                                         future
                ));
            }
            return factory;
        } catch (Exception e) {
            return null;
        }
    }

    private FabricTagProvider<?> instance(Class<? extends FabricTagProvider<?>> clazz, FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> future) {
        return EntrustEnvironment.trys(
                () -> clazz
                        .getConstructor(FabricDataOutput.class,
                                        CompletableFuture.class
                        )
                        .newInstance(output,
                                     future
                        ),
                () -> EntrustEnvironment.trys(
                        () -> clazz
                                .getConstructor(FabricDataOutput.class,
                                                RegistryKey.class,
                                                CompletableFuture.class
                                )
                                .newInstance(output,
                                             Registries.BLOCK.getKey(),
                                             future
                                )
                )
        );
    }
}
