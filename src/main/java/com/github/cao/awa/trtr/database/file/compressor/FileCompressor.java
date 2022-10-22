package com.github.cao.awa.trtr.database.file.compressor;

public abstract class FileCompressor {
    /**
     * Do compress for input
     *
     * @param source A data
     * @return Compress result
     */
    public abstract byte[] compress(byte[] source);

    /**
     * Do decompress for input
     *
     * @param source A compressed data
     * @return Decompress result
     */
    public abstract byte[] decompress(byte[] source);
}
