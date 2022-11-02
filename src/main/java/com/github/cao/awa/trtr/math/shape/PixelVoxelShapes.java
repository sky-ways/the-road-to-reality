package com.github.cao.awa.trtr.math.shape;

import net.minecraft.util.shape.*;

/**
 * Utils for {@link net.minecraft.util.shape.VoxelShape}.
 *
 * @since 1.0.0
 */
public class PixelVoxelShapes {
    /**
     * Build the rectangle shape.
     *
     * @param px1 First point of x
     * @param py1 First point of y
     * @param pz1 First point of z
     * @param px2 Second point of x
     * @param py2 Second point of y
     * @param pz2 Second point of z
     * @return Rectangle shape
     *
     * @author cao_awa
     *
     * @see net.minecraft.util.shape.VoxelShape
     * @see net.minecraft.util.shape.VoxelShapes
     */
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

    /**
     * Union the shapes.
     *
     * @param first Base shape
     * @param other Component shapes
     * @return Union result
     *
     * @author cao_awa
     *
     * @see net.minecraft.util.shape.VoxelShape
     * @see net.minecraft.util.shape.VoxelShapes
     */
    public static VoxelShape union(VoxelShape first, VoxelShape... other) {
        return VoxelShapes.union(
                first,
                other
        );
    }
}
