package com.github.cao.awa.trtr.framework.nbt.serializer.type.raw;

import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtInt;

public class NbtCharSerializer implements NbtSerializer<Character> {
    @Override
    public NbtElement serialize(Character b) {
        return NbtInt.of(b);
    }

    @Override
    public Character deserialize(NbtElement element) {
        return as(element,
                  NbtInt.class,
                  i -> (char) i.intValue()
        );
    }

    @Override
    public Character initializer() {
        return (char) - 1;
    }
}
