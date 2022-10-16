package com.github.cao.awa.trtr.util.compressor;

import net.jpountz.lz4.*;
import org.apache.commons.codec.binary.*;

import java.io.*;
import java.nio.charset.*;
import java.util.zip.*;

public interface InformationCompressor {
    byte[] compress(byte[] bytes);

    byte[] decompress(byte[] bytes);
}
