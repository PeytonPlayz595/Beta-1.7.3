package net.minecraft.src;

import net.PeytonPlayz585.opengl.GL11;
import net.PeytonPlayz585.textures.TextureLocation;

public class TextureFX {
	public byte[] imageData = new byte[1024];
	public int iconIndex;
	public boolean anaglyphEnabled = false;
	public int textureId = 0;
	public int tileSize = 1;
	public int tileImage = 0;
	
	public static final TextureLocation terrainTexture = new TextureLocation("/terrain.png");
	public static final TextureLocation itemsTexture = new TextureLocation("/gui/items.png");

	public TextureFX(int var1) {
		this.iconIndex = var1;
	}

	public void onTick() {
	}

	public void bindImage(RenderEngine var1) {
		if(this.tileImage == 0) {
			terrainTexture.bindTexture();
		} else if(this.tileImage == 1) {
			itemsTexture.bindTexture();
		}

	}
}
