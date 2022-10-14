package com.github.cao.awa.trtr.database.file.storage.system.sector;

import com.github.cao.awa.trtr.database.file.storage.system.*;
import com.github.cao.awa.trtr.database.file.storage.system.sector.block.*;
import com.github.cao.awa.trtr.database.file.storage.system.writer.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;

import java.io.*;

public class Sector extends DiskRegion {
    public static final int SIZE = 4096;
    private final long pos;
    private final int tag;
    private final SectorBlock block;

    public Sector(long pos, File file, SectorBlock block, int tag) {
        super(
                EntrustParser.trying(() -> new RandomAccessFile(
                        file,
                        "rw"
                )),
                file,
                new SectorRegionAccessor()
        );
        this.pos = pos;
        this.block = block;
        this.tag = tag;
    }

    @Override
    public void load() throws IOException {

    }

    @Override
    public DiskRegion seek(long pos) throws IOException {
        return super.seek(pos + (block.getPos() * SectorBlock.SIZE) + this.pos * SIZE);
    }

    public long getPos() {
        return pos;
    }

    public void write(byte[] bytes) throws IOException {
        byte[] newer = new byte[Sector.SIZE];
        System.arraycopy(
                bytes,
                0,
                newer,
                0,
                bytes.length
        );
        super.write(
                0,
                newer
        );
    }

    private static class SectorRegionAccessor extends RegionAccessor {
        @Override
        public void write(long pos, byte[] bytes) throws IOException {
            byte[] newer = new byte[Sector.SIZE];
            System.arraycopy(
                    bytes,
                    0,
                    newer,
                    0,
                    bytes.length
            );
            super.write(
                    0,
                    newer
            );
        }
    }
}
