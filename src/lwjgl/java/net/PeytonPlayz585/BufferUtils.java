package net.PeytonPlayz585;

import net.lax1dude.eaglercraft.internal.buffer.ByteBuffer;
import net.lax1dude.eaglercraft.internal.buffer.EaglerLWJGLAllocator;
import net.lax1dude.eaglercraft.internal.buffer.FloatBuffer;
import net.lax1dude.eaglercraft.internal.buffer.IntBuffer;
import net.lax1dude.eaglercraft.internal.buffer.ShortBuffer;

/**
 * Some often-used Buffer code for creating native buffers of the appropriate size.
 *
 * @author $Author$
 * @version $Revision$
 * $Id$
 */

public final class BufferUtils {

    /**
     * Construct a direct native-ordered bytebuffer with the specified size.
     * @param size The size, in bytes
     * @return a ByteBuffer
     */
    public static ByteBuffer createByteBuffer(int size) {
        //return EaglerLWJGLAllocator.allocateDirect(size).order(ByteOrder.nativeOrder());
    	return EaglerLWJGLAllocator.allocByteBuffer(size);
    }

    /**
     * Construct a direct native-order shortbuffer with the specified number
     * of elements.
     * @param size The size, in shorts
     * @return a ShortBuffer
     */
    public static ShortBuffer createShortBuffer(int size) {
        return createByteBuffer(size << 1).asShortBuffer();
    }

    /**
     * Construct a direct native-order intbuffer with the specified number
     * of elements.
     * @param size The size, in ints
     * @return an IntBuffer
     */
    public static IntBuffer createIntBuffer(int size) {
        return createByteBuffer(size << 2).asIntBuffer();
    }

    /**
     * Construct a direct native-order floatbuffer with the specified number
     * of elements.
     * @param size The size, in floats
     * @return a FloatBuffer
     */
    public static FloatBuffer createFloatBuffer(int size) {
        return createByteBuffer(size << 2).asFloatBuffer();
    }
}