package net.PeytonPlayz585.glemu;

import static net.PeytonPlayz585.opengl.GL11.*;
import static net.PeytonPlayz585.opengl.GL11.EaglerAdapterImpl2.*;

import net.lax1dude.eaglercraft.internal.buffer.ByteBuffer;

public class GameOverlayFramebuffer {

	private static final int _GL_FRAMEBUFFER = 0x8D40;
	private static final int _GL_RENDERBUFFER = 0x8D41;
	private static final int _GL_COLOR_ATTACHMENT0 = 0x8CE0;
	private static final int _GL_DEPTH_ATTACHMENT = 0x8D00;
	private static final int _GL_DEPTH_COMPONENT16 = 0x81A5;

	private long age = -1l;

	private int currentWidth = -1;
	private int currentHeight = -1;

	private FramebufferGL framebuffer = null;
	private RenderbufferGL depthBuffer = null;

	private int framebufferColor = -1;

	private final boolean enableDepth = true;
	
	public GameOverlayFramebuffer() {
		
	}

	public void beginRender(int width, int height) {
		if(framebuffer == null) {
			framebuffer = _wglCreateFramebuffer();
			depthBuffer = enableDepth ? _wglCreateRenderBuffer() : null;
			framebufferColor = glGenTextures();
			_wglBindFramebuffer(_GL_FRAMEBUFFER, framebuffer);
			glBindTexture(framebufferColor);
			_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
			_wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
			_wglFramebufferTexture2D(_GL_FRAMEBUFFER, _GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, glGetTextures(framebufferColor), 0);
			if(enableDepth) {
				_wglBindRenderbuffer(_GL_RENDERBUFFER, depthBuffer);
				_wglFramebufferRenderbuffer(_GL_FRAMEBUFFER, _GL_DEPTH_ATTACHMENT, _GL_RENDERBUFFER, depthBuffer);
			}
		}

		boolean resized = currentWidth != width || currentHeight != height;
		if(resized) {
			currentWidth = width;
			currentHeight = height;
			glBindTexture(framebufferColor);
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, (ByteBuffer)null);
			if(enableDepth) {
				_wglBindRenderbuffer(_GL_RENDERBUFFER, depthBuffer);
				_wglRenderbufferStorage(_GL_RENDERBUFFER, _GL_DEPTH_COMPONENT16, width, height);
			}
		}

		_wglBindFramebuffer(_GL_FRAMEBUFFER, framebuffer);
	}

	public void endRender() {
		_wglBindFramebuffer(_GL_FRAMEBUFFER, null);
		age = System.currentTimeMillis();
	}

	public long getAge() {
		return age == -1l ? -1l : (System.currentTimeMillis() - age);
	}

	public int getTexture() {
		return framebufferColor;
	}

	public void destroy() {
		if(framebuffer != null) {
			_wglDeleteFramebuffer(framebuffer);
			if(enableDepth) {
				_wglDeleteRenderbuffer(depthBuffer);
			}
			glDeleteTextures(framebufferColor);
			framebuffer = null;
			depthBuffer = null;
			framebufferColor = -1;
			age = -1l;
			_wglBindFramebuffer(_GL_FRAMEBUFFER, null);
		}
	}

}