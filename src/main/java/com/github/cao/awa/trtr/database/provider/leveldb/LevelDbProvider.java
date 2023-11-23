package com.github.cao.awa.trtr.database.provider.leveldb;

import com.github.cao.awa.trtr.database.KeyValueDatabase;
import com.github.cao.awa.trtr.database.provider.KeyValueDatabaseProvider;

import java.io.IOException;

@Deprecated
public class LevelDbProvider extends KeyValueDatabaseProvider implements KeyValueDatabase {
//    private final DB db;

    public LevelDbProvider(String path) throws IOException {
        super(path);
//        this.db = new Iq80DBFactory().open(new File(path),
//                                           new Options().createIfMissing(true)
//                                                        .writeBufferSize(1048560 * 16)
//                                                        .compressionType(CompressionType.SNAPPY)
//        );
    }

    @Override
    public void put(byte[] key, byte[] value) {
//        this.db.put(key,
//                    value
//        );
    }

    @Override
    public byte[] get(byte[] key) {
//        return this.db.get(key);
        return null;
    }

    @Override
    public void remove(byte[] key) {
//        this.db.delete(key);
    }

    public boolean close() {
//        return EntrustEnvironment.trys(
//                () -> {
//                    this.db.close();
//                    return true;
//                },
//                () -> false
//        );
        return false;
    }
}
