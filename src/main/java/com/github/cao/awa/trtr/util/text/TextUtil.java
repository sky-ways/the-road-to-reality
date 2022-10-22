package com.github.cao.awa.trtr.util.text;

import it.unimi.dsi.fastutil.chars.*;
import it.unimi.dsi.fastutil.objects.*;

import java.util.*;

public class TextUtil {
    public static List<String> partitionToList(String str, int size) {
        return partitionToList(
                str,
                size,
                false
        );
    }

    public static List<String> partitionToList(String str, int size, boolean saveUnnecessary) {
        int capacity = str.length() / size + 1;
        List<String> result = new ObjectArrayList<>(capacity);
        int cursor = 0;
        for (int i = 0; i < capacity; i++) {
            int nextCursor = cursor + size;
            if (str.length() > cursor) {
                if (str.length() > nextCursor) {
                    result.add(str.substring(
                            cursor,
                            nextCursor
                    ));
                } else if (saveUnnecessary) {
                    result.add(str.substring(cursor));
                    break;
                }
            }
            cursor = nextCursor;
        }
        return result;
    }

    public static String[] partitionToArray(String str, int size) {
        return partitionToArray(
                str,
                size,
                false
        );
    }

    public static String[] partitionToArray(String str, int size, boolean saveUnnecessary) {
        int capacity = str.length() / size + 1;
        String[] strings = new String[capacity];
        int cursor = 0;
        for (int i = 0; i < capacity; i++) {
            int nextCursor = cursor + size;
            if (str.length() > cursor) {
                if (str.length() > nextCursor) {
                    strings[i] = str.substring(
                            cursor,
                            nextCursor
                    );
                } else if (saveUnnecessary) {
                    strings[i] = str.substring(cursor);
                    break;
                } else {
                    strings[i] = str.substring(cursor);
                    String[] processNull = new String[capacity - 1];
                    System.arraycopy(strings, 0, processNull, 0, processNull.length);
                    return processNull;
                }
            }
            cursor = nextCursor;
        }
        return strings;
    }

    public static List<String> equivalentRepeated(String str) {
        List<String> result = new ObjectArrayList<>();
        StringBuilder builder = new StringBuilder();
        char last = str.charAt(0);
        boolean started = false;
        for (char value : str.toCharArray()) {
            if (started) {
                if (Objects.equals(
                        value,
                        last
                )) {
                    builder.append(value);
                } else {
                    builder.append(last);
                    result.add(builder.toString());
                    builder = new StringBuilder();
                }
                last = value;
            } else {
                started = true;
            }
        }
        builder.append(last);
        result.add(builder.toString());
        return result;
    }
}
