package com.github.cao.awa.trtr.framework.accessor.data.gen.loot;

import com.github.cao.awa.trtr.framework.accessor.filed.FieldAccessor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;

public class LootDataGeneratorAccessor implements FieldAccessor {
    public static final LootDataGeneratorAccessor ACCESSOR = new LootDataGeneratorAccessor();

    public LootFactory<?> get(Class<?> clazz) {
        return EntrustEnvironment.nonnull(get(clazz,
                                              "LOOT"
                                          ),
                                          () -> get(clazz,
                                                    "LOOT_PROVIDER"
                                          )
        );
    }

    public LootFactory<?> get(Object o) {
        return get(o.getClass());
    }

    public Class<? extends SimpleFabricLootTableProvider> getType(Class<?> clazz) {
        return EntrustEnvironment.nonnull(type(clazz,
                                               "LOOT"
                                          ),
                                          () -> type(clazz,
                                                     "LOOT_PROVIDER"
                                          )
        );
    }

    public Class<? extends SimpleFabricLootTableProvider> getType(Object o) {
        return getType(o.getClass());
    }

    public boolean has(Class<?> clazz) {
        if (has(clazz,
                "LOOT"
        )) {
            return true;
        }
        return has(clazz,
                   "LOOT_PROVIDER"
        );
    }

    public boolean has(Object o) {
        return has(o.getClass());
    }
}
