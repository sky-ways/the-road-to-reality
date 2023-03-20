package com.github.cao.awa.trtr.framework.nbt.serializer.type.raw;

import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtFloat;

public class NbtFloatSerializer implements NbtSerializer<Float> {
    @Override
    public NbtElement serialize(Float i) {
        return NbtFloat.of(i);
    }

    @Override
    public Float deserialize(NbtElement element) {
        return as(element,
                  NbtFloat.class,
                  NbtFloat :: floatValue
        );
    }

    @Override
    public Float initializer() {
        return - 1F;
    }
}
