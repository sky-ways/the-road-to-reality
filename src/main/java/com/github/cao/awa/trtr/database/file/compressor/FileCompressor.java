package com.github.cao.awa.trtr.database.file.compressor;

public abstract class FileCompressor {
    public abstract byte[] compress(byte[] source);

    public abstract byte[] decompress(byte[] compressed);
}
