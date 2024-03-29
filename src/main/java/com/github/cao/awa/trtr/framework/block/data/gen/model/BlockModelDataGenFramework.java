package com.github.cao.awa.trtr.framework.block.data.gen.model;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.TrtrMod;
import com.github.cao.awa.trtr.annotation.data.gen.NoModel;
import com.github.cao.awa.trtr.data.gen.model.generic.GenericBlockModelProvider;
import com.github.cao.awa.trtr.data.gen.model.no.BlockStatedNoModelProvider;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.ModelDataGeneratorAccessor;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.TrtrModelFactory;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BlockModelDataGenFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("BlockModelDataGenFramework");
    private final BlockFramework blockFramework;
    private final List<TrtrModelFactory> factories = ApricotCollectionFactor.arrayList();

    public BlockModelDataGenFramework(BlockFramework blockFramework) {
        this.blockFramework = blockFramework;
    }

    public void model(FabricDataGenerator generator) {
        this.blockFramework.dumpBlocks()
                           .stream()
                           .filter(this :: match)
                           .filter(this :: verify)
                           .map(this :: instance)
                           .forEach(this.factories :: add);
        done();
    }

    private boolean match(Block block) {
        // Get the class of block.
        Class<? extends Block> clazz = block.getClass();

        // Framework will not process the unsupported class.
        boolean unsupported = unsupported(clazz);
        boolean dev = dev(clazz);

        // Notice the unsupported class.
        if (unsupported) {
            LOGGER.warn("Class '{}' is unsupported, ignored it",
                        clazz.getName()
            );
        }

        // Notice development class.
        if (dev && ! TrtrMod.DEV_MODE) {
            LOGGER.warn("Class '{}' is only available in development environment, ignored it",
                        clazz.getName()
            );
        }

        // Combine conditions.
        return
                // Ignored dev check when dev mode enabled.
                (TrtrMod.DEV_MODE || ! dev) &&
                        // Unsupported class will not be proxy.
                        ! unsupported;
    }

    private void done() {
        TrtrMod.MODEL_FRAMEWORK.add(this.factories);
    }

    private TrtrModelFactory instance(Block block) {
        // Construct the block model provider.
        LOGGER.info("Constructing model provider for block '{}'",
                    block.getClass()
                         .getName()
        );

        return create(block);
    }

    private boolean verify(Block block) {
        if (ModelDataGeneratorAccessor.ACCESSOR.has(block)) {
            LOGGER.info("Block '{}' has model provider, will use to generate",
                        block.getClass()
                             .getName()
            );
        } else {
            LOGGER.info("Block '{}' has no model provider, will use generic provider to generate",
                        block.getClass()
                             .getName()
            );
        }
        return true;
    }

    private TrtrModelFactory create(Block block) {
        try {
            if (! block.getClass()
                       .isAnnotationPresent(NoModel.class)) {
                final TrtrModelFactory provider = o -> ModelDataGeneratorAccessor.ACCESSOR.get(block);
                if (EntrustEnvironment.trys(() -> provider.apply(null)) == null) {
                    Class<? extends FabricModelProvider> type = ModelDataGeneratorAccessor.ACCESSOR.getType(block);
                    if (FabricModelProvider.class.isAssignableFrom(type)) {
                        return output -> EntrustEnvironment.cast(EntrustEnvironment.trys(() -> type
                                .getConstructor(FabricDataOutput.class)
                                .newInstance(output)));
                    } else {
                        LOGGER.info("Model generator of '{}' will use generic provider",
                                    block.getClass()
                                         .getName()
                        );
                        return output -> new GenericBlockModelProvider(output,
                                                                       block
                        );
                    }
                }
                return provider;
            } else {
                LOGGER.info("No model marked in '{}', will not generate",
                            block.getClass()
                                 .getName()
                );
                return output -> new BlockStatedNoModelProvider(output,
                                                                block
                );
            }
        } catch (Exception e) {
            LOGGER.info("Model generator of '{}' will use generic provider",
                        block.getClass()
                             .getName()
            );
            return output -> new GenericBlockModelProvider(output,
                                                           block
            );
        }
    }
}
