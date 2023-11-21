package com.github.cao.awa.trtr.block.stove.mud.fuel;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.anntation.Unsupported;
import com.github.cao.awa.trtr.annotation.serializer.AutoNbt;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializable;
import net.minecraft.nbt.NbtElement;

// TODO Waiting for plan 'Smelting Process'
@Auto
@Unsupported
public class MudStoveFuelLayer implements NbtSerializable {
    @Auto
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

    @Override
    public void fromNbt(NbtElement element) {
        throw new IllegalArgumentException(element.toString());
    }

    public int get() {
        return this.count;
    }
}
