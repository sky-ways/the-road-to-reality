package com.github.cao.awa.trtr.database.roj.collect;

import org.jetbrains.annotations.*;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.OutputStream;
import java.io.UTFDataFormatException;
import java.nio.ByteBuffer;

/**
 * @author Roj233
 * @since 2022/5/19 1:44
 */
public abstract class DynByteBuf extends OutputStream implements DataInput, DataOutput {
	public final DynByteBuf putChar(char c) {
		return putShort(c);
	}

	public final DynByteBuf putChar(int i, char c) {
		return putShort(i, c);
	}

	public final byte get() {
		return readByte();
	}

	@Deprecated
	public final int limit() {
		return this.wIndex;
	}

	@Deprecated
	public final int position() {
		return this.rIndex;
	}

	@Deprecated
	public final DynByteBuf limit(int i) {
		this.wIndex = i;
		return this;
	}

	@Deprecated
	public final DynByteBuf position(int i) {
		this.rIndex = i;
		return this;
	}

	@Deprecated
	public final DynByteBuf flip() {
		return this;
	}

	public abstract int capacity();

	public abstract int maxCapacity();

	int wIndex;
	public int rIndex;

	public int wIndex() {
		return wIndex;
	}

	public void wIndex(int w) {
		ensureCapacity(w);
		this.wIndex = w;
	}

	public int rIndex() {
		return rIndex;
	}

	public void rIndex(int r) {
		if (rIndex > capacity()) throw new IllegalArgumentException();
		this.rIndex = r;
	}

	public boolean isReadable() {
		return wIndex > rIndex;
	}

	public int readableBytes() {
		return wIndex - rIndex;
	}

	public boolean isWritable() {
		return wIndex < maxCapacity();
	}

	public int writableBytes() {
		return maxCapacity() - wIndex;
	}

	public boolean ensureWritable(int count) {
		int v = count + wIndex;
		if (v > writableBytes()) return false;
		ensureCapacity(v);
		return true;
	}

	public abstract boolean isDirect();

	public boolean immutableCapacity() {
		return false;
	}

	public boolean isBuffer() {
		return true;
	}

	public long memoryAddress() {
		throw new UnsupportedOperationException();
	}

	public abstract boolean hasArray();

	public byte[] array() {
		throw new UnsupportedOperationException();
	}

	public int arrayOffset() {
		throw new UnsupportedOperationException();
	}

	public abstract void clear();

	public abstract void ensureCapacity(int capacity);

	int moveWI(int i) {
		int t = wIndex + i;
		ensureCapacity(t);
		wIndex = t;
		return t - i;
	}

	int moveRI(int i) {
		int ri = rIndex + i;
		if (ri > wIndex) throw new ArrayIndexOutOfBoundsException("mov=" + i + ",rIdx=" + rIndex + ",wIdx=" + wIndex);
		rIndex = ri;
		return ri - i;
	}

	int testWI(int i, int req) {
		if (i + req > wIndex) throw new ArrayIndexOutOfBoundsException("mov=" + req + ",rIdx=" + i + ",wIdx=" + wIndex);
		return i;
	}

	// region DataInput
	@Override
	public final void readFully(@NotNull byte[] b) {
		read(b, 0, b.length);
	}

	@Override
	public final void readFully(@NotNull byte[] b, int off, int len) {
		read(b, off, len);
	}

	// endregion
	// region DataOutput
	@Override
	public final void write(int b) {
		put((byte) b);
	}

	@Override
	public final void write(@NotNull byte[] b) {
		put(b, 0, b.length);
	}

	@Override
	public final void write(@NotNull byte[] b, int off, int len) {
		put(b, off, len);
	}

	@Override
	public final void writeBoolean(boolean v) {
		put((byte) (v ? 1 : 0));
	}

	@Override
	public final void writeByte(int v) {
		put((byte) v);
	}

	@Override
	public final void writeShort(int s) {
		put((byte) (s >>> 8)).put((byte) s);
	}

	@Override
	public final void writeChar(int c) {
		writeShort(c);
	}

	@Override
	public final void writeInt(int i) {
		putInt(i);
	}

	@Override
	public final void writeLong(long l) {
		putLong(l);
	}

	@Override
	public final void writeFloat(float v) {
		putInt(Float.floatToRawIntBits(v));
	}

	@Override
	public final void writeDouble(double v) {
		putLong(Double.doubleToRawLongBits(v));
	}

	@Override
	public final void writeBytes(@NotNull String s) {
		int len = s.length();
		for (int i = 0; i < len; i++) {
			put((byte) s.charAt(i));
		}
	}

	@Override
	public final void writeChars(@NotNull String s) {
		putChars(s);
	}

	@Override
	public void writeUTF(@NotNull String str) throws UTFDataFormatException {
		try {
			ByteList.writeUTF(this, str, 0);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new UTFDataFormatException(e.getMessage());
		}
	}

	@Override
	public final int skipBytes(int i) {
		int skipped = Math.min(wIndex - rIndex, i);
		rIndex += skipped;
		return skipped;
	}
	// endregion

	public abstract DynByteBuf putBool(boolean b);

	public abstract DynByteBuf put(byte e);

	public abstract DynByteBuf put(int i, byte e);

	public DynByteBuf put(byte[] b) {
		return put(b, 0, b.length);
	}

	public abstract DynByteBuf put(byte[] b, int off, int len);

	public final DynByteBuf put(DynByteBuf b) {
		return put(b, b.readableBytes());
	}
	public abstract DynByteBuf put(DynByteBuf b, int cnt);

	public static int zig(int i) {
		return (i & Integer.MIN_VALUE) == 0 ? i << 1 : ((-i << 1) - 1);
	}

	public static long zig(long i) {
		return (i & Long.MIN_VALUE) == 0 ? i << 1 : ((-i << 1) - 1);
	}

	public DynByteBuf putVarInt(int i) {
		putVarLong(this, zig(i));
		return this;
	}

	public DynByteBuf putVarInt(int i, boolean canBeNegative) {
		putVarLong(this, canBeNegative ? zig(i) : i);
		return this;
	}

	public DynByteBuf putVarLong(long i) {
		putVarLong(this, zig(i));
		return this;
	}

	public DynByteBuf putVarLong(long i, boolean canBeNegative) {
		putVarLong(this, canBeNegative ? zig(i) : i);
		return this;
	}

	public static void putVarLong(DynByteBuf list, long i) {
		do {
			if (i < 0x80) {
				list.put((byte) i);
				return;
			} else {
				list.put((byte) ((i & 0x7F) | 0x80));
				i >>>= 7;
			}
		} while (true);
	}

	public abstract DynByteBuf putIntLE(int i);
	public abstract DynByteBuf putIntLE(int wi, int i);

	public abstract DynByteBuf putInt(int i);
	public abstract DynByteBuf putInt(int wi, int i);

	public abstract DynByteBuf putLongLE(long l);
	public abstract DynByteBuf putLongLE(int wi, long l);

	public abstract DynByteBuf putLong(long l);
	public abstract DynByteBuf putLong(int wi, long l);

	public abstract DynByteBuf putFloat(float f);
	public DynByteBuf putFloat(int wi, float f) {
		return putInt(wi, Float.floatToRawIntBits(f));
	}

	public abstract DynByteBuf putDouble(double d);
	public DynByteBuf putDouble(int wi, double d) {
		return putLong(wi, Double.doubleToRawLongBits(d));
	}

	public abstract DynByteBuf putShort(int s);
	public abstract DynByteBuf putShort(int wi, int s);

	public abstract DynByteBuf putShortLE(int s);
	public abstract DynByteBuf putShortLE(int wi, int s);

	public abstract DynByteBuf putMedium(int m);
	public abstract DynByteBuf putMedium(int wi, int m);

	public abstract DynByteBuf putChars(CharSequence s);
	public abstract DynByteBuf putChars(int wi, CharSequence s);

	public DynByteBuf putAscii(CharSequence s) {
		return putAscii(moveWI(s.length()), s);
	}
	public abstract DynByteBuf putAscii(int wi, CharSequence s);

	public DynByteBuf putUTF(CharSequence s) {
		ByteList.writeUTF(this, s, 0);
		return this;
	}
	public DynByteBuf putIntUTF(CharSequence s) {
		int off = wIndex();
		wIndex(off + 4);
		ByteList.writeUTF(this, s, -1);
		putInt(off, wIndex() - off - 4);
		return this;
	}
	public DynByteBuf putVarIntUTF(CharSequence s) {
		ByteList.writeUTF(this, s, 1);
		return this;
	}

	public DynByteBuf putUTFData(CharSequence s) {
		ByteList.writeUTF(this, s, -1);
		return this;
	}

	public abstract DynByteBuf putVIVIC(CharSequence s);
	public abstract DynByteBuf putVICData(CharSequence s);

	public abstract DynByteBuf put(ByteBuffer buf);
	public abstract DynByteBuf put(int wi, ByteBuffer buf);

	public abstract byte[] toByteArray();

	public abstract void preInsert(int off, int len);

	public final byte[] readBytes(int len) {
		byte[] result = new byte[len];
		read(result, 0, len);
		return result;
	}

	public final void read(byte[] b) {
		read(b, 0, b.length);
	}
	public abstract void read(byte[] b, int off, int len);

	public abstract boolean readBoolean(int i);
	public abstract boolean readBoolean();

	public abstract byte get(int i);
	public abstract byte readByte();

	public abstract int getU(int i);
	public abstract int readUnsignedByte();

	public final int readUnsignedShort() {
		return readUnsignedShort(moveRI(2));
	}
	public abstract int readUnsignedShort(int i);

	public final int readUShortLE() {
		return readUShortLE(moveRI(2));
	}
	public abstract int readUShortLE(int i);

	@Override
	public final short readShort() {
		return (short) readUnsignedShort();
	}

	@Override
	public final char readChar() {
		return (char) readUnsignedShort();
	}

	public final int readChar(int i) {
		return readUnsignedShort(i);
	}

	public final int readMedium() {
		return readInt(moveRI(3));
	}
	public abstract int readMedium(int i);

	public static int zag(int i) {
		return (i >> 1) & ~(1 << 31) ^ -(i & 1);
	}
	public static long zag(long i) {
		return (i >> 1) & ~(1L << 63) ^ -(i & 1);
	}

	public final int readVarInt() {
		return readVarInt(true);
	}
	public abstract int readVarInt(boolean mayNeg);

	public final long readVarLong() {
		return readVarLong(true);
	}
	public abstract long readVarLong(boolean mayNeg);

	public final int readInt() {
		return readInt(moveRI(4));
	}
	public abstract int readInt(int i);

	public final int readIntLE() {
		return readIntLE(moveRI(4));
	}
	public abstract int readIntLE(int i);

	public final long readUInt(int i) {
		return readInt(i) & 0xFFFFFFFFL;
	}
	public final long readUInt() {
		return readInt() & 0xFFFFFFFFL;
	}

	public final long readUIntLE() {
		return readIntLE() & 0xFFFFFFFFL;
	}
	public final long readUIntLE(int i) {
		return readIntLE(i) & 0xFFFFFFFFL;
	}

	public final long readLongLE() {
		return readLongLE(moveRI(8));
	}
	public abstract long readLongLE(int i);

	public final long readLong() {
		return readLong(moveRI(8));
	}
	public abstract long readLong(int i);

	public final float readFloat() {
		return readFloat(moveRI(4));
	}
	public final float readFloat(int i) {
		return Float.intBitsToFloat(readInt(i));
	}

	public final double readDouble() {
		return readDouble(moveRI(8));
	}
	public final double readDouble(int i) {
		return Double.longBitsToDouble(readLong(i));
	}

	public final String readAscii(int len) {
		return readAscii(moveRI(len), len);
	}
	public abstract String readAscii(int pos, int len);

	@NotNull
	public final String readUTF() {
		return readUTF(readUnsignedShort());
	}
	public abstract String readUTF(int len);

	public final String readIntUTF() {
		return readIntUTF(Integer.MAX_VALUE);
	}
	public final String readIntUTF(int max) {
		int l = readInt();
		if (l < 0) throw new IllegalArgumentException("Invalid string length " + l);
		if (l == 0) return "";
		if (l > max) throw new IllegalArgumentException("Maximum allowed " + max + " got " + l);
		return readUTF(l);
	}

	public final String readVarIntUTF() {
		return readVarIntUTF(1000000);
	}
	public final String readVarIntUTF(int max) {
		int l = readVarInt(false);
		if (l < 0) throw new IllegalArgumentException("Invalid string length " + l);
		if (l == 0) return "";
		if (l > max) throw new IllegalArgumentException("Maximum allowed " + max + " got " + l);
		return readUTF(l);
	}

	public abstract String readVIVIC();
	public abstract String readVIC(int len);

	public abstract DynByteBuf readZeroTerminate(int max);

	public abstract DynByteBuf slice(int length);
	public abstract DynByteBuf slice(int off, int len);

	public abstract DynByteBuf duplicate();
	public abstract DynByteBuf compact();

	public abstract int nioBufferCount();
}
