package com.github.cao.awa.trtr.database.file.system.adapter.data;

import com.github.cao.awa.trtr.database.file.system.adapter.*;

import java.io.*;

public class AdapterDataAccessor extends AdapterAccessor {
    public AdapterDataAccessor(RandomAccessFile access) throws IOException {
        super(access);
    }
}