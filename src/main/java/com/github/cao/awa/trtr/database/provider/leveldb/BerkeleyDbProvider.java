package com.github.cao.awa.trtr.database.provider.leveldb;

import com.github.cao.awa.trtr.database.KeyValueDatabase;
import com.github.cao.awa.trtr.database.provider.KeyValueDatabaseProvider;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import com.sleepycat.je.*;

import java.io.File;

public class BerkeleyDbProvider extends KeyValueDatabaseProvider implements KeyValueDatabase {
    private final Database database;

    public BerkeleyDbProvider(String path) {
        super(path);
        EnvironmentConfig config = new EnvironmentConfig();
        config.setAllowCreate(true);
        config.setCacheSize(1048560 * 16);

        File dir = new File(path);
        dir.mkdirs();

        try (Environment environment = new Environment(dir,
                                                       config
        )) {
            DatabaseConfig databaseConfig = new DatabaseConfig();
            databaseConfig.setAllowCreate(true);
            this.database = environment.openDatabase(null,
                                                     "test-db",
                                                     databaseConfig
            );
        }
    }

    @Override
    public void put(byte[] key, byte[] value) {
        this.database.put(null,
                          new DatabaseEntry(key),
                          new DatabaseEntry(value)
        );
    }

    @Override
    public byte[] get(byte[] key) {
        DatabaseEntry result = new DatabaseEntry();
        this.database.get(null,
                          new DatabaseEntry(key),
                          result,
                          LockMode.DEFAULT
        );
        return result.getData();
    }

    @Override
    public void remove(byte[] key) {
        this.database.delete(null,
                             new DatabaseEntry(key)
        );
    }

    @Override
    public boolean close() {
        return EntrustEnvironment.trys(
                () -> {
                    this.database.close();
                    return true;
                },
                () -> false
        );
    }
}
