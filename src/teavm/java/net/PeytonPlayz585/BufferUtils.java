package net.PeytonPlayz585;

import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.LongBuffer;

import net.lax1dude.eaglercraft.internal.buffer.ByteBuffer;
import net.lax1dude.eaglercraft.internal.buffer.EaglerArrayBufferAllocator;
import net.lax1dude.eaglercraft.internal.buffer.FloatBuffer;
import net.lax1dude.eaglercraft.internal.buffer.IntBuffer;
import net.lax1dude.eaglercraft.internal.buffer.ShortBuffer;

public class BufferUtils {

    /**
     * It's not possible to directly allocate and then convert the buffers in TeaVM
     * So to fix this, I'm wrapping the values to the buffer instead
     */

    public static ByteBuffer createByteBuffer(int size) {
    	//return ByteBuffer.wrap(new byte[size]).order(ByteOrder.nativeOrder());
    	return EaglerArrayBufferAllocator.allocateByteBuffer(size);
    }

    public static ShortBuffer createShortBuffer(int size) {
    	//return ShortBuffer.wrap(new short[size]);
    	return EaglerArrayBufferAllocator.allocateByteBuffer(size).asShortBuffer();
    }

    public static CharBuffer createCharBuffer(int size) {
    	return CharBuffer.wrap(new char[size]);
	}

    public static IntBuffer createIntBuffer(int size) {
    	//return IntBuffer.wrap(new int[size]);
    	return EaglerArrayBufferAllocator.allocateIntBuffer(size);
	}

    public static LongBuffer createLongBuffer(int size) {
    	return LongBuffer.wrap(new long[size]);
	}

    public static FloatBuffer createFloatBuffer(int size) {
    	//return FloatBuffer.wrap(new float[size]);
    	return EaglerArrayBufferAllocator.allocateFloatBuffer(size);
	}

    public static DoubleBuffer createDoubleBuffer(int size) {
    	return DoubleBuffer.wrap(new double[size]);
	}

    public static PointerBuffer createPointerBuffer(int size) {
        //Doesn't actually directly allocate
        //I rewrote the PointerBuffer class to use buffer.wrap
		return PointerBuffer.allocateDirect(size);
	}
}