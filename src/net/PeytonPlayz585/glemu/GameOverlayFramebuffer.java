package net.PeytonPlayz585.glemu;

import static net.PeytonPlayz585.opengl.GL11.*;

import java.nio.ByteBuffer;

public class GameOverlayFramebuffer {

	private long age = -1l;

	private int currentWidth = -1;
	private int currentHeight = -1;

	private FramebufferGL framebuffer = null;
	private TextureGL framebufferColor = null;
	private RenderbufferGL depthBuffer = null;

	public void beginRender(int width, int height) {
		if(framebuffer == null) {
			framebuffer = _wglCreateFramebuffer();
			depthBuffer = _wglCreateRenderBuffer();
			framebufferColor = _wglGenTextures();
			_wglBindFramebuffer(_wGL_FRAMEBUFFER, framebuffer);
			glBindTexture(_wGL_TEXTURE_2D, framebufferColor);
			_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
			_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			_wglFramebufferTexture2D(_wGL_COLOR_ATTACHMENT0, framebufferColor, 0);
			_wglBindRenderbuffer(depthBuffer);
			_wglFramebufferRenderbuffer(_wGL_DEPTH_ATTACHMENT, depthBuffer);
		}

		if(currentWidth != width || currentHeight != height) {
			currentWidth = width;
			currentHeight = height;
			glBindTexture(_wGL_TEXTURE_2D, framebufferColor);
			_wglTexImage2D(_wGL_TEXTURE_2D, 0, _wGL_RGBA8, width, height, 0, _wGL_RGBA, _wGL_UNSIGNED_BYTE, (ByteBuffer)null);
			_wglBindRenderbuffer(depthBuffer);
			_wglRenderbufferStorage(0x81A5, width, height);
		}

		_wglBindFramebuffer(_wGL_FRAMEBUFFER, framebuffer);
	}

	public void endRender() {
		_wglBindFramebuffer(_wGL_FRAMEBUFFER, null);
		age = System.currentTimeMillis();
	}

	public long getAge() {
		return age == -1l ? -1l : (System.currentTimeMillis() - age);
	}

	public void bindTexture() {
		glBindTexture(_wGL_TEXTURE_2D, framebufferColor);
	}

	public void destroy() {
		if(framebuffer != null) {
			_wglDeleteFramebuffer(framebuffer);
			_wglDeleteRenderbuffer(depthBuffer);
			_wglDeleteTextures(framebufferColor);
			framebuffer = null;
			depthBuffer = null;
			framebufferColor = null;
			age = -1l;
			_wglBindFramebuffer(_wGL_FRAMEBUFFER, null);
		}
	}

}