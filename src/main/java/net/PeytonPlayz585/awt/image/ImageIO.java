package net.PeytonPlayz585.awt.image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.PeytonPlayz585.opengl.GL11;

public class ImageIO {
	
	public static BufferedImage read(InputStream var1) throws IOException {
		ByteArrayInputStream bais = (ByteArrayInputStream)var1;
		byte[] data = bais.readAllBytes();
		
		return GL11.EaglerAdapterImpl2.loadPNG(data);
	}

	public static BufferedImage read(BufferedImage resource) {
		return resource;
	}
	
	public static InputStream getResourceAsStream(String var1) {
		return GL11.EaglerAdapterImpl2.loadResource(var1);
	}

	public static BufferedImage getResource(String string) {
		return GL11.EaglerAdapterImpl2.loadPNG(GL11.EaglerAdapterImpl2.loadResourceBytes(string));
	}

}