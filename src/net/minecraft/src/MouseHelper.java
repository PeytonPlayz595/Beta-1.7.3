package net.minecraft.src;

import java.awt.Component;
import java.nio.IntBuffer;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;

public class MouseHelper {
	private Component field_1117_c;
	private Cursor cursor;
	public int deltaX;
	public int deltaY;
	private int field_1115_e = 10;

	public MouseHelper(Component var1) {
		this.field_1117_c = var1;
		IntBuffer var2 = GLAllocation.createDirectIntBuffer(1);
		var2.put(0);
		var2.flip();
		IntBuffer var3 = GLAllocation.createDirectIntBuffer(1024);

		try {
			this.cursor = new Cursor(32, 32, 16, 16, 1, var3, var2);
		} catch (LWJGLException var5) {
			var5.printStackTrace();
		}

	}

	public void grabMouseCursor() {
		Mouse.setGrabbed(true);
		this.deltaX = 0;
		this.deltaY = 0;
	}

	public void ungrabMouseCursor() {
		Mouse.setCursorPosition(this.field_1117_c.getWidth() / 2, this.field_1117_c.getHeight() / 2);
		Mouse.setGrabbed(false);
	}

	public void mouseXYChange() {
		this.deltaX = Mouse.getDX();
		this.deltaY = Mouse.getDY();
	}
}
