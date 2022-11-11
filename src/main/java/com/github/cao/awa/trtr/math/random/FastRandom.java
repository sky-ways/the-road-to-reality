package com.github.cao.awa.trtr.math.random;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.times.*;

public class FastRandom {
    public long seed = TimeUtil.nano();
    private long flip = 0B100;

    public long nextLong() {
        return (seed = (seed * 252149039174L) ^ seed) / (flip = 4L << flip);
    }

    public int nextInt() {
        return (int)((seed = (seed * 1392137464) ^ seed) / (flip = 16L << flip));
    }
}
