package com.github.cao.awa.trtr.math;

public class Numerical {
    public static int parseInt(String str) {
        return Integer.parseInt(str);
    }

    public static float parseFloat(String str) {
        return Float.parseFloat(str);
    }

    public static double parseDouble(String str) {
        return Double.parseDouble(str);
    }

    public static short parseShort(String str) {
        return Short.parseShort(str);
    }

    public static byte parseByte(String str) {
        return Byte.parseByte(str);
    }

    public static long parseLong(String str) {
        return Long.parseLong(str.replace("L", "").replace("l", ""));
    }

    public static char parseChar(String str) {
        return (char) Integer.parseInt(str);
    }
}
