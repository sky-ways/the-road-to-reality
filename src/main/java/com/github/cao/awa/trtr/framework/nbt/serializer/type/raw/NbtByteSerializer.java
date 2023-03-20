package com.github.cao.awa.trtr.framework.nbt.serializer.type.raw;

import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import net.minecraft.nbt.NbtByte;
import net.minecraft.nbt.NbtElement;

public class NbtByteSerializer implements NbtSerializer<Byte> {
    @Override
    public NbtElement serialize(Byte b) {
        return NbtByte.of(b);
    }

    @Override
    public Byte deserialize(NbtElement element) {
        return as(element,
                  NbtByte.class,
                  NbtByte :: byteValue
        );
    }

    @Override
    public Byte initializer() {
        return - 1;
    }
}
