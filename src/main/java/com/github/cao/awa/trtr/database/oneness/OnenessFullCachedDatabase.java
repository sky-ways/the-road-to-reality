package com.github.cao.awa.trtr.database.oneness;

import com.github.cao.awa.trtr.database.*;
import com.github.cao.awa.trtr.database.file.storage.leisurely.*;
import com.github.cao.awa.trtr.database.file.storage.oneness.*;
import com.github.cao.awa.trtr.database.properties.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.times.*;
import org.json.*;

public class OnenessFullCachedDatabase extends InstancePropertiesDatabase {
    public OnenessFullCachedDatabase(String path) {
        super(new OnenessStorage(path));
    }

    @Override
    public void load() {
        try {
            getStorage().operationEach((key, information) -> {
                InstanceProperties<?> instance = new InstanceProperties<>(null);
                instance.readJSONObject(new JSONObject(information));
                this.getProperties()
                    .put(
                            key,
                            instance
                    );
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void export() {

    }

    @Override
    public void put(String key, InstanceProperties<?> properties) {
        this.getProperties()
            .put(
                    key,
                    properties
            );
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
        while (! getStorage().isShutdown()) {
            TimeUtil.coma(10);
        }
    }

    @Override
    public InstanceProperties<?> get(String key) {
        return this.getProperties()
                   .get(key);
    }
}
