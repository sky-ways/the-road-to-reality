package com.github.cao.awa.trtr.framework.accessor.data.gen.model;

import com.github.cao.awa.trtr.framework.accessor.filed.FieldAccessor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

public class ModelDataGeneratorAccessor implements FieldAccessor {
    public static final ModelDataGeneratorAccessor ACCESSOR = new ModelDataGeneratorAccessor();

    public FabricModelProvider get(Class<?> clazz) {
        return EntrustEnvironment.nonnull(get(clazz,
                                              "MODEL"
                                          ),
                                          get(clazz,
                                              "MODEL_PROVIDER"
                                          )
        );
    }

    public FabricModelProvider get(Object o) {
        return get(o.getClass());
    }

    public Class<? extends FabricModelProvider> getType(Class<?> clazz) {
        return EntrustEnvironment.nonnull(type(clazz,
                                              "MODEL"
                                          ),
                                          type(clazz,
                                              "MODEL_PROVIDER"
                                          )
        );
    }

    public Class<? extends FabricModelProvider> getType(Object o) {
        return getType(o.getClass());
    }

    public boolean has(Class<?> clazz) {
        if (has(clazz, "MODEL")) {
            return true;
        }
        return has(clazz, "MODEL_PROVIDER");
    }

    public boolean has(Object o) {
        return has(o.getClass());
    }
}
