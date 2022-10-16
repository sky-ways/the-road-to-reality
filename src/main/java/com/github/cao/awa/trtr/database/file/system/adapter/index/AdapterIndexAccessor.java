package com.github.cao.awa.trtr.database.file.system.adapter.index;

import com.github.cao.awa.trtr.database.file.system.adapter.*;

import java.io.*;

public class AdapterIndexAccessor extends AdapterAccessor {
    public static final long INDEX_SIZE = 16;
    public static final long INDEX_SIZE_BIT = 4;

    public AdapterIndexAccessor(RandomAccessFile access) throws IOException {
        super(access);
    }

    public void seek(long pos) throws IOException {
        this.getAccess()
            .seek(calculateSeek(pos));
    }

    @Override
    public long calculateSeek(long l) {
        return l << AdapterIndexAccessor.INDEX_SIZE_BIT;
    }

    @Override
    public void write(long pos, byte[] bytes) throws IOException {
        throw new IllegalAccessError("Indexer can only write long");
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        throw new IllegalAccessError("Indexer can only write long");
    }

    public void subSeek(long pos, long sub) throws IOException {
        this.getAccess()
            .seek(calculateSeek(pos) + sub);
    }
}
