package com.github.cao.awa.trtr.block.stove.mud.fuel;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.serializer.AutoNbt;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializable;

@Auto
public class MudStoveFuelLayer implements NbtSerializable {
    @AutoNbt
    private int count;

    public boolean add() {
        if (! isMax()) {
            this.count += 1;
            return true;
        }
        return false;
    }

    public boolean isMax() {
        return this.count == 45;
    }

    public int get() {
        return this.count;
    }
}
