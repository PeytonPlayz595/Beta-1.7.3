package net.minecraft.src;

import net.PeytonPlayz585.opengl.GL11;

public class RenderGiantZombie extends RenderLiving {
	private float scale;

	public RenderGiantZombie(ModelBase var1, float var2, float var3) {
		super(var1, var2 * var3);
		this.scale = var3;
	}

	protected void preRenderScale(EntityGiantZombie var1, float var2) {
		GL11.glScalef(this.scale, this.scale, this.scale);
	}

	protected void preRenderCallback(EntityLiving var1, float var2) {
		this.preRenderScale((EntityGiantZombie)var1, var2);
	}

	@Override
	protected boolean loadDownloadableImageTexture(String s, String s1) {
		return true;
	}
}
