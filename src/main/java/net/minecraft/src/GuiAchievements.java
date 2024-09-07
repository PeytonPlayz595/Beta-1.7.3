package net.minecraft.src;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiAchievements extends GuiScreen {
	private static final int field_27126_s = AchievementList.minDisplayColumn * 24 - 112;
	private static final int field_27125_t = AchievementList.minDisplayRow * 24 - 112;
	private static final int field_27124_u = AchievementList.maxDisplayColumn * 24 - 77;
	private static final int field_27123_v = AchievementList.maxDisplayRow * 24 - 77;
	protected int field_27121_a = 256;
	protected int field_27119_i = 202;
	protected int field_27118_j = 0;
	protected int field_27117_l = 0;
	protected double field_27116_m;
	protected double field_27115_n;
	protected double field_27114_o;
	protected double field_27113_p;
	protected double field_27112_q;
	protected double field_27111_r;
	private int field_27122_w = 0;
	private StatFileWriter field_27120_x;

	public GuiAchievements(StatFileWriter var1) {
		this.field_27120_x = var1;
		short var2 = 141;
		short var3 = 141;
		this.field_27116_m = this.field_27114_o = this.field_27112_q = (double)(AchievementList.openInventory.displayColumn * 24 - var2 / 2 - 12);
		this.field_27115_n = this.field_27113_p = this.field_27111_r = (double)(AchievementList.openInventory.displayRow * 24 - var3 / 2);
	}

	public void initGui() {
		this.controlList.clear();
		this.controlList.add(new GuiSmallButton(1, this.width / 2 + 24, this.height / 2 + 74, 80, 20, StatCollector.translateToLocal("gui.done")));
	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.id == 1) {
			this.mc.displayGuiScreen((GuiScreen)null);
			this.mc.setIngameFocus();
		}

		super.actionPerformed(var1);
	}

	protected void keyTyped(char var1, int var2) {
		if(var2 == this.mc.gameSettings.keyBindInventory.keyCode) {
			this.mc.displayGuiScreen((GuiScreen)null);
			this.mc.setIngameFocus();
		} else {
			super.keyTyped(var1, var2);
		}

	}

	public void drawScreen(int var1, int var2, float var3) {
		if(Mouse.isButtonDown(0)) {
			int var4 = (this.width - this.field_27121_a) / 2;
			int var5 = (this.height - this.field_27119_i) / 2;
			int var6 = var4 + 8;
			int var7 = var5 + 17;
			if((this.field_27122_w == 0 || this.field_27122_w == 1) && var1 >= var6 && var1 < var6 + 224 && var2 >= var7 && var2 < var7 + 155) {
				if(this.field_27122_w == 0) {
					this.field_27122_w = 1;
				} else {
					this.field_27114_o -= (double)(var1 - this.field_27118_j);
					this.field_27113_p -= (double)(var2 - this.field_27117_l);
					this.field_27112_q = this.field_27116_m = this.field_27114_o;
					this.field_27111_r = this.field_27115_n = this.field_27113_p;
				}

				this.field_27118_j = var1;
				this.field_27117_l = var2;
			}

			if(this.field_27112_q < (double)field_27126_s) {
				this.field_27112_q = (double)field_27126_s;
			}

			if(this.field_27111_r < (double)field_27125_t) {
				this.field_27111_r = (double)field_27125_t;
			}

			if(this.field_27112_q >= (double)field_27124_u) {
				this.field_27112_q = (double)(field_27124_u - 1);
			}

			if(this.field_27111_r >= (double)field_27123_v) {
				this.field_27111_r = (double)(field_27123_v - 1);
			}
		} else {
			this.field_27122_w = 0;
		}

		this.drawDefaultBackground();
		this.func_27109_b(var1, var2, var3);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		this.func_27110_k();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	public void updateScreen() {
		this.field_27116_m = this.field_27114_o;
		this.field_27115_n = this.field_27113_p;
		double var1 = this.field_27112_q - this.field_27114_o;
		double var3 = this.field_27111_r - this.field_27113_p;
		if(var1 * var1 + var3 * var3 < 4.0D) {
			this.field_27114_o += var1;
			this.field_27113_p += var3;
		} else {
			this.field_27114_o += var1 * 0.85D;
			this.field_27113_p += var3 * 0.85D;
		}

	}

	protected void func_27110_k() {
		int var1 = (this.width - this.field_27121_a) / 2;
		int var2 = (this.height - this.field_27119_i) / 2;
		this.fontRenderer.drawString("Achievements", var1 + 15, var2 + 5, 4210752);
	}

	protected void func_27109_b(int var1, int var2, float var3) {
		int var4 = MathHelper.floor_double(this.field_27116_m + (this.field_27114_o - this.field_27116_m) * (double)var3);
		int var5 = MathHelper.floor_double(this.field_27115_n + (this.field_27113_p - this.field_27115_n) * (double)var3);
		if(var4 < field_27126_s) {
			var4 = field_27126_s;
		}

		if(var5 < field_27125_t) {
			var5 = field_27125_t;
		}

		if(var4 >= field_27124_u) {
			var4 = field_27124_u - 1;
		}

		if(var5 >= field_27123_v) {
			var5 = field_27123_v - 1;
		}

		int var7 = this.mc.renderEngine.getTexture("/achievement/bg.png");
		int var8 = (this.width - this.field_27121_a) / 2;
		int var9 = (this.height - this.field_27119_i) / 2;
		int var10 = var8 + 16;
		int var11 = var9 + 17;
		this.zLevel = 0.0F;
		GL11.glClearDepth(0.0f);
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearDepth(1.0f);
		GL11.glDepthFunc(GL11.GL_GEQUAL);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, 0.0F, -200.0F);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		int var12 = var4 + 288 >> 4;
		int var13 = var5 + 288 >> 4;
		int var14 = (var4 + 288) % 16;
		int var15 = (var5 + 288) % 16;
		Random var21 = new Random();

		for(int var22 = 0; var22 * 16 - var15 < 155; ++var22) {
			float var23 = 0.6F - (float)(var13 + var22) / 25.0F * 0.3F;
			GL11.glColor4f(var23, var23, var23, 1.0F);

			for(int var24 = 0; var24 * 16 - var14 < 224; ++var24) {
				var21.setSeed((long)(1234 + var12 + var24));
				var21.nextInt();
				int var25 = var21.nextInt(1 + var13 + var22) + (var13 + var22) / 2;
				int var26 = Block.sand.blockIndexInTexture;
				if(var25 <= 37 && var13 + var22 != 35) {
					if(var25 == 22) {
						if(var21.nextInt(2) == 0) {
							var26 = Block.oreDiamond.blockIndexInTexture;
						} else {
							var26 = Block.oreRedstone.blockIndexInTexture;
						}
					} else if(var25 == 10) {
						var26 = Block.oreIron.blockIndexInTexture;
					} else if(var25 == 8) {
						var26 = Block.oreCoal.blockIndexInTexture;
					} else if(var25 > 4) {
						var26 = Block.stone.blockIndexInTexture;
					} else if(var25 > 0) {
						var26 = Block.dirt.blockIndexInTexture;
					}
				} else {
					var26 = Block.bedrock.blockIndexInTexture;
				}

				GL11.glBindTexture(this.mc.renderEngine.getTexture("/terrain.png"));
				this.drawTexturedModalRect(var10 + var24 * 16 - var14, var11 + var22 * 16 - var15, var26 % 16 << 4, var26 >> 4 << 4, 16, 16);
			}
		}

		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		int var16;
		int var17;
		int var33;
		int var38;
		for(var12 = 0; var12 < AchievementList.achievementList.size(); ++var12) {
			Achievement var28 = (Achievement)AchievementList.achievementList.get(var12);
			if(var28.parentAchievement != null) {
				var14 = var28.displayColumn * 24 - var4 + 11 + var10;
				var15 = var28.displayRow * 24 - var5 + 11 + var11;
				var16 = var28.parentAchievement.displayColumn * 24 - var4 + 11 + var10;
				var17 = var28.parentAchievement.displayRow * 24 - var5 + 11 + var11;
				boolean var18 = false;
				boolean var19 = this.field_27120_x.hasAchievementUnlocked(var28);
				boolean var20 = this.field_27120_x.func_27181_b(var28);
				var38 = Math.sin((double)(System.currentTimeMillis() % 600L) / 600.0D * Math.PI * 2.0D) > 0.6D ? 255 : 130;
				if(var19) {
					var33 = -9408400;
				} else if(var20) {
					var33 = '\uff00' + (var38 << 24);
				} else {
					var33 = -16777216;
				}

				this.func_27100_a(var14, var16, var15, var33);
				this.func_27099_b(var16, var15, var17, var33);
			}
		}

		Achievement var27 = null;
		RenderItem var29 = new RenderItem();
		GL11.glPushMatrix();
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);

		int var34;
		for(var14 = 0; var14 < AchievementList.achievementList.size(); ++var14) {
			Achievement var30 = (Achievement)AchievementList.achievementList.get(var14);
			var16 = var30.displayColumn * 24 - var4;
			var17 = var30.displayRow * 24 - var5;
			if(var16 >= -24 && var17 >= -24 && var16 <= 224 && var17 <= 155) {
				float var35;
				if(this.field_27120_x.hasAchievementUnlocked(var30)) {
					var35 = 1.0F;
					GL11.glColor4f(var35, var35, var35, 1.0F);
				} else if(this.field_27120_x.func_27181_b(var30)) {
					var35 = Math.sin((double)(System.currentTimeMillis() % 600L) / 600.0D * Math.PI * 2.0D) < 0.6D ? 0.6F : 0.8F;
					GL11.glColor4f(var35, var35, var35, 1.0F);
				} else {
					var35 = 0.3F;
					GL11.glColor4f(var35, var35, var35, 1.0F);
				}

				this.mc.renderEngine.bindTexture(var7);
				var33 = var10 + var16;
				var34 = var11 + var17;
				if(var30.getSpecial()) {
					this.drawTexturedModalRect(var33 - 2, var34 - 2, 26, 202, 26, 26);
				} else {
					this.drawTexturedModalRect(var33 - 2, var34 - 2, 0, 202, 26, 26);
				}

				if(!this.field_27120_x.func_27181_b(var30)) {
					float var36 = 0.1F;
					GL11.glColor4f(var36, var36, var36, 1.0F);
					var29.field_27004_a = false;
				}

				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_CULL_FACE);
				var29.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var30.theItemStack, var33 + 3, var34 + 3);
				GL11.glDisable(GL11.GL_LIGHTING);
				if(!this.field_27120_x.func_27181_b(var30)) {
					var29.field_27004_a = true;
				}

				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				if(var1 >= var10 && var2 >= var11 && var1 < var10 + 224 && var2 < var11 + 155 && var1 >= var33 && var1 <= var33 + 22 && var2 >= var34 && var2 <= var34 + 22) {
					var27 = var30;
				}
			}
		}

		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var7);
		this.drawTexturedModalRect(var8, var9, 0, 0, this.field_27121_a, this.field_27119_i);
		GL11.glPopMatrix();
		this.zLevel = 0.0F;
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		super.drawScreen(var1, var2, var3);
		if(var27 != null) {
			String var31 = var27.statName;
			String var32 = var27.getDescription();
			var17 = var1 + 12;
			var33 = var2 - 4;
			if(this.field_27120_x.func_27181_b(var27)) {
				var34 = Math.max(this.fontRenderer.getStringWidth(var31), 120);
				int var37 = this.fontRenderer.func_27277_a(var32, var34);
				if(this.field_27120_x.hasAchievementUnlocked(var27)) {
					var37 += 12;
				}

				this.drawGradientRect(var17 - 3, var33 - 3, var17 + var34 + 3, var33 + var37 + 3 + 12, -1073741824, -1073741824);
				this.fontRenderer.func_27278_a(var32, var17, var33 + 12, var34, -6250336);
				if(this.field_27120_x.hasAchievementUnlocked(var27)) {
					this.fontRenderer.drawStringWithShadow(StatCollector.translateToLocal("achievement.taken"), var17, var33 + var37 + 4, -7302913);
				}
			} else {
				var34 = Math.max(this.fontRenderer.getStringWidth(var31), 120);
				String var39 = StatCollector.translateToLocalFormatted("achievement.requires", new Object[]{var27.parentAchievement.statName});
				var38 = this.fontRenderer.func_27277_a(var39, var34);
				this.drawGradientRect(var17 - 3, var33 - 3, var17 + var34 + 3, var33 + var38 + 12 + 3, -1073741824, -1073741824);
				this.fontRenderer.func_27278_a(var39, var17, var33 + 12, var34, -9416624);
			}

			this.fontRenderer.drawStringWithShadow(var31, var17, var33, this.field_27120_x.func_27181_b(var27) ? (var27.getSpecial() ? -128 : -1) : (var27.getSpecial() ? -8355776 : -8355712));
		}

		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_LIGHTING);
		RenderHelper.disableStandardItemLighting();
	}

	public boolean doesGuiPauseGame() {
		return true;
	}
}
