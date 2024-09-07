package org.lwjgl;

import java.nio.*;

public class PointerBuffer {
    protected final ByteBuffer pointers;
    protected final Buffer view;
    protected final LongBuffer view64;

    public PointerBuffer(final int capacity) {
        this(ByteBuffer.wrap(new byte[capacity * getPointerSize()]).order(ByteOrder.nativeOrder()));
    }

    public PointerBuffer(final ByteBuffer source) {
        checkSource(source);
        pointers = source.slice().order(source.order());

        view = view64 = pointers.asLongBuffer(); //Assumes 64-bit, no way to check for it
    }

    private static void checkSource(final ByteBuffer source) {
		if (!source.isDirect()) {
			throw new IllegalArgumentException("The source buffer is not direct.");
        }

        final int alignment = 8;

		//There is no reliable way of getting a memory address for a buffer in JS
        if (source.remaining() % alignment != 0) {
            throw new IllegalArgumentException("The source buffer is not aligned to " + alignment + " bytes.");
        }
	}

    public ByteBuffer getBuffer() {
        return pointers;
    }

    public static boolean is64Bit() {
        //No reliable way to check this
        //So I'm just assuming it's true
        return true;
    }

    public static int getPointerSize() {
        return 8; //Also assumes 64-bit
    }

    public final int capacity() {
        return view.capacity();
	}

	public final int position() {
		return view.position();
	}

    public final int positionByte() {
		return position() * getPointerSize();
	}

    public final PointerBuffer position(int newPosition) {
		view.position(newPosition);
		return this;
	}

    public final int limit() {
		return view.limit();
	}

    public final PointerBuffer limit(int newLimit) {
		view.limit(newLimit);
		return this;
	}

    public final PointerBuffer mark() {
		view.mark();
		return this;
	}

    public final PointerBuffer reset() {
		view.reset();
		return this;
	}

    public final PointerBuffer clear() {
		view.clear();
		return this;
	}

    public final PointerBuffer flip() {
		view.flip();
		return this;
	}

    public final PointerBuffer rewind() {
		view.rewind();
		return this;
	}

    public final int remaining() {
		return view.remaining();
	}

    public final int remainingByte() {
		return remaining() * getPointerSize();
	}

    public final boolean hasRemaining() {
		return view.hasRemaining();
	}

    public static PointerBuffer allocateDirect(int capacity) {
		return new PointerBuffer(capacity);
	}

    protected PointerBuffer newInstance(final ByteBuffer source) {
		return new PointerBuffer(source);
	}

    public PointerBuffer slice() {
		final int pointerSize = getPointerSize();

		pointers.position(view.position() * pointerSize);
		pointers.limit(view.limit() * pointerSize);

		try {
			return newInstance(pointers); //Buffer is sliced in constructors
		} finally {
			pointers.clear();
		}
	}

    public PointerBuffer duplicate() {
		final PointerBuffer buffer = newInstance(pointers);

		buffer.position(view.position());
		buffer.limit(view.limit());

		return buffer;
	}

    //I don't like this
    public PointerBuffer asReadOnlyBuffer() {
		final PointerBuffer buffer = new PointerBufferR(pointers);

		buffer.position(view.position());
		buffer.limit(view.limit());

		return buffer;
	}

    public boolean isReadOnly() {
		return false;
	}

    public long get() {
        return view64.get();
    }

    public PointerBuffer put(long l) {
        view64.put(l);
        return this;
    }

    public PointerBuffer put(final PointerWrapper pointer) {
		return put(pointer.getPointer());
	}

    public static void put(final ByteBuffer target, long l) {
        target.putLong(l);
    }

    public long get(int index) {
        return view64.get(index);
    }

    public PointerBuffer put(int index, long l) {
        view64.put(index, l);
        return this;
    }

    public PointerBuffer put(int index, PointerWrapper pointer) {
		return put(index, pointer.getPointer());
	}

    public static void put(final ByteBuffer target, int index, long l) {
        target.putLong(index, l);
    }

    public PointerBuffer get(long[] dst, int offset, int length) {
        view64.get(dst, offset, length);
        return this;
    }

    public PointerBuffer get(long[] dst) {
		return get(dst, 0, dst.length);
	}

    public PointerBuffer put(PointerBuffer src) {
		view64.put(src.view64);
		return this;
	}

    public PointerBuffer put(long[] src, int offset, int length) {
		view64.put(src, offset, length);
		return this;
	}

    public final PointerBuffer put(long[] src) {
		return put(src, 0, src.length);
	}

    public PointerBuffer compact() {
        view64.compact();
        return this;
    }

    public ByteOrder order() {
        return view64.order();
    }

    public String toString() {
        //Use String.format instead of StringBuilder
        return String.format("%s[pos=%d, lim=%d, cap=%d]", getClass().getName(), position(), limit(), capacity());
	}

    public int hashCode() {
		int h = 1;
		int p = position();
		for (int i = limit() - 1; i >= p; i--) {
			h = 31 * h + (int)get(i);
        }
		return h;
	}

    public boolean equals(Object ob) {
		if (!(ob instanceof PointerBuffer)) {
			return false;
        }
		PointerBuffer that = (PointerBuffer)ob;
		if (this.remaining() != that.remaining()) {
			return false;
        }
		int p = this.position();
		for (int i = this.limit() - 1, j = that.limit() - 1; i >= p; i--, j--) {
			long v1 = this.get(i);
			long v2 = that.get(j);
			if ( v1 != v2 ) {
				return false;
			}
		}
		return true;
	}

    public int compareTo(Object o) {
		final PointerBuffer that = (PointerBuffer)o;
		int n = this.position() + Math.min(this.remaining(), that.remaining());
		for (int i = this.position(), j = that.position(); i < n; i++, j++) {
			long v1 = this.get(i);
			long v2 = that.get(j);
			if ( v1 == v2 ) {
				continue;
            }
			if (v1 < v2) {
				return -1;
            }
			return +1;
		}
		return this.remaining() - that.remaining();
	}

    private static void checkBounds(int off, int len, int size) {
		if ((off | len | (off + len) | (size - (off + len))) < 0) {
			throw new IndexOutOfBoundsException();
        }
	}

    private static final class PointerBufferR extends PointerBuffer {
		PointerBufferR(final ByteBuffer source) {
			super(source);
		}

		public boolean isReadOnly() {
			return true;
		}

		protected PointerBuffer newInstance(final ByteBuffer source) {
			return new PointerBufferR(source);
		}

		public PointerBuffer asReadOnlyBuffer() {
			return duplicate();
		}

		public PointerBuffer put(final long l) {
			throw new ReadOnlyBufferException();
		}

		public PointerBuffer put(final int index, final long l) {
			throw new ReadOnlyBufferException();
		}

		public PointerBuffer put(final PointerBuffer src) {
			throw new ReadOnlyBufferException();
		}

		public PointerBuffer put(final long[] src, final int offset, final int length) {
			throw new ReadOnlyBufferException();
		}

		public PointerBuffer compact() {
			throw new ReadOnlyBufferException();
		}
	}
}