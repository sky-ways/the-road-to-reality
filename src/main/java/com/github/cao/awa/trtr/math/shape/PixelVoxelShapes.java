package com.github.cao.awa.trtr.math.shape;


import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

/**
 * Utils for {@link net.minecraft.util.shape.VoxelShape}.
 *
 * @author cao_awa
 * @since 1.0.0
 */
public class PixelVoxelShapes {
    /**
     * Build the rectangle shape.
     *
     * @param x1 First point of x
     * @param y1 First point of y
     * @param z1 First point of z
     * @param x2 Second point of x
     * @param y2 Second point of y
     * @param z2 Second point of z
     * @return Rectangle shape
     * @author cao_awa
     * @see net.minecraft.util.shape.VoxelShape
     * @see net.minecraft.util.shape.VoxelShapes
     */
    public static VoxelShape cuboid(int x1, int y1, int z1, int x2, int y2, int z2) {
        return VoxelShapes.cuboid(
                x1 / 16D,
                y1 / 16D,
                z1 / 16D,
                x2 / 16D,
                y2 / 16D,
                z2 / 16D
        );
    }

    /**
     * Union the shapes.
     *
     * @param first Base shape
     * @param other Component shapes
     * @return Union result
     * @author cao_awa
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