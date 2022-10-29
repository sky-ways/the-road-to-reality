package com.github.cao.awa.trtr.database.separate.leveldb;

import com.github.cao.awa.trtr.database.*;
import com.github.cao.awa.trtr.database.file.storage.independent.db.leveldb.cached.*;
import com.github.cao.awa.trtr.database.properties.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.times.*;
import org.jetbrains.annotations.*;
import org.json.*;

public class LevelDbCachedDatabase extends InstancePropertiesDatabase {
    public LevelDbCachedDatabase(String path) {
        super(new LevelDbStorage(path, 0));
    }

    @Override
    public void put(String key, InstanceProperties properties) {
        try {
            getStorage().entrustWrite(
                    key,
                    () -> properties.toJSONObject()
                                    .toString()
            );
        } catch (Exception e) {

        }
    }

    @Override
    public void shutdown() {
        getStorage().shutdown();
        while (!getStorage().isShutdown()) {
            TimeUtil.coma(10);
        }
    }

    @Override
    public @NotNull InstanceProperties get(String key) {
        InstanceProperties properties = new InstanceProperties();
        try {
            properties.readJSONObject(new JSONObject(getStorage().read(key)));
            return properties;
        } catch (Exception e) {
            return new InstanceProperties();
        }
    }
}
