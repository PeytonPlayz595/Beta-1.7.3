package net.PeytonPlayz585.textures;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.src.RenderEngine;

public class TextureLocation {

	private String path;
	private int glObject;

	public TextureLocation(String path) {
		this.path = path;
		this.glObject = -1;
		locations.add(this);
	}

	public static void freeTextures() {
		for (TextureLocation l : locations) {
			l.glObject = -1;
		}
	}

	public int getTexturePointer() {
		RenderEngine r = Minecraft.getMinecraft().renderEngine;
		if (glObject == -1) {
			glObject = r.getTexture(path);
			if (glObject == -1) {
				System.err.println("could not load: " + path);
			}
		}
		return glObject;
	}
	
	public void bindTexture() {
		RenderEngine r = Minecraft.getMinecraft().renderEngine;
		int i = getTexturePointer();
		if(i != -1) {
			r.bindTexture(i);
		}
	}

	private static final ArrayList<TextureLocation> locations = new ArrayList();

}