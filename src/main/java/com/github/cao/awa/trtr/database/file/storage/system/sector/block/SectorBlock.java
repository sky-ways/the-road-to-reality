package com.github.cao.awa.trtr.database.file.storage.system.sector.block;

import com.github.cao.awa.trtr.database.file.storage.system.*;
import com.github.cao.awa.trtr.database.file.storage.system.sector.*;
import com.github.cao.awa.trtr.database.file.storage.system.writer.*;
import com.github.cao.awa.trtr.math.base.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import it.unimi.dsi.fastutil.longs.*;

import java.io.*;

public class SectorBlock extends DiskRegion {
    private final long pos;
    public static final long SIZE = 256 * 4096;
    private final int metaTag;
    private final Partition partition;
    private final Sector metadata;
    private final Long2ObjectOpenHashMap<Sector> sectors = new Long2ObjectOpenHashMap<>();

    public SectorBlock(long pos, File file, Partition partition, int metaTag) {
        super(
                EntrustParser.trying(() -> new RandomAccessFile(
                        file,
                        "rw"
                )),
                file,
                new SectorBlockRegionAccessor()
        );
        this.partition = partition;
        this.pos = pos;
        this.metaTag = metaTag;

        EntrustExecution.tryTemporary(this::load);

        this.metadata = EntrustParser.trying(this::loadMetadata);

        EntrustExecution.tryTemporary(this::writeMeta);
    }

    private Sector loadMetadata() throws IOException {
        getAccess().seek(0);
        return new Sector(
                0,
                getFile(),
                this,
                metaTag
        );
    }

    public void load() throws IOException {
        getAccess().seek(pos * 4096);
        long sectorPos = 1;

        sectors.put(
                sectorPos,
                new Sector(
                        sectorPos,
                        getFile(),
                        this,
                        0
                )
        );

        int tag = seek(0).readTag();

        if (tag == 1) {
            long sectors = seek(2).readLong();

            if (sectors > 0) {
                for (; sectorPos < sectors; sectorPos++) {
                    this.sectors.put(
                            sectorPos,
                            new Sector(
                                    sectorPos,
                                    getFile(),
                                    this,
                                    0
                            )
                    );
                }
            }
        }
    }

    @Override
    public DiskRegion seek(long pos) throws IOException {
        return super.seek(pos + (this.pos * SIZE));
    }

    public void writeMeta() throws IOException {
        metadata.write(
                0,
                Base256.intToBuf(metaTag)
        );
        metadata.write(
                2,
                Base256.intToBuf(sectors.size())
        );
    }

    public Sector getSector(long pos) {
        Sector sector = sectors.get(pos);
        if (sector == null) {
            sector = new Sector(
                    pos,
                    getFile(),
                    this,
                    0
            );
            sectors.put(
                    pos,
                    sector
            );
        }
        return sector;
    }

    public Sector getMetadata() {
        return metadata;
    }

    public long getPos() {
        return pos;
    }

    private static class SectorBlockRegionAccessor extends RegionAccessor {

    }
}
