package com.github.cao.awa.trtr.database.file.external.redis;

import com.github.cao.awa.trtr.database.*;
import com.github.cao.awa.trtr.database.file.storage.*;
import com.github.cao.awa.trtr.database.file.storage.external.redis.*;
import com.github.cao.awa.trtr.database.file.storage.independent.*;
import com.github.cao.awa.trtr.database.file.storage.leisurely.*;
import com.github.cao.awa.trtr.database.properties.*;
import net.minecraft.server.*;
import org.json.*;

public class RedisCachedDatabase extends InstancePropertiesDatabase {
    public RedisCachedDatabase(String ip, int port) {
        super(new RedisStorage(ip,port));
    }

    @Override
    public void export() {

    }

    @Override
    public void put(String key, InstanceProperties<?> properties) {
        try {
            getStorage().write(key,
                               properties.toJSONObject()
                                         .toString()
            );
        } catch (Exception e) {

        }
    }

    @Override
    public void shutdown() {

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

