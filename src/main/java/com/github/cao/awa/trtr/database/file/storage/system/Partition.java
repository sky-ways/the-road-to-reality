package com.github.cao.awa.trtr.database.file.storage.system;

import com.github.cao.awa.trtr.database.file.storage.system.sector.*;
import com.github.cao.awa.trtr.database.file.storage.system.sector.block.*;
import com.github.cao.awa.trtr.database.file.storage.system.writer.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import it.unimi.dsi.fastutil.longs.*;
import org.apache.commons.codec.binary.*;

import java.io.*;

public class Partition extends DiskRegion {
    private final Long2ObjectOpenHashMap<SectorBlock> sectors = new Long2ObjectOpenHashMap<>();
    private final SectorBlock metadata;

    public Partition(File file) {
        super(
                EntrustParser.trying(() -> new RandomAccessFile(
                        file,
                        "rw"
                )),
                file,
                new PartitionRegionAccessor()
        );

        EntrustExecution.tryTemporary(this::load);

        this.metadata = EntrustParser.trying(this::loadMetadata);
    }

    public SectorBlock loadMetadata() throws IOException {
        getAccess().seek(0);
        long blockPos = 0;
        return new SectorBlock(
                blockPos,
                getFile(),
                this,
                1
        );
    }

    public void load() throws IOException {
        int tag = metadata.getSector(0)
                          .seek(0)
                          .readTag();
        if (tag == 0) {
            long blocks = metadata.getSector(0)
                                  .seek(2)
                                  .readLong();

            long blockPos = 1;

            sectors.put(
                    blockPos,
                    new SectorBlock(
                            blockPos,
                            getFile(),
                            this,
                            0
                    )
            );

            if (blocks > 0) {
                for (; blockPos < blocks; blockPos++) {
                    sectors.put(
                            blockPos,
                            new SectorBlock(
                                    blockPos,
                                    getFile(),
                                    this,
                                    0
                            )
                    );
                }
            }
        } else {
            throw new IllegalStateException("Partition destroyed");
        }
    }

    public SectorBlock getSectorBlock(long pos) {
        long blockPos = (pos / 256) + 1;
        SectorBlock block = sectors.get(blockPos);
        if (block == null) {
            block = new SectorBlock(blockPos, getFile(), this, 0);
            sectors.put(pos, block);
        }
        return block;
    }

    public Sector getSector(long pos) {
        return getSectorBlock(pos).getSector(pos % 256 + 1);
    }

    public void swap(long sectorPos1, long sectorPos2) throws IOException {
        if (sectorPos1 == 0 || sectorPos2 == 0) {
            throw new IllegalArgumentException("The sector 0 is metadata sector, unable to access");
        }
        Sector sector1 = getSector(sectorPos1);
        Sector sector2 = getSector(sectorPos2);
        byte[] sectorInfo1 = sector1.read(0, 4096);
        byte[] sectorInfo2 = sector2.read(0, 4096);
        sector2.write(0, sectorInfo1);
        sector1.write(0, sectorInfo2);
    }

    private static class PartitionRegionAccessor extends RegionAccessor {
        @Override
        public void write(long pos, byte[] bytes) throws IOException {
            checkSector(pos).write(pos, bytes);
        }

        @Override
        public void read(long pos, byte[] bytes) throws IOException {
            checkSector(pos).read(0, bytes);
        }

        private Sector checkSector(long pos) {
            Sector sector = ((Partition)getRegion()).getSector(pos);
            if (sector.getPos() == 0) {
                throw new IllegalArgumentException("The sector 0 is metadata sector, unable to access");
            }
            return sector;
        }
    }
}
