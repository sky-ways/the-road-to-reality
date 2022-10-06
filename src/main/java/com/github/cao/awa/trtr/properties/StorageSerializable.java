package com.github.cao.awa.trtr.properties;

public interface StorageSerializable<T> {
    String serialize();
    void deserialize(String source);
}
