package com.github.cao.awa.trtr.math;

import net.minecraft.util.math.*;

import java.math.*;

public class Mathematics extends MathHelper {
    public static final float QUARTER_PI = 0.7853981633974483F;

    public static int absHash(Object key) {
        return key.hashCode() & Integer.MAX_VALUE;
    }

    public static Number truncation(long value, long truncate) {
        return new BigDecimal(value).divideToIntegralValue(new BigDecimal(truncate));
    }

    /**
     * Returns the string representation size for a given long value.
     *
     * @param x Long value
     * @return String size
     *
     * @see java.lang.Long#stringSize(long)
     */
    static int stringSize(long x) {
        int d = 1;
        if (x > -1) {
            d = 0;
            x = -x;
        }
        long p = -10;
        for (int i = 1; i < 19; i++) {
            if (x > p)
                return i + d;
            p *= 10;
        }
        return 19 + d;
    }
}
