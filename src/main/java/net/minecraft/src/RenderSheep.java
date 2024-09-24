package net.minecraft.src;

import net.PeytonPlayz585.opengl.GL11;
import net.PeytonPlayz585.textures.TextureLocation;

public class RenderSheep extends RenderLiving {
	
	private static final TextureLocation sheepTexture = new TextureLocation("/mob/sheep.png");
	private static final TextureLocation sheepFurTexture = new TextureLocation("/mob/sheep_fur.png");
	
	public RenderSheep(ModelBase var1, ModelBase var2, float var3) {
		super(var1, var3);
		this.setRenderPassModel(var2);
	}

	protected boolean setWoolColorAndRender(EntitySheep var1, int var2, float var3) {
		if(var2 == 0 && !var1.getSheared()) {
			sheepFurTexture.bindTexture();
			float var4 = var1.getEntityBrightness(var3);
			int var5 = var1.getFleeceColor();
			GL11.glColor3f(var4 * EntitySheep.fleeceColorTable[var5][0], var4 * EntitySheep.fleeceColorTable[var5][1], var4 * EntitySheep.fleeceColorTable[var5][2]);
			return true;
		} else {
			return false;
		}
	}

	protected boolean shouldRenderPass(EntityLiving var1, int var2, float var3) {
		return this.setWoolColorAndRender((EntitySheep)var1, var2, var3);
	}
	
	@Override
	protected boolean loadDownloadableImageTexture(String s, String s1) {
		sheepTexture.bindTexture();
		return true;
	}
}
