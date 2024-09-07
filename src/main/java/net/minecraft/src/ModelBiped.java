package net.minecraft.src;

public class ModelBiped extends ModelBase {
	public ModelRenderer bipedHead;
	public ModelRenderer bipedHeadwear;
	public ModelRenderer bipedBody;
	public ModelRenderer bipedRightArm;
	public ModelRenderer bipedLeftArm;
	public ModelRenderer bipedRightLeg;
	public ModelRenderer bipedLeftLeg;
	public ModelRenderer bipedEars;
	public ModelRenderer bipedCloak;
	public boolean field_1279_h;
	public boolean field_1278_i;
	public boolean isSneak;

	public ModelBiped() {
		this(0.0F);
	}

	public ModelBiped(float var1) {
		this(var1, 0.0F);
	}

	public ModelBiped(float var1, float var2) {
		this.field_1279_h = false;
		this.field_1278_i = false;
		this.isSneak = false;
		this.bipedCloak = new ModelRenderer(0, 0);
		this.bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, var1);
		this.bipedEars = new ModelRenderer(24, 0);
		this.bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, var1);
		this.bipedHead = new ModelRenderer(0, 0);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, var1);
		this.bipedHead.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
		this.bipedHeadwear = new ModelRenderer(32, 0);
		this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, var1 + 0.5F);
		this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
		this.bipedBody = new ModelRenderer(16, 16);
		this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, var1);
		this.bipedBody.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
		this.bipedRightArm = new ModelRenderer(40, 16);
		this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, var1);
		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + var2, 0.0F);
		this.bipedLeftArm = new ModelRenderer(40, 16);
		this.bipedLeftArm.mirror = true;
		this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, var1);
		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + var2, 0.0F);
		this.bipedRightLeg = new ModelRenderer(0, 16);
		this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, var1);
		this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F + var2, 0.0F);
		this.bipedLeftLeg = new ModelRenderer(0, 16);
		this.bipedLeftLeg.mirror = true;
		this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, var1);
		this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F + var2, 0.0F);
	}

	public void render(float var1, float var2, float var3, float var4, float var5, float var6) {
		this.setRotationAngles(var1, var2, var3, var4, var5, var6);
		this.bipedHead.render(var6);
		this.bipedBody.render(var6);
		this.bipedRightArm.render(var6);
		this.bipedLeftArm.render(var6);
		this.bipedRightLeg.render(var6);
		this.bipedLeftLeg.render(var6);
		this.bipedHeadwear.render(var6);
	}

	public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
		this.bipedHead.rotateAngleY = var4 / (180.0F / (float)Math.PI);
		this.bipedHead.rotateAngleX = var5 / (180.0F / (float)Math.PI);
		this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
		this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
		this.bipedRightArm.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float)Math.PI) * 2.0F * var2 * 0.5F;
		this.bipedLeftArm.rotateAngleX = MathHelper.cos(var1 * 0.6662F) * 2.0F * var2 * 0.5F;
		this.bipedRightArm.rotateAngleZ = 0.0F;
		this.bipedLeftArm.rotateAngleZ = 0.0F;
		this.bipedRightLeg.rotateAngleX = MathHelper.cos(var1 * 0.6662F) * 1.4F * var2;
		this.bipedLeftLeg.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float)Math.PI) * 1.4F * var2;
		this.bipedRightLeg.rotateAngleY = 0.0F;
		this.bipedLeftLeg.rotateAngleY = 0.0F;
		if(this.isRiding) {
			this.bipedRightArm.rotateAngleX += (float)Math.PI * -0.2F;
			this.bipedLeftArm.rotateAngleX += (float)Math.PI * -0.2F;
			this.bipedRightLeg.rotateAngleX = (float)Math.PI * -0.4F;
			this.bipedLeftLeg.rotateAngleX = (float)Math.PI * -0.4F;
			this.bipedRightLeg.rotateAngleY = (float)Math.PI * 0.1F;
			this.bipedLeftLeg.rotateAngleY = (float)Math.PI * -0.1F;
		}

		if(this.field_1279_h) {
			this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - (float)Math.PI * 0.1F;
		}

		if(this.field_1278_i) {
			this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - (float)Math.PI * 0.1F;
		}

		this.bipedRightArm.rotateAngleY = 0.0F;
		this.bipedLeftArm.rotateAngleY = 0.0F;
		if(this.onGround > -9990.0F) {
			float var7 = this.onGround;
			this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(var7) * (float)Math.PI * 2.0F) * 0.2F;
			this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
			this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
			this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
			var7 = 1.0F - this.onGround;
			var7 *= var7;
			var7 *= var7;
			var7 = 1.0F - var7;
			float var8 = MathHelper.sin(var7 * (float)Math.PI);
			float var9 = MathHelper.sin(this.onGround * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * (12.0F / 16.0F);
			this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX - ((double)var8 * 1.2D + (double)var9));
			this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
			this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.4F;
		}

		if(this.isSneak) {
			this.bipedBody.rotateAngleX = 0.5F;
			this.bipedRightLeg.rotateAngleX -= 0.0F;
			this.bipedLeftLeg.rotateAngleX -= 0.0F;
			this.bipedRightArm.rotateAngleX += 0.4F;
			this.bipedLeftArm.rotateAngleX += 0.4F;
			this.bipedRightLeg.rotationPointZ = 4.0F;
			this.bipedLeftLeg.rotationPointZ = 4.0F;
			this.bipedRightLeg.rotationPointY = 9.0F;
			this.bipedLeftLeg.rotationPointY = 9.0F;
			this.bipedHead.rotationPointY = 1.0F;
		} else {
			this.bipedBody.rotateAngleX = 0.0F;
			this.bipedRightLeg.rotationPointZ = 0.0F;
			this.bipedLeftLeg.rotationPointZ = 0.0F;
			this.bipedRightLeg.rotationPointY = 12.0F;
			this.bipedLeftLeg.rotationPointY = 12.0F;
			this.bipedHead.rotationPointY = 0.0F;
		}

		this.bipedRightArm.rotateAngleZ += MathHelper.cos(var3 * 0.09F) * 0.05F + 0.05F;
		this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(var3 * 0.09F) * 0.05F + 0.05F;
		this.bipedRightArm.rotateAngleX += MathHelper.sin(var3 * 0.067F) * 0.05F;
		this.bipedLeftArm.rotateAngleX -= MathHelper.sin(var3 * 0.067F) * 0.05F;
	}

	public void renderEars(float var1) {
		this.bipedEars.rotateAngleY = this.bipedHead.rotateAngleY;
		this.bipedEars.rotateAngleX = this.bipedHead.rotateAngleX;
		this.bipedEars.rotationPointX = 0.0F;
		this.bipedEars.rotationPointY = 0.0F;
		this.bipedEars.render(var1);
	}

	public void renderCloak(float var1) {
		this.bipedCloak.render(var1);
	}
}
