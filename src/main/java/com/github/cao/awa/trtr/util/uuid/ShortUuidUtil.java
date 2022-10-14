package com.github.cao.awa.trtr.util.uuid;

import java.util.*;

public class ShortUuidUtil {
    private final static char[] CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".toCharArray();

    public static String shortUuid(UUID uuid) {
        return shorter(uuid.getMostSignificantBits()) + shorter(uuid.getLeastSignificantBits());
    }

    public static String randomShortUuid() {
        return shortUuid(UUID.randomUUID());
    }

    private static String shorter(long l) {
        char[] buf = new char[11];
        long least = 0x3FL;
        int length = 11;
        do {
            buf[--length] = CHARS[(int) (l & least)];
            l >>>= 6;
        } while (l > 0);
        return new String(buf);
    }
}
