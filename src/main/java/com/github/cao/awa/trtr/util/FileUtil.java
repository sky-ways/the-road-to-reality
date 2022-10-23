package com.github.cao.awa.trtr.util;

import it.unimi.dsi.fastutil.io.*;
import net.jpountz.lz4.*;
import org.apache.commons.io.*;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.*;
import java.util.zip.*;

public class FileUtil {
    public static void unzip(String zip, String path) throws RuntimeException {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(zip);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                if (entry.isDirectory()) {
                    String dirPath = path + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    File targetFile = new File(path + "/" + entry.getName());
                    if (! targetFile.getParentFile()
                                    .exists()) {
                        targetFile.getParentFile()
                                  .mkdirs();
                    }
                    targetFile.createNewFile();
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[4096];
                    while ((len = is.read(buf)) != - 1) {
                        fos.write(
                                buf,
                                0,
                                len
                        );
                    }
                    fos.close();
                    is.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to unzip: " + zip,
                    e
            );
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readZip(String zip, String inZipFile) throws RuntimeException {
        try {
            FastBufferedInputStream is = new FastBufferedInputStream(new ZipFile(zip).getInputStream(new ZipEntry(inZipFile)));
            StringBuilder builder = new StringBuilder();
            int length;
            byte[] buf = new byte[1024];
            while ((length = is.read(buf)) != - 1) {
                String s = new String(
                        buf,
                        0,
                        length,
                        StandardCharsets.UTF_8
                );
                builder.append(s);
            }
            is.close();
            return builder.toString();
        } catch (Exception e) {

        }
        return "";
    }

    public static void zipFile(File file, File toFile) {
        FileInputStream in = null;
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FastBufferedOutputStream(new FileOutputStream(toFile)));
            ZipEntry zipEntry = new ZipEntry("");
            zipOutputStream.putNextEntry(zipEntry);
            in = new FileInputStream(file);
            int len;
            byte[] buf = new byte[4096];
            while ((len = in.read(buf)) != - 1) {
                zipOutputStream.write(
                        buf,
                        0,
                        len
                );
            }
            zipOutputStream.closeEntry();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void expandZip(File zipFile, List<File> files) throws IOException {
        File tempFile = File.createTempFile(
                zipFile.getName(),
                null
        );
        tempFile.delete();

        boolean renameOk = zipFile.renameTo(tempFile);
        if (! renameOk) {
            throw new RuntimeException("Could not rename the file " + zipFile.getAbsolutePath() + " to " + tempFile.getAbsolutePath());
        }
        byte[] buf = new byte[4096];

        ZipInputStream zin = new ZipInputStream(new FastBufferedInputStream(new FileInputStream(tempFile)));
        ZipOutputStream out = new ZipOutputStream(new FastBufferedOutputStream(new FileOutputStream(zipFile)));

        ZipEntry entry = zin.getNextEntry();
        while (entry != null) {
            String name = entry.getName();
            boolean notInFiles = true;
            for (File f : files) {
                if (f.getName()
                     .equals(name)) {
                    notInFiles = false;
                    break;
                }
            }
            if (notInFiles) {
                out.putNextEntry(new ZipEntry(name));
                int len;
                while ((len = zin.read(buf)) > 0) {
                    out.write(
                            buf,
                            0,
                            len
                    );
                }
            }
            entry = zin.getNextEntry();
        }
        zin.close();
        for (File file : files) {
            InputStream in = new FastBufferedInputStream(new FileInputStream(file));
            out.putNextEntry(new ZipEntry(file.getName()));
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(
                        buf,
                        0,
                        len
                );
            }
            out.closeEntry();
            in.close();
        }
        out.close();
        tempFile.delete();
    }
}

