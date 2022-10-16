package com.github.cao.awa.trtr.database.roj.collect;

import org.jetbrains.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UTFDataFormatException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.Arrays;

/**
 * @author Roj234
 * @since 2021/5/29 20:45
 */
public class ByteList extends DynByteBuf implements CharSequence {
    public static final int FLAG_PARTIAL = 1;
    public static final byte[] EMPTY_BYTES = new byte[0];
    public byte[] list;

    public ByteList() {
        this.list = EMPTY_BYTES;
    }

    public ByteList(int len) {
        list = new byte[len];
    }

    public ByteList(byte[] array) {
        list = array;
        wIndex = array.length;
    }

    public static ByteList wrap(byte[] b) {
        return new ByteList(b);
    }

    public static ByteList wrap(byte[] b, int off, int len) {
        return new ByteList.Slice(
                b,
                off,
                len
        );
    }

    public static ByteList wrapWrite(byte[] b) {
        return wrapWrite(
                b,
                0,
                b.length
        );
    }

    public static ByteList wrapWrite(byte[] b, int off, int len) {
        ByteList bl = new ByteList.Slice(
                b,
                off,
                len
        );
        bl.wIndex = 0;
        return bl;
    }

    public static ByteList allocate(int cap) {
        return new ByteList(cap);
    }

    public static ByteList allocate(int capacity, int maxCapacity) {
        return new ByteList(capacity) {
            @Override
            public void ensureCapacity(int required) {
                if (required > maxCapacity) {
                    throw new IllegalArgumentException("Exceeds max capacity " + maxCapacity);
                }
                super.ensureCapacity(required);
            }

            @Override
            public boolean isWritable() {
                return wIndex < maxCapacity;
            }

            @Override
            public int writableBytes() {
                return maxCapacity - wIndex;
            }
        };
    }

    public static ByteList encodeUTF(CharSequence s) {
        ByteList bl = new ByteList(s.length() > 1000 ? (s.length() / 3) << 1 : byteCountUTF8(s));
        writeUTF(
                bl,
                s,
                - 1
        );
        return bl;
    }

    public static int byteCountUTF8(char c) {
        if ((c >= 0x0001) && (c <= 0x007F)) {
            return 1;
        } else if (c > 0x07FF) {
            return 3;
        } else {
            return 2;
        }
    }

    public static String readUTF(ByteList list) throws IOException {
        StringBuilder cl = new StringBuilder();
        decodeUTF0(
                list,
                0,
                list.wIndex(),
                cl,
                0
        );
        return cl.toString();
    }

    public static void decodeUTF(int cnt, Appendable out, DynByteBuf in) throws IOException {
        if (cnt <= 0) {
            cnt = in.wIndex();
        } else {
            in.testWI(
                    in.rIndex,
                    cnt
            );
        }

        if (out instanceof StringBuilder) {
            ((StringBuilder) out).ensureCapacity(cnt);
        }

        in.rIndex = decodeUTF0(
                in,
                in.rIndex,
                cnt,
                out,
                0
        );
    }

    public static int decodeUTFPartial(int off, int max, Appendable out, ByteList in) throws IOException {
        if (max <= 0) {
            max = in.wIndex();
        }

        return decodeUTF0(
                in,
                off,
                max,
                out,
                FLAG_PARTIAL
        ) - off;
    }

    public static int FOURCC(CharSequence x) {
        return ((x.charAt(0) & 0xFF) << 24) | ((x.charAt(1) & 0xFF) << 16) | ((x.charAt(2) & 0xFF) << 8) | ((x.charAt(3) & 0xFF));
    }

    @Override
    public int capacity() {
        return list.length;
    }

    @Override
    public int maxCapacity() {
        return 2147000000;
    }

    @Override
    public final boolean isDirect() {
        return false;
    }

    @Override
    public boolean hasArray() {
        return true;
    }

    @Override
    public byte[] array() {
        return list;
    }

    public int arrayOffset() {
        return 0;
    }

    public void clear() {
        wIndex = rIndex = 0;
    }

    public void ensureCapacity(int required) {
        if (required > list.length) {
            byte[] newList = new byte[list.length == 0 ?
                                      Math.max(
                                              required,
                                              256
                                      ) :
                                      ((required * 3) >>> 1) + 1];

            if (wIndex > 0) {
                System.arraycopy(
                        list,
                        0,
                        newList,
                        0,
                        wIndex
                );
            }
            list = newList;
        }
    }

    @Override
    int testWI(int i, int req) {
        if (i + req > wIndex) {
            throw new ArrayIndexOutOfBoundsException("req=" + i + ",wIdx=" + wIndex);
        }
        return i + arrayOffset();
    }

    public final ByteList putBool(boolean b) {
        int i = testWI(
                moveWI(1),
                1
        );
        list[i] = (byte) (b ? 1 : 0);
        return this;
    }

    public final ByteList put(byte e) {
        int i = testWI(
                moveWI(1),
                1
        );
        list[i] = e;
        return this;
    }

    public final ByteList put(int i, byte e) {
        list[testWI(
                i,
                1
        )] = e;
        return this;
    }

    public final ByteList put(byte[] b) {
        return put(
                b,
                0,
                b.length
        );
    }

    // region Relative and Absolute bulk PUT methods from original ByteWriter

    public final ByteList put(byte[] b, int off, int len) {
        if (len < 0 || off < 0 || len > b.length - off) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (len > 0) {
            int off1 = moveWI(len);
            System.arraycopy(
                    b,
                    off,
                    list,
                    off1,
                    len
            );
        }
        return this;
    }

    @Override
    public final ByteList put(DynByteBuf b, int cnt) {
        if (cnt > b.readableBytes()) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(wIndex + cnt);
        b.read(
                list,
                wIndex + arrayOffset(),
                cnt
        );
        b.rIndex -= cnt;
        wIndex += cnt;
        return this;
    }

    public final ByteList putVarInt(int i) {
        putVarLong(
                this,
                zig(i)
        );
        return this;
    }

    public final ByteList putVarInt(int i, boolean canBeNegative) {
        putVarLong(
                this,
                canBeNegative ? zig(i) : i
        );
        return this;
    }

    public final ByteList putVarLong(long i) {
        putVarLong(
                this,
                zig(i)
        );
        return this;
    }

    public final ByteList putVarLong(long i, boolean canBeNegative) {
        putVarLong(
                this,
                canBeNegative ? zig(i) : i
        );
        return this;
    }

    public final ByteList putIntLE(int i) {
        return putIntLE(
                moveWI(4),
                i
        );
    }

    public final ByteList putIntLE(int wi, int i) {
        wi = testWI(
                wi,
                4
        );
        byte[] list = this.list;
        list[wi++] = (byte) i;
        list[wi++] = (byte) (i >>> 8);
        list[wi++] = (byte) (i >>> 16);
        list[wi] = (byte) (i >>> 24);
        return this;
    }

    public final ByteList putInt(int i) {
        return putInt(
                moveWI(4),
                i
        );
    }

    public final ByteList putInt(int wi, int i) {
        wi = testWI(
                wi,
                4
        );
        byte[] list = this.list;
        list[wi++] = (byte) (i >>> 24);
        list[wi++] = (byte) (i >>> 16);
        list[wi++] = (byte) (i >>> 8);
        list[wi] = (byte) i;
        return this;
    }

    public final ByteList putLongLE(long l) {
        return putLongLE(
                moveWI(8),
                l
        );
    }

    public final ByteList putLongLE(int wi, long l) {
        wi = testWI(
                wi,
                8
        );
        byte[] list = this.list;
        list[wi++] = (byte) l;
        list[wi++] = (byte) (l >>> 8);
        list[wi++] = (byte) (l >>> 16);
        list[wi++] = (byte) (l >>> 24);
        list[wi++] = (byte) (l >>> 32);
        list[wi++] = (byte) (l >>> 40);
        list[wi++] = (byte) (l >>> 48);
        list[wi] = (byte) (l >>> 56);
        return this;
    }

    public final ByteList putLong(long l) {
        return putLong(
                moveWI(8),
                l
        );
    }

    public final ByteList putLong(int wi, long l) {
        wi = testWI(
                wi,
                8
        );
        byte[] list = this.list;
        list[wi++] = (byte) (l >>> 56);
        list[wi++] = (byte) (l >>> 48);
        list[wi++] = (byte) (l >>> 40);
        list[wi++] = (byte) (l >>> 32);
        list[wi++] = (byte) (l >>> 24);
        list[wi++] = (byte) (l >>> 16);
        list[wi++] = (byte) (l >>> 8);
        list[wi] = (byte) l;
        return this;
    }

    public final ByteList putFloat(float f) {
        return putFloat(
                moveWI(4),
                f
        );
    }

    public final ByteList putFloat(int wi, float f) {
        return putInt(
                wi,
                Float.floatToRawIntBits(f)
        );
    }

    public final ByteList putDouble(double d) {
        return putDouble(
                moveWI(8),
                d
        );
    }

    public final ByteList putDouble(int wi, double d) {
        return putLong(
                wi,
                Double.doubleToRawLongBits(d)
        );
    }

    public final ByteList putShort(int s) {
        return putShort(
                moveWI(2),
                s
        );
    }

    public final ByteList putShort(int wi, int s) {
        wi = testWI(
                wi,
                2
        );
        byte[] list = this.list;
        list[wi++] = (byte) (s >>> 8);
        list[wi] = (byte) s;
        return this;
    }

    public final ByteList putShortLE(int s) {
        return putShortLE(
                moveWI(2),
                s
        );
    }

    public final ByteList putShortLE(int wi, int s) {
        wi = testWI(
                wi,
                2
        );
        byte[] list = this.list;
        list[wi++] = (byte) s;
        list[wi] = (byte) (s >>> 8);
        return this;
    }

    public final ByteList putMedium(int m) {
        return putMedium(
                moveWI(3),
                m
        );
    }

    public final ByteList putMedium(int wi, int m) {
        wi = testWI(
                wi,
                3
        );
        byte[] list = this.list;
        list[wi++] = (byte) (m >>> 16);
        list[wi++] = (byte) (m >>> 8);
        list[wi] = (byte) m;
        return this;
    }

    public final ByteList putChars(CharSequence s) {
        return putChars(
                moveWI(s.length() << 1),
                s
        );
    }

    public final ByteList putChars(int wi, CharSequence s) {
        wi = testWI(
                wi,
                s.length() << 1
        );
        byte[] list = this.list;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            list[wi++] = (byte) (c >>> 8);
            list[wi++] = (byte) c;
        }
        return this;
    }

    public final ByteList putAscii(CharSequence s) {
        return putAscii(
                moveWI(s.length()),
                s
        );
    }

    @SuppressWarnings("deprecation")
    public final ByteList putAscii(int wi, CharSequence s) {
        wi = testWI(
                wi,
                s.length()
        );
        if (s.getClass() == String.class) {
            s.toString()
             .getBytes(0,
                       s.length(),
                       list,
                       wi
             );
        } else {
            byte[] list = this.list;
            for (int i = 0; i < s.length(); i++) {
                list[wi++] = (byte) s.charAt(i);
            }
        }
        return this;
    }

    public final ByteList putUTF(CharSequence s) {
        ByteList.writeUTF(
                this,
                s,
                0
        );
        return this;
    }

    public final ByteList putIntUTF(CharSequence s) {
        int off = wIndex();
        wIndex(off + 4);
        ByteList.writeUTF(
                this,
                s,
                - 1
        );
        putInt(
                off,
                wIndex() - off - 4
        );
        return this;
    }

    public final ByteList putVarIntUTF(CharSequence s) {
        ByteList.writeUTF(
                this,
                s,
                1
        );
        return this;
    }

    public final ByteList putUTFData(CharSequence s) {
        ByteList.writeUTF(
                this,
                s,
                - 1
        );
        return this;
    }

    public final ByteList putVIVIC(CharSequence s) {
        ensureCapacity(wIndex + s.length());
        int len = s.length();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 0 || c >= 0x80) {
                if (c > 0x8080) {
                    len += 2;
                } else {
                    len++;
                }
            }
        }
        DynByteBuf.putVarLong(
                this,
                len
        );
        return putVICData(s);
    }

    public final ByteList putVICData(CharSequence s) {
        ensureCapacity(wIndex + s.length());
        byte[] list = this.list;
        int wi = wIndex;
        for (int i = 0; i < s.length(); i++) {
            if (wi + 2 >= list.length) {
                list = this.list = Arrays.copyOf(
                        list,
                        list.length + s.length()
                );
            }

            char c = s.charAt(i);
            if (c >= 0x8080) {
                list[wi++] = 0;
                list[wi++] = (byte) (c >>> 8);
                list[wi++] = (byte) c;
            } else if (c == 0 || c >= 0x80) {
                c -= 0x80;
                list[wi++] = (byte) ((c >>> 8) | 0x80);
                list[wi++] = (byte) c;
            } else {
                list[wi++] = (byte) c;
            }
        }
        wIndex = wi;
        return this;
    }

    public final ByteList put(ByteBuffer buf) {
        return put(
                moveWI(buf.remaining()),
                buf
        );
    }

    public final ByteList put(int wi, ByteBuffer buf) {
        int rem = buf.remaining();
        buf.get(
                list,
                testWI(
                        wi,
                        rem
                ),
                rem
        );
        return this;
    }

    public byte[] toByteArray() {
        byte[] b = new byte[wIndex];
        System.arraycopy(
                list,
                arrayOffset(),
                b,
                0,
                b.length
        );
        return b;
    }

    @Override
    public void preInsert(int off, int len) {
        if (immutableCapacity() | ! isBuffer()) {
            throw new ReadOnlyBufferException();
        }
        byte[] tmp = list;
        if (tmp.length < wIndex + len) {
            tmp = new byte[wIndex + len];
        }
        if (wIndex != off) {
            System.arraycopy(
                    list,
                    off,
                    tmp,
                    len,
                    wIndex - off
            );
        }
        list = tmp;
    }

    public final void read(byte[] b, int off, int len) {
        if (len < 0 || off < 0 || len > b.length - off) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (len > 0) {
            System.arraycopy(
                    list,
                    moveRI(len) + arrayOffset(),
                    b,
                    off,
                    len
            );
        }
    }

    public final boolean readBoolean(int i) {
        return list[testWI(
                i,
                1
        )] == 1;
    }

    @Override
    public final boolean readBoolean() {
        return list[moveRI(1) + arrayOffset()] == 1;
    }

    public final byte get(int i) {
        return list[testWI(
                i,
                1
        )];
    }

    @Override
    public final byte readByte() {
        return list[moveRI(1) + arrayOffset()];
    }

    public final int getU(int i) {
        return list[testWI(
                i,
                1
        )] & 0xFF;
    }

    @Override
    public final int readUnsignedByte() {
        return list[moveRI(1) + arrayOffset()] & 0xFF;
    }

    public final int readUnsignedShort(int i) {
        i = testWI(
                i,
                2
        );
        byte[] l = this.list;
        return (l[i++] & 0xFF) << 8 | (l[i] & 0xFF);
    }

    // endregion
    // region Relative bulk GET methods from original ByteReader

    public final int readUShortLE(int i) {
        i = testWI(
                i,
                2
        );
        byte[] l = this.list;
        return (l[i++] & 0xFF) | (l[i] & 0xFF) << 8;
    }

    public final int readMedium(int i) {
        i = testWI(
                i,
                3
        );
        byte[] l = this.list;
        return (l[i++] & 0xFF) << 16 | (l[i++] & 0xFF) << 8 | (l[i] & 0xFF);
    }

    public final int readVarInt(boolean mayNeg) {
        int value = 0;
        int i = 0;

        byte[] list = this.list;
        int off = arrayOffset() + rIndex;
        while (i <= 28) {
            if (off >= wIndex) {
                throw new ArrayIndexOutOfBoundsException();
            }

            int chunk = list[off++];
            rIndex++;
            value |= (chunk & 0x7F) << i;
            i += 7;
            if ((chunk & 0x80) == 0) {
                if (mayNeg) {
                    return zag(value);
                }
                if (value < 0) {
                    break;
                }
                return value;
            }
        }

        throw new RuntimeException("VarInt format error near " + rIndex);
    }

    public final long readVarLong(boolean mayNeg) {
        long value = 0;
        int i = 0;

        while (i <= 63) {
            int chunk = readByte();
            value |= (chunk & 0x7F) << i;
            i += 7;
            if ((chunk & 0x80) == 0) {
                if (mayNeg) {
                    return zag(value);
                }
                if (value < 0) {
                    break;
                }
                return value;
            }
        }

        throw new RuntimeException("VarLong format error near " + rIndex);
    }

    public final int readInt(int i) {
        i = testWI(
                i,
                4
        );
        byte[] l = this.list;
        return (l[i++] & 0xFF) << 24 | (l[i++] & 0xFF) << 16 | (l[i++] & 0xFF) << 8 | (l[i] & 0xFF);
    }

    public final int readIntLE(int i) {
        i = testWI(
                i,
                4
        );
        byte[] l = this.list;
        return (l[i++] & 0xFF) | (l[i++] & 0xFF) << 8 | (l[i++] & 0xFF) << 16 | (l[i] & 0xFF) << 24;
    }

    public final long readLongLE(int i) {
        i = testWI(
                i,
                8
        );
        byte[] l = this.list;
        return (l[i++] & 0xFFL) | (l[i++] & 0xFFL) << 8 | (l[i++] & 0xFFL) << 16 | (l[i++] & 0xFFL) << 24 | (l[i++] & 0xFFL) << 32 | (l[i++] & 0xFFL) << 40 | (l[i++] & 0xFFL) << 48 | (l[i] & 0xFFL) << 56;
    }

    public final long readLong(int i) {
        i = testWI(
                i,
                8
        );
        byte[] l = this.list;
        return (l[i++] & 0xFFL) << 56 | (l[i++] & 0xFFL) << 48 | (l[i++] & 0xFFL) << 40 | (l[i++] & 0xFFL) << 32 | (l[i++] & 0xFFL) << 24 | (l[i++] & 0xFFL) << 16 | (l[i++] & 0xFFL) << 8 | l[i] & 0xFFL;
    }

    @SuppressWarnings("deprecation")
    public final String readAscii(int i, int len) {
        return new String(
                list,
                0,
                testWI(
                        i,
                        len
                ),
                len
        );
    }

    public final String readUTF(int len) {
        testWI(rIndex,
               len);

        StringBuilder rct = new StringBuilder();
        rct.ensureCapacity(len);
        try {
            decodeUTF0(
                    this,
                    rIndex,
                    len + rIndex,
                    rct,
                    0
            );
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        rIndex += len;
        return rct.toString();
    }

    public final String readVIVIC() {
        return readVIC(readVarInt(false));
    }

    public final String readVIC(int len) {
        int off = moveRI(len) + arrayOffset();

        StringBuilder tmp = new StringBuilder();
        tmp.ensureCapacity(len / 2);

        byte[] list = this.list;

        while (len > 0) {
            byte b = list[off++];

            if (b == 0) {
                tmp.append((char) ((list[off++] & 0xFF) << 8 | (list[off++] & 0xFF)));
                len -= 3;
            } else if ((b & 0x80) != 0) {
                tmp.append((char) ((((b & 0x7F) << 8) | (list[off++] & 0xFF)) + 0x80));
                len -= 2;
            } else {
                tmp.append((char) b);
                len -= 1;
            }
        }

        if (len < 0) {
            throw new IllegalStateException("Partial character at end");
        }

        return tmp.toString();
    }

    public final ByteList readZeroTerminate(int max) {
        int i = rIndex + arrayOffset();
        byte[] l = list;
        while (max-- > 0) {
            if (l[i++] == 0) {
                return slice(i - rIndex - arrayOffset());
            }
        }
        return null;
    }

    public ByteList slice(int length) {
        if (length == 0) {
            return new ByteList();
        }
        ByteList list = slice(
                rIndex,
                length
        );
        rIndex += length;
        return list;
    }

    public final ByteList slice(int off, int len) {
        return new Slice(
                list,
                off + arrayOffset(),
                len
        );
    }

    @Override
    public DynByteBuf duplicate() {
        return new Slice(
                list,
                0,
                wIndex
        );
    }

    @Override
    public DynByteBuf compact() {
        if (rIndex > 0) {
            System.arraycopy(
                    list,
                    rIndex,
                    list,
                    0,
                    wIndex - rIndex
            );
            wIndex -= rIndex;
            rIndex = 0;
        }
        return this;
    }

    @Override
    public int nioBufferCount() {
        return 1;
    }

    @SuppressWarnings("fallthrough")
    static int decodeUTF0(DynByteBuf in, int i, int max, Appendable out, int flag) throws IOException {
        i += in.arrayOffset();
        max += in.arrayOffset();
        byte[] inn = in.array();

        int c;
        while (i < max) {
            c = inn[i] & 0xFF;
            if (c > 127) {
                break;
            }
            i++;
            out.append((char) c);
        }

        int c2, c3;
        cyl:
        while (i < max) {
            c = inn[i] & 0xFF;
            switch (c >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    /* 0xxxxxxx*/
                    i++;
                    out.append((char) c);
                    break;
                case 12:
                case 13:
                    /* 110x xxxx   10xx xxxx*/
                    i += 2;
                    if (i > max) {
                        if ((flag & 1) != 0) {
                            i -= 2;
                            break cyl;
                        } else {
                            throw new UTFDataFormatException("malformed input: partial character at end");
                        }
                    }

                    c2 = inn[i - 1];
                    if ((c2 & 0xC0) != 0x80) {
                        i -= in.arrayOffset();
                        throw new UTFDataFormatException("malformed input around byte " + (i - 1));
                    }
                    out.append((char) (((c & 0x1F) << 6) | (c2 & 0x3F)));
                    break;
                case 14:
                    /* 1110 xxxx  10xx xxxx  10xx xxxx */
                    i += 3;
                    if (i > max) {
                        if ((flag & 1) != 0) {
                            i -= 3;
                            break cyl;
                        } else {
                            throw new UTFDataFormatException("malformed input: partial character at end");
                        }
                    }

                    c2 = inn[i - 2];
                    c3 = inn[i - 1];
                    if (((c2 & 0xC0) != 0x80) || ((c3 & 0xC0) != 0x80)) {
                        i -= in.arrayOffset();
                        throw new UTFDataFormatException("malformed input around byte " + i);
                    }

                    out.append((char) (((c & 0x0F) << 12) | ((c2 & 0x3F) << 6) | c3 & 0x3F));
                    break;
                case 15:
                default:
                    i -= in.arrayOffset();
                    /* 10xx xxxx,  1111 xxxx */
                    throw new UTFDataFormatException("malformed input around byte " + i);
            }
        }

        return i - in.arrayOffset();
    }

    public static void writeUTF(DynByteBuf list, CharSequence str, int type) {
        int len = str.length();

        if (type == 0 || type == 1) {
            if (type == 0 && len > 65535) {
                throw new ArrayIndexOutOfBoundsException("String too long: > 65535 bytes");
            }

            if (type == 1) {
                int utfLen = byteCountUTF8(str);
                list.ensureCapacity(utfLen + 4);
                DynByteBuf.putVarLong(
                        list,
                        utfLen
                );
            } else {
                list.putShort(0);
            }
        }
        int pos = list.wIndex();

        int c;
        for (int j = 0; j < len; j++) {
            c = str.charAt(j);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                list.put((byte) c);
            } else if (c > 0x07FF) {
                list.put((byte) (0xE0 | ((c >> 12) & 0x0F)));
                list.put((byte) (0x80 | ((c >> 6) & 0x3F)));
                list.put((byte) (0x80 | (c & 0x3F)));
            } else {
                list.put((byte) (0xC0 | ((c >> 6) & 0x1F)));
                list.put((byte) (0x80 | (c & 0x3F)));
            }
        }

        len = list.wIndex() - pos;
        if (type == 0) {
            if (len > 65535) {
                list.wIndex(pos);
                throw new ArrayIndexOutOfBoundsException("Encoded string too long: " + len + " bytes");
            }
            list.putShort(
                    pos - 2,
                    len
            );
        }
    }

    public static int byteCountUTF8(CharSequence str) {
        int len = str.length();
        int utfLen = 0;

        /* use charAt instead of copying String to char array */
        for (int i = 0; i < len; i++) {
            int c = str.charAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                utfLen++;
            } else if (c > 0x07FF) {
                utfLen += 3;
            } else {
                utfLen += 2;
            }
        }

        return utfLen;
    }

    /**
     * 注: 读取指针与rIndex独立，历史原因
     */
    public final InputStream asInputStream() {
        return new AsInputStream();
    }

    // 前向兼容
    public final OutputStream asOutputStream() {
        return this;
    }

    public final ByteList readStreamFully(InputStream in) throws IOException {
        return readStreamFully(
                in,
                true
        );
    }

    public final ByteList readStreamFully(InputStream in, boolean close) throws IOException {
        int i = in.available();
        // todo remove this line?
        if (i <= 0) {
            i = 127;
        }
        ensureCapacity(wIndex + i + 1);

        int real;
        do {
            real = in.read(
                    this.list,
                    arrayOffset() + wIndex,
                    list.length - wIndex
            );
            if (real <= 0) {
                break;
            }
            wIndex += real;
            ensureCapacity(wIndex + 1);
        } while (true);
        if (close) {
            in.close();
        }
        return this;
    }

    public final int readStream(InputStream in, int max) throws IOException {
        ensureCapacity(wIndex + max);
        int real = in.read(
                list,
                arrayOffset() + wIndex,
                max
        );
        if (real > 0) {
            wIndex += real;
        }
        return real;
    }

    public final void writeToStream(OutputStream os) throws IOException {
        if (wIndex > 0) {
            os.write(
                    list,
                    arrayOffset(),
                    wIndex
            );
        }
    }

    public ByteList setArray(byte[] array) {
        if (array == null) {
            array = EMPTY_BYTES;
        }

        list = array;
        clear();
        wIndex = array.length;
        return this;
    }

    public int lastIndexOf(byte[] bytes) {
        return lastIndexOf(
                list,
                arrayOffset(),
                wIndex,
                bytes,
                0,
                bytes.length,
                wIndex
        );
    }

    static int lastIndexOf(byte[] self, int slfOff, int slfLen, byte[] find, int finOff, int finLen, int idx) {
        /*
         * Check arguments; return immediately where possible. For
         * consistency, don't check for null str.
         */
        int rightIndex = slfLen - finLen;
        if (idx < 0) {
            return - 1;
        }
        if (idx > rightIndex) {
            idx = rightIndex;
        }
        /* Empty string always matches. */
        if (finLen == 0) {
            return idx;
        }

        int strLastIndex = finOff + finLen - 1;
        byte strLastChar = find[strLastIndex];
        int min = slfOff + finLen - 1;
        int i = min + idx;

        last:
        while (true) {
            while (i >= min && self[i] != strLastChar) {
                i--;
            }
            if (i < min) {
                return - 1;
            }
            int j = i - 1;
            int start = j - (finLen - 1);
            int k = strLastIndex - 1;

            while (j > start) {
                if (self[j--] != find[k--]) {
                    i--;
                    continue last;
                }
            }
            return start - slfOff + 1;
        }
    }

    public final ByteList putVLUI(int i) {
        long v = i & 0xFFFFFFFFL;
        byte[] l;
        int wi = wIndex;

        if (v >= 0x200000) {
            if (v >= 0x10000000) { // 5 bytes, 29-32 bits
                ensureCapacity(wi + 5);
                wIndex = wi + 5;
                l = list;
                l[wi++] = 0b00001000;
                l[wi++] = ((byte) (v >>> 24));
            } else { // 4 bytes, 22-28 bits
                ensureCapacity(wi + 4);
                wIndex = wi + 4;
                l = list;
                l[wi++] = ((byte) (0b00010000 | ((v >> 24) & 0xF)));
            }
            l[wi++] = ((byte) (v >>> 16));
            l[wi++] = ((byte) (v >>> 8));
            l[wi] = ((byte) v);
        } else {
            if (v >= 0x4000) { // 3 bytes, 15-21 bits
                ensureCapacity(wi + 3);
                wIndex = wi + 3;
                l = list;
                l[wi++] = ((byte) (0b00100000 | ((v >>> 16) & 0x1F)));
                l[wi++] = ((byte) (v >>> 8));
                l[wi] = ((byte) v);
            } else if (v >= 0x80) { // 2 bytes, 8-14bits
                ensureCapacity(wi + 2);
                wIndex = wi + 2;
                l = list;
                l[wi++] = ((byte) (0b01000000 | ((v >>> 8) & 0x3F)));
                l[wi] = ((byte) v);
            } else { // 1byte, 0-7 bits
                return put((byte) (0b10000000 | v));
            }
        }
        return this;
    }

    // Variable length unsigned int
    public final int readVLUI() {
        int v = readUnsignedByte();

        int len = 7;
        while (len >= 3) {
            if ((v & (1 << len)) != 0) {
                // strip out marker
                v ^= 1 << len;
                break;
            }
            len--;
        }
        if (len == 2) {
            throw new RuntimeException("VLUI width");
        }
        len = 8 - len;
        while (-- len > 0) {
            v = (v << 8) | readUnsignedByte();
        }

        return v;
    }

    // endregion
    // region Ascii Sequence

    @Override
    @SuppressWarnings("deprecation")
    public final String readLine() {
        int i = rIndex + arrayOffset();
        byte[] l = list;
        while (true) {
            if (i >= wIndex) {
                throw new ArrayIndexOutOfBoundsException();
            }
            byte b = l[i++];
            if (b == '\r' || b == '\n') {
                if (b == '\r' && i < wIndex && l[i] == '\n') {
                    i++;
                }
                break;
            }
        }
        String s = new String(
                l,
                0,
                rIndex,
                i - rIndex
        );
        rIndex = i - arrayOffset();
        return s;
    }

    @Override
    public int length() {
        return wIndex;
    }

    @Override
    public char charAt(int i) {
        return (char) list[testWI(
                i,
                1
        ) + arrayOffset()];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new Slice(
                list,
                start,
                end - start
        );
    }
    // endregion

    @Override
    public String toString() {
        return "";
    }

    public static final class Streamed extends ByteList {
        OutputStream out;
        int fakeWriteIndex;

        public Streamed() {
            super(12);
        }

        public Streamed(OutputStream out) {
            super(1024);
            this.out = out;
        }

        public void setOut(OutputStream out) {
            this.out = out;
        }

        @Override
        public int wIndex() {
            flush();
            return fakeWriteIndex;
        }

        @Override
        public void wIndex(int w) {
            flush();
            this.fakeWriteIndex = w;
        }

        @Override
        public boolean isBuffer() {
            return false;
        }

        int moveWI(int i) {
            ensureCapacity(wIndex + i);
            int j = wIndex;
            wIndex = j + i;
            return j;
        }

        @Override
        public void ensureCapacity(int required) {
            if (required >= list.length) {
                required -= wIndex;
                flush();
            }
            if (required >= list.length) {
                list = new byte[required];
            }
        }

        @Override
        public boolean hasArray() {
            return false;
        }

        @Override
        public byte[] array() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ByteList setArray(byte[] array) {
            throw new UnsupportedOperationException();
        }

        public void flush() {
            if (wIndex > 0) {
                if (out != null) {
                    try {
                        out.write(
                                list,
                                0,
                                wIndex
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                fakeWriteIndex += wIndex;
                wIndex = 0;
            }
        }

        @Override
        public void close() {
            flush();
        }

        @Override
        int moveRI(int i) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * 只读
     */
    public static final class Slice extends ByteList {
        private int off, len;

        public Slice() {
        }

        public Slice(byte[] array, int start, int len) {
            super(array);
            this.wIndex = len;
            this.len = len;
            this.off = start;
        }

        public ByteList set(byte[] array, int start, int len) {
            wIndex = rIndex = 0;

            list = array;
            off = start;
            this.len = len;
            return this;
        }

        @Override
        public int capacity() {
            return len;
        }

        @Override
        public int maxCapacity() {
            return len;
        }

        @Override
        public void ensureCapacity(int required) {
            if (required > len) {
                throw new UnsupportedOperationException();
            }
        }

        @Override
        public int arrayOffset() {
            return off;
        }

        @Override
        public ByteList setArray(byte[] array) {
            throw new UnsupportedOperationException();
        }

        public ByteList copy(DynByteBuf src) {
            list = src.array();
            rIndex = src.rIndex;
            wIndex = src.wIndex;
            off = src.arrayOffset();
            len = src.wIndex();
            return this;
        }

        @Override
        public void wIndex(int w) {
            if (w > len) {
                throw new UnsupportedOperationException();
            }
            wIndex = w;
        }

        @Override
        public boolean immutableCapacity() {
            return true;
        }
    }

    public class AsInputStream extends InputStream {
        protected int pos;

        public AsInputStream() {
            pos = arrayOffset();
        }

        @Override
        public int read() {
            if (pos == wIndex) {
                return - 1;
            }
            return list[pos++] & 0xFF;
        }

        @Override
        public int read(@NotNull byte[] arr, int off, int len) {
            if (len == 0) {
                return 0;
            }

            int r = Math.min(
                    wIndex - pos,
                    len
            );
            if (r <= 0) {
                return - 1;
            }

            System.arraycopy(
                    list,
                    pos,
                    arr,
                    off,
                    r
            );
            pos += r;
            return r;
        }

        @Override
        public long skip(long len) {
            long skipped = Math.min(
                    available(),
                    len
            );
            pos += skipped;
            return skipped;
        }

        @Override
        public int available() {
            return Math.max(
                    0,
                    wIndex - pos
            );
        }
    }
}