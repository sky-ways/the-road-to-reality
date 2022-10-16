package com.github.cao.awa.trtr.database.file.storage;

import com.github.cao.awa.trtr.database.file.compressor.*;
import com.github.cao.awa.trtr.util.compressor.deflater.*;
import com.github.cao.awa.trtr.util.compressor.lz4.*;

import java.io.*;
import java.util.function.*;

public abstract class DatabaseStorage {
    private final String path;
    private final FileCompressor compressor;

    public DatabaseStorage() {
        this.path = null;
        this.compressor = null;
    }

    public DatabaseStorage(FileLimitCompressor compressor) {
        this.path = null;
        this.compressor = compressor;
    }

    public DatabaseStorage(String path) {
        this.path = path;
        this.compressor = new FileInactionCompressor();
    }

    public DatabaseStorage(String path, long compressActiveFrom) {
        this.path = path;
        this.compressor = compressActiveFrom == - 1 ?
                          new FileInactionCompressor() :
                          new FileLimitCompressor(compressActiveFrom,
                                                  DeflaterCompressor.INSTANCE
//                                                  Lz4Compressor.INSTANCE
                          );
    }

    public DatabaseStorage(String path, FileCompressor compressor) {
        this.path = path;
        this.compressor = compressor;
    }

    public abstract void entrustWrite(String key, Supplier<String> action);

    public abstract void write(String key, String information) throws IOException;

    public abstract String read(String key) throws IOException;

    public String getPath() {
        return path;
    }

    public FileCompressor getCompressor() {
        return compressor;
    }

    public long idleTime() {
        return -1;
    }

    public boolean isShutdown() {
        return false;
    }

    public abstract void shutdown();
}
