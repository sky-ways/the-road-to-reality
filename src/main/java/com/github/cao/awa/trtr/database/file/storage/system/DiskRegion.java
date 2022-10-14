package com.github.cao.awa.trtr.database.file.storage.system;

import com.github.cao.awa.trtr.database.file.storage.system.writer.*;
import com.github.cao.awa.trtr.math.base.*;
import org.apache.commons.codec.binary.*;

import java.io.*;

public abstract class DiskRegion {
    private final RandomAccessFile access;
    private final File file;
    private final RegionAccessor accessor;

    public DiskRegion(RandomAccessFile access, File file, RegionAccessor accessor) {
        this.access = access;
        this.file = file;
        this.accessor = accessor;
        accessor.setRegion(this);
    }

    public abstract void load() throws IOException;

    public RandomAccessFile getAccess() {
        return access;
    }

    public File getFile() {
        return file;
    }

    public DiskRegion seek(long pos) throws IOException {
        access.seek(pos);
        return this;
    }

    public int readInt() throws IOException {
        byte[] bytes = new byte[4];
        accessor.read(bytes);
        return Base256.intFromBuf(bytes);
    }

    public long readLong() throws IOException {
        byte[] bytes = new byte[8];
        accessor.read(bytes);
        return Base256.longFromBuf(bytes);
    }

    public void writeLong(int l) throws IOException {
        byte[] bytes = Base256.intToBuf(l);
        accessor.write(bytes);
    }

    public void writeLong(long l) throws IOException {
        byte[] bytes = Base256.longToBuf(l);
        accessor.write(bytes);
    }

    public int readTag() throws IOException {
        byte[] bytes = new byte[2];
        accessor.read(bytes);
        return Base256.intFromBuf(bytes);
    }

    public void write(long pos, byte[] bytes) throws IOException {
        accessor.write(
                pos,
                bytes
        );
    }

    public byte[] read(int length) throws IOException {
        byte[] bytes = new byte[length];
        accessor.read(bytes);
        return bytes;
    }

    public byte[] read(long pos, int length) throws IOException {
        byte[] bytes = new byte[length];
        read(pos, bytes);
        return bytes;
    }

    public byte[] read(long pos, byte[] bytes) throws IOException {
        accessor.read(pos, bytes);
        return bytes;
    }

    public RegionAccessor getAccessor() {
        return accessor;
    }
}
