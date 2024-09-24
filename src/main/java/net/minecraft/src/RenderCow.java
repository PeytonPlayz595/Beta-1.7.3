package net.minecraft.src;

import net.PeytonPlayz585.textures.TextureLocation;

public class RenderCow extends RenderLiving {
	public RenderCow(ModelBase var1, float var2) {
		super(var1, var2);
	}

	public void renderCow(EntityCow var1, double var2, double var4, double var6, float var8, float var9) {
		super.doRenderLiving(var1, var2, var4, var6, var8, var9);
	}

	public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
		this.renderCow((EntityCow)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		this.renderCow((EntityCow)var1, var2, var4, var6, var8, var9);
	}
	
	private static final TextureLocation texture = new TextureLocation("/mob/cow.png");

	@Override
	protected boolean loadDownloadableImageTexture(String s, String s1) {
		texture.bindTexture();
		return true;
	}
}
