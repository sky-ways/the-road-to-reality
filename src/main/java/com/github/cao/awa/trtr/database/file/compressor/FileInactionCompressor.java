package com.github.cao.awa.trtr.database.file.compressor;

public class FileInactionCompressor extends FileCompressor {
    /**
     * Inaction when compressing
     *
     * @param source Source data
     * @return Intact source data
     */
    @Override
    public byte[] compress(byte[] source) {
        return source;
    }

    /**
     * Inaction when compressing
     *
     * @param source Source data
     * @return Intact source data
     */
    @Override
    public byte[] decompress(byte[] source) {
        return source;
    }
}
