package org.lwjgl.input;

import org.lwjgl.opengl.GL11;

public class Mouse {
	
	public static int getX() {
		return GL11.EaglerAdapterImpl2.mouseGetX();
	}
	
	public static int getY() {
		return GL11.EaglerAdapterImpl2.mouseGetY();
	}

	public static void create() {
	}

	public static void setGrabbed(boolean b) {
		GL11.EaglerAdapterImpl2.mouseSetGrabbed(b);
	}

	public static int getDX() {
		return GL11.EaglerAdapterImpl2.mouseGetDX();
	}
	
	public static int getDY() {
		return GL11.EaglerAdapterImpl2.mouseGetDY();
	}

	public static void destroy() {
	}

	public static boolean next() {
		return GL11.EaglerAdapterImpl2.mouseNext();
	}

	public static int getEventButton() {
		return GL11.EaglerAdapterImpl2.mouseGetEventButton();
	}

	public static boolean getEventButtonState() {
		return GL11.EaglerAdapterImpl2.mouseGetEventButtonState();
	}

	public static int getEventDWheel() {
		return GL11.EaglerAdapterImpl2.mouseGetEventDWheel();
	}

	public static boolean isButtonDown(int i) {
		return GL11.EaglerAdapterImpl2.mouseIsButtonDown(i);
	}

	public static int getEventX() {
		return GL11.EaglerAdapterImpl2.mouseGetEventX();
	}
	
	public static int getEventY() {
		return GL11.EaglerAdapterImpl2.mouseGetEventY();
	}

}
