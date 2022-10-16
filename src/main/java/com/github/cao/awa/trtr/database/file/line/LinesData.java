package com.github.cao.awa.trtr.database.file.line;

import it.unimi.dsi.fastutil.objects.*;

import java.nio.charset.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class LinesData {
    private final Map<String, String> lines = new Object2ObjectOpenHashMap<>();

    public LinesData() {

    }

    public LinesData(String data) {
        read(data);
    }

    public void read(String data) {
        //      try {
        //          int lines = 0;
        //          BufferedReader reader = new BufferedReader(new StringReader(data));
        //          String line;
        //          String key = null;
        //          String value;
        //          while ((line = reader.readLine()) != null) {
        //              lines++;
        //              if ((lines & 1) == 0) {
        //                  value = line;
        //                  this.lines.put(
        //                          key,
        //                          value
        //                  );
        //              } else {
        //                  key = line;
        //              }
        //          }
        //      } catch (Exception e) {
        //
        //      }

        //      try {
        //          int nextIndex;
        //          int cursor = 0;
        //          while ((nextIndex = data.indexOf("\n", cursor)) != -1) {
        //              String key = data.substring(cursor, nextIndex);
        //              cursor = nextIndex + 1;
        //              nextIndex = data.indexOf("\n", cursor);
        //              if (nextIndex == - 1) {
        //                  nextIndex = data.length();
        //              }
        //              this.lines.put(key, data.substring(cursor - 1, nextIndex));
        //
        //              cursor = nextIndex + 1;
        //          }
        //      } catch (Exception e) {
        //
        //      }

        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        byte[] key = null;
        byte[] value;
        int index = 0;
        int cursor = 0;
        int length = 0;
        boolean w = false;
        for (byte b : bytes) {
            if (b == '\n') {
                if (w) {
                    value = new byte[length];
                    System.arraycopy(bytes,
                                     cursor,
                                     value,
                                     0,
                                     length
                    );
                    this.lines.put(
                            new String(
                                    key,
                                    StandardCharsets.UTF_8
                            ),
                            new String(
                                    value,
                                    StandardCharsets.UTF_8
                            )
                    );
                    w = false;
                } else {
                    key = new byte[length];
                    System.arraycopy(bytes,
                                     cursor,
                                     key,
                                     0,
                                     length
                    );
                    w = true;
                }
                length = 0;
                cursor = index + 1;
            } else {
                length++;
            }
            index++;
        }
    }

    public static void main(String[] args) {
        System.out.println("------");

        LinesData data1 = new LinesData();
        data1.read("""
                           a1
                           a
                           a2
                           a
                           a3
                           a
                           a4
                           a
                           a5
                           c
                           """);
        System.out.println(data1);
    }

    public static <T> String toString(Map<String, T> map, BiFunction<String, T, String> keyFunction, BiFunction<String, T, String> valueFunction) {
        StringBuffer builder = new StringBuffer();
        map.forEach((key, value) -> builder.append(keyFunction.apply(
                                                   key,
                                                   value
                                           ))
                                           .append("\n")
                                           .append(valueFunction.apply(
                                                   key,
                                                   value
                                           ))
                                           .append("\n"));
        return builder.toString();
    }

    public void put(String key, String value) {
        this.lines.put(
                key,
                value
        );
    }

    public Set<String> keySet() {
        return lines.keySet();
    }

    public String get(String key) {
        return lines.get(key);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        lines.forEach((key, value) -> builder.append(key)
                                             .append("\n")
                                             .append(value)
                                             .append("\n"));
        return builder.toString();
    }
}
