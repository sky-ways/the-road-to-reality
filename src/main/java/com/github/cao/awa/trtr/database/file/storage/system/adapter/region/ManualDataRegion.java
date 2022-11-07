package com.github.cao.awa.trtr.database.file.storage.system.adapter.region;

import com.github.cao.awa.trtr.database.file.storage.system.adapter.data.*;
import com.github.cao.awa.trtr.database.file.storage.system.adapter.index.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;

import java.io.*;
import java.util.*;

public class ManualDataRegion {
    private static final Random RANDOM = new Random();
    private static final byte[] EMPTY_PLACEHOLDER = new byte[1];
    private static final long REGION_SIZE = Long.MAX_VALUE;
    private final AdapterIndexAccessor index;
    private final AdapterDataAccessor data;
    private final File baseFile;

    public ManualDataRegion(File base) {
        super();
        this.baseFile = base;
        if (! base.isDirectory()) {
            base.mkdirs();
        }
        this.data = EntrustEnvironment.trying(() -> new AdapterDataAccessor(new RandomAccessFile(
                this.baseFile + "/page.page",
                "rw"
        )));
        this.index = EntrustEnvironment.trying(() -> new AdapterIndexAccessor(
                new RandomAccessFile(
                        this.baseFile + "/page.index",
                        "rw"
                )
        ));
    }

    public AdapterIndexAccessor getIndex() {
        return index;
    }

    public AdapterDataAccessor getData() {
        return data;
    }
}

