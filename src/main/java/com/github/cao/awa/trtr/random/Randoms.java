package com.github.cao.awa.trtr.random;

import java.security.SecureRandom;
import java.util.Random;

public class Randoms {
    private static final RandomDelegate RANDOM = new RandomDelegate(new Random());
    private static final RandomDelegate SECURE_RANDOM = new RandomDelegate(new SecureRandom());


    public static double d() {
        return RANDOM.d();
    }

    public static double d(double bound) {
        return RANDOM.d(bound);
    }

    public static double d(double origin, double bound) {
        return RANDOM.d(origin,
                        bound
        );
    }

    public static float f() {
        return RANDOM.f();
    }

    public static float f(float bound) {
        return RANDOM.f(bound);
    }

    public static float f(float origin, float bound) {
        return RANDOM.f(origin,
                        bound
        );
    }

    public static int i() {
        return RANDOM.i();
    }

    public static int i(int bound) {
        return RANDOM.i(bound);
    }

    public static int i(int origin, int bound) {
        return RANDOM.i(origin, bound);
    }

    public static long l() {
        return RANDOM.l();
    }

    public static long l(long bound) {
        return RANDOM.l(bound);
    }

    public static long l(long origin, long bound) {
        return RANDOM.l(origin, bound);
    }

    public static RandomDelegate s() {
        return SECURE_RANDOM;
    }
}
