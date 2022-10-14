package com.github.cao.awa.trtr.database.file.storage.system.writer;

import com.github.cao.awa.trtr.database.file.storage.system.*;

import java.io.*;
import java.util.*;

public abstract class RegionAccessor {
    private DiskRegion region;

    public DiskRegion getRegion() {
        return region;
    }

    public void setRegion(DiskRegion region) {
        this.region = region;
    }

    public void write(long pos, byte[] bytes) throws IOException {
        region.seek(pos);
        region.getAccess()
              .write(bytes);
    }

    public void write(byte[] bytes) throws IOException {
        region.getAccess()
              .write(bytes);
    }

    public void read(long pos, byte[] bytes) throws IOException {
        region.seek(pos);
        region.getAccess()
              .read(bytes);
    }

    public void read(byte[] bytes) throws IOException {
        region.getAccess()
              .read(bytes);
    }
}