package com.github.cao.awa.trtr.pair.integer;

import net.minecraft.network.PacketByteBuf;

public record IntegerPair(int main, int off) {
    public void write(PacketByteBuf buf) {
        buf.writeInt(main());
        buf.writeInt(off());
    }

    public static IntegerPair create(PacketByteBuf buf) {
        int mainStack = buf.readInt();
        int offStack = buf.readInt();

        return new IntegerPair(
                mainStack,
                offStack
        );
    }
}