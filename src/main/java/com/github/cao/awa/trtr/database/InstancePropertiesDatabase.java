package com.github.cao.awa.trtr.database;

import com.github.cao.awa.trtr.database.file.storage.*;
import com.github.cao.awa.trtr.database.properties.*;

public abstract class InstancePropertiesDatabase {
    private final DatabaseStorage storage;

    public InstancePropertiesDatabase(DatabaseStorage storage) {
        this.storage = storage;
    }

    public abstract void export();

    public abstract void put(String key, InstanceProperties properties);

    public abstract void shutdown();

    public abstract InstanceProperties get(String key);

    public DatabaseStorage getStorage() {
        return storage;
    }
}
