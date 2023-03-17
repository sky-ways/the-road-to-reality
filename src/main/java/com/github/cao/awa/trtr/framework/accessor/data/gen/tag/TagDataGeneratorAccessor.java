package com.github.cao.awa.trtr.framework.accessor.data.gen.tag;

import com.github.cao.awa.trtr.framework.accessor.filed.FieldAccessor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

public class TagDataGeneratorAccessor implements FieldAccessor {
    public static final TagDataGeneratorAccessor ACCESSOR = new TagDataGeneratorAccessor();

    public FabricTagProvider<?> get(Class<?> clazz) {
        return EntrustEnvironment.nonnull(get(clazz,
                                              "TAG"
                                          ),
                                          () -> get(clazz,
                                                    "TAG_PROVIDER"
                                          )
        );
    }

    public FabricTagProvider<?> get(Object o) {
        return get(o.getClass());
    }

    public Class<? extends FabricTagProvider<?>> getType(Class<?> clazz) {
        return EntrustEnvironment.nonnull(type(clazz,
                                               "TAG"
                                          ),
                                          () -> type(clazz,
                                                     "TAG_PROVIDER"
                                          )
        );
    }

    public Class<? extends FabricTagProvider<?>> getType(Object o) {
        return getType(o.getClass());
    }

    public boolean has(Class<?> clazz) {
        if (has(clazz,
                "TAG"
        )) {
            return true;
        }
        return has(clazz,
                   "TAG_PROVIDER"
        );
    }

    public boolean has(Object o) {
        return has(o.getClass());
    }
}
