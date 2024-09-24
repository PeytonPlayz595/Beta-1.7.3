package net.minecraft.src;

import net.PeytonPlayz585.opengl.GL11;
import net.PeytonPlayz585.textures.TextureLocation;

public class RenderTNTPrimed extends Render {
	
	private static final TextureLocation terrainTexture = new TextureLocation("/terrain.png");
	
	private RenderBlocks blockRenderer = new RenderBlocks();

	public RenderTNTPrimed() {
		this.shadowSize = 0.5F;
	}

	public void func_153_a(EntityTNTPrimed var1, double var2, double var4, double var6, float var8, float var9) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)var2, (float)var4, (float)var6);
		float var10;
		if((float)var1.fuse - var9 + 1.0F < 10.0F) {
			var10 = 1.0F - ((float)var1.fuse - var9 + 1.0F) / 10.0F;
			if(var10 < 0.0F) {
				var10 = 0.0F;
			}

			if(var10 > 1.0F) {
				var10 = 1.0F;
			}

			var10 *= var10;
			var10 *= var10;
			float var11 = 1.0F + var10 * 0.3F;
			GL11.glScalef(var11, var11, var11);
		}

		var10 = (1.0F - ((float)var1.fuse - var9 + 1.0F) / 100.0F) * 0.8F;
		terrainTexture.bindTexture();
		this.blockRenderer.renderBlockOnInventory(Block.tnt, 0, var1.getEntityBrightness(var9));
		if(var1.fuse / 5 % 2 == 0) {
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, var10);
			this.blockRenderer.renderBlockOnInventory(Block.tnt, 0, 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}

		GL11.glPopMatrix();
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		this.func_153_a((EntityTNTPrimed)var1, var2, var4, var6, var8, var9);
	}
	
	@Override
	protected boolean loadDownloadableImageTexture(String s, String s1) {
		return true;
	}
}
