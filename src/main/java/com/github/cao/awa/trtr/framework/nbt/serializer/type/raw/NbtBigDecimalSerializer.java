package com.github.cao.awa.trtr.framework.nbt.serializer.type.raw;

import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtString;

import java.math.BigDecimal;

public class NbtBigDecimalSerializer implements NbtSerializer<BigDecimal> {
    @Override
    public NbtElement serialize(BigDecimal str) {
        return NbtString.of(str.toString());
    }

    @Override
    public BigDecimal deserialize(NbtElement element) {
        return as(element,
                  NbtString.class,
                  str -> new BigDecimal(str.asString())
        );
    }

    @Override
    public BigDecimal initializer() {
        return BigDecimal.valueOf(- 1L);
    }
}
