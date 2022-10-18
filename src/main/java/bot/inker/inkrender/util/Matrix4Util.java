package bot.inker.inkrender.util;

import com.github.cao.awa.trtr.math.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.math.Vector4f;

import java.nio.FloatBuffer;

public final class Matrix4Util {
    public static final Matrix4f IDENTITY_MATRIX = identityMatrix();
    public static final Matrix4f ROTATE_Y_OF_QUARTER_PI = rotateY(Mathematics.QUARTER_PI);
    public static final Matrix4f HALF_OF_XYZ_TRANSLATE = translate(
            0.5F,
            0.5F,
            0.5F
    );

    public static Matrix4f identityMatrix() {
        return build(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
    }

    public static Matrix4f build(
            float x1y1, float x2y1, float x3y1, float x4y1,
            float x1y2, float x2y2, float x3y2, float x4y2,
            float x1y3, float x2y3, float x3y3, float x4y3,
            float x1y4, float x2y4, float x3y4, float x4y4
    ) {
        Matrix4f mat = new Matrix4f();
        mat.readRowMajor(
                FloatBuffer.wrap(
                        new float[]{
                                x1y1, x2y1, x3y1, x4y1,
                                x1y2, x2y2, x3y2, x4y2,
                                x1y3, x2y3, x3y3, x4y3,
                                x1y4, x2y4, x3y4, x4y4
                        })
        );
        return mat;
    }

    public static Matrix4f translate(float x, float y, float z) {
        return build(
                1, 0, 0, x,
                0, 1, 0, y,
                0, 0, 1, z,
                0, 0, 0, 1
        );
    }

    public static Matrix4f rotateX(float rad) {
        return build(
                1, 0, 0, 0,
                0, MathHelper.cos(rad), - MathHelper.sin(rad), 0,
                0, MathHelper.sin(rad), MathHelper.cos(rad), 0,
                0, 0, 0, 1
        );
    }

    public static Matrix4f rotateY(float rad) {
        return build(
                MathHelper.cos(rad), 0, MathHelper.sin(rad), 0,
                0, 1, 0, 0,
                - MathHelper.sin(rad), 0, MathHelper.cos(rad), 0,
                0, 0, 0, 1
        );
    }

    public static Matrix4f rotateZ(float rad) {
        return build(
                MathHelper.cos(rad), - MathHelper.sin(rad), 0, 0,
                MathHelper.sin(rad), MathHelper.cos(rad), 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
    }

    public static Vec3f transform(Matrix4f mat, Vec3f vec) {
        Vector4f v = new Vector4f(
                vec.getX(),
                vec.getY(),
                vec.getZ(),
                1
        );
        v.transform(mat);
        return new Vec3f(
                v.getX(),
                v.getY(),
                v.getZ()
        );
    }
}
