package t;

import com.github.cao.awa.trtr.math.base.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import it.unimi.dsi.fastutil.longs.*;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\normal\\Codes\\Code-Java\\the-road-to-reality\\test\\partition.dump");

            Partition partition = new Partition(file);

            StringBuilder builder = new StringBuilder();

            partition.buildPrint(
                    builder,
                    1
            );

            System.out.println(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Sector extends DiskRegion {
        private final long pos;
        private final long size = 4096;
        private final int tag;
        private final SectorBlock block;

        public Sector(long pos, File file, SectorBlock block, int tag) {
            super(
                    EntrustParser.trying(() -> new RandomAccessFile(
                            file,
                            "rw"
                    )),
                    file,
                    new SectorRegionWriter()
            );
            this.pos = pos;
            this.block = block;
            this.tag = tag;
        }

        private static class SectorRegionWriter extends RegionWriter {

        }

        @Override
        public void load() throws IOException {

        }

        @Override
        public DiskRegion seek(long pos) throws IOException {
            return super.seek(pos + (block.pos * 4096 * 256) + this.pos * 4096);
        }

        public void buildPrint(StringBuilder builder, int identer) {
            String ident = "    ".repeat(identer);
            builder.append(ident)
                   .append("Tag: ")
                   .append(tag);
        }
    }

    public static class SectorBlock extends DiskRegion {
        private final long pos;
        private final long size = 256 * 4096;
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
                    new SectorBlockRegionWriter()
            );
            this.partition = partition;
            this.pos = pos;
            this.metaTag = metaTag;

            EntrustExecution.tryTemporary(this::load);

            this.metadata = EntrustParser.trying(this::loadMetadata);

            EntrustExecution.tryTemporary(this::writeMeta);
        }

        private static class SectorBlockRegionWriter extends RegionWriter {

        }

        private Sector loadMetadata() throws IOException {
            getAccess().seek(0);
            long blockPos = 0;
            return new Sector(
                    blockPos,
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

        public void writeMeta() throws IOException {
            metadata.write(0, Base256.intToBuf(metaTag));
            metadata.write(2, Base256.intToBuf(sectors.size()));
        }

        @Override
        public DiskRegion seek(long pos) throws IOException {
            return super.seek(pos + (this.pos * 4096 * 256));
        }

        public void buildPrint(StringBuilder builder, int identer) {
            String ident = "    ".repeat(identer);
            builder.append(ident)
                   .append("BlockSector-")
                   .append(pos)
                   .append("\n");
            ident += ident + ident;
            builder.append(ident)
                   .append("metadata")
                   .append("\n");
            metadata.buildPrint(
                    builder,
                    identer + 3
            );
            builder.append("\n");
            for (Sector sector : sectors.values()) {
                builder.append(ident)
                       .append("Sector-")
                       .append(sector.pos)
                       .append("\n");
                sector.buildPrint(
                        builder,
                        identer + 3
                );
            }
        }

        public Sector getSector(long pos) {
            return sectors.get(pos);
        }

        public Sector getMetadata() {
            return metadata;
        }
    }

    public static class Partition extends DiskRegion {
        private final Long2ObjectOpenHashMap<SectorBlock> sectors = new Long2ObjectOpenHashMap<>();
        private final SectorBlock metadata;

        public Partition(File file) {
            super(
                    EntrustParser.trying(() -> new RandomAccessFile(
                            file,
                            "rw"
                    )),
                    file,
                    new PartitionRegionWriter()
            );

            EntrustExecution.tryTemporary(this::load);

            this.metadata = EntrustParser.trying(this::loadMetadata);
        }

        private static class PartitionRegionWriter extends RegionWriter {

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

        public void buildPrint(StringBuilder builder, int identer) {
            String ident = "    ".repeat(identer);
            builder.append(getFile())
                   .append("\n");
            builder.append(ident)
                   .append("metadata")
                   .append("\n");
            builder.append(ident);
            metadata.buildPrint(
                    builder,
                    identer
            );
            builder.append("\n");
            builder.append(ident)
                   .append("data");
            //            "[metadata: " + metadata + ", \ndata: " + sectors + "]";
        }
    }

    public abstract static class DiskRegion {
        private final RandomAccessFile access;
        private final File file;
        private final RegionWriter writer;

        public DiskRegion(RandomAccessFile access, File file, RegionWriter writer) {
            this.access = access;
            this.file = file;
            this.writer = writer;
            writer.setRegion(this);
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
            access.read(bytes);
            return Base256.intFromBuf(bytes);
        }

        public long readLong() throws IOException {
            byte[] bytes = new byte[8];
            access.read(bytes);
            return Base256.longFromBuf(bytes);
        }

        public void writeLong(int l) throws IOException {
            byte[] bytes = Base256.intToBuf(l);
            access.write(bytes);
        }

        public void writeLong(long l) throws IOException {
            byte[] bytes = Base256.longToBuf(l);
            access.write(bytes);
        }

        public int readTag() throws IOException {
            byte[] bytes = new byte[2];
            access.read(bytes);
            return Base256.intFromBuf(bytes);
        }

        public void write(long pos, byte[] bytes) throws IOException {
            writer.write(pos, bytes);
        }

        public RegionWriter getWriter() {
            return writer;
        }
    }

    public static abstract class RegionWriter {
        private DiskRegion region;

        public void setRegion(DiskRegion region) {
            this.region = region;
        }

        public DiskRegion getRegion() {
            return region;
        }

        public void write(long pos, byte[] bytes) throws IOException {
            region.seek(pos);
            region.getAccess().write(bytes);
        }
    }
}
