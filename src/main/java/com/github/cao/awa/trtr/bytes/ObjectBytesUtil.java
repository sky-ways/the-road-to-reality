package com.github.cao.awa.trtr.bytes;

import com.github.cao.awa.apricot.mathematic.base.SkippedBase256;
import com.github.cao.awa.apricot.util.io.bytes.BytesReader;
import com.github.cao.awa.apricot.util.io.bytes.BytesUtil;
import net.minecraft.util.math.BlockPos;

public class ObjectBytesUtil {
    public static byte[] blockPosToBytes(BlockPos pos) {
        return BytesUtil.concat(
                SkippedBase256.intToBuf(pos.getX()),
                SkippedBase256.intToBuf(pos.getY()),
                SkippedBase256.intToBuf(pos.getZ())
        );
    }

    public static BlockPos bytesToBlockpos(byte[] bytes) {
        BytesReader reader = BytesReader.of(bytes);

        int x = SkippedBase256.readInt(reader);
        int y = SkippedBase256.readInt(reader);
        int z = SkippedBase256.readInt(reader);

        return new BlockPos(
                x,
                y,
                z
        );
    }
}
