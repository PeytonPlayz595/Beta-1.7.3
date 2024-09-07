package net.minecraft.src;

import java.util.List;

public class EntityFireball extends Entity {
	private int field_9402_e = -1;
	private int field_9401_f = -1;
	private int field_9400_g = -1;
	private int field_9399_h = 0;
	private boolean field_9398_i = false;
	public int field_9406_a = 0;
	public EntityLiving field_9397_j;
	private int field_9396_k;
	private int field_9395_l = 0;
	public double field_9405_b;
	public double field_9404_c;
	public double field_9403_d;

	public EntityFireball(World var1) {
		super(var1);
		this.setSize(1.0F, 1.0F);
	}

	protected void entityInit() {
	}

	public boolean isInRangeToRenderDist(double var1) {
		double var3 = this.boundingBox.getAverageEdgeLength() * 4.0D;
		var3 *= 64.0D;
		return var1 < var3 * var3;
	}

	public EntityFireball(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
		super(var1);
		this.setSize(1.0F, 1.0F);
		this.setLocationAndAngles(var2, var4, var6, this.rotationYaw, this.rotationPitch);
		this.setPosition(var2, var4, var6);
		double var14 = (double)MathHelper.sqrt_double(var8 * var8 + var10 * var10 + var12 * var12);
		this.field_9405_b = var8 / var14 * 0.1D;
		this.field_9404_c = var10 / var14 * 0.1D;
		this.field_9403_d = var12 / var14 * 0.1D;
	}

	public EntityFireball(World var1, EntityLiving var2, double var3, double var5, double var7) {
		super(var1);
		this.field_9397_j = var2;
		this.setSize(1.0F, 1.0F);
		this.setLocationAndAngles(var2.posX, var2.posY, var2.posZ, var2.rotationYaw, var2.rotationPitch);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = this.motionY = this.motionZ = 0.0D;
		var3 += this.rand.nextGaussian() * 0.4D;
		var5 += this.rand.nextGaussian() * 0.4D;
		var7 += this.rand.nextGaussian() * 0.4D;
		double var9 = (double)MathHelper.sqrt_double(var3 * var3 + var5 * var5 + var7 * var7);
		this.field_9405_b = var3 / var9 * 0.1D;
		this.field_9404_c = var5 / var9 * 0.1D;
		this.field_9403_d = var7 / var9 * 0.1D;
	}

	public void onUpdate() {
		super.onUpdate();
		this.fire = 10;
		if(this.field_9406_a > 0) {
			--this.field_9406_a;
		}

		if(this.field_9398_i) {
			int var1 = this.worldObj.getBlockId(this.field_9402_e, this.field_9401_f, this.field_9400_g);
			if(var1 == this.field_9399_h) {
				++this.field_9396_k;
				if(this.field_9396_k == 1200) {
					this.setEntityDead();
				}

				return;
			}

			this.field_9398_i = false;
			this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
			this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
			this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
			this.field_9396_k = 0;
			this.field_9395_l = 0;
		} else {
			++this.field_9395_l;
		}

		Vec3D var15 = Vec3D.createVector(this.posX, this.posY, this.posZ);
		Vec3D var2 = Vec3D.createVector(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var15, var2);
		var15 = Vec3D.createVector(this.posX, this.posY, this.posZ);
		var2 = Vec3D.createVector(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		if(var3 != null) {
			var2 = Vec3D.createVector(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
		}

		Entity var4 = null;
		List var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
		double var6 = 0.0D;

		for(int var8 = 0; var8 < var5.size(); ++var8) {
			Entity var9 = (Entity)var5.get(var8);
			if(var9.canBeCollidedWith() && (var9 != this.field_9397_j || this.field_9395_l >= 25)) {
				float var10 = 0.3F;
				AxisAlignedBB var11 = var9.boundingBox.expand((double)var10, (double)var10, (double)var10);
				MovingObjectPosition var12 = var11.func_1169_a(var15, var2);
				if(var12 != null) {
					double var13 = var15.distanceTo(var12.hitVec);
					if(var13 < var6 || var6 == 0.0D) {
						var4 = var9;
						var6 = var13;
					}
				}
			}
		}

		if(var4 != null) {
			var3 = new MovingObjectPosition(var4);
		}

		if(var3 != null) {
			if(!this.worldObj.multiplayerWorld) {
				if(var3.entityHit != null && var3.entityHit.attackEntityFrom(this.field_9397_j, 0)) {
				}

				this.worldObj.newExplosion((Entity)null, this.posX, this.posY, this.posZ, 1.0F, true);
			}

			this.setEntityDead();
		}

		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		float var16 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / (double)((float)Math.PI));

		for(this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var16) * 180.0D / (double)((float)Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
		}

		while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
			this.prevRotationPitch += 360.0F;
		}

		while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
			this.prevRotationYaw -= 360.0F;
		}

		while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
			this.prevRotationYaw += 360.0F;
		}

		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		float var17 = 0.95F;
		if(this.isInWater()) {
			for(int var18 = 0; var18 < 4; ++var18) {
				float var19 = 0.25F;
				this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)var19, this.posY - this.motionY * (double)var19, this.posZ - this.motionZ * (double)var19, this.motionX, this.motionY, this.motionZ);
			}

			var17 = 0.8F;
		}

		this.motionX += this.field_9405_b;
		this.motionY += this.field_9404_c;
		this.motionZ += this.field_9403_d;
		this.motionX *= (double)var17;
		this.motionY *= (double)var17;
		this.motionZ *= (double)var17;
		this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
		this.setPosition(this.posX, this.posY, this.posZ);
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		var1.setShort("xTile", (short)this.field_9402_e);
		var1.setShort("yTile", (short)this.field_9401_f);
		var1.setShort("zTile", (short)this.field_9400_g);
		var1.setByte("inTile", (byte)this.field_9399_h);
		var1.setByte("shake", (byte)this.field_9406_a);
		var1.setByte("inGround", (byte)(this.field_9398_i ? 1 : 0));
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		this.field_9402_e = var1.getShort("xTile");
		this.field_9401_f = var1.getShort("yTile");
		this.field_9400_g = var1.getShort("zTile");
		this.field_9399_h = var1.getByte("inTile") & 255;
		this.field_9406_a = var1.getByte("shake") & 255;
		this.field_9398_i = var1.getByte("inGround") == 1;
	}

	public boolean canBeCollidedWith() {
		return true;
	}

	public float getCollisionBorderSize() {
		return 1.0F;
	}

	public boolean attackEntityFrom(Entity var1, int var2) {
		this.setBeenAttacked();
		if(var1 != null) {
			Vec3D var3 = var1.getLookVec();
			if(var3 != null) {
				this.motionX = var3.xCoord;
				this.motionY = var3.yCoord;
				this.motionZ = var3.zCoord;
				this.field_9405_b = this.motionX * 0.1D;
				this.field_9404_c = this.motionY * 0.1D;
				this.field_9403_d = this.motionZ * 0.1D;
			}

			return true;
		} else {
			return false;
		}
	}

	public float getShadowSize() {
		return 0.0F;
	}
}
