package com.github.cao.awa.trtr.database.provider;

import com.github.cao.awa.trtr.database.KeyValueDatabase;
import com.github.cao.awa.trtr.database.provider.leveldb.LevelDbProvider;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.function.ExceptingFunction;

public class DatabaseProviders {
    public static ExceptingFunction<String, KeyValueDatabase> defaultProvider = LevelDbProvider :: new;

    public static KeyValueDatabase kv(String path) throws Exception {
        return defaultProvider.apply(path);
    }
}
