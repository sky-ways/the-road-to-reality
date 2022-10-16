package com.github.cao.awa.trtr.database.roj;

import com.github.cao.awa.trtr.database.roj.collect.*;
import org.apache.commons.io.*;

import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Roj234
 * @since 2022/10/14 0014 23:07
 */
public class BinaryDB {
	static final byte VERSION = 1;

	public final File base;
	private final RandomAccessFile index;
	private DataFile[] files;
	private long nextEmpty;
	private final int chunkBits, chunkMask, smallChunkSize;
	private final ReentrantReadWriteLock globalLock = new ReentrantReadWriteLock(true);

	private long reserved;
	private RingBuffer<DataFile> byOrder;

	public BinaryDB(File base, int chunkBits, int smallChunkSize, int memoryMax, int countMax) throws IOException {
		this.base = base;
		if (chunkBits <= 0 || chunkBits > 28)
			throw new IllegalStateException();

		File index = new File(base, "_.idx");
		this.index = new RandomAccessFile(index, "rw");
		this.chunkBits = chunkBits;
		this.chunkMask = (1<<chunkBits)-1;
		this.smallChunkSize = smallChunkSize;

		this.reserved = (long)memoryMax << 20;
		this.byOrder = new RingBuffer<>(countMax, false);

		long each = chunkMask * 10;
		if (0==index.length()) {
			files = new DataFile[1];
			return;
		}

		ByteList shared = ByteList.wrap(FileUtils.readFileToByteArray(index));
		if (shared.length() != 21) {
			throw new IOException("无效的索引文件 - 大小不是21");
		}
		if (VERSION != shared.readUnsignedByte())
			throw new IOException("无效的索引文件 - 版本不匹配");
		if (chunkBits != shared.readInt())
			throw new IOException("无效的索引文件 - chunkBits不匹配");
		if (smallChunkSize != shared.readInt())
			throw new IOException("无效的索引文件 - smallChunkSize不匹配");
		files = new DataFile[shared.readInt()];
		for (int i = 0; i < files.length; i++) {
			File file = new File(base, Integer.toString(i, 36) + ".mcr");
			if (!file.isFile()) {
				throw new IOException("无法找到必须的数据: " + file);
			}
		}
		nextEmpty = shared.readLong();
	}

	public InputStream get(long id) throws IOException {
		return getInfo(id, null);
	}

	public InputStream getInfo(long id, int[] info) throws IOException {
		globalLock.readLock().lock();
		try {
			DataFile file = getFile(id);
			if (file == null) return null;
			return file.getData((int) (id & chunkMask), info);
		} finally {
			globalLock.readLock().unlock();
		}
	}

	public long getTimestamp(long id) throws IOException {
		globalLock.readLock().lock();
		try {
			DataFile file = getFile(id);
			if (file == null) return 0;
			return (file.getTimestamp((int) (id & chunkMask))&0xFFFFFFFFL) * 1000;
		} finally {
			globalLock.readLock().unlock();
		}
	}

	public long addId() {
		return nextEmpty;
	}

	public MyRegionFile.ChunkOut add(int type) throws IOException {
		Lock lock = globalLock.writeLock();
		lock.lock();
		try {
			nextEntry();
		} finally {
			lock.unlock();
		}

		lock = globalLock.readLock();
		lock.lock();
		try {
			return loadFile((int) (nextEmpty >>> chunkBits)).getOutput((int) (nextEmpty & chunkMask), type);
		} finally {
			lock.unlock();
		}
	}

	public long add(ByteList data) throws IOException {
		Lock lock = globalLock.writeLock();
		lock.lock();
		try {
			nextEntry();
		} finally {
			lock.unlock();
		}

		lock = globalLock.readLock();
		lock.lock();
		try {
			long empty = nextEmpty;
			loadFile((int) (empty >>> chunkBits)).write((int) (empty & chunkMask), MyRegionFile.PLAIN, data);
			return empty;
		} finally {
			lock.unlock();
		}
	}

	public MyRegionFile.ChunkOut set(long id, int type) throws IOException {
		globalLock.readLock().lock();
		try {
			DataFile file = getFile(id);
			if (file == null) return null;

			int subId = (int) (id & chunkMask);
			if (!file.hasData(subId)) return null;
			return file.getOutput(subId, type);
		} finally {
			globalLock.readLock().unlock();
		}
	}

	public boolean set(long id, ByteList data) throws IOException {
		globalLock.readLock().lock();
		try {
			DataFile file = getFile(id);
			if (file == null) return false;

			int subId = (int) (id & chunkMask);
			if (!file.hasData(subId)) return false;
			file.write(subId, 2, data);
			return true;
		} finally {
			globalLock.readLock().unlock();
		}
	}

	public boolean delete(long id) throws IOException {
		Lock lock = globalLock.readLock();
		lock.lock();
		try {
			MyRegionFile file = getFile(id);
			if (file == null) return false;

			int subId = (int) (id & chunkMask);
			if (!file.hasData(subId)) return false;
			file.delete(subId);

			lock.unlock();
			(lock = globalLock.writeLock()).lock();
			if (id < nextEmpty) nextEmpty = id;
			return true;
		} finally {
			lock.unlock();
		}
	}

	public int unloadIdle(int timeout) {
		int count = 0;
		long time = System.currentTimeMillis()-timeout;

		globalLock.writeLock().lock();

		DataFile[] files = this.files;
		for (int i = files.length - 1; i >= 0; i--) {
			DataFile data = files[i];
			if (data != null && data.timestamp < time) {
				reserved += data.memorySize();
				count++;
				files[i] = null;
				try {
					data.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		globalLock.writeLock().unlock();
		return count;
	}

	private DataFile getFile(long id) throws IOException {
		if (id < 0 || id >= (long) files.length << chunkBits) return null;
		return loadFile((int) (id >>> chunkBits));
	}

	private DataFile loadFile(int arrId) throws IOException {
		DataFile f = files[arrId];
		if (f == null) {
			f = files[arrId] = new DataFile(new File(base, Integer.toString(arrId, 36) + ".mcr"), smallChunkSize, 1 << chunkBits);
			reserved -= f.memorySize();
			while (reserved < 0 && !byOrder.isEmpty()) {
				reserved += byOrder.removeFirst().memorySize();
			}
			DataFile prev = byOrder.ringAddLast(f);
			if (prev != null) reserved += prev.memorySize();
		}
		f.timestamp = System.currentTimeMillis();
		return f;
	}

	private void nextEntry() throws IOException {
		long empty = this.nextEmpty;
		int fileId = (int) (empty >>> chunkBits);
		while (fileId < files.length) {
			DataFile file = loadFile(fileId);
			int id = (int) (empty & chunkMask);
			int[] arr = file.getOffsets();
			while (id < arr.length) {
				// 这个文件还有剩余
				if (arr[id] == 0) {
					// 未改变
					if (nextEmpty != (nextEmpty = (long)(fileId << chunkBits) | id)) {
						updateIndex();
					}
					return;
				}
				id++;
			}
			fileId++;
		}

		DataFile[] newFiles = new DataFile[fileId+1];
		System.arraycopy(files, 0, newFiles, 0, fileId);

		files = newFiles;
		nextEmpty = fileId << chunkBits;
		updateIndex();
	}

	private void updateIndex() throws IOException {
		if (index.length() != 20) {
			index.setLength(21);
			index.seek(0);
			index.write(VERSION);
			index.writeInt(chunkBits);
			index.writeInt(smallChunkSize);
		} else {
			index.seek(9);
		}

		index.writeInt(files.length);
		index.writeLong(nextEmpty);
	}

	static final class DataFile extends MyRegionFile {
		public long timestamp;

		public DataFile(File file, int chunkSize, int fileCap) throws IOException {
			super(file, chunkSize, fileCap);
		}

		public int[] getOffsets() {
			return offsets;
		}

		@Override
		protected ByteList allocateBufferEarly(int i) {
			ByteList buf = new ByteList();
			buf.ensureCapacity(i);
			return buf;
		}

		@Override
		protected void releaseBufferEarly(ByteList buf) {}

		public long memorySize() {
			return ((long) offsets.length << 3) + (free.last() >>> 3) + 192;
		}
	}
}
