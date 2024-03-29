package com.github.cao.awa.trtr.math;

import com.github.cao.awa.trtr.math.range.IntegerRange;
import net.minecraft.util.math.MathHelper;

import java.math.BigDecimal;

public class Mathematics extends MathHelper {
    public static final float QUARTER_PI = 0.7853981633974483F;

    public static boolean inRange(int current, int min, int max) {
        return Math.max(min,
                        current
        ) == Math.min(current,
                      max
        );
    }

    public static boolean inRange(int current, IntegerRange range) {
        return inRange(current,
                       range.min(),
                       range.max()
        );
    }

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
     */
    static int stringSize(long x) {
        int d = 1;
        if (x > - 1) {
            d = 0;
            x = - x;
        }
        long p = - 10;
        for (int i = 1; i < 19; i++) {
            if (x > p)
                return i + d;
            p *= 10;
        }
        return 19 + d;
    }
}
