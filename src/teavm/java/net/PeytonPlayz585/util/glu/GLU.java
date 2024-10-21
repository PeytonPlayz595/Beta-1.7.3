package net.PeytonPlayz585.util.glu;

import net.PeytonPlayz585.opengl.GL11;
import net.lax1dude.eaglercraft.internal.buffer.FloatBuffer;

public class GLU {
	
	public static final void gluUnProject(float p1, float p2, float p3, FloatBuffer p4, FloatBuffer p5, int[] p6, FloatBuffer p7) {
		GL11.gluUnProject(p1, p2, p3, p4, p5, p6, p7);
	}
	
	public static final void gluPerspective(float fovy, float aspect, float zNear, float zFar) {
		GL11.gluPerspective(fovy, aspect, zNear, zFar);
	}
	
	public static final String gluErrorString(int p1) {
		return GL11.gluErrorString(p1);
	}
}
