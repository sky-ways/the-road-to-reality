package com.github.cao.awa.trtr.share;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;

import java.util.Map;

public class SharedObjectData {
    private static final Map<String, Object> EMPTY = ApricotCollectionFactor.hashMap();
    public static final Map<Object, Map<String, Object>> data = ApricotCollectionFactor.hashMap();

    public static void set(Object object, String name, Object value) {
        data.compute(object,
                     (k, v) -> {
                         if (v == null) {
                             v = ApricotCollectionFactor.hashMap();
                         }
                         v.put(name,
                               value
                         );
                         return v;
                     }
        );
    }

    public static <T> T get(Object object, String name) {
        return EntrustEnvironment.cast(data.getOrDefault(object,
                                                         EMPTY
                                           )
                                           .get(name));
    }

    public static void remove(Object object) {
        data.remove(object);
    }

    public static void remove(Object object, String name) {
        EntrustEnvironment.cast(data.getOrDefault(object,
                                                  EMPTY
                                    )
                                    .remove(name));
    }
}
