package com.github.cao.awa.trtr.math.base;

import org.apache.commons.codec.binary.*;

import java.nio.charset.*;
import java.util.*;

public class Base256 {
    public static byte[] longToBuf(long i) {
        byte[] buf = new byte[8];
        buf[0] = (byte) (i >>> 56);
        buf[1] = (byte) (i >>> 48);
        buf[2] = (byte) (i >>> 40);
        buf[3] = (byte) (i >>> 32);
        buf[4] = (byte) (i >>> 24);
        buf[5] = (byte) (i >>> 16);
        buf[6] = (byte) (i >>> 8);
        buf[7] = (byte) (i);
        return buf;
    }

    public static long longFromBuf(byte[] buf) {
        return ((buf[0] & 0xFFL) << 56) + ((buf[1] & 0xFFL) << 48) + ((buf[2] & 0xFFL) << 40) + ((buf[3] & 0xFFL) << 32) + ((buf[4] & 0xFFL) << 24) + ((buf[5] & 0xFFL) << 16) + ((buf[6] & 0xFFL) << 8) + ((buf[7] & 0xFFL));
    }

    public static byte[] intToBuf(int i) {
        byte[] buf = new byte[4];
        buf[0] = (byte) (i >>> 24);
        buf[1] = (byte) (i >>> 16);
        buf[2] = (byte) (i >>> 8);
        buf[3] = (byte) (i);
        return buf;
    }

    public static int intFromBuf(byte[] buf) {
        return (((buf[0] & 0xFF) << 24) + ((buf[1] & 0xFF) << 16) + ((buf[2] & 0xFF) << 8) + ((buf[3] & 0xFF)));
    }

    public static int tagFromBuf(byte[] buf) {
        return (((buf[0] & 0xFF) << 8) + ((buf[1] & 0xFF)));
    }

    public static byte[] tagToBuf(int i) {
        byte[] buf = new byte[2];
        buf[0] = (byte) (i >>> 8);
        buf[1] = (byte) (i);
        return buf;
    }
}

