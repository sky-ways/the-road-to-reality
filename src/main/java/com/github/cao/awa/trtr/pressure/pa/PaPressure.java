package com.github.cao.awa.trtr.pressure.pa;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.serializer.AutoNbt;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializable;

@Auto
public class PaPressure implements NbtSerializable {
    @Auto
    @AutoNbt
    private long paValue;

    public PaPressure() {
        this.paValue = 0;
    }

    public PaPressure(long paValue) {
        this.paValue = paValue;
    }

    public long value() {
        return this.paValue;
    }

    public PaPressure value(long paValue) {
        this.paValue = paValue;
        return this;
    }

    public PaPressure copy() {
        return new PaPressure(this.paValue);
    }
}
