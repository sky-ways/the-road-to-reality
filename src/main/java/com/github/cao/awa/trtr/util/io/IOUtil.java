package com.github.cao.awa.trtr.util.io;

import java.io.*;

public class IOUtil {
    public static void write(OutputStream output, InputStream input) throws IOException {
        output.write(input.readAllBytes());
        output.close();
        input.close();
    }

    public static void write(OutputStream output, byte[] input) throws IOException {
        output.write(input);
        output.close();
    }

    public static void write(OutputStream output, String input) throws IOException {
        output.write(input.getBytes());
        output.close();
    }

    public static void write(Writer output, String input) throws IOException {
        output.write(input);
        output.close();
    }


    public static void write(Writer writer, Reader reader) throws IOException {
        char[] chars = new char[4096];
        int length;
        while ((length = reader.read(chars)) != - 1) {
            writer.write(
                    chars,
                    0,
                    length
            );
        }
        writer.close();
        reader.close();
    }

    public static void write(Writer writer, char[] information) throws IOException {
        writer.write(information);
        writer.close();
    }

    public static String read(InputStream inputStream) {
        return read(new InputStreamReader(inputStream));
    }

    public static String read(Reader reader) {
        try {
            char[] chars = new char[4096];
            int length;
            StringBuilder builder = new StringBuilder();
            while ((length = reader.read(chars)) != - 1) {
                builder.append(chars, 0, length);
            }
            reader.close();
            return builder.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
