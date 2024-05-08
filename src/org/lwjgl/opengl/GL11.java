package org.lwjgl.opengl;

import java.io.InputStream;

public class GL11 extends net.PeytonPlayz585.opengl.GL11 {

	public static final int GL_GEQUAL = webgl.GEQUAL;
	public static final int GL_POINTS = 0;
	
	public static InputStream getResourceAsStream(String var1) {
		return loadResource(var1);
	}

	public static void glEnableClientState(int glTextureState) {
		glEnableVertexAttrib(glTextureState);
	}

	public static void glDisableClientState(int glTextureState) {
		glDisableVertexAttrib(glTextureState);
	}

}
