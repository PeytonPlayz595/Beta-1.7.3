package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class EntityFootStepFX extends EntityFX {
	private int field_27018_a = 0;
	private int field_27020_o = 0;
	private RenderEngine field_27019_p;

	public EntityFootStepFX(RenderEngine var1, World var2, double var3, double var5, double var7) {
		super(var2, var3, var5, var7, 0.0D, 0.0D, 0.0D);
		this.field_27019_p = var1;
		this.motionX = this.motionY = this.motionZ = 0.0D;
		this.field_27020_o = 200;
	}

	public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
		float var8 = ((float)this.field_27018_a + var2) / (float)this.field_27020_o;
		var8 *= var8;
		float var9 = 2.0F - var8 * 2.0F;
		if(var9 > 1.0F) {
			var9 = 1.0F;
		}

		var9 *= 0.2F;
		GL11.glDisable(GL11.GL_LIGHTING);
		float var10 = 2.0F / 16.0F;
		float var11 = (float)(this.posX - interpPosX);
		float var12 = (float)(this.posY - interpPosY);
		float var13 = (float)(this.posZ - interpPosZ);
		float var14 = this.worldObj.getLightBrightness(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
		this.field_27019_p.bindTexture(this.field_27019_p.getTexture("/misc/footprint.png"));
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		var1.startDrawingQuads();
		var1.setColorRGBA_F(var14, var14, var14, var9);
		var1.addVertexWithUV((double)(var11 - var10), (double)var12, (double)(var13 + var10), 0.0D, 1.0D);
		var1.addVertexWithUV((double)(var11 + var10), (double)var12, (double)(var13 + var10), 1.0D, 1.0D);
		var1.addVertexWithUV((double)(var11 + var10), (double)var12, (double)(var13 - var10), 1.0D, 0.0D);
		var1.addVertexWithUV((double)(var11 - var10), (double)var12, (double)(var13 - var10), 0.0D, 0.0D);
		var1.draw();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
	}

	public void onUpdate() {
		++this.field_27018_a;
		if(this.field_27018_a == this.field_27020_o) {
			this.setEntityDead();
		}

	}

	public int getFXLayer() {
		return 3;
	}
}
