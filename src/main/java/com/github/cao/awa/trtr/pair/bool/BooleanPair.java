package com.github.cao.awa.trtr.pair.bool;

import net.minecraft.network.PacketByteBuf;

public record BooleanPair(boolean main, boolean off) {
    public void write(PacketByteBuf buf) {
        buf.writeBoolean(main());
        buf.writeBoolean(off());
    }

    public static BooleanPair create(PacketByteBuf buf) {
        boolean mainStack = buf.readBoolean();
        boolean offStack = buf.readBoolean();

        return new BooleanPair(
                mainStack,
                offStack
        );
    }
}