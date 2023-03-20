package com.github.cao.awa.trtr.block.stove.mud.fuel;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

@Auto
public class MudStoveFuelLayer implements NbtSerializable {
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
    public NbtElement toNbt() {
        return compound(nbt -> nbt.putInt("count",
                                          this.count
        ));
    }

    @Auto
    @Override
    public void fromNbt(NbtElement element) {
        as(element,
           NbtCompound.class,
           compound -> {
               this.count = compound.getInt("count");
           }
        );
    }
}
