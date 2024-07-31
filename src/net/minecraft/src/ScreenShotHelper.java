package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class ScreenShotHelper {
	
	public static String saveScreenshot() {
		return "Saved screenshot as " + GL11.EaglerAdapterImpl2.saveScreenshot();
	}

}
