package com.github.cao.awa.trtr.framework.nbt.serializer.type.raw;

import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtShort;

public class NbtShortSerializer implements NbtSerializer<Short> {
    @Override
    public NbtElement serialize(Short b) {
        return NbtShort.of(b);
    }

    @Override
    public Short deserialize(NbtElement element) {
        return as(element,
                  NbtShort.class,
                  NbtShort :: shortValue
        );
    }

    @Override
    public Short initializer() {
        return - 1;
    }
}
