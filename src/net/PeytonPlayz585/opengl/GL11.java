package net.PeytonPlayz585.opengl;

public class GL11 extends EaglerAdapterGL30 {

	public static void glPixelStorei(int glUnpackAlignment, int i) {
		_wglPixelStorei(glUnpackAlignment, i);
	}

}
