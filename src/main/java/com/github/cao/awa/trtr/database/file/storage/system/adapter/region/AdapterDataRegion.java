package com.github.cao.awa.trtr.database.file.storage.system.adapter.region;

import com.github.cao.awa.trtr.database.file.storage.system.adapter.*;
import com.github.cao.awa.trtr.database.file.storage.system.adapter.data.*;
import com.github.cao.awa.trtr.database.file.storage.system.adapter.index.*;
import com.github.zhuaidadaya.rikaishinikui.handler.option.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import org.apache.commons.codec.binary.*;

import java.io.*;
import java.util.*;

public class AdapterDataRegion {
    private static final Random RANDOM = new Random();
    private static final byte[] EMPTY_PLACEHOLDER = new byte[1];
    private static final long REGION_SIZE = Long.MAX_VALUE;
    private final AdapterIndexAccessor index;
    private final AdapterDataAccessor data;
    private final File baseFile;

    public AdapterDataRegion(File base) {
        super();
        this.baseFile = base;
        if (! base.isDirectory()) {
            base.mkdirs();
        }
        this.data = EntrustEnvironment.trys(() -> new AdapterDataAccessor(new RandomAccessFile(
                this.baseFile + "/page.page",
                "rw"
        )));
        this.index = EntrustEnvironment.trys(() -> new AdapterIndexAccessor(
                new RandomAccessFile(
                        this.baseFile + "/page.index",
                        "rw"
                )
        ));
    }

    public long append(byte[] bytes) throws IOException {
        return writeToEnd(bytes);
    }

    public void createPlaceholder(long dataOrder) throws IOException {
        long presentLimit = insertTo() - 1;
        if (dataOrder > presentLimit) {
            for (long i = presentLimit; i < dataOrder; i++) {
                RANDOM.nextBytes(EMPTY_PLACEHOLDER);
                writeToEnd(EMPTY_PLACEHOLDER);
            }
        }
    }

    private long writeToEnd(byte[] bytes) throws IOException {
        long order = insertTo();
        long startPos = insertToPos();

        data.adapterWrite(
                startPos,
                bytes
        );

        updateIndex(
                order,
                startPos,
                startPos + bytes.length
        );

        return order;
    }

    public long insertToPos() throws IOException {
        return data.length();
    }

    public void updateIndex(long dataOrder, long start, long end) throws IOException {
        index.adapterWriteLong(
                dataOrder,
                start
        );
        index.writeLong(end);
    }

    public long insertTo() throws IOException {
        return index.length() >> AdapterIndexAccessor.INDEX_SIZE_BIT;
    }

    public long adapterWrite(long dataOrder, byte[] bytes) throws IOException {
        if (dataOrder == - 1) {
            return append(bytes);
        } else if (insertTo() - 1 < dataOrder) {
            return append(bytes);
        } else {
            modify(
                    dataOrder,
                    bytes
            );
        }
        return dataOrder;
    }

    public void modify(long dataOrder, byte[] bytes) throws IOException {
        long presentLimit = insertTo() - 1;
        if (presentLimit < dataOrder) {
            throw new IllegalArgumentException("Does not have so many order");
        }
        if (presentLimit == dataOrder) {
            write(
                    dataOrder,
                    bytes
            );
        } else {
            BiOption<Long> option = availableSize(dataOrder);
            long nextOrder = dataOrder + 1;
            long moveOrders = nextOrder;
            long movedPos = option.second();
            long needPos = option.first() + bytes.length;
            while (movedPos < needPos) {
                System.out.println(dataOrder + ":" + movedPos + ":" + needPos);
                if (moveOrders == presentLimit) {
                    moveOrders = nextOrder;
                }
                movedPos += moveToEnd(moveOrders++);
            }
            write(
                    dataOrder,
                    bytes
            );
            System.out.println("Done 2");
        }
    }

    private void write(long dataOrder, byte[] bytes) throws IOException {
        long startPos = availableSize(dataOrder).first();

        data.adapterWrite(
                startPos,
                bytes
        );

        updateIndex(
                dataOrder,
                startPos,
                startPos + bytes.length
        );
    }

    public long moveToEnd(long moveOrder) throws IOException {
        byte[] bytes = readBytes(moveOrder);
        writeToEnd(bytes);
        return bytes.length;
    }

    public byte[] readBytes(long dataOrder) throws IOException {
        BiOption<Long> option = availableSize(dataOrder);
        long startAvailablePos = option.first();
        long endAvailablePos = option.second();

        if (endAvailablePos < startAvailablePos) {
            return AdapterAccessor.EMPTY;
        }
        byte[] bytes = new byte[(int) (endAvailablePos - startAvailablePos)];

        data.adapterRead(
                startAvailablePos,
                bytes
        );

        return bytes;
    }

    public String read(long dataOrder) throws IOException {
        BiOption<Long> option = availableSize(dataOrder);
        long startAvailablePos = option.first();
        long endAvailablePos = option.second();

        if (endAvailablePos < startAvailablePos) {
            return "";
        }
        byte[] bytes = new byte[(int) (endAvailablePos - startAvailablePos)];

        data.adapterRead(
                startAvailablePos,
                bytes
        );

        return StringUtils.newStringUtf8(bytes);
    }

    private BiOption<Long> availableSize(long dataOrder) throws IOException {
        if (dataOrder == 0) {
            index.subSeek(
                    0,
                    8
            );
            return BiOption.of(
                    0L,
                    index.readLong()
            );
        } else {
            long startPos = index.adapterReadLong(dataOrder);
            long endPos = - 1L;
            if (startPos == 0) {
                index.subSeek(
                        dataOrder - 1,
                        8
                );
                startPos = index.readLong();
            } else {
                endPos = index.readLong();
            }

            return BiOption.of(
                    startPos,
                    endPos
            );
        }
    }

    public long readLong() throws IOException {
        return data.readLong();
    }

    public void writeLong(long l) throws IOException {
        data.writeLong(l);
    }

    public int readInt() throws IOException {
        return data.readInt();
    }

    public void writeInt(int i) throws IOException {
        data.writeInt(i);
    }

    public int readTag() throws IOException {
        return data.readTag();
    }

    public void writeTag(int i) throws IOException {
        data.writeTag(i);
    }
}

