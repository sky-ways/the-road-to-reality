package com.github.cao.awa.trtr.framework.nbt.serializer.type.raw;

import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtString;

import java.math.BigInteger;

public class NbtBigIntegerSerializer implements NbtSerializer<BigInteger> {
    @Override
    public NbtElement serialize(BigInteger str) {
        return NbtString.of(str.toString(36));
    }

    @Override
    public BigInteger deserialize(NbtElement element) {
        return as(element,
                  NbtString.class,
                  str -> new BigInteger(str.asString(),
                                        36
                  )
        );
    }

    @Override
    public BigInteger initializer() {
        return BigInteger.valueOf(- 1L);
    }
}
