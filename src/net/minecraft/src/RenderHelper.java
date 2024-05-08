package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderHelper {
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
		GL11.glRotatef((float)var3.xCoord, (float)var3.yCoord, (float)var3.zCoord, 0.0F);
		GL11.glRotatef(var1, var1, var1, 1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(var2, var2, var2, 1.0F);
		GL11.glRotatef(var2, var0, var1, var2);
		var3 = Vec3D.createVector((double)-0.2F, 1.0D, (double)0.7F).normalize();
		GL11.glRotatef((float)var3.xCoord, (float)var3.yCoord, (float)var3.zCoord, 0.0F);
		GL11.glRotatef(var1, var1, var1, 1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(var2, var2, var2, 1.0F);
		GL11.glPopMatrix();
	}
}
