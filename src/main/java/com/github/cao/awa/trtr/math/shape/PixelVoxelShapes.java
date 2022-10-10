package com.github.cao.awa.trtr.math.shape;

import net.minecraft.util.shape.*;

public class PixelVoxelShapes {
    public static VoxelShape cuboid(int px1, int py1, int pz1, int px2, int py2, int pz2) {
        return VoxelShapes.cuboid(
                px1 / 16D,
                py1 / 16D,
                pz1 / 16D,
                px2 / 16D,
                py2 / 16D,
                pz2 / 16D
        );
    }

    public static VoxelShape union(VoxelShape first, VoxelShape... other) {
        return VoxelShapes.union(
                first,
                other
        );
    }
}
