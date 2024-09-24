package net.minecraft.src;

import java.util.Arrays;

import net.PeytonPlayz585.awt.image.BufferedImage;
import net.PeytonPlayz585.awt.image.ImageIO;
import net.PeytonPlayz585.opengl.GL11;

public class TerrainTextureManager {
	private float[] field_1181_a = new float[768];
	private int[] field_1180_b = new int[5120];
	private int[] field_1186_c = new int[5120];
	private int[] field_1185_d = new int[5120];
	private int[] field_1184_e = new int[5120];
	private int[] field_1183_f = new int[34];
	private int[] field_1182_g = new int[768];

	public TerrainTextureManager() {
		try {
			BufferedImage var1 = ImageIO.read(GL11.getResource("/terrain.png"));
			int[] var2 = new int[65536];
			var1.getRGB(0, 0, 256, 256, var2, 0, 256);

			for(int var3 = 0; var3 < 256; ++var3) {
				int var4 = 0;
				int var5 = 0;
				int var6 = 0;
				int var7 = var3 % 16 * 16;
				int var8 = var3 / 16 * 16;
				int var9 = 0;

				for(int var10 = 0; var10 < 16; ++var10) {
					for(int var11 = 0; var11 < 16; ++var11) {
						int var12 = var2[var11 + var7 + (var10 + var8) * 256];
						int var13 = var12 >> 24 & 255;
						if(var13 > 128) {
							var4 += var12 >> 16 & 255;
							var5 += var12 >> 8 & 255;
							var6 += var12 & 255;
							++var9;
						}
					}

					if(var9 == 0) {
						++var9;
					}

					this.field_1181_a[var3 * 3 + 0] = (float)(var4 / var9);
					this.field_1181_a[var3 * 3 + 1] = (float)(var5 / var9);
					this.field_1181_a[var3 * 3 + 2] = (float)(var6 / var9);
				}
			}
		} catch (Exception var14) {
			var14.printStackTrace();
		}

		for(int var15 = 0; var15 < 256; ++var15) {
			if(Block.blocksList[var15] != null) {
				this.field_1182_g[var15 * 3 + 0] = Block.blocksList[var15].getBlockTextureFromSide(1);
				this.field_1182_g[var15 * 3 + 1] = Block.blocksList[var15].getBlockTextureFromSide(2);
				this.field_1182_g[var15 * 3 + 2] = Block.blocksList[var15].getBlockTextureFromSide(3);
			}
		}

	}

	private void func_800_a() {
		for(int var1 = 0; var1 < 32; ++var1) {
			for(int var2 = 0; var2 < 160; ++var2) {
				int var3 = var1 + var2 * 32;
				if(this.field_1186_c[var3] == 0) {
					this.field_1180_b[var3] = 0;
				}

				if(this.field_1185_d[var3] > this.field_1186_c[var3]) {
					int var4 = this.field_1180_b[var3] >> 24 & 255;
					this.field_1180_b[var3] = ((this.field_1180_b[var3] & 16711422) >> 1) + this.field_1184_e[var3];
					if(var4 < 128) {
						this.field_1180_b[var3] = Integer.MIN_VALUE + this.field_1184_e[var3] * 2;
					} else {
						this.field_1180_b[var3] |= -16777216;
					}
				}
			}
		}

	}
}
