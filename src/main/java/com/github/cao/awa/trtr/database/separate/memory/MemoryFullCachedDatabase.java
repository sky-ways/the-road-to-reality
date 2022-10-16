package com.github.cao.awa.trtr.database.separate.memory;

import com.github.cao.awa.trtr.database.*;
import com.github.cao.awa.trtr.database.file.storage.independent.*;
import com.github.cao.awa.trtr.database.file.storage.leisurely.*;
import com.github.cao.awa.trtr.database.properties.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.times.*;
import it.unimi.dsi.fastutil.objects.*;
import org.json.*;

import java.util.*;

public class MemoryFullCachedDatabase extends InstancePropertiesDatabase {
    private final Map<String, InstanceProperties<?>> properties = new Object2ObjectOpenHashMap<>();
    //                    new IndependentStorage(LevelUtil.getServerLevelPath(server))

    public MemoryFullCachedDatabase(String path) {
        super(new LeisurelyStorage(new IndependentStorage(path), false));
    }

    @Override
    public void export() {

    }

    @Override
    public void put(String key, InstanceProperties<?> properties) {
         this.properties
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
        while (!getStorage().isShutdown()) {
            TimeUtil.coma(10);
        }
    }

    @Override
    public InstanceProperties<?> get(String key) {
        InstanceProperties<?> properties = new InstanceProperties<>(null);
        try {
            properties.readJSONObject(new JSONObject(getStorage().read(key)));
            return properties;
        } catch (Exception e) {
            return new InstanceProperties<>(null);
        }
    }
}
