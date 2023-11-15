package com.github.cao.awa.trtr.math.range;

import net.minecraft.network.PacketByteBuf;

public record IntegerRange(int min, int max) {
    public IntegerRange {
        checkRange(min,
                   max
        );
    }

    public static void checkRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("The minimum value cannot large than maximum value");
        }
    }

    public void write(PacketByteBuf buf) {
        buf.writeInt(min());
        buf.writeInt(max());
    }

    public static IntegerRange create(PacketByteBuf buf) {
        int min = buf.readInt();
        int max = buf.readInt();

        return new IntegerRange(
                min,
                max
        );
    }
}
