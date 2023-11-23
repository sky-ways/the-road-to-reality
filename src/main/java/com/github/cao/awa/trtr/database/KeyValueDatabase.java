package com.github.cao.awa.trtr.database;

public interface KeyValueDatabase {
    void put(byte[] key, byte[] value);

    byte[] get(byte[] key);

    void remove(byte[] key);

    default boolean close() {
        return true;
    }
}
