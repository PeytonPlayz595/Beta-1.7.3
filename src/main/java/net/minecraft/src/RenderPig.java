package net.minecraft.src;

import net.PeytonPlayz585.textures.TextureLocation;

public class RenderPig extends RenderLiving {
	
	private static final TextureLocation saddleTexture = new TextureLocation("/mob/saddle.png");
	private static final TextureLocation pigTexture = new TextureLocation("/mob/pig.png");
	
	public RenderPig(ModelBase var1, ModelBase var2, float var3) {
		super(var1, var3);
		this.setRenderPassModel(var2);
	}

	protected boolean renderSaddledPig(EntityPig var1, int var2, float var3) {
		saddleTexture.bindTexture();
		return var2 == 0 && var1.getSaddled();
	}

	protected boolean shouldRenderPass(EntityLiving var1, int var2, float var3) {
		return this.renderSaddledPig((EntityPig)var1, var2, var3);
	}
	
	@Override
	protected boolean loadDownloadableImageTexture(String s, String s1) {
		pigTexture.bindTexture();
		return true;
	}
}
