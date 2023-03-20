package com.github.cao.awa.trtr.block.stove.mud.fuel;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.framework.serializer.NbtSerializer;
import net.minecraft.nbt.NbtCompound;

@Auto
public class MudStoveFuelLayer implements NbtSerializer {
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

    @Auto
    @Override
    public NbtCompound toNbt() {
        return nbt(nbt -> nbt.putInt("count",
                                     this.count
        ));
    }

    @Auto
    @Override
    public void fromNbt(NbtCompound compound) {
        this.count = compound.getInt("count");
    }
}
