package net.minecraft.src;

public class EntityNoteFX extends EntityFX {
	float field_21065_a;

	public EntityNoteFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
		this(var1, var2, var4, var6, var8, var10, var12, 2.0F);
	}

	public EntityNoteFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, float var14) {
		super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
		this.motionX *= (double)0.01F;
		this.motionY *= (double)0.01F;
		this.motionZ *= (double)0.01F;
		this.motionY += 0.2D;
		this.particleRed = MathHelper.sin(((float)var8 + 0.0F) * (float)Math.PI * 2.0F) * 0.65F + 0.35F;
		this.particleGreen = MathHelper.sin(((float)var8 + 1.0F / 3.0F) * (float)Math.PI * 2.0F) * 0.65F + 0.35F;
		this.particleBlue = MathHelper.sin(((float)var8 + 2.0F / 3.0F) * (float)Math.PI * 2.0F) * 0.65F + 0.35F;
		this.particleScale *= 12.0F / 16.0F;
		this.particleScale *= var14;
		this.field_21065_a = this.particleScale;
		this.particleMaxAge = 6;
		this.noClip = false;
		this.particleTextureIndex = 64;
	}

	public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
		float var8 = ((float)this.particleAge + var2) / (float)this.particleMaxAge * 32.0F;
		if(var8 < 0.0F) {
			var8 = 0.0F;
		}

		if(var8 > 1.0F) {
			var8 = 1.0F;
		}

		this.particleScale = this.field_21065_a * var8;
		super.renderParticle(var1, var2, var3, var4, var5, var6, var7);
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if(this.particleAge++ >= this.particleMaxAge) {
			this.setEntityDead();
		}

		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		if(this.posY == this.prevPosY) {
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= (double)0.66F;
		this.motionY *= (double)0.66F;
		this.motionZ *= (double)0.66F;
		if(this.onGround) {
			this.motionX *= (double)0.7F;
			this.motionZ *= (double)0.7F;
		}

	}
}
