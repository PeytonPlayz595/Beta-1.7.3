package net.PeytonPlayz585.input;

import net.PeytonPlayz585.opengl.GL11;

public class Mouse extends GL11 {

	public static int getX() {
		return mouseGetX();
	}
	
	public static int getY() {
		return mouseGetY();
	}

	public static boolean next() {
		return mouseNext();
	}

	public static boolean getEventButtonState() {
		return mouseGetEventButtonState();
	}

	public static int getEventX() {
		return mouseGetEventX();
	}

	
	public static int getEventY() {
		return mouseGetEventY();
	}

	public static int getEventButton() {
		return mouseGetEventButton();
	}
	
	public static int getDX() {
		return mouseGetDX();
	}

	public static int getDY() {
		return mouseGetDY();
	}

	public static void setGrabbed(boolean b) {
		mouseSetGrabbed(b);
	}

	public static boolean isButtonDown(int i) {
		return mouseIsButtonDown(i);
	}

	public static int getEventDWheel() {
		return mouseGetEventDWheel();
	}
}
