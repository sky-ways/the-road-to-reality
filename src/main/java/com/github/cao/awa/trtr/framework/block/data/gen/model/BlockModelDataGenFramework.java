package com.github.cao.awa.trtr.framework.block.data.gen.model;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.block.TrtrBlock;
import com.github.cao.awa.trtr.data.gen.model.GenericBlockModelProvider;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import com.github.cao.awa.trtr.framework.data.gen.model.ModelDataGeneratorAccessor;
import com.github.cao.awa.trtr.framework.data.gen.model.TrtrModelFactory;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
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
                           .filter(this :: verify)
                           .map(this :: instance)
                           .forEach(this.factories :: add);
        done(generator);
    }

    private void done(FabricDataGenerator generator) {
        generator.createPack()
                 .addProvider((o, f) -> new ModelProvider(o));
    }

    class ModelProvider extends FabricModelProvider {
        private final FabricDataOutput output;
        private final List<FabricModelProvider> providers = ApricotCollectionFactor.newArrayList();

        public ModelProvider(FabricDataOutput output) {
            super(output);
            this.output = output;
            this.providers.addAll(factories.stream()
                                           .map(p -> p.apply(this.output))
                                           .toList());
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
            for (FabricModelProvider provider : this.providers) {
                provider.generateBlockStateModels(blockStateModelGenerator);
            }
        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            for (FabricModelProvider provider : this.providers) {
                provider.generateItemModels(itemModelGenerator);
            }
        }
    }

    private TrtrModelFactory instance(TrtrBlock block) {
        // Construct the block model provider.
        LOGGER.info("Constructing model provider for block '{}'",
                    block.getClass()
                         .getName()
        );

        return create(block);
    }

    private boolean verify(TrtrBlock block) {
        if (! ModelDataGeneratorAccessor.ACCESSOR.has(block)) {
            LOGGER.info("Block '{}' has no model provider, will use generic provider to generate",
                        block.getClass()
                             .getName()
            );
        } else {
            LOGGER.info("Block '{}' has model provider, will use to generate",
                        block.getClass()
                             .getName()
            );
        }
        return true;
    }

    private TrtrModelFactory create(TrtrBlock block) {
        try {
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
        } catch (Exception e) {
            return output -> new GenericBlockModelProvider(output,
                                                           block
            );
        }
    }
}
