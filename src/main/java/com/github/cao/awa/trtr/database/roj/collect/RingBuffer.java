package com.github.cao.awa.trtr.database.roj.collect;

import com.github.cao.awa.trtr.database.roj.collect.iterator.*;
import org.jetbrains.annotations.*;

import java.util.*;

/**
 * A simple ring buffer
 *
 * @author Roj234
 * @since 2021/4/13 23:25
 */
public class RingBuffer<E> extends AbstractCollection<E> implements Deque<E> {
	public final class Itr extends AbstractIterator<E> {
		int i;
		int dir;
		int fence;

		@SuppressWarnings("unchecked")
		public Itr(boolean rev) {
			if (size == 0) {
				stage = ENDED;
				return;
			}

			if (rev) {
				i = (tail == 0 ? array.length : tail) - 1;
				dir = -1;
				fence = (head == 0 ? array.length : head) - 1;
			} else {
				i = head;
				dir = 1;
				fence = tail;
			}

			stage = AbstractIterator.CHECKED;
			result = (E) array[i];
			i += dir;
		}

		@Override
		@SuppressWarnings("unchecked")
		public boolean computeNext() {
			if (i == -1) {
				if (size < array.length) return false;
				i = array.length - 1;
			} else if (i == array.length) {
				i = 0;
			}

			if (i == fence) return false;
			result = (E) array[i];
			i += dir;
			return true;
		}

		public int getPos() {
			return i;
		}

		@Override
		public String toString() {
			return "Itr{" + i + "+" + dir + " => " + fence + '}';
		}
	}

	private int cap;
	protected Object[] array;

	protected int head, tail, size;

	public RingBuffer(int capacity) {
		this(capacity, true);
	}

	public RingBuffer(int capacity, boolean allocateNow) {
		cap = capacity;
		if (!allocateNow) capacity = Math.min(10, capacity);
		array = new Object[capacity];
	}

	public void setCapacity(int capacity) {
		if (array.length > capacity) resize(capacity);
		cap = capacity;
	}

	public void ensureCapacity(int capacity) {
		if (capacity > cap)
			capacity = cap;
		if (capacity > array.length) {
			// in loop mode
			resize(capacity);
		}
	}

	private void resize(int capacity) {
		Object[] newArray = new Object[capacity];
		int i = 0;
		for (E e : this) {
			newArray[i++] = e;
			if (i == newArray.length) break;
		}
		array = newArray;
		head = 0;
		tail = i;
	}

	public int capacity() {
		return cap;
	}

	public int head() {
		return head;
	}

	public int tail() {
		return tail;
	}

	public Object[] getArray() {
		return array;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	@NotNull
	@Override
	public Iterator<E> iterator() {
		return size == 0 ? Collections.emptyIterator() : new Itr(false);
	}

	@NotNull
	@Override
	public Iterator<E> descendingIterator() {
		return size == 0 ? Collections.emptyIterator() : new Itr(true);
	}

	public Itr listItr(boolean reverse) {
		return new Itr(reverse);
	}

	@Override
	public E pollFirst() {
		return size == 0 ? null : removeFirst();
	}
	@Override
	public E pollLast() {
		return size == 0 ? null : removeLast();
	}

	@SuppressWarnings("unchecked")
	@Override
	public E removeFirst() {
		if (size == 0) throw new NoSuchElementException();

		int head = this.head;
		E val = (E) array[head];
		array[head++] = null;

		if (--size == 0) {
			this.head = tail = 0;
		} else {
			// not using %
			this.head = head == array.length ? 0 : head;
		}

		return val;
	}
	@SuppressWarnings("unchecked")
	public E removeLast() {
		if (size == 0) throw new NoSuchElementException();

		int e = tail;
		if (e == 0) e = array.length;

		E val = (E) array[--e];
		array[e] = null;

		if (--size == 0) {
			head = tail = 0;
		} else {
			int h = head;
			if (e == h) {
				head = (e == 0 ? array.length : h) - 1;
			}
			tail = e;
		}

		return val;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E getFirst() {
		if (size == 0) throw new NoSuchElementException();
		return (E) array[head];
	}
	@Override
	@SuppressWarnings("unchecked")
	public E getLast() {
		if (size == 0) throw new NoSuchElementException();
		return (E) array[tail];
	}

	@Override
	@SuppressWarnings("unchecked")
	public E peekFirst() {
		return size == 0 ? null : (E) array[head];
	}
	@Override
	@SuppressWarnings("unchecked")
	public E peekLast() {
		return size == 0 ? null : (E) array[tail];
	}

	// region *** Deque incompatible methods ***
	@Deprecated
	public void addFirst(E e) {
		ringAddFirst(e);
	}
	@Deprecated
	public void addLast(E e) {
		ringAddLast(e);
	}
	@Deprecated
	public boolean offerFirst(E e) {
		ringAddFirst(e);
		return true;
	}
	@Deprecated
	public boolean offerLast(E e) {
		ringAddLast(e);
		return true;
	}
	// endregion
	// region *** Queue methods ***
	@Deprecated
	public boolean add(E e) {
		ringAddLast(e);
		return true;
	}
	@Deprecated
	public boolean offer(E e) {
		ringAddLast(e);
		return true;
	}
	@Deprecated
	public E remove() {
		return removeFirst();
	}
	@Deprecated
	public E poll() {
		return size == 0 ? null : removeFirst();
	}
	@Deprecated
	public E element() {
		return getFirst();
	}
	@Deprecated
	public E peek() {
		return peekFirst();
	}
	// endregion
	// region *** Stack methods ***
	@Deprecated
	public void push(E e) {
		ringAddLast(e);
	}
	@Deprecated
	public E pop() {
		return removeLast();
	}
	// endregion
	// region *** Collection methods ***
	@Override
	public boolean remove(Object o) {
		return removeFirstOccurrence(o);
	}
	@Override
	public boolean retainAll(@NotNull Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	// endregion

	@SuppressWarnings("unchecked")
	public E ringAddFirst(E e) {
		int s = size;
		if (s < cap) {
			if (s == array.length) ensureCapacity(array.length + 10);
			size = s + 1;
		}

		int h = head;
		if (tail == 0) {
			tail = array.length - 1;
		}
		if (tail == h) {
			tail--;
		}

		head = h == 0 ? (h = array.length-1) : --h;
		E orig = (E) array[h];
		array[h] = e;
		return orig;
	}

	@SuppressWarnings("unchecked")
	public E ringAddLast(E e) {
		int end = tail;

		int s = size;
		if (s < cap) {
			if (s == array.length) ensureCapacity(array.length + 10);
			size = s + 1;
		} else {
			int h = head;
			if (end == h) {
				head = ++h == array.length ? 0 : h;
			}
		}

		E orig = (E) array[end];
		array[end++] = e;
		if ((tail = end) == array.length) {
			tail = 0;
		}
		return orig;
	}

	@Override
	public void clear() {
		head = tail = size = 0;
		Arrays.fill(array, null);
	}

	@SuppressWarnings("unchecked")
	public void getSome(int dir, int i, int fence, List<E> dst) {
		if (size == 0) return;
		Object[] arr = array;
		do {
			dst.add((E) arr[i]);
			i += dir;

			if (i == arr.length) {i = 0;} else if (i < 0) i = arr.length - 1;
		} while (i != fence);
	}

	@SuppressWarnings("unchecked")
	public void getSome(int dir, int i, int fence, List<E> dst, int off, int len) {
		if (size == 0) return;
		Object[] arr = array;
		do {
			if (off != 0) {off--;} else if (len-- > 0) dst.add((E) arr[i]);
			i += dir;

			if (i == arr.length) {i = 0;} else if (i < 0) i = arr.length - 1;
		} while (i != fence);
	}

	@SuppressWarnings("unchecked")
	public E getArray(int i) {
		return (E) array[i];
	}

	@SuppressWarnings("unchecked")
	public E get(int i) {
		if (i < 0 || i >= size) throw new ArrayIndexOutOfBoundsException(i);

		i = head - i;
		return (E) (i < 0 ? array[i + array.length] : array[i]);
	}

	@SuppressWarnings("unchecked")
	public E set(int i, E val) {
		E orig = (E) array[i];
		array[i] = val;
		return orig;
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		int i = indexOf(o);
		if (i < 0) return false;
		remove(i);
		return true;
	}

	public int indexOf(Object o) {
		Object[] arr = array;
		int i = head;
		while (i != tail) {
			if (o == null ? arr[i] == null : o.equals(arr[i])) return i;
			if (--i < 0) i = arr.length - 1;
		}
		return -1;
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		int i = lastIndexOf(o);
		if (i < 0) return false;
		remove(i);
		return true;
	}

	public int lastIndexOf(Object o) {
		Object[] arr = array;
		int i = tail;
		while (i != head) {
			if (o == null ? arr[i] == null : o.equals(arr[i])) return i;
			if (++i == arr.length) i = 0;
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	public E remove(int arrIdx) {
		Object[] array = this.array;
		E e = (E) array[arrIdx];
		int tail = this.tail;

		if (head > tail) {
			/**
			 *   E S =>
			 * 0 1 2 3 4
			 */
			if (arrIdx > tail) {
				if (arrIdx < head) throw new IllegalStateException();
				// i = 3:
				// move 4 to 3
				System.arraycopy(array, arrIdx + 1, array, arrIdx, array.length - arrIdx);
				// move 0 to 4
				array[array.length - 1] = array[0];
			}
			// move 1 to 0
			System.arraycopy(array, 1, array, 0, tail);
			this.tail = (tail == 0 ? array.length : tail) - 1;
		} else {
			if (arrIdx < head || arrIdx > tail) throw new IllegalStateException();
			/**
			 *   S =>  E
			 * 0 1 2 3 4
			 */
			if (tail > arrIdx) System.arraycopy(array, arrIdx + 1, array, arrIdx, tail - arrIdx);
			this.tail = tail - 1;
		}
		// clear ref
		array[tail] = null;

		size--;
		return e;
	}

	@Override
	public String toString() {
		return "";
	}
}
