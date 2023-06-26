package bot.inker.inkrender.util;

import com.github.cao.awa.trtr.math.Mathematics;
import net.minecraft.util.math.MathHelper;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.nio.FloatBuffer;

public final class Matrix4Util {
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
        readRowMajor(
                mat,
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

    public static Vector3f transform(Matrix4f mat, Vector3f vec) {
        Vector4f v = new Vector4f(
                vec.x(),
                vec.y(),
                vec.z(),
                1
        );
        transform(v,
                  mat
        );
        return new Vector3f(v.x(),
                            v.y(),
                            v.z()
        );
    }

    public static void transform(Vector4f vec, Matrix4f matrix) {
        float x = vec.x;
        float y = vec.y;
        float z = vec.z;
        float w = vec.w;
        vec.x = matrix.m00() * x + matrix.m01() * y + matrix.m02() * z + matrix.m03() * w;
        vec.y = matrix.m10() * x + matrix.m11() * y + matrix.m12() * z + matrix.m13() * w;
        vec.z = matrix.m20() * x + matrix.m21() * y + matrix.m22() * z + matrix.m23() * w;
        vec.w = matrix.m30() * x + matrix.m31() * y + matrix.m32() * z + matrix.m33() * w;
    }

    public static void readRowMajor(Matrix4f matrix, FloatBuffer buf) {
        matrix.m00(buf.get(pack(0,
                                0
        )));
        matrix.m01(buf.get(pack(1,
                                0
        )));
        matrix.m02(buf.get(pack(2,
                                0
        )));
        matrix.m03(buf.get(pack(3,
                                0
        )));
        matrix.m10(buf.get(pack(0,
                                1
        )));
        matrix.m11(buf.get(pack(1,
                                1
        )));
        matrix.m12(buf.get(pack(2,
                                1
        )));
        matrix.m13(buf.get(pack(3,
                                1
        )));
        matrix.m20(buf.get(pack(0,
                                2
        )));
        matrix.m21(buf.get(pack(1,
                                2
        )));
        matrix.m22(buf.get(pack(2,
                                2
        )));
        matrix.m23(buf.get(pack(3,
                                2
        )));
        matrix.m30(buf.get(pack(0,
                                3
        )));
        matrix.m31(buf.get(pack(1,
                                3
        )));
        matrix.m32(buf.get(pack(2,
                                3
        )));
        matrix.m33(buf.get(pack(3,
                                3
        )));
    }

    private static int pack(int x, int y) {
        return y * 4 + x;
    }

    public static void multiply(Matrix4f matrix1, Matrix4f matrix2) {
        float f = matrix1.m00() * matrix2.m00() + matrix1.m01() * matrix2.m10() + matrix1.m02() * matrix2.m20() + matrix1.m03() * matrix2.m30();
        float g = matrix1.m00() * matrix2.m01() + matrix1.m01() * matrix2.m11() + matrix1.m02() * matrix2.m21() + matrix1.m03() * matrix2.m31();
        float h = matrix1.m00() * matrix2.m02() + matrix1.m01() * matrix2.m12() + matrix1.m02() * matrix2.m22() + matrix1.m03() * matrix2.m32();
        float i = matrix1.m00() * matrix2.m03() + matrix1.m01() * matrix2.m13() + matrix1.m02() * matrix2.m23() + matrix1.m03() * matrix2.m33();
        float j = matrix1.m10() * matrix2.m00() + matrix1.m11() * matrix2.m10() + matrix1.m12() * matrix2.m20() + matrix1.m13() * matrix2.m30();
        float k = matrix1.m10() * matrix2.m01() + matrix1.m11() * matrix2.m11() + matrix1.m12() * matrix2.m21() + matrix1.m13() * matrix2.m31();
        float l = matrix1.m10() * matrix2.m02() + matrix1.m11() * matrix2.m12() + matrix1.m12() * matrix2.m22() + matrix1.m13() * matrix2.m32();
        float m = matrix1.m10() * matrix2.m03() + matrix1.m11() * matrix2.m13() + matrix1.m12() * matrix2.m23() + matrix1.m13() * matrix2.m33();
        float n = matrix1.m20() * matrix2.m00() + matrix1.m21() * matrix2.m10() + matrix1.m22() * matrix2.m20() + matrix1.m23() * matrix2.m30();
        float o = matrix1.m20() * matrix2.m01() + matrix1.m21() * matrix2.m11() + matrix1.m22() * matrix2.m21() + matrix1.m23() * matrix2.m31();
        float p = matrix1.m20() * matrix2.m02() + matrix1.m21() * matrix2.m12() + matrix1.m22() * matrix2.m22() + matrix1.m23() * matrix2.m32();
        float q = matrix1.m20() * matrix2.m03() + matrix1.m21() * matrix2.m13() + matrix1.m22() * matrix2.m23() + matrix1.m23() * matrix2.m33();
        float r = matrix1.m30() * matrix2.m00() + matrix1.m31() * matrix2.m10() + matrix1.m32() * matrix2.m20() + matrix1.m33() * matrix2.m30();
        float s = matrix1.m30() * matrix2.m01() + matrix1.m31() * matrix2.m11() + matrix1.m32() * matrix2.m21() + matrix1.m33() * matrix2.m31();
        float t = matrix1.m30() * matrix2.m02() + matrix1.m31() * matrix2.m12() + matrix1.m32() * matrix2.m22() + matrix1.m33() * matrix2.m32();
        float u = matrix1.m30() * matrix2.m03() + matrix1.m31() * matrix2.m13() + matrix1.m32() * matrix2.m23() + matrix1.m33() * matrix2.m33();
        matrix1.m00(f);
        matrix1.m01(g);
        matrix1.m02(h);
        matrix1.m03(i);
        matrix1.m10(j);
        matrix1.m11(k);
        matrix1.m12(l);
        matrix1.m13(m);
        matrix1.m20(n);
        matrix1.m21(o);
        matrix1.m22(p);
        matrix1.m23(q);
        matrix1.m30(r);
        matrix1.m31(s);
        matrix1.m32(t);
        matrix1.m33(u);
    }
}
