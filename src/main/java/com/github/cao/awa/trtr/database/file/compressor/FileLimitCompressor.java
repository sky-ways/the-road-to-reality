package com.github.cao.awa.trtr.database.file.compressor;

import com.github.cao.awa.trtr.information.compressor.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;

public class FileLimitCompressor extends FileCompressor {
    private final long requireSize;
    private final InformationCompressor compressor;

    public FileLimitCompressor(long requireSize, InformationCompressor compressor) {
        this.requireSize = requireSize;
        this.compressor = compressor;
    }

    /**
     * Compress when source reach to require size
     * Or else return source data
     *
     * @param source A data
     * @return Compress result
     */
    @Override
    public byte[] compress(byte[] source) {
        return source.length > requireSize ? compressor.compress(
                source
        ) : source;
    }

    /**
     * Decompress data
     *
     * @param source A compressed data
     * @return Decompress result
     */
    @Override
    public byte[] decompress(byte[] source) {
        return EntrustParser.trying(() -> compressor.decompress(source));
    }
}
