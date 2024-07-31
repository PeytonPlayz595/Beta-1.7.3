package org.lwjgl.input;

import org.lwjgl.opengl.GL11;

public class Keyboard {

	public static void destroy() {
	}

	public static int getEventKey() {
		return GL11.EaglerAdapterImpl2.getEventKey();
	}

	public static boolean isKeyDown(int i) {
		return GL11.EaglerAdapterImpl2.isKeyDown(i);
	}

	public static boolean getEventKeyState() {
		return GL11.EaglerAdapterImpl2.getEventKeyState();	
	}

	public static boolean next() {
		return GL11.EaglerAdapterImpl2.keysNext();
	}

	public static String getKeyName(int var0) {
		return GL11.EaglerAdapterImpl2.getKeyName(var0);
	}

	public static void enableRepeatEvents(boolean b) {
		GL11.EaglerAdapterImpl2.enableRepeatEvents(b);
	}
	
	public static String getClipboardString() {
		return GL11.EaglerAdapterImpl2.getClipboard();
	}
	
	public static void setClipboard(String s) {
		GL11.EaglerAdapterImpl2.setClipboard(s);
	}

	public static char getEventCharacter() {
		return GL11.EaglerAdapterImpl2.getEventChar();
	}
	
	public static boolean isFunctionKeyDown(int p1, int p2) {
		return isKeyDown(p1) && getEventKey() == p2;
	}

}
