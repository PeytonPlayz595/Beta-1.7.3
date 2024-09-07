package net.lax1dude.eaglercraft;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.Item;
import net.minecraft.src.TextureFX;
import net.minecraft.src.World;

public class TextureNewCompassFX extends TextureFX {

	private final int[] compassSpriteSheet;
	private final int compassSpriteSheetLength;
	private float angleDelta = 0.0f;
	private float currentAngle = 0.0f;

	public TextureNewCompassFX() {
		super(Item.compass.getIconFromDamage(0));
		tileImage = 1;
		this.compassSpriteSheet = GL11.EaglerAdapterImpl2.loadPNG(GL11.EaglerAdapterImpl2.loadResourceBytes("/sprite_sheet/compass.png")).getData();
		this.compassSpriteSheetLength = compassSpriteSheet.length / 256;
	}

	public void onTick() {
		Minecraft var1 = Minecraft.getMinecraft();

		if (var1.theWorld != null && var1.thePlayer != null) {
			this.updateCompass(var1.theWorld, var1.thePlayer.posX, var1.thePlayer.posZ, (double) var1.thePlayer.rotationYaw, false, false);
		} else {
			this.updateCompass((World) null, 0.0D, 0.0D, 0.0D, true, false);
		}
	}

	public void updateCompass(World par1World, double par2, double par4, double par6, boolean par8, boolean par9) {
		double var10 = 0.0D;

		if (par1World != null && !par8) {
			ChunkCoordinates var12 = par1World.getSpawnPoint();
			double var13 = (double) var12.x - par2;
			double var15 = (double) var12.z - par4;
			par6 %= 360.0D;
			var10 = -((par6 - 90.0D) * Math.PI / 180.0D - Math.atan2(var15, var13));

			if (par1World.worldProvider.isNether) {
				var10 = Math.random() * Math.PI * 2.0D;
			}
		}

		if (par9) {
			this.currentAngle = (float) var10;
		} else {
			double var17;

			for (var17 = var10 - this.currentAngle; var17 < -Math.PI; var17 += (Math.PI * 2D)) {
				;
			}

			while (var17 >= Math.PI) {
				var17 -= (Math.PI * 2D);
			}

			if (var17 < -1.0D) {
				var17 = -1.0D;
			}

			if (var17 > 1.0D) {
				var17 = 1.0D;
			}

			this.angleDelta += var17 * 0.1D;
			this.angleDelta *= 0.8D;
			this.currentAngle += this.angleDelta;
		}

		int var18;

		for (var18 = (int) ((this.currentAngle / (Math.PI * 2D) + 1.0D) * (double) compassSpriteSheetLength) % compassSpriteSheetLength; var18 < 0; var18 = (var18 + compassSpriteSheetLength) % compassSpriteSheetLength) {
			;
		}

		int offset = var18 * 256;
		for(int i = 0; i < 256; ++i) {
			this.imageData[i * 4] = (byte)((compassSpriteSheet[offset + i] >> 16) & 0xFF);
			this.imageData[i * 4 + 1] = (byte)((compassSpriteSheet[offset + i] >> 8) & 0xFF);
			this.imageData[i * 4 + 2] = (byte)((compassSpriteSheet[offset + i]) & 0xFF);
			this.imageData[i * 4 + 3] = (byte)((compassSpriteSheet[offset + i] >> 24) & 0xFF);
		}

		//if (var18 != this.frameCounter) {
		//	this.frameCounter = var18;
		//	this.textureSheet.func_104062_b(this.originX, this.originY, (Texture) this.textureList.get(this.frameCounter));
		//}
	}

}