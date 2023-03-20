package com.github.cao.awa.trtr.framework.nbt.serializer.type.raw;

import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtElement;

public class NbtDoubleSerializer implements NbtSerializer<Double> {
    @Override
    public NbtElement serialize(Double i) {
        return NbtDouble.of(i);
    }

    @Override
    public Double deserialize(NbtElement element) {
        return as(element,
                  NbtDouble.class,
                  NbtDouble :: doubleValue
        );
    }

    @Override
    public Double initializer() {
        return - 1D;
    }
}
