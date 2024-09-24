package net.minecraft.src;

import net.PeytonPlayz585.opengl.GL11;
import net.PeytonPlayz585.textures.TextureLocation;

public class RenderFallingSand extends Render {
	private RenderBlocks field_197_d = new RenderBlocks();
	
	private static final TextureLocation terrainTexture = new TextureLocation("/terrain.png");

	public RenderFallingSand() {
		this.shadowSize = 0.5F;
	}

	public void doRenderFallingSand(EntityFallingSand var1, double var2, double var4, double var6, float var8, float var9) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)var2, (float)var4, (float)var6);
		terrainTexture.bindTexture();
		Block var10 = Block.blocksList[var1.blockID];
		World var11 = var1.getWorld();
		GL11.glDisable(GL11.GL_LIGHTING);
		this.field_197_d.renderBlockFallingSand(var10, var11, MathHelper.floor_double(var1.posX), MathHelper.floor_double(var1.posY), MathHelper.floor_double(var1.posZ));
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		this.doRenderFallingSand((EntityFallingSand)var1, var2, var4, var6, var8, var9);
	}
	
	@Override
	protected boolean loadDownloadableImageTexture(String s, String s1) {
		return true;
	}
}
