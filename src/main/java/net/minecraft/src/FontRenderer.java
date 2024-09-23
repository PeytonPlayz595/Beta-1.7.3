package net.minecraft.src;

import java.io.IOException;

import net.PeytonPlayz585.awt.image.BufferedImage;
import net.PeytonPlayz585.awt.image.ImageIO;
import net.PeytonPlayz585.opengl.GL11;
import net.PeytonPlayz585.textures.TextureLocation;

public class FontRenderer {
	public static final char formatChar = '\247';
	private int[] charWidth = new int[256];
	private int[] colorCode = new int[32];
	private final TextureLocation fontTexture;
	private float posX;
	private float posY;
	
	//color :3
	private float red;
	private float blue;
	private float green;
	private float alpha;

	public FontRenderer(GameSettings var1, String var2, RenderEngine var3) {
		this.fontTexture = new TextureLocation(var2);
		fontTexture.bindTexture();
		this.readFontTexture(var2);

		for (int var5 = 0; var5 < 32; ++var5) {
			int var6 = (var5 >> 3 & 1) * 85;
			int var7 = (var5 >> 2 & 1) * 170 + var6;
			int var8 = (var5 >> 1 & 1) * 170 + var6;
			int var9 = (var5 >> 0 & 1) * 170 + var6;

			if (var5 == 6) {
				var7 += 85;
			}

			if (var1.anaglyph) {
				int var10 = (var7 * 30 + var8 * 59 + var9 * 11) / 100;
				int var11 = (var7 * 30 + var8 * 70) / 100;
				int var12 = (var7 * 30 + var9 * 70) / 100;
				var7 = var10;
				var8 = var11;
				var9 = var12;
			}

			if (var5 >= 16) {
				var7 /= 4;
				var8 /= 4;
				var9 /= 4;
			}

			this.colorCode[var5] = (var7 & 255) << 16 | (var8 & 255) << 8 | var9 & 255;
		}
	}
	
	private void readFontTexture(String par1Str) {
		BufferedImage e;
		try {
			e = ImageIO.read(GL11.getResourceAsStream(par1Str));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		int[] var5 = e.getData();
		int var3 = e.getWidth();
		int var6 = 0;

		while (var6 < 256) {
			int var7 = var6 % 16;
			int var8 = var6 / 16;
			int var9 = 7;

			while (true) {
				if (var9 >= 0) {
					int var10 = var7 * 8 + var9;
					boolean var11 = true;

					for (int var12 = 0; var12 < 8 && var11; ++var12) {
						int var13 = (var8 * 8 + var12) * var3;
						int var14 = var5[var10 + var13] & 255;

						if (var14 > 0) {
							var11 = false;
						}
					}

					if (var11) {
						--var9;
						continue;
					}
				}

				if (var6 == 32) {
					var9 = 2;
				}

				this.charWidth[var6] = var9 + 2;
				++var6;
				break;
			}
		}
	}

	public void drawStringWithShadow(String var1, int var2, int var3, int var4) {
		this.renderString(var1, var2 + 1, var3 + 1, var4, true);
		this.drawString(var1, var2, var3, var4);
	}

	public void drawString(String var1, int var2, int var3, int var4) {
		this.renderString(var1, var2, var3, var4, false);
	}

	public void renderString(String var1, int var2, int var3, int var4, boolean var5) {
		if (var1 == null) {
			return;
		} else {
			if ((var4 & -67108864) == 0) {
				var4 |= -16777216;
			}

			if (var5) {
				var4 = (var4 & 16579836) >> 2 | var4 & -16777216;
			}

			this.red = (float) (var4 >> 16 & 255) / 255.0F;
			this.blue = (float) (var4 >> 8 & 255) / 255.0F;
			this.green = (float) (var4 & 255) / 255.0F;
			this.alpha = (float) (var4 >> 24 & 255) / 255.0F;
			GL11.glColor4f(this.red, this.blue, this.green, this.alpha);
			this.posX = (float) var2;
			this.posY = (float) var3;
			this.renderStringAtPos(var1, var5);
		}
	}

	public int getStringWidth(String var1) {
		if(var1 == null) {
			return 0;
		} else {
			int var2 = 0;

			for(int var3 = 0; var3 < var1.length(); ++var3) {
				if(var1.charAt(var3) == 167) {
					++var3;
				} else {
					int var4 = ChatAllowedCharacters.allowedCharacters.indexOf(var1.charAt(var3));
					if(var4 >= 0) {
						var2 += this.charWidth[var4 + 32];
					}
				}
			}

			return var2;
		}
	}

	public void func_27278_a(String var1, int var2, int var3, int var4, int var5) {
		String[] var6 = var1.split("\n");
		if(var6.length > 1) {
			for(int var11 = 0; var11 < var6.length; ++var11) {
				this.func_27278_a(var6[var11], var2, var3, var4, var5);
				var3 += this.func_27277_a(var6[var11], var4);
			}

		} else {
			String[] var7 = var1.split(" ");
			int var8 = 0;

			while(var8 < var7.length) {
				String var9;
				for(var9 = var7[var8++] + " "; var8 < var7.length && this.getStringWidth(var9 + var7[var8]) < var4; var9 = var9 + var7[var8++] + " ") {
				}

				int var10;
				for(; this.getStringWidth(var9) > var4; var9 = var9.substring(var10)) {
					for(var10 = 0; this.getStringWidth(var9.substring(0, var10 + 1)) <= var4; ++var10) {
					}

					if(var9.substring(0, var10).trim().length() > 0) {
						this.drawString(var9.substring(0, var10), var2, var3, var5);
						var3 += 8;
					}
				}

				if(var9.trim().length() > 0) {
					this.drawString(var9, var2, var3, var5);
					var3 += 8;
				}
			}

		}
	}

	public int func_27277_a(String var1, int var2) {
		String[] var3 = var1.split("\n");
		int var5;
		if(var3.length > 1) {
			int var9 = 0;

			for(var5 = 0; var5 < var3.length; ++var5) {
				var9 += this.func_27277_a(var3[var5], var2);
			}

			return var9;
		} else {
			String[] var4 = var1.split(" ");
			var5 = 0;
			int var6 = 0;

			while(var5 < var4.length) {
				String var7;
				for(var7 = var4[var5++] + " "; var5 < var4.length && this.getStringWidth(var7 + var4[var5]) < var2; var7 = var7 + var4[var5++] + " ") {
				}

				int var8;
				for(; this.getStringWidth(var7) > var2; var7 = var7.substring(var8)) {
					for(var8 = 0; this.getStringWidth(var7.substring(0, var8 + 1)) <= var2; ++var8) {
					}

					if(var7.substring(0, var8).trim().length() > 0) {
						var6 += 8;
					}
				}

				if(var7.trim().length() > 0) {
					var6 += 8;
				}
			}

			if(var6 < 8) {
				var6 += 8;
			}

			return var6;
		}
	}
	
	/**
	 * Render a single line string at the current (posX,posY) and update posX
	 */
	private void renderStringAtPos(String par1Str, boolean par2) {
		Tessellator t = Tessellator.instance;
		this.fontTexture.bindTexture();
		t.startDrawingQuads();
		for (int var3 = 0; var3 < par1Str.length(); ++var3) {
			char var4 = par1Str.charAt(var3);
			int var5;
			int var6;

			if (var4 == '\u00a7' && var3 + 1 < par1Str.length()) {
				var5 = "0123456789abcdefklmnor".indexOf((char)Character.toLowerCase(par1Str.charAt(var3 + 1)));

				if (var5 < 16) {
					if (var5 < 0 || var5 > 15) {
						var5 = 15;
					}

					if (par2) {
						var5 += 16;
					}
					
					t.draw();
					t.startDrawingQuads();

					var6 = this.colorCode[var5];
					GL11.glColor4f((float) ((var6 >> 16) & 255) / 255.0F, (float) ((var6 >> 8) & 255) / 255.0F, (float) (var6 & 255) / 255.0F, this.alpha);
				} else if (var5 == 21) {
					t.draw();
					t.startDrawingQuads();
					GL11.glColor4f(this.red, this.blue, this.green, this.alpha);
				}

				++var3;
			} else {
				var5 = ChatAllowedCharacters.allowedCharacters.indexOf(var4);

				float var11 = 0.98F;
				boolean var7 = (var5 <= 0) && par2;

				if (var7) {
					this.posX -= var11;
					this.posY -= var11;
				}

				float var8 = this.renderCharAtPos(var5, var4);

				if (var7) {
					this.posX += var11;
					this.posY += var11;
				}

				this.posX += (float) ((int) var8);
			}
		}
		t.draw();
	}
	
	private float renderCharAtPos(int par1, char par2) {
		return par2 == 32 ? 4.0F : this.renderDefaultChar(par1 + 32);
	}
	
	private float renderDefaultChar(int par1) {
		float var3 = (float) (par1 % 16 * 8);
		float var4 = (float) (par1 / 16 * 8);
		float var5 = 0.0F;
		float var6 = (float) this.charWidth[par1] - 0.02F;
		Tessellator t = Tessellator.instance;
		t.addVertexWithUV(this.posX + 0.01F + var5, this.posY + 0.01F, 0.0F, (var3 + 0.02F) / 128.0F, (var4 + 0.02F) / 128.0F);
		t.addVertexWithUV(this.posX + 0.01F - var5, this.posY + 7.99F, 0.0F, (var3 + 0.02F) / 128.0F, (var4 + 7.96F) / 128.0F);
		t.addVertexWithUV(this.posX + var6 - var5, this.posY + 7.99F, 0.0F, (var3 + var6) / 128.0F, (var4 + 7.96F) / 128.0F);
		t.addVertexWithUV(this.posX + var6 + var5, this.posY + 0.01F, 0.0F, (var3 + var6) / 128.0F, (var4 + 0.02F) / 128.0F);
		return (float) this.charWidth[par1];
	}
}