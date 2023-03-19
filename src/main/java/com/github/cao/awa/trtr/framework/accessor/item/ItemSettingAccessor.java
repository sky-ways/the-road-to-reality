package com.github.cao.awa.trtr.framework.accessor.item;

import com.github.cao.awa.trtr.framework.accessor.filed.FieldAccessor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class ItemSettingAccessor implements FieldAccessor {
    public static final ItemSettingAccessor ACCESSOR = new ItemSettingAccessor();

    public Item.Settings get(Class<? extends Item> clazz) {
        return EntrustEnvironment.nonnull(get(clazz,
                                              "SETTINGS"
                                          ),
                                          () -> EntrustEnvironment.nonnull(get(clazz,
                                                                         "SETTING"
                                                                     ),
                                                                     FabricItemSettings :: new
                                          )
        );
    }

    public boolean has(Class<Item> clazz) {
        if (has(clazz, "SETTINGS")) {
            return true;
        }
        return has(clazz, "SETTING");
    }

    @SuppressWarnings("unchecked")
    public boolean has(Item item) {
        return has((Class<Item>) item.getClass());
    }
}
