package net.PeytonPlayz585.opengl;

import org.teavm.jso.webgl.WebGLFramebuffer;

import net.PeytonPlayz585.opengl.GL11.EaglerAdapterImpl2.FramebufferGL;
import net.PeytonPlayz585.opengl.GL11.EaglerAdapterImpl2.RenderbufferGL;
import net.PeytonPlayz585.opengl.GL11.WebGL2RenderingContext;

import static net.PeytonPlayz585.opengl.GL11.*;
import static net.PeytonPlayz585.opengl.GL11.EaglerAdapterImpl2.*;

public class WebGLBackBuffer {
	
	private static WebGL2RenderingContext ctx;
	private static WebGLFramebuffer framebuffer;
	private static FramebufferGL eagFramebuffer;
	private static int width;
	private static int height;

	// GLES 3.0+
	private static RenderbufferGL gles3ColorRenderbuffer;
	private static RenderbufferGL gles3DepthRenderbuffer;
	
	private static final int _GL_FRAMEBUFFER = 0x8D40;
	private static final int _GL_RENDERBUFFER = 0x8D41;
	private static final int _GL_COLOR_ATTACHMENT0 = 0x8CE0;
	private static final int _GL_DEPTH_ATTACHMENT = 0x8D00;
	private static final int _GL_DEPTH_COMPONENT16 = 0x81A5;
	private static final int _GL_DEPTH_COMPONENT32F = 0x8CAC;
	private static final int _GL_READ_FRAMEBUFFER = 0x8CA8;
	private static final int _GL_DRAW_FRAMEBUFFER = 0x8CA9;
	
	public static void initBackBuffer(WebGL2RenderingContext ctxIn, WebGLFramebuffer fbo, FramebufferGL eagFbo, int sw, int sh) {
		ctx = ctxIn;
		framebuffer = fbo;
		eagFramebuffer = eagFbo;
		width = sw;
		height = sh;
		gles3ColorRenderbuffer = _wglCreateRenderBuffer();
		gles3DepthRenderbuffer = _wglCreateRenderBuffer();
		_wglBindFramebuffer(_GL_FRAMEBUFFER, eagFbo);
		_wglBindRenderbuffer(_GL_RENDERBUFFER, gles3ColorRenderbuffer);
		_wglRenderbufferStorage(_GL_RENDERBUFFER, GL11.GL_RGBA8, sw, sh);
		_wglFramebufferRenderbuffer(_GL_FRAMEBUFFER, _GL_COLOR_ATTACHMENT0, _GL_RENDERBUFFER, gles3ColorRenderbuffer);
		_wglBindRenderbuffer(_GL_RENDERBUFFER, gles3DepthRenderbuffer);
		_wglRenderbufferStorage(_GL_RENDERBUFFER, _GL_DEPTH_COMPONENT32F, sw, sh);
		_wglFramebufferRenderbuffer(_GL_FRAMEBUFFER, _GL_DEPTH_ATTACHMENT, _GL_RENDERBUFFER, gles3DepthRenderbuffer);
		_wglDrawBuffers(_GL_COLOR_ATTACHMENT0);
	}
	
	public static void flipBuffer(int windowWidth, int windowHeight) {
		ctx.bindFramebuffer(_GL_READ_FRAMEBUFFER, framebuffer);
		ctx.bindFramebuffer(_GL_DRAW_FRAMEBUFFER, null);
		ctx.blitFramebuffer(0, 0, width, height, 0, 0, windowWidth, windowHeight, GL_COLOR_BUFFER_BIT, GL_NEAREST);
			
		ctx.bindFramebuffer(_GL_FRAMEBUFFER, framebuffer);
			
		if(windowWidth != width || windowHeight != height) {
			width = windowWidth;
			height = windowHeight;
				
			_wglBindRenderbuffer(_GL_RENDERBUFFER, gles3ColorRenderbuffer);
			_wglRenderbufferStorage(_GL_RENDERBUFFER, GL_RGBA8, windowWidth, windowHeight);
				
			_wglBindRenderbuffer(_GL_RENDERBUFFER, gles3DepthRenderbuffer);
			_wglRenderbufferStorage(_GL_RENDERBUFFER, _GL_DEPTH_COMPONENT32F, windowWidth, windowHeight);
		}
	}
	
	public static void destroy() {
		if(eagFramebuffer != null) {
			_wglDeleteFramebuffer(eagFramebuffer);
			eagFramebuffer = null;
		}
		if(gles3ColorRenderbuffer != null) {
			_wglDeleteRenderbuffer(gles3ColorRenderbuffer);
			gles3ColorRenderbuffer = null;
		}
		if(gles3DepthRenderbuffer != null) {
			_wglDeleteRenderbuffer(gles3DepthRenderbuffer);
			gles3DepthRenderbuffer = null;
		}
		framebuffer = null;
		width = 0;
		height = 0;
	}

}
