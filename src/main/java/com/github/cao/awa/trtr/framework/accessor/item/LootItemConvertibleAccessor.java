package com.github.cao.awa.trtr.framework.accessor.item;

import com.github.cao.awa.trtr.framework.accessor.data.gen.loot.LootFactory;
import com.github.cao.awa.trtr.framework.accessor.filed.FieldAccessor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;

public class LootItemConvertibleAccessor implements FieldAccessor {
    public static final LootItemConvertibleAccessor ACCESSOR = new LootItemConvertibleAccessor();

    public ItemConvertible get(Class<?> clazz) {
        ItemConvertible loot = get(clazz,
            "LOOT"
        );
        return EntrustEnvironment.nonnull(loot,
                                          () -> get(clazz,
                                                    "LOOT_PROVIDER"
                                          )
        );
    }

    public ItemConvertible get(Object o) {
        return get(o.getClass());
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

    public boolean has(Object target) {
        return has(target.getClass());
    }
}
