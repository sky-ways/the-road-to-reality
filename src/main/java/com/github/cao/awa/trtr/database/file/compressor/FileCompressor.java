package com.github.cao.awa.trtr.database.file.compressor;

public abstract class FileCompressor {
    public abstract String compress(String source);

    public abstract String decompress(String compressed);
}
