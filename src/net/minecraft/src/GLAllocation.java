package net.minecraft.src;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class GLAllocation {
	private static List displayLists = new ArrayList();
	private static List textureNames = new ArrayList();

	public static synchronized int generateDisplayLists(int var0) {
		int var1 = GL11.glGenLists(var0);
		displayLists.add(Integer.valueOf(var1));
		displayLists.add(Integer.valueOf(var0));
		return var1;
	}

	public static synchronized void generateTextureNames(IntBuffer var0) {
		for (int i = var0.position(); i < var0.limit(); i++) {
			int tx = GL11.glGenTextures();
			var0.put(i, tx);
			textureNames.add(Integer.valueOf(tx));
		}
	}

	public static synchronized void func_28194_b(int var0) {
		int var1 = displayLists.indexOf(Integer.valueOf(var0));
		GL11.glDeleteLists(((Integer)displayLists.get(var1)).intValue(), ((Integer)displayLists.get(var1 + 1)).intValue());
		displayLists.remove(var1);
		displayLists.remove(var1);
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
		return ByteBuffer.wrap(new byte[par0]).order(ByteOrder.nativeOrder());
	}
	
	public static IntBuffer createDirectIntBuffer(int par0) {
		return IntBuffer.wrap(new int[par0]);
	}
	
	public static FloatBuffer createDirectFloatBuffer(int par0) {
		return FloatBuffer.wrap(new float[par0]);
	}
}
