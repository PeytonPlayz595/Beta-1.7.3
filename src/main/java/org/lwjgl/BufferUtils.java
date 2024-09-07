package org.lwjgl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import net.minecraft.src.GLAllocation;

public class BufferUtils {

    /**
     * It's not possible to directly allocate and then convert the buffers in TeaVM
     * So to fix this, I'm wrapping the values to the buffer instead
     */

    public static ByteBuffer createByteBuffer(int size) {
    	return ByteBuffer.wrap(new byte[size]).order(ByteOrder.nativeOrder());
    }

    public static ShortBuffer createShortBuffer(int size) {
    	return ShortBuffer.wrap(new short[size]);
    }

    public static CharBuffer createCharBuffer(int size) {
    	return CharBuffer.wrap(new char[size]);
	}

    public static IntBuffer createIntBuffer(int size) {
    	return IntBuffer.wrap(new int[size]);
	}

    public static LongBuffer createLongBuffer(int size) {
    	return LongBuffer.wrap(new long[size]);
	}

    public static FloatBuffer createFloatBuffer(int size) {
    	return FloatBuffer.wrap(new float[size]);
	}

    public static DoubleBuffer createDoubleBuffer(int size) {
    	return DoubleBuffer.wrap(new double[size]);
	}

    public static PointerBuffer createPointerBuffer(int size) {
        //Doesn't actually directly allocate
        //I rewrote the PointerBuffer class to use buffer.wrap
		return PointerBuffer.allocateDirect(size);
	}

    public static int getElementSizeExponent(Buffer buf) {
		if (buf instanceof ByteBuffer) {
			return 0;
        } else if (buf instanceof ShortBuffer || buf instanceof CharBuffer) {
			return 1;
        } else if (buf instanceof FloatBuffer || buf instanceof IntBuffer) {
			return 2;
        } else if (buf instanceof LongBuffer || buf instanceof DoubleBuffer) {
			return 3;
        } else {
			throw new IllegalStateException("Unsupported buffer type: " + buf);
        }
	}

    public static int getOffset(Buffer buffer) {
		return buffer.position() << getElementSizeExponent(buffer);
	}

    public static void zeroBuffer(ByteBuffer b) {
	    zeroBuffer0(b, b.position(), b.remaining());
	}

    public static void zeroBuffer(ShortBuffer b) {
	    zeroBuffer0(b, b.position()*2L, b.remaining()*2L);
	}

    public static void zeroBuffer(CharBuffer b) {
	    zeroBuffer0(b, b.position()*2L, b.remaining()*2L);
	}

    public static void zeroBuffer(IntBuffer b) {
	    zeroBuffer0(b, b.position()*4L, b.remaining()*4L);
	}

    public static void zeroBuffer(FloatBuffer b) {
	    zeroBuffer0(b, b.position()*4L, b.remaining()*4L);
	}

    public static void zeroBuffer(LongBuffer b) {
	    zeroBuffer0(b, b.position()*8L, b.remaining()*8L);
	}

    public static void zeroBuffer(DoubleBuffer b) {
	    zeroBuffer0(b, b.position()*8L, b.remaining()*8L);
	}

    //Wrote my own implementation since JNI isn't supported in TeaVM
    private static void zeroBuffer0(Buffer b, long offset, long length) {
    	for (int i = 0; i < b.remaining(); i++) {
    		put(b, i, 0);
    	}
    }
    
    private static void put(Buffer b, int i, int i2) {
    	if(b instanceof ByteBuffer) {
    		put((ByteBuffer)b, i, i2);
    	} else if(b instanceof ShortBuffer) {
    		put((ShortBuffer)b, i, i2);
    	} else if(b instanceof CharBuffer) {
    		put((CharBuffer)b, i, i2);
    	} else if(b instanceof IntBuffer) {
    		put((IntBuffer)b, i, i2);
    	} else if(b instanceof FloatBuffer) {
    		put((FloatBuffer)b, i, i2);
    	} else if(b instanceof LongBuffer) {
    		put((LongBuffer)b, i, i2);
    	} else if(b instanceof DoubleBuffer) {
    		put((DoubleBuffer)b, i, i2);
    	} else {
    		throw new IllegalArgumentException("Unsupported buffer type!");
    	}
    }
    
    private static void put(ByteBuffer b, int i, int i2) {
    	b.put(i, (byte) i2);
    }
    
    private static void put(ShortBuffer b, int i, int i2) {
    	b.put(i, (short) i2);
    }
    
    private static void put(CharBuffer b, int i, int i2) {
    	b.put(i, (char) i2);
    }
    
    private static void put(IntBuffer b, int i, int i2) {
    	b.put(i, i2);
    }
    
    private static void put(FloatBuffer b, int i, int i2) {
    	b.put(i, i2);
    }
    
    private static void put(LongBuffer b, int i, int i2) {
    	b.put(i, i2);
    }
      
    private static void put(DoubleBuffer b, int i, int i2) {
    	b.put(i, i2);
    }

    //Not in BufferUtils but thought I would add it anyways
    public static int getBufferSizeInBytes(Buffer buffer) {
    	return buffer.capacity() * getElementSizeExponent(buffer);
    }
}