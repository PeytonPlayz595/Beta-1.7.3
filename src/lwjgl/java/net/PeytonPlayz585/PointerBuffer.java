package net.PeytonPlayz585;

import java.nio.ByteBuffer;

public class PointerBuffer extends org.lwjgl.PointerBuffer {
	public PointerBuffer(ByteBuffer buf) {
		super(buf);
	}

	public PointerBuffer(int capacity) {
		super(capacity);
	}
}
