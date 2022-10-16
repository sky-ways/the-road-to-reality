package com.github.cao.awa.trtr.database.file.storage.independent;

import com.github.cao.awa.trtr.database.file.compressor.*;
import com.github.cao.awa.trtr.database.file.storage.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.times.*;
import it.unimi.dsi.fastutil.objects.*;
import org.apache.commons.codec.binary.*;
import org.apache.commons.io.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.util.function.*;

public class IndependentStorage extends DatabaseStorage {
    private final String suffix;

    public IndependentStorage(String path, String suffix) {
        super(path);
        verifySuffix(suffix);
        this.suffix = suffix;
    }

    private void verifySuffix(String suffix) {
        if (suffix.contains("/") || suffix.contains("\\")) {
            throw new IllegalArgumentException("The suffix cannot contains any chars in [/, \\]");
        }
    }

    public IndependentStorage(String path) {
        super(path);
        this.suffix = "prp";
    }

    public IndependentStorage(String path, String suffix, long compressActiveFrom) {
        super(
                path,
                compressActiveFrom
        );
        verifySuffix(suffix);
        this.suffix = suffix;
    }

    public IndependentStorage(String path, long compressActiveFrom) {
        super(
                path,
                compressActiveFrom
        );
        this.suffix = "prp";
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
        File file = new File(getPath() + "/" + key + "." + suffix.replace(
                ".",
                ""
        ));
        FileUtils.writeByteArrayToFile(
                file,
                getCompressor().compress(information.getBytes(StandardCharsets.UTF_8))
        );
    }

    @Override
    public String read(String key) throws IOException {
        File file = new File(getPath() + "/" + key + "." + suffix.replace(
                ".",
                ""
        ));
        if (file.isFile()) {
            return StringUtils.newStringUtf8(getCompressor().decompress(FileUtils.readFileToByteArray(
                    file
            )));
        }
        return "";
    }

    @Override
    public void shutdown() {

    }
}
