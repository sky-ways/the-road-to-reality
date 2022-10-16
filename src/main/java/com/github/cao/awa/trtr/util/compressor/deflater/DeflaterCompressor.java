package com.github.cao.awa.trtr.util.compressor.deflater;

import com.github.cao.awa.trtr.util.compressor.*;

import java.io.*;
import java.util.zip.*;

public class DeflaterCompressor implements InformationCompressor {
    public static final DeflaterCompressor INSTANCE = new DeflaterCompressor();

    public byte[] compress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return bytes;
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
            deflater.setStrategy(Deflater.DEFAULT_STRATEGY);
            DeflaterOutputStream outputStream = new DeflaterOutputStream(
                    out,
                    deflater
            );
            outputStream.write(bytes);
            outputStream.close();
            return out.toByteArray();
        } catch (Exception e) {
            return bytes;
        }
    }

    public byte[] decompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return bytes;
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            InflaterInputStream inflaterInputStream = new InflaterInputStream(new ByteArrayInputStream(bytes));
            byte[] buf = new byte[4096];
            int size;
            while ((size = inflaterInputStream.read(buf)) > - 1) {
                out.write(
                        buf,
                        0,
                        size
                );
            }
            return out.toByteArray();
        } catch (Exception ex) {
            return bytes;
        }
    }
}
