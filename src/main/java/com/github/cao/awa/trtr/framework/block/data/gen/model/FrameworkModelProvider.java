package com.github.cao.awa.trtr.framework.block.data.gen.model;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.data.gen.model.GenericBlockModelProvider;
import com.github.cao.awa.trtr.data.gen.model.GenericItemModelProvider;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.TrtrModelFactory;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.TrtrModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

import java.util.List;

/**
 * Provide automatic model generate by framework.
 *
 * @author cao_awa
 * @see BlockModelDataGenFramework
 * @see TrtrModelProvider
 * @see TrtrModelFactory
 * @see GenericItemModelProvider
 * @see GenericBlockModelProvider
 * @since 1.0.0
 */
public class FrameworkModelProvider extends FabricModelProvider {
    private final FabricDataOutput output;
    private final List<FabricModelProvider> providers = ApricotCollectionFactor.newArrayList();

    public FrameworkModelProvider(FabricDataOutput output, List<TrtrModelFactory> factories) {
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