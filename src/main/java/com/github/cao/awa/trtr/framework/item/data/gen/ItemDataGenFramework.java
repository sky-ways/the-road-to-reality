package com.github.cao.awa.trtr.framework.item.data.gen;

import com.github.cao.awa.trtr.framework.item.ItemFramework;
import com.github.cao.awa.trtr.framework.item.data.gen.model.ItemModelDataGenFramework;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemDataGenFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("BlockDataGenFramework");
    private final ItemModelDataGenFramework modelFramework;

    public ItemDataGenFramework(ItemFramework blockFramework) {
        this.modelFramework = new ItemModelDataGenFramework(blockFramework);
    }

    public void model(FabricDataGenerator generator) {
        this.modelFramework.model(generator);
    }
}
