package com.github.cao.awa.trtr.framework.model.provider;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.TrtrModelFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import java.util.List;

public class ModelFramework {
    private final List<TrtrModelFactory> factories = ApricotCollectionFactor.newArrayList();

    public void add(List<TrtrModelFactory> factories) {
        this.factories.addAll(factories);
    }

    public void done(FabricDataGenerator generator) {
        generator.createPack()
                 .addProvider((o, f) -> new FrameworkModelProvider(o,
                                                                   this.factories
                 ));
    }
}
