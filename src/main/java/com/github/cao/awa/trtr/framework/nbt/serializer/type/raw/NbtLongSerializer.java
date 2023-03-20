package com.github.cao.awa.trtr.framework.nbt.serializer.type.raw;

import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtLong;

public class NbtLongSerializer implements NbtSerializer<Long> {
    @Override
    public NbtElement serialize(Long l) {
        return NbtLong.of(l);
    }

    @Override
    public Long deserialize(NbtElement element) {
        return as(element,
                  NbtLong.class,
                  NbtLong :: longValue
        );
    }

    @Override
    public Long initializer() {
        return - 1L;
    }
}
