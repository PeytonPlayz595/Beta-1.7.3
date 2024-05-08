package net.lax1dude.eaglercraft;

import java.nio.*;
import java.util.ArrayList;
import java.util.List;

import net.PeytonPlayz585.opengl.GL11;

public class GLAllocation {

	public GLAllocation() {
	}

	public static synchronized int generateDisplayLists(int i) {
		int j = GL11.glGenLists(i);
		displayLists.add(Integer.valueOf(j));
		displayLists.add(Integer.valueOf(i));
		return j;
	}

	public static synchronized void generateTextureNames(IntBuffer intbuffer) {
		
		for (int i = intbuffer.position(); i < intbuffer.limit(); i++) {
			int tx = GL11.glGenTextures();
			intbuffer.put(i, tx);
			textureNames.add(Integer.valueOf(tx));
		}

	}

	public static synchronized void deleteTexturesAndDisplayLists() {
		for (int i = 0; i < displayLists.size(); i += 2) {
			GL11.glDeleteLists(((Integer) displayLists.get(i)).intValue(),
					((Integer) displayLists.get(i + 1)).intValue());
		}
		
		for (int j = 0; j < textureNames.size(); j++) {
			GL11.glDeleteTextures(((Integer) textureNames.get(j)).intValue());
		}
		
		displayLists.clear();
		textureNames.clear();
	}
	
	public static ByteBuffer createDirectByteBuffer(int par0) {
		return GL11.isWebGL ? ByteBuffer.wrap(new byte[par0]).order(ByteOrder.nativeOrder()) : ByteBuffer.allocateDirect(par0).order(ByteOrder.nativeOrder());
	}
	
	public static IntBuffer createDirectIntBuffer(int par0) {
		return GL11.isWebGL ? IntBuffer.wrap(new int[par0]) : createDirectByteBuffer(par0 << 2).asIntBuffer();
	}
	
	public static FloatBuffer createDirectFloatBuffer(int par0) {
		return GL11.isWebGL ? FloatBuffer.wrap(new float[par0]) : createDirectByteBuffer(par0 << 2).asFloatBuffer();
	}

	private static List displayLists = new ArrayList();
	private static List textureNames = new ArrayList();

}