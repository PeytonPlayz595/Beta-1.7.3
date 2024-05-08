package net.minecraft.src;

public class EntityReddustFX extends EntityFX {
	float field_673_a;

	public EntityReddustFX(World var1, double var2, double var4, double var6, float var8, float var9, float var10) {
		this(var1, var2, var4, var6, 1.0F, var8, var9, var10);
	}

	public EntityReddustFX(World var1, double var2, double var4, double var6, float var8, float var9, float var10, float var11) {
		super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
		this.motionX *= (double)0.1F;
		this.motionY *= (double)0.1F;
		this.motionZ *= (double)0.1F;
		if(var9 == 0.0F) {
			var9 = 1.0F;
		}

		float var12 = (float)Math.random() * 0.4F + 0.6F;
		this.particleRed = ((float)(Math.random() * (double)0.2F) + 0.8F) * var9 * var12;
		this.particleGreen = ((float)(Math.random() * (double)0.2F) + 0.8F) * var10 * var12;
		this.particleBlue = ((float)(Math.random() * (double)0.2F) + 0.8F) * var11 * var12;
		this.particleScale *= 12.0F / 16.0F;
		this.particleScale *= var8;
		this.field_673_a = this.particleScale;
		this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
		this.particleMaxAge = (int)((float)this.particleMaxAge * var8);
		this.noClip = false;
	}

	public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
		float var8 = ((float)this.particleAge + var2) / (float)this.particleMaxAge * 32.0F;
		if(var8 < 0.0F) {
			var8 = 0.0F;
		}

		if(var8 > 1.0F) {
			var8 = 1.0F;
		}

		this.particleScale = this.field_673_a * var8;
		super.renderParticle(var1, var2, var3, var4, var5, var6, var7);
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if(this.particleAge++ >= this.particleMaxAge) {
			this.setEntityDead();
		}

		this.particleTextureIndex = 7 - this.particleAge * 8 / this.particleMaxAge;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		if(this.posY == this.prevPosY) {
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= (double)0.96F;
		this.motionY *= (double)0.96F;
		this.motionZ *= (double)0.96F;
		if(this.onGround) {
			this.motionX *= (double)0.7F;
			this.motionZ *= (double)0.7F;
		}

	}
}
