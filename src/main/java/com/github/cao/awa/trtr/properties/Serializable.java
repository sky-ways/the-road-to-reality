package com.github.cao.awa.trtr.properties;

public interface Serializable<T> {
    String serialize();
    T deserialize();
}
