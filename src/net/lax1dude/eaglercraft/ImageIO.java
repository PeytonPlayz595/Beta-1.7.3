package net.lax1dude.eaglercraft;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import net.PeytonPlayz585.opengl.GL11;

public class ImageIO {
	
	public static BufferedImage read(InputStream var1) {
		ByteArrayInputStream bais = (ByteArrayInputStream)var1;
		byte[] data = bais.readAllBytes();
		
		return GL11.loadPNG(data);
	}

}
