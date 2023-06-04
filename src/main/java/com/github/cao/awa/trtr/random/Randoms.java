package com.github.cao.awa.trtr.random;

import java.util.Random;

public class Randoms {
    private static final Random RANDOM = new Random();

    public static double d() {
        return RANDOM.nextDouble();
    }

    public static double d(double bound) {
        return RANDOM.nextDouble(bound);
    }
}
