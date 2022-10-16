package com.github.cao.awa.trtr.util.compressor.lz4;

import com.github.cao.awa.trtr.util.compressor.*;
import net.jpountz.lz4.*;

public class Lz4Compressor implements InformationCompressor {
    public static final Lz4Compressor INSTANCE = new Lz4Compressor();

    public byte[] compress(byte[] bytes) {
        return LZ4Factory.fastestInstance().fastCompressor().compress(bytes);
    }

    public byte[] decompress(byte[] bytes) {
        return LZ4Factory.fastestInstance().fastDecompressor().decompress(bytes, bytes.length);
    }
}