package net.PeytonPlayz585.opengl;

public class Display {
	
	static Display display = new Display();
	
	//Can NOT be null!
	private static String title = "Minecraft";
	
	public Display() {
		
	}
	
	public static boolean isActive() {
		return GL11.EaglerAdapterImpl2.isFocused();
	}
	
	public static int getWidth() {
		return GL11.EaglerAdapterImpl2.getCanvasWidth();
	}
	
	public static int getHeight() {
		return GL11.EaglerAdapterImpl2.getCanvasHeight();
	}
	
	public static void setFullscreen(boolean b) {
		GL11.EaglerAdapterImpl2.setFullscreen(b);
	}
	
	public static void setTitle(String newTitle) {
		if(newTitle == null) {
			throw new IllegalArgumentException("title CAN NOT be null");
		}
		title = newTitle;
	}
	
	public static void update() {
		//GL11.EaglerAdapterImpl2.doc.setTitle(title);
		GL11.EaglerAdapterImpl2.updateDisplay();
	}

	public static Display getDisplayMode() {
		return display;
	}

	public static void swapBuffers() {
		GL11.glFlush();
		GL11.EaglerAdapterImpl2.updateDisplay();
	}

	public static void destroy() {
	}

	public static boolean isFocused() {
		return GL11.EaglerAdapterImpl2.isFocused();
	}

}
