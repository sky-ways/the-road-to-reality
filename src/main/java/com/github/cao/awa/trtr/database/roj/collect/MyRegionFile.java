package com.github.cao.awa.trtr.database.roj.collect;

import com.github.cao.awa.trtr.database.roj.*;
import it.unimi.dsi.fastutil.objects.*;
import org.apache.logging.log4j.*;
import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/**
 * @author Roj233
 * @since 2022/5/8 1:36
 */
public class MyRegionFile {
	private static final Logger LOGGER = LogManager.getLogger();
	public static final int GZIP=1,DEFLATE=2,PLAIN=3;

	public final File file;
	protected final RandomAccessFile raf;
	protected final int[] offsets, timestamps;
	protected final MyBitSet free;
	protected int sectorCount;
	protected List<ByteList> buffers = new ObjectArrayList<>();
	protected final int bitmapSize;

	public MyRegionFile(File file) throws IOException {
		this(file, 4096, 1024);
	}

	public MyRegionFile(File file, int chunkSize, int fileCap) throws IOException {
		this.file = file;
		if (chunkSize <= 0 || fileCap <= 1) throw new IllegalStateException("chunkSize="+chunkSize+",fileSize="+fileCap);
		this.bitmapSize = MathUtils.getMin2PowerOf(chunkSize);

		raf = new RandomAccessFile(file, "rw");
		offsets = new int[fileCap];
		timestamps = new int[fileCap];

		int header = fileCap<<3;

		if (raf.length() < header) {
			raf.setLength(0);
			raf.setLength(header);
		}

		int bitmapMask = chunkSize - 1;
		if ((raf.length() & bitmapMask) != 0L) {
			raf.setLength((raf.length() & ~bitmapMask) + chunkSize);
		}

		int sectors = sectorCount = (int) (raf.length() / chunkSize);
		free = new MyBitSet(sectors);
		free.fill(sectors);
		free.removeRange(0, header/chunkSize + ((header&chunkSize) == 0 ? 0 : 1));

		int headerBytes = header >>> 1;
		ByteList tmp = allocateBufferEarly(headerBytes);

		raf.seek(0L);
		raf.readFully(tmp.list, tmp.arrayOffset(), headerBytes);
		tmp.clear();
		tmp.wIndex(headerBytes);

		for (int i = 0; i < fileCap; ++i) {
			int n = offsets[i] = tmp.readInt();
			if (n == 0) continue;

			int off = n >>> 8;
			int len = n & 255;
			if (len == 255 && off <= sectors) {
				raf.seek(off * chunkSize);
				len = (raf.readInt() + 4) / chunkSize + 1;
			}

			if (off + len <= sectors) {
				free.removeRange(off, off + len);
			} else if (len > 0) {
				log("无效的分块: {} #{} 块范围:[{}+{}] 超出文件大小.", file, i, off, len);
			}
		}

		raf.seek(headerBytes);
		raf.readFully(tmp.list, tmp.arrayOffset(), headerBytes);
		tmp.clear();
		tmp.wIndex(headerBytes);

		for (int i = 0; i < fileCap; ++i) {
			timestamps[i] = tmp.readInt();
		}

		releaseBufferEarly(tmp);
	}

	public DataInputStream getDataInput(int id) throws IOException {
		InputStream in = getData(id, null);
		return in == null ? null : new DataInputStream(in);
	}

	// holder[0]是压缩的数据长度 [1]是Unix时间戳/1000
	public InputStream getData(int id, int[] holder) throws IOException {
		int i;
		synchronized (offsets) {
			i = offsets[id];
			if (i == 0) return null;
		}

		ChunkInput cin = new ChunkInput(file);
		RandomAccessFile raf = cin.raf;

		int off = i >>> 8;
		int len = i & 255;
		if (len == 255) {
			raf.seek(off * bitmapSize);
			len = (raf.readInt() + 4) / bitmapSize + 1;
		}

		if (off + len > sectorCount) return null;

		raf.seek(off * bitmapSize);
		int byteLength = raf.readInt();
		if (byteLength > bitmapSize * len) {
			log("无效的分块: {} #{} 块范围:[{}+{}] 数据超出分块尾: {} > {}", file, id, off, len, byteLength, len * bitmapSize);
			return null;
		} else if (byteLength <= 0) {
			log("无效的分块: {} #{} 块范围:[{}+{}] 长度小于0: {}", file, id, off, len, byteLength);
			return null;
		}

		if (holder != null) {
			holder[0] = byteLength;
			holder[1] = timestamps[id];
		}

		byte type = raf.readByte();
		cin.length = len*bitmapSize;

		return getDataByType(type, cin);
	}

	protected InputStream getDataByType(int type, InputStream in) throws IOException {
		switch (type) {
			case GZIP: return new GZIPInputStream(in);
			case DEFLATE: return new InflaterInputStream(in);
			case PLAIN: return in;
			default: return null;
		}
	}

	@Nullable
	public DataOutputStream getDataOutput(int id) {
		return outOfBounds(id) ? null : new DataOutputStream(new DeflaterOutputStream(new ChunkOut(id,2)));
	}

	@Nullable
	public ChunkOut getOutput(int id, int type) {
		return new ChunkOut(id,type);
	}

	public void write(int id, int type, ByteList data) throws IOException {
		int i1 = offsets[id];
		int off = i1 >>> 8;
		int oldCLen = i1 & 255;
		if (oldCLen == 255) {
			synchronized (free) {
				raf.seek(off * bitmapSize);
				oldCLen = (raf.readInt() + 4) / bitmapSize + 1;
			}
		}

		int cLen = (data.wIndex() + 5) / bitmapSize + 1;
		if (cLen >= 256) {
			log("大数据: {} #{} 占用的块: {} 长度: ", file, id, cLen, data.wIndex());
		}

		if (off != 0 && oldCLen == cLen) {
			write0(off, type, data);
		} else {
			synchronized (this) {
				if (oldCLen >= cLen) { // 数据块长度相同(考虑到上面,相同只能为零)或更小
					write1(id, type, data, off, cLen);

					free.addRange(off+cLen, off+oldCLen);
				} else {
					free.addRange(off,off+oldCLen);

					// 找到符合条件的连续区块
					int begin = -1;
					int len = 0;
					for (int i = 0; i < free.last(); i++) {
						if (len != 0) {
							if (free.contains(i)) {
								++len;
							} else {
								len = 0;
							}
						} else if (free.contains(i)) {
							begin = i;
							len = 1;
						}

						if (len >= cLen) break;
					}

					int prevOff = offsets[id];
					try {
						if (len >= cLen) {
							// 找到了
							write1(id, type, data, begin, cLen);

							free.removeRange(begin, begin + cLen);
						} else {
							// 在文件后增加连续空间
							raf.setLength(raf.length() + bitmapSize * cLen);

							write1(id, type, data, sectorCount, cLen);

							sectorCount += cLen;
						}
					} catch (Exception e) {
						free.removeRange(off,off+oldCLen);
						offsets[id] = prevOff;

						throw e;
					}
				}
			}
		}

		setTimestamp(id, (int) (System.currentTimeMillis() / 1000L));
	}

	public void delete(int id) throws IOException {
		int i;
		synchronized (offsets) {
			i = offsets[id];
		}
		if (id == 0) return;

		int off = i >>> 8;
		int len = i & 255;

		synchronized (this) {
			if (len == 255) {
				raf.seek(off * bitmapSize);
				len = (raf.readInt() + 4) / bitmapSize + 1;
			}

			synchronized (offsets) {
				offsets[id] = 0;
				while (len-- > 0) free.add(off + len);
			}
		}

		setTimestamp(id, 0);
	}

	public synchronized int copyTo(MyRegionFile target) throws IOException {
		int moved = 0;
		ByteList tmp = new ByteList();

		if (offsets.length != target.offsets.length || bitmapSize != target.bitmapSize)
			throw new IOException("File not compatible");

		for (int i = 0; i < offsets.length; i++) {
			int id = offsets[i];
			if (id == 0) continue;

			int off = id >>> 8;
			int len = id & 255;
			if (len == 255) {
				raf.seek(off * bitmapSize);
				len = (raf.readInt() + 4) / bitmapSize + 1;
			}

			if (off + len <= sectorCount) {
				raf.seek(off * bitmapSize);
				int dataLen = raf.readInt();
				if (dataLen > bitmapSize * len) {
					log("无效的分块: {} #{} 块范围:[{}+{}] 数据超出分块尾: {} > {}", file, id, off, len, dataLen, len * bitmapSize);
				} else if (dataLen <= 0) {
					log("无效的分块: {} #{} 块范围:[{}+{}] 长度小于0: {}", file, id, off, len, dataLen);
				} else {
					byte type = raf.readByte();

					tmp.clear();
					tmp.ensureCapacity(--dataLen);
					raf.read(tmp.list, 0, dataLen);
					tmp.wIndex(dataLen);

					target.write(i, type, tmp);
				}
			}
		}
		return moved;
	}

	public boolean hasData(int id) {
		synchronized (offsets) {
			return offsets[id] != 0;
		}
	}

	public int getTimestamp(int id) {
		return timestamps[id];
	}

	public synchronized void close() throws IOException {
		if (raf != null) raf.close();
	}

	protected void log(String msg, Object... params) {
		LOGGER.log(Level.ERROR, msg, null, params);
	}

	protected ByteList allocateBuffer(int i) {
		return allocateBufferEarly(i);
	}

	protected void releaseBuffer(ByteList buf) {
		releaseBufferEarly(buf);
	}

	protected ByteList allocateBufferEarly(int i) {
		List<ByteList> b = this.buffers;
		if (!b.isEmpty()) {
			synchronized (b) {
				if (!b.isEmpty()) {
					ByteList buf = b.remove(b.size() - 1);
					buf.clear();
					buf.ensureCapacity(i);
					return buf;
				}
			}
		}
		return new ByteList(i);
	}

	protected void releaseBufferEarly(ByteList buf) {
		List<ByteList> b = this.buffers;
		if (b.size() < 30) {
			synchronized (b) {
				if (b.size() < 30) {
					b.add(buf);
					buf.clear();
				}
			}
		}
	}

	private void write1(int id, int type, ByteList data, int off, int len) throws IOException {
		synchronized (offsets) {
			setOffset(id, (off << 8) | (len > 255 ? 255 : len));
			write0(off, type, data);
		}
	}

	private void write0(int beginSector, int type, ByteList data) throws IOException {
		raf.seek(beginSector * bitmapSize);
		raf.writeInt(data.wIndex() + 1);
		raf.writeByte(type);
		raf.write(data.array(), data.arrayOffset(), data.readableBytes());
	}

	private boolean outOfBounds(int id) {
		return id < 0 || id > offsets.length;
	}

	private void setOffset(int id, int offset) throws IOException {
		offsets[id] = offset;
		raf.seek(id<<2);
		raf.writeInt(offset);
	}

	private void setTimestamp(int id, int timestamp) throws IOException {
		timestamps[id] = timestamp;
		raf.seek((offsets.length<<2) + (id<<2));
		raf.writeInt(timestamp);
	}

	static final class ChunkInput extends InputStream {
		int length;
		RandomAccessFile raf;

		ChunkInput(File file) throws IOException {
			raf = new RandomAccessFile(file, "r");
		}

		@Override
		public int read() throws IOException {
			if (length <= 0) return -1;
			int v = raf.read();
			length--;
			return v;
		}

		@Override
		public int read(@NotNull byte[] b, int off, int len) throws IOException {
			if (length <= 0) return -1;

			int v = Math.min(len, length);

			v = raf.read(b, off, v);
			if (v > 0) length -= v;
			return v;
		}

		@Override
		public void close() throws IOException {
			raf.close();
		}
	}

	public final class ChunkOut extends OutputStream {
		public final int id;
		public final byte dataType;
		private ByteList buf;

		ChunkOut(int id, int dataType) {
			this.id = id;
			this.dataType = (byte) dataType;
			buf = allocateBuffer(2048);
		}

		@Override
		public void write(int b) {
			if (!buf.isWritable()) expand(1);
			buf.put((byte) b);
		}

		@Override
		public void write(byte[] b, int off, int len) {
			if (buf.writableBytes() < len) expand(len);
			buf.put(b, off, len);
		}

		private void expand(int more) {
			ByteList new_ = allocateBuffer(MathUtils.getMin2PowerOf(buf.capacity()+more));
			new_.put(buf);
			releaseBuffer(buf);
			buf = new_;
		}

		public void close() throws IOException {
			if (buf != null) {
				MyRegionFile.this.write(id, dataType, buf);
				releaseBuffer(buf);
				buf = null;
			}
		}

		public void fail() {
			if (buf != null) {
				releaseBuffer(buf);
				buf = null;
			}
		}
	}
}