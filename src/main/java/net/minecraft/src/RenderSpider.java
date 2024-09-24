package net.minecraft.src;

import net.PeytonPlayz585.opengl.GL11;
import net.PeytonPlayz585.textures.TextureLocation;

public class RenderSpider extends RenderLiving {
	
	private static final TextureLocation spiderTexture = new TextureLocation("/mob/spider.png");
	private static final TextureLocation spiderEyesTexture = new TextureLocation("/mob/spider_eyes.png");
	
	public RenderSpider() {
		super(new ModelSpider(), 1.0F);
		this.setRenderPassModel(new ModelSpider());
	}

	protected float setSpiderDeathMaxRotation(EntitySpider var1) {
		return 180.0F;
	}

	protected boolean setSpiderEyeBrightness(EntitySpider var1, int var2, float var3) {
		if(var2 != 0) {
			return false;
		} else if(var2 != 0) {
			return false;
		} else {
			spiderEyesTexture.bindTexture();
			float var4 = (1.0F - var1.getEntityBrightness(1.0F)) * 0.5F;
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, var4);
			return true;
		}
	}

	protected float getDeathMaxRotation(EntityLiving var1) {
		return this.setSpiderDeathMaxRotation((EntitySpider)var1);
	}

	protected boolean shouldRenderPass(EntityLiving var1, int var2, float var3) {
		return this.setSpiderEyeBrightness((EntitySpider)var1, var2, var3);
	}
	
	@Override
	protected boolean loadDownloadableImageTexture(String s, String s1) {
		spiderTexture.bindTexture();
		return true;
	}
}
