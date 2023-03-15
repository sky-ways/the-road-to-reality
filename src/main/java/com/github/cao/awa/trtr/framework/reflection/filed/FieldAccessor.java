package com.github.cao.awa.trtr.framework.reflection.filed;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;

public interface FieldAccessor {
    default <T> T get(Class<?> clazz, String field) {
        return EntrustEnvironment.cast(EntrustEnvironment.trys(() -> clazz.getField(field).get(null)));
    }
}
