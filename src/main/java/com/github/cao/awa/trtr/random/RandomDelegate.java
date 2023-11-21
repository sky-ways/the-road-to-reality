package com.github.cao.awa.trtr.random;

import java.util.random.RandomGenerator;

public class RandomDelegate {
    private final RandomGenerator delegate;

    public RandomDelegate(RandomGenerator delegate) {
        this.delegate = delegate;
    }

    public double d() {
        return this.delegate.nextDouble();
    }

    public double d(double bound) {
        return this.delegate.nextDouble(bound);
    }

    public double d(double origin, double bound) {
        return this.delegate.nextDouble(origin,
                                        bound
        );
    }

    public float f() {
        return this.delegate.nextFloat();
    }

    public float f(float bound) {
        return this.delegate.nextFloat(bound);
    }

    public float f(float origin, float bound) {
        return this.delegate.nextFloat(origin,
                                       bound
        );
    }

    public int i() {
        return this.delegate.nextInt();
    }

    public int i(int bound) {
        return this.delegate.nextInt(bound);
    }

    public int i(int origin, int bound) {
        return this.delegate.nextInt(origin, bound);
    }

    public long l() {
        return this.delegate.nextLong();
    }

    public long l(long bound) {
        return this.delegate.nextLong(bound);
    }

    public long l(long origin, long bound) {
        return this.delegate.nextLong(origin,
                                      bound
        );
    }

    public boolean b() {
        return this.delegate.nextBoolean();
    }
}
