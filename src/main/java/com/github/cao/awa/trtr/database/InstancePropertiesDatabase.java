package com.github.cao.awa.trtr.database;

import com.github.cao.awa.trtr.database.file.storage.*;
import com.github.cao.awa.trtr.database.properties.*;
import org.jetbrains.annotations.*;

public abstract class InstancePropertiesDatabase {
    private final DatabaseStorage storage;

    public InstancePropertiesDatabase(DatabaseStorage storage) {
        this.storage = storage;
    }

    public abstract void put(String key, InstanceProperties properties);

    public abstract void shutdown();

    @NotNull
    public abstract InstanceProperties get(String key);

    public DatabaseStorage getStorage() {
        return storage;
    }
}
