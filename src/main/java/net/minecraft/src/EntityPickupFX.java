package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class EntityPickupFX extends EntityFX {
	private Entity field_675_a;
	private Entity field_679_o;
	private int field_678_p = 0;
	private int field_677_q = 0;
	private float field_676_r;

	public EntityPickupFX(World var1, Entity var2, Entity var3, float var4) {
		super(var1, var2.posX, var2.posY, var2.posZ, var2.motionX, var2.motionY, var2.motionZ);
		this.field_675_a = var2;
		this.field_679_o = var3;
		this.field_677_q = 3;
		this.field_676_r = var4;
	}

	public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
		float var8 = ((float)this.field_678_p + var2) / (float)this.field_677_q;
		var8 *= var8;
		double var9 = this.field_675_a.posX;
		double var11 = this.field_675_a.posY;
		double var13 = this.field_675_a.posZ;
		double var15 = this.field_679_o.lastTickPosX + (this.field_679_o.posX - this.field_679_o.lastTickPosX) * (double)var2;
		double var17 = this.field_679_o.lastTickPosY + (this.field_679_o.posY - this.field_679_o.lastTickPosY) * (double)var2 + (double)this.field_676_r;
		double var19 = this.field_679_o.lastTickPosZ + (this.field_679_o.posZ - this.field_679_o.lastTickPosZ) * (double)var2;
		double var21 = var9 + (var15 - var9) * (double)var8;
		double var23 = var11 + (var17 - var11) * (double)var8;
		double var25 = var13 + (var19 - var13) * (double)var8;
		int var27 = MathHelper.floor_double(var21);
		int var28 = MathHelper.floor_double(var23 + (double)(this.yOffset / 2.0F));
		int var29 = MathHelper.floor_double(var25);
		float var30 = this.worldObj.getLightBrightness(var27, var28, var29);
		var21 -= interpPosX;
		var23 -= interpPosY;
		var25 -= interpPosZ;
		GL11.glColor4f(var30, var30, var30, 1.0F);
		RenderManager.instance.renderEntityWithPosYaw(this.field_675_a, (double)((float)var21), (double)((float)var23), (double)((float)var25), this.field_675_a.rotationYaw, var2);
	}

	public void onUpdate() {
		++this.field_678_p;
		if(this.field_678_p == this.field_677_q) {
			this.setEntityDead();
		}

	}

	public int getFXLayer() {
		return 3;
	}
}
