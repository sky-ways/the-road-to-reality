package com.github.cao.awa.trtr.util;

import java.io.*;
import java.nio.charset.*;
import java.util.zip.*;

public class FileUtil {
    public static String compress(String str, int strategy) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (strategy == - 1) {
            strategy = Deflater.DEFAULT_STRATEGY;
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
            deflater.setStrategy(strategy);
            DeflaterOutputStream gzip = new DeflaterOutputStream(out, deflater);
            gzip.write(str.getBytes(StandardCharsets.UTF_8));
            gzip.close();
            return out.toString(StandardCharsets.ISO_8859_1);
        } catch (Exception e) {
            return str;
        }
    }

    public static String decompress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            InflaterInputStream gunzip = new InflaterInputStream(new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1)));
            byte[] buf = new byte[4096];
            int size;
            while ((size = gunzip.read(buf)) > - 1) {
                out.write(buf, 0, size);
            }
            return out.toString(StandardCharsets.UTF_8);
        } catch (Exception ex) {
            return str;
        }
    }
}
