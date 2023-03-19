package com.github.cao.awa.trtr.framework.block.data.gen.model;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.annotation.data.gen.NoModel;
import com.github.cao.awa.trtr.data.gen.model.GenericBlockModelProvider;
import com.github.cao.awa.trtr.data.gen.model.no.BlockNoModelProvider;
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
    private static final Logger LOGGER = LogManager.getLogger("Trtr/BlockModelDataGenFramework");
    private final BlockFramework blockFramework;
    private final List<TrtrModelFactory> factories = ApricotCollectionFactor.newArrayList();

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
        done(generator);
    }

    private boolean match(Block block) {
        return checkDev(block.getClass());
    }

    private void done(FabricDataGenerator generator) {
        generator.createPack()
                 .addProvider((o, f) -> new FrameworkModelProvider(o,
                                                                   this.factories
                 ));
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
            if (block.getClass()
                     .getAnnotation(NoModel.class) == null) {
                final TrtrModelFactory provider = o -> ModelDataGeneratorAccessor.ACCESSOR.get(block);
                if (EntrustEnvironment.trys(() -> provider.apply(null)) == null) {
                    Class<? extends FabricModelProvider> type = ModelDataGeneratorAccessor.ACCESSOR.getType(block);
                    if (FabricModelProvider.class.isAssignableFrom(type)) {
                        return output -> EntrustEnvironment.cast(EntrustEnvironment.trys(() -> type
                                .getConstructor(FabricDataOutput.class)
                                .newInstance(output)));
                    } else {
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
                return output -> new BlockNoModelProvider(output,
                                                          block
                );
            }
        } catch (Exception e) {
            return output -> new GenericBlockModelProvider(output,
                                                           block
            );
        }
    }
}
