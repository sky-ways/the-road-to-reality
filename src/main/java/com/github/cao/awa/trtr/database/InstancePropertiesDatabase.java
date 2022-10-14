package com.github.cao.awa.trtr.database;

import com.github.cao.awa.trtr.database.file.storage.*;
import com.github.cao.awa.trtr.database.properties.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.server.*;

import java.util.*;

public abstract class InstancePropertiesDatabase {
    private final Map<String, InstanceProperties<?>> properties;
    private final DatabaseStorage storage;

    public InstancePropertiesDatabase(DatabaseStorage storage, MinecraftServer server, Map<String, InstanceProperties<?>> properties) {
        this.storage = storage;
        this.properties = properties;
        load();
    }

    public abstract void load();

    public InstancePropertiesDatabase(DatabaseStorage storage) {
        this.storage = storage;
        this.properties = new Object2ObjectOpenHashMap<>();
        load();
    }

    public abstract void export();

    public abstract void put(String key, InstanceProperties<?> properties);

    public abstract void shutdown();

    public abstract InstanceProperties<?> get(String key);

    public Map<String, InstanceProperties<?>> getProperties() {
        return properties;
    }

    public DatabaseStorage getStorage() {
        return storage;
    }
}
