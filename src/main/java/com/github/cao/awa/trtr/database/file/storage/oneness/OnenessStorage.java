package com.github.cao.awa.trtr.database.file.storage.oneness;

import com.github.cao.awa.trtr.database.file.storage.*;
import com.github.cao.awa.trtr.util.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import org.apache.commons.io.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.util.function.*;

public class OnenessStorage extends DatabaseStorage {
    public OnenessStorage(String path) {
        super(path);
    }

    @Override
    public void entrustWrite(String key, Supplier<String> action) throws IOException {
    }

    @Override
    public void write(String key, String information) throws IOException {
        File file = new File(getPath() + "/" + key + ".prp");
        FileUtils.write(
                file,
                getCompressor().compress(information),
                StandardCharsets.UTF_8
        );
    }

    @Override
    public String read(String key) throws IOException {
        return null;
    }

    @Override
    public Map<String, String> readEach() throws IOException {
        return null;
    }

    @Override
    public void operationEach(BiConsumer<String, String> information) throws IOException {

    }

    public void entrustPacking(String pack, String key, Supplier<String> action) throws IOException {

    }

    @Override
    public void shutdown() {

    }
}
