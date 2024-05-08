package net.minecraft.src;

import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;

public class RenderHelper {
	private static FloatBuffer field_1695_a = GLAllocation.createDirectFloatBuffer(16);

	public static void disableStandardItemLighting() {
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_LIGHT0);
		GL11.glDisable(GL11.GL_LIGHT1);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
	}

	public static void enableStandardItemLighting() {
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glEnable(GL11.GL_LIGHT1);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glColorMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE);
		float var0 = 0.4F;
		float var1 = 0.6F;
		float var2 = 0.0F;
		Vec3D var3 = Vec3D.createVector((double)0.2F, 1.0D, (double)-0.7F).normalize();
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, func_1157_a(var3.xCoord, var3.yCoord, var3.zCoord, 0.0D));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, func_1156_a(var1, var1, var1, 1.0F));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, func_1156_a(0.0F, 0.0F, 0.0F, 1.0F));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, func_1156_a(var2, var2, var2, 1.0F));
		var3 = Vec3D.createVector((double)-0.2F, 1.0D, (double)0.7F).normalize();
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, func_1157_a(var3.xCoord, var3.yCoord, var3.zCoord, 0.0D));
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, func_1156_a(var1, var1, var1, 1.0F));
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, func_1156_a(0.0F, 0.0F, 0.0F, 1.0F));
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, func_1156_a(var2, var2, var2, 1.0F));
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, func_1156_a(var0, var0, var0, 1.0F));
	}

	private static FloatBuffer func_1157_a(double var0, double var2, double var4, double var6) {
		return func_1156_a((float)var0, (float)var2, (float)var4, (float)var6);
	}

	private static FloatBuffer func_1156_a(float var0, float var1, float var2, float var3) {
		field_1695_a.clear();
		field_1695_a.put(var0).put(var1).put(var2).put(var3);
		field_1695_a.flip();
		return field_1695_a;
	}
}
