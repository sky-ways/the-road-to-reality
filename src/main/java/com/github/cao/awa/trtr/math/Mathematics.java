package com.github.cao.awa.trtr.math;

import net.minecraft.util.math.*;

import java.math.*;

public class Mathematics extends MathHelper {
    public static int absHash(Object key) {
        return key.hashCode() & Integer.MAX_VALUE;
    }

    public static Number truncation(long value, long truncate) {
        return new BigDecimal(value).divideToIntegralValue(new BigDecimal(truncate));
    }

    static int longSize(long x) {
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
