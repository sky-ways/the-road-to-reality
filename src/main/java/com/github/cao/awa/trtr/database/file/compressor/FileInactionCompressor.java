package com.github.cao.awa.trtr.database.file.compressor;

public class FileInactionCompressor extends FileCompressor {
    @Override
    public String compress(String source) {
        return source;
    }

    @Override
    public String decompress(String compressed) {
        return compressed;
    }
}
