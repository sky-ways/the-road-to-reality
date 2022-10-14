package com.github.cao.awa.trtr.database.file.compressor;

import com.github.cao.awa.trtr.util.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;

public class FileLimitCompressor extends FileCompressor {
    public final long activeFrom;

    public FileLimitCompressor(long activeFrom) {
        this.activeFrom = activeFrom;
    }

    @Override
    public String compress(String source) {
        return source.length() > activeFrom ? FileUtil.compress(
                source,
                - 1
        ) : source;
    }

    @Override
    public String decompress(String compressed) {
        return EntrustParser.trying(() -> FileUtil.decompress(compressed));
    }
}
