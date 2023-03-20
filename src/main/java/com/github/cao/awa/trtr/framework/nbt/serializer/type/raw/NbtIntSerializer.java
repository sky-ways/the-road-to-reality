package com.github.cao.awa.trtr.framework.nbt.serializer.type.raw;

import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtInt;

public class NbtIntSerializer implements NbtSerializer<Integer> {
    @Override
    public NbtElement serialize(Integer i) {
        return NbtInt.of(i);
    }

    @Override
    public Integer deserialize(NbtElement element) {
        return as(element,
                  NbtInt.class,
                  NbtInt :: intValue
        );
    }

    @Override
    public Integer initializer() {
        return - 1;
    }
}
