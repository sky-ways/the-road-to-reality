package com.github.cao.awa.trtr.framework.item.data.gen.model;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.TrtrMod;
import com.github.cao.awa.trtr.data.gen.model.generic.GenericItemModelProvider;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.ModelDataGeneratorAccessor;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.TrtrModelFactory;
import com.github.cao.awa.trtr.framework.item.ItemFramework;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ItemModelDataGenFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("ItemModelDataGenFramework");
    private final ItemFramework itemFramework;
    private final List<TrtrModelFactory> factories = ApricotCollectionFactor.arrayList();

    public ItemModelDataGenFramework(ItemFramework itemFramework) {
        this.itemFramework = itemFramework;
    }

    public void model(FabricDataGenerator generator) {
        this.itemFramework.dumpItems()
                          .stream()
                          .filter(this :: match)
                          .filter(this :: verify)
                          .map(this :: instance)
                          .forEach(this.factories :: add);
        done();
    }

    private boolean match(Item item) {
        // Get the class of ite,.
        Class<? extends Item> clazz = item.getClass();

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

    private TrtrModelFactory instance(Item item) {
        // Construct the item model provider.
        LOGGER.info("Constructing model provider for item '{}'",
                    item.getClass()
                        .getName()
        );

        return create(item);
    }

    private boolean verify(Item item) {
        if (ModelDataGeneratorAccessor.ACCESSOR.has(item)) {
            LOGGER.info("Item '{}' has model provider, will use to generate",
                        item.getClass()
                            .getName()
            );
        } else {
            LOGGER.info("Item '{}' has no model provider, will use generic provider to generate",
                        item.getClass()
                            .getName()
            );
        }
        return true;
    }

    private TrtrModelFactory create(Item item) {
        try {
            final TrtrModelFactory provider = o -> ModelDataGeneratorAccessor.ACCESSOR.get(item);
            if (EntrustEnvironment.trys(() -> provider.apply(null)) == null) {
                Class<? extends FabricModelProvider> type = ModelDataGeneratorAccessor.ACCESSOR.getType(item);
                if (FabricModelProvider.class.isAssignableFrom(type)) {
                    return output -> EntrustEnvironment.cast(EntrustEnvironment.trys(
                            () -> type
                                    .getConstructor(FabricDataOutput.class)
                                    .newInstance(output),
                            () -> EntrustEnvironment.trys(
                                    () -> type
                                            .getConstructor(FabricDataOutput.class,
                                                            Item.class
                                            )
                                            .newInstance(output,
                                                         item
                                            ))
                    ));
                } else {
                    return output -> new GenericItemModelProvider(output,
                                                                  item
                    );
                }
            }
            return provider;

        } catch (Exception e) {
            return output -> new GenericItemModelProvider(output,
                                                          item
            );
        }
    }
}
