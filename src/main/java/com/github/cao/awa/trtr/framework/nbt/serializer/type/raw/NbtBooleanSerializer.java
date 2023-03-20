package com.github.cao.awa.trtr.framework.nbt.serializer.type.raw;

import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import net.minecraft.nbt.NbtByte;
import net.minecraft.nbt.NbtElement;

public class NbtBooleanSerializer implements NbtSerializer<Boolean> {
    @Override
    public NbtElement serialize(Boolean b) {
        return NbtByte.of(b);
    }

    @Override
    public Boolean deserialize(NbtElement element) {
        return as(element,
                  NbtByte.class,
                  b -> b.byteValue() == 0
        );
    }

    @Override
    public Boolean initializer() {
        return false;
    }
}
