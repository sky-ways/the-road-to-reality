package com.github.cao.awa.trtr.database.provider;

public abstract class KeyValueDatabaseProvider {
    private final String path;

    public KeyValueDatabaseProvider(String path) {
        this.path = path;
    }

    public String path() {
        return this.path;
    }
}
