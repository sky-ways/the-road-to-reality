package com.github.cao.awa.trtr.database.file.storage.external.redis;

import com.github.cao.awa.trtr.database.file.storage.*;
import redis.clients.jedis.*;

import java.io.*;
import java.util.function.*;

public class RedisStorage extends DatabaseStorage {
    private final String ip;
    private final int port;
    private final Jedis redis;

    public RedisStorage(String ip, int port) {
        super();
        this.ip = ip;
        this.port = port;
        redis = new Jedis(
                ip,
                port
        );
    }

    @Override
    public void entrustWrite(String key, Supplier<String> action) {
        try {
            write(
                    key,
                    action.get()
            );
        } catch (Exception e) {

        }
    }

    @Override
    public void write(String key, String information) throws IOException {
        checkConnection();
        redis.set(
                key,
                information
        );
    }

    @Override
    public String read(String key) throws IOException {
        checkConnection();
        return redis.get(key);
    }

    @Override
    public void shutdown() {

    }

    public void checkConnection() {
        if (! redis.ping()
                   .equals("PONG")) {
            redis.connect();
        }
    }
}
