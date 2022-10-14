package com.github.cao.awa.trtr.database.memory;

import com.github.cao.awa.trtr.database.*;
import com.github.cao.awa.trtr.database.file.storage.independent.*;
import com.github.cao.awa.trtr.database.file.storage.leisurely.*;
import com.github.cao.awa.trtr.database.properties.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.times.*;
import org.json.*;

public class MemoryFullCachedDatabase extends InstancePropertiesDatabase {

    //                    new IndependentStorage(LevelUtil.getServerLevelPath(server))

    public MemoryFullCachedDatabase(String path) {
        super(new LeisurelyStorage(new IndependentStorage(path), false));
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

    public static void main(String[] args) {
        MemoryFullCachedDatabase database = new MemoryFullCachedDatabase("C:\\normal\\Codes\\Code-Java\\the-road-to-reality\\run\\saves\\test");

        database.shutdown();
    }

    @Override
    public void shutdown() {
        getStorage().shutdown();
        while (!getStorage().isShutdown()) {
            TimeUtil.coma(10);
        }
    }

    @Override
    public InstanceProperties<?> get(String key) {
        return this.getProperties()
                   .get(key);
    }
}
