package com.github.cao.awa.trtr.database.file.storage.system.adapter;

import com.github.cao.awa.trtr.math.base.*;

import java.io.*;

public abstract class AdapterAccessor {
    public static final byte[] EMPTY = new byte[0];
    private final RandomAccessFile access;
    private long length;
    private long lastSeek;

    public AdapterAccessor(RandomAccessFile access) throws IOException {
        this.access = access;
        this.length = access.length();
    }

    public void adapterWriteLong(long pos, long l) throws IOException {
        this.adapterSeek(pos);
        this.writeLong(l);
    }

    public void writeLong(long l) throws IOException {
        this.getAccess()
            .write(Base256.longToBuf(l));
        this.length += 8;
        this.lastSeek += 8;
    }

    public void adapterSeek(long pos) throws IOException {
        long calculated = calculateSeek(pos);
        if (calculated == lastSeek) {
            return;
        }
        this.directSeek(calculated);
    }

    public final void directSeek(long pos) throws IOException {
        this.getAccess()
            .seek(pos);
        this.lastSeek = pos;
    }

    public RandomAccessFile getAccess() {
        return access;
    }

    public long calculateSeek(long l) {
        return l;
    }

    public long adapterReadLong(long pos) throws IOException {
        this.adapterSeek(pos);
        return this.readLong();
    }

    public long readLong() throws IOException {
        byte[] bytes = new byte[8];
        this.getAccess()
            .read(bytes);
        this.lastSeek += 8;
        return Base256.longFromBuf(bytes);
    }

    public long adapterReadInt(long pos) throws IOException {
        this.adapterSeek(pos);
        return this.readInt();
    }

    public int readInt() throws IOException {
        byte[] bytes = new byte[4];
        this.getAccess()
            .read(bytes);
        this.lastSeek += 4;
        return Base256.intFromBuf(bytes);
    }

    public int readTag() throws IOException {
        byte[] bytes = new byte[3];
        this.getAccess()
            .read(bytes);
        this.lastSeek += 2;
        return Base256.tagFromBuf(bytes);
    }

    public void adapterWriteInt(long pos, int i) throws IOException {
        this.adapterSeek(pos);
        this.writeInt(i);
    }

    public void writeInt(int i) throws IOException {
        this.getAccess()
            .write(Base256.intToBuf(i));
        this.lastSeek += 4;
        this.length += 4;
    }

    public void writeTag(int t) throws IOException {
        this.getAccess()
            .write(Base256.tagToBuf(t));
        this.lastSeek += 2;
        this.length += 2;
    }

    public long length() throws IOException {
        return length;
    }

    public void write(long pos, byte[] bytes) throws IOException {
        this.seek(pos);
        this.write(bytes);
    }

    public void seek(long pos) throws IOException {
        long calculated = calculateSeek(pos);
        this.directSeek(calculated);
    }

    public void write(byte[] bytes) throws IOException {
        this.getAccess()
            .write(bytes);
        this.length += bytes.length;
        this.lastSeek += bytes.length;
    }

    public void adapterWrite(long pos, byte[] bytes) throws IOException {
        this.adapterSeek(pos);
        this.write(bytes);
    }

    public void read(long pos, byte[] bytes) throws IOException {
        this.seek(pos);
        this.read(bytes);
    }

    public void read(byte[] bytes) throws IOException {
        this.getAccess()
            .read(bytes);
        this.lastSeek += bytes.length;
    }

    public void adapterRead(long pos, byte[] bytes) throws IOException {
        this.adapterSeek(pos);
        this.read(bytes);
    }
}
