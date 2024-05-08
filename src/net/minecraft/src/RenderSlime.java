package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderSlime extends RenderLiving {
	private ModelBase scaleAmount;

	public RenderSlime(ModelBase var1, ModelBase var2, float var3) {
		super(var1, var3);
		this.scaleAmount = var2;
	}

	protected boolean renderSlimePassModel(EntitySlime var1, int var2, float var3) {
		if(var2 == 0) {
			this.setRenderPassModel(this.scaleAmount);
			GL11.glEnable(GL11.GL_NORMALIZE);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			return true;
		} else {
			if(var2 == 1) {
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			}

			return false;
		}
	}

	protected void scaleSlime(EntitySlime var1, float var2) {
		int var3 = var1.getSlimeSize();
		float var4 = (var1.field_767_b + (var1.field_768_a - var1.field_767_b) * var2) / ((float)var3 * 0.5F + 1.0F);
		float var5 = 1.0F / (var4 + 1.0F);
		float var6 = (float)var3;
		GL11.glScalef(var5 * var6, 1.0F / var5 * var6, var5 * var6);
	}

	protected void preRenderCallback(EntityLiving var1, float var2) {
		this.scaleSlime((EntitySlime)var1, var2);
	}

	protected boolean shouldRenderPass(EntityLiving var1, int var2, float var3) {
		return this.renderSlimePassModel((EntitySlime)var1, var2, var3);
	}
}
