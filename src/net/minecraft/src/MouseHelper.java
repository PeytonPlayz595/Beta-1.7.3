package net.minecraft.src;

import net.PeytonPlayz585.input.Mouse;

public class MouseHelper {
	public int deltaX;
	public int deltaY;

	public MouseHelper() {
	}

	public void grabMouseCursor() {
		Mouse.setGrabbed(true);
		this.deltaX = 0;
		this.deltaY = 0;
	}

	public void ungrabMouseCursor() {
		Mouse.setGrabbed(false);
	}

	public void mouseXYChange() {
		this.deltaX = Mouse.getDX();
		this.deltaY = Mouse.getDY();
	}
}
