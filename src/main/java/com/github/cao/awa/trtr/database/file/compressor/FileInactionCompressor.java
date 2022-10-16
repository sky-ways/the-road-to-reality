package com.github.cao.awa.trtr.database.file.compressor;

public class FileInactionCompressor extends FileCompressor {
    @Override
    public byte[] compress(byte[] source) {
        return source;
    }

    @Override
    public byte[] decompress(byte[] compressed) {
        return compressed;
    }
}
