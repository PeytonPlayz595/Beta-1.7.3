package net.minecraft.src;

import java.io.IOException;

import net.PeytonPlayz585.awt.image.BufferedImage;
import net.PeytonPlayz585.awt.image.ImageIO;
import net.PeytonPlayz585.opengl.GL11;
import net.PeytonPlayz585.textures.TextureLocation;
import net.minecraft.client.Minecraft;

public class TexturePackDefault extends TexturePackBase {
	private int texturePackName = -1;
	private BufferedImage texturePackThumbnail;
	
	private static final TextureLocation unknownPack = new TextureLocation("/gui/unknown_pack.png");

	public TexturePackDefault() {
		this.texturePackFileName = "Default";
		this.firstDescriptionLine = "The default look of Minecraft";

		try {
			this.texturePackThumbnail = ImageIO.read(ImageIO.getResource("/pack.png"));
		} catch (Exception var2) {
			var2.printStackTrace();
		}

	}

	public void func_6484_b(Minecraft var1) {
		if(this.texturePackThumbnail != null) {
			var1.renderEngine.deleteTexture(this.texturePackName);
		}

	}

	public void bindThumbnailTexture(Minecraft var1) {
		if(this.texturePackThumbnail != null && this.texturePackName < 0) {
			this.texturePackName = var1.renderEngine.allocateAndSetupTexture(this.texturePackThumbnail);
		}

		if(this.texturePackThumbnail != null) {
			var1.renderEngine.bindTexture(this.texturePackName);
		} else {
			unknownPack.bindTexture();
		}

	}
}
