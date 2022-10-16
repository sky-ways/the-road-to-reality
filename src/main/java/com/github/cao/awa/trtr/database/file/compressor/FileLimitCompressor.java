package com.github.cao.awa.trtr.database.file.compressor;

import com.github.cao.awa.trtr.util.compressor.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;

public class FileLimitCompressor extends FileCompressor {
    private final long activeFrom;
    private final InformationCompressor compressor;

    public FileLimitCompressor(long activeFrom, InformationCompressor compressor) {
        this.activeFrom = activeFrom;
        this.compressor = compressor;
    }

    @Override
    public byte[] compress(byte[] source) {
        return source.length > activeFrom ? compressor.compress(
                source
        ) : source;
    }

    @Override
    public byte[] decompress(byte[] compressed) {
        return EntrustParser.trying(() -> compressor.decompress(compressed));
    }
}
