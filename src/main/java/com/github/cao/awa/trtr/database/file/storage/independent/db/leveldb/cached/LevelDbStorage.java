package com.github.cao.awa.trtr.database.file.storage.independent.db.leveldb.cached;

import com.github.cao.awa.trtr.database.file.storage.*;
import org.iq80.leveldb.*;
import org.iq80.leveldb.impl.*;

import java.io.*;
import java.nio.charset.*;
import java.util.function.*;

public class LevelDbStorage extends DatabaseStorage {
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private DB db;

    public LevelDbStorage(String path) {
        this(
                path,
                -1
        );
    }

    public LevelDbStorage(String path, long compressActiveFrom) {
        super(
                path,
                compressActiveFrom
        );
        try {
            initLevelDB();
        } catch (Exception e) {

        }
    }

    private void initLevelDB() throws IOException {
        DBFactory factory = new Iq80DBFactory();
        Options options = new Options();
        options.createIfMissing(true)
               .writeBufferSize(0xF000000).compressionType(CompressionType.SNAPPY);
        this.db = factory.open(
                new File(getPath()),
                options
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
        this.db.put(
                key.getBytes(CHARSET),
                getCompressor().compress(information.getBytes(CHARSET))
        );
    }

    @Override
    public String read(String key) throws IOException {
        byte[] value = getCompressor().decompress(db.get(key.getBytes(CHARSET)));
        return value == null ?
               null :
               new String(
                       value,
                       CHARSET
               );
    }

    @Override
    public void shutdown() {
        try {
            if (db == null) {
                return;
            }
            db.close();
        } catch (Exception e) {

        }
        db = null;
    }

    public boolean isShutdown() {
        return db == null;
    }
}
