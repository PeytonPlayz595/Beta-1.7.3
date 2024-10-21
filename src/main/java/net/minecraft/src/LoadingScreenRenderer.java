package net.minecraft.src;

import net.PeytonPlayz585.opengl.Display;
import net.PeytonPlayz585.opengl.GL11;
import net.minecraft.client.Minecraft;

public class LoadingScreenRenderer implements IProgressUpdate {
	private String field_1004_a = "";
	private Minecraft mc;
	private String field_1007_c = "";
	private long field_1006_d = System.currentTimeMillis();
	private boolean field_1005_e = false;

	public LoadingScreenRenderer(Minecraft var1) {
		this.mc = var1;
	}

	public void printText(String var1) {
		this.field_1005_e = false;
		this.func_597_c(var1);
	}

	public void func_594_b(String var1) {
		this.field_1005_e = true;
		this.func_597_c(this.field_1007_c);
	}

	public void func_597_c(String var1) {
		if(!this.mc.running) {
			if(!this.field_1005_e) {
				throw new MinecraftError();
			}
		} else {
			this.field_1007_c = var1;
			ScaledResolution var2 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
			GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0.0D, var2.field_25121_a, var2.field_25120_b, 0.0D, 100.0D, 300.0D);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();
			GL11.glTranslatef(0.0F, 0.0F, -200.0F);
		}
	}
	
	public void displayLoadingString(String s, String s1) {
		if (!mc.running) {
			if (field_1005_e) {
				return;
			} else {
				throw new MinecraftError();
			}
		} else {
			field_1006_d = 0L;
			field_1004_a = s1;
			field_1007_c = s;
			setLoadingProgress(-1);
			field_1006_d = 0L;
			return;
		}
	}

	public void displayLoadingString(String var1) {
		if(!this.mc.running) {
			if(!this.field_1005_e) {
				throw new MinecraftError();
			}
		} else {
			this.field_1006_d = 0L;
			this.field_1004_a = var1;
			this.setLoadingProgress(-1);
			this.field_1006_d = 0L;
		}
	}

	public void setLoadingProgress(int var1) {
		if(!this.mc.running) {
			if(!this.field_1005_e) {
				throw new MinecraftError();
			}
		} else {
			long var2 = System.currentTimeMillis();
			if(var2 - this.field_1006_d >= 20L) {
				this.field_1006_d = var2;
				ScaledResolution var4 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
				int var5 = var4.getScaledWidth();
				int var6 = var4.getScaledHeight();
				GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
				GL11.glMatrixMode(GL11.GL_PROJECTION);
				GL11.glLoadIdentity();
				GL11.glOrtho(0.0D, var4.field_25121_a, var4.field_25120_b, 0.0D, 100.0D, 300.0D);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glLoadIdentity();
				GL11.glTranslatef(0.0F, 0.0F, -200.0F);
				GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);
				Tessellator var7 = Tessellator.instance;
				int var8 = this.mc.renderEngine.getTexture("/gui/background.png");
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, var8);
				float var9 = 32.0F;
				var7.startDrawingQuads();
				var7.setColorOpaque_I(4210752);
				var7.addVertexWithUV(0.0D, (double)var6, 0.0D, 0.0D, (double)((float)var6 / var9));
				var7.addVertexWithUV((double)var5, (double)var6, 0.0D, (double)((float)var5 / var9), (double)((float)var6 / var9));
				var7.addVertexWithUV((double)var5, 0.0D, 0.0D, (double)((float)var5 / var9), 0.0D);
				var7.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
				var7.draw();
				if(var1 >= 0) {
					byte var10 = 100;
					byte var11 = 2;
					int var12 = var5 / 2 - var10 / 2;
					int var13 = var6 / 2 + 16;
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					var7.startDrawingQuads();
					var7.setColorOpaque_I(8421504);
					var7.addVertex((double)var12, (double)var13, 0.0D);
					var7.addVertex((double)var12, (double)(var13 + var11), 0.0D);
					var7.addVertex((double)(var12 + var10), (double)(var13 + var11), 0.0D);
					var7.addVertex((double)(var12 + var10), (double)var13, 0.0D);
					var7.setColorOpaque_I(8454016);
					var7.addVertex((double)var12, (double)var13, 0.0D);
					var7.addVertex((double)var12, (double)(var13 + var11), 0.0D);
					var7.addVertex((double)(var12 + var1), (double)(var13 + var11), 0.0D);
					var7.addVertex((double)(var12 + var1), (double)var13, 0.0D);
					var7.draw();
					GL11.glEnable(GL11.GL_TEXTURE_2D);
				}

				this.mc.fontRenderer.drawStringWithShadow(this.field_1007_c, (var5 - this.mc.fontRenderer.getStringWidth(this.field_1007_c)) / 2, var6 / 2 - 4 - 16, 16777215);
				this.mc.fontRenderer.drawStringWithShadow(this.field_1004_a, (var5 - this.mc.fontRenderer.getStringWidth(this.field_1004_a)) / 2, var6 / 2 - 4 + 8, 16777215);
				Display.update();
			}
		}
	}
}
