package com.github.cao.awa.trtr.framework.nbt.serializer.type.raw;

import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtString;

public class NbtStringSerializer implements NbtSerializer<String> {
    @Override
    public NbtElement serialize(String str) {
        return NbtString.of(str);
    }

    @Override
    public String deserialize(NbtElement element) {
        return as(element,
                  NbtString.class,
                  NbtString :: asString
        );
    }

    @Override
    public String initializer() {
        return "";
    }
}
