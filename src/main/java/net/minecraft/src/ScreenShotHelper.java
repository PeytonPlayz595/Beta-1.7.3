package net.minecraft.src;

import net.PeytonPlayz585.opengl.GL11;

public class ScreenShotHelper {
	
	public static String saveScreenshot() {
		return "Saved screenshot as " + GL11.EaglerAdapterImpl2.saveScreenshot();
	}

}
