package net.PeytonPlayz585.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.PeytonPlayz585.glemu.FixedFunctionShader;
import net.PeytonPlayz585.glemu.GLObjectMap;

import net.lax1dude.eaglercraft.adapter.EaglerAdapterImpl2;
import net.PeytonPlayz585.glemu.vector.*;

import static net.PeytonPlayz585.glemu.StreamBuffer.StreamBufferInstance;

public class EaglerAdapterGL30 extends EaglerAdapterImpl2 {

	public static final int GL_ZERO = RealOpenGLEnums.GL_ZERO;
	public static final int GL_ONE = RealOpenGLEnums.GL_ONE;
	public static final int GL_TEXTURE_2D = RealOpenGLEnums.GL_TEXTURE_2D;
	public static final int GL_SMOOTH = RealOpenGLEnums.GL_SMOOTH;
	public static final int GL_DEPTH_TEST = RealOpenGLEnums.GL_DEPTH_TEST;
	public static final int GL_LEQUAL = RealOpenGLEnums.GL_LEQUAL;
	public static final int GL_ALPHA_TEST = RealOpenGLEnums.GL_ALPHA_TEST;
	public static final int GL_GREATER = RealOpenGLEnums.GL_GREATER;
	public static final int GL_BACK = RealOpenGLEnums.GL_BACK;
	public static final int GL_PROJECTION = RealOpenGLEnums.GL_PROJECTION;
	public static final int GL_MODELVIEW = RealOpenGLEnums.GL_MODELVIEW;
	public static final int GL_COLOR_BUFFER_BIT = RealOpenGLEnums.GL_COLOR_BUFFER_BIT;
	public static final int GL_DEPTH_BUFFER_BIT = RealOpenGLEnums.GL_DEPTH_BUFFER_BIT;
	public static final int GL_LIGHTING = RealOpenGLEnums.GL_LIGHTING;
	public static final int GL_FOG = RealOpenGLEnums.GL_FOG;
	public static final int GL_COLOR_MATERIAL = RealOpenGLEnums.GL_COLOR_MATERIAL;
	public static final int GL_BLEND = RealOpenGLEnums.GL_BLEND;
	public static final int GL_RGBA = RealOpenGLEnums.GL_RGBA;
	public static final int GL_UNSIGNED_BYTE = RealOpenGLEnums.GL_UNSIGNED_BYTE;
	public static final int GL_TEXTURE_WIDTH = RealOpenGLEnums.GL_TEXTURE_WIDTH;
	public static final int GL_LIGHT0 = RealOpenGLEnums.GL_LIGHT0;
	public static final int GL_LIGHT1 = RealOpenGLEnums.GL_LIGHT1;
	public static final int GL_POSITION = RealOpenGLEnums.GL_POSITION;
	public static final int GL_DIFFUSE = RealOpenGLEnums.GL_DIFFUSE;
	public static final int GL_SPECULAR = RealOpenGLEnums.GL_SPECULAR;
	public static final int GL_AMBIENT = RealOpenGLEnums.GL_AMBIENT;
	public static final int GL_FLAT = RealOpenGLEnums.GL_FLAT;
	public static final int GL_LIGHT_MODEL_AMBIENT = RealOpenGLEnums.GL_LIGHT_MODEL_AMBIENT;
	public static final int GL_FRONT_AND_BACK = RealOpenGLEnums.GL_FRONT_AND_BACK;
	public static final int GL_AMBIENT_AND_DIFFUSE = RealOpenGLEnums.GL_AMBIENT_AND_DIFFUSE;
	public static final int GL_MODELVIEW_MATRIX = RealOpenGLEnums.GL_MODELVIEW_MATRIX;
	public static final int GL_PROJECTION_MATRIX = RealOpenGLEnums.GL_PROJECTION_MATRIX;
	public static final int GL_VIEWPORT = RealOpenGLEnums.GL_VIEWPORT;
	public static final int GL_RESCALE_NORMAL = RealOpenGLEnums.GL_RESCALE_NORMAL;
	public static final int GL_SRC_ALPHA = RealOpenGLEnums.GL_SRC_ALPHA;
	public static final int GL_ONE_MINUS_SRC_ALPHA = RealOpenGLEnums.GL_ONE_MINUS_SRC_ALPHA;
	public static final int GL_ONE_MINUS_DST_COLOR = RealOpenGLEnums.GL_ONE_MINUS_DST_COLOR;
	public static final int GL_ONE_MINUS_SRC_COLOR = RealOpenGLEnums.GL_ONE_MINUS_SRC_COLOR;
	public static final int GL_CULL_FACE = RealOpenGLEnums.GL_CULL_FACE;
	public static final int GL_TEXTURE_MIN_FILTER = RealOpenGLEnums.GL_TEXTURE_MIN_FILTER;
	public static final int GL_TEXTURE_MAG_FILTER = RealOpenGLEnums.GL_TEXTURE_MAG_FILTER;
	public static final int GL_LINEAR = RealOpenGLEnums.GL_LINEAR;
	public static final int GL_COLOR_LOGIC_OP = RealOpenGLEnums.GL_COLOR_LOGIC_OP;
	public static final int GL_OR_REVERSE = RealOpenGLEnums.GL_OR_REVERSE;
	public static final int GL_EQUAL = RealOpenGLEnums.GL_EQUAL;
	public static final int GL_SRC_COLOR = RealOpenGLEnums.GL_SRC_COLOR;
	public static final int GL_TEXTURE = RealOpenGLEnums.GL_TEXTURE;
	public static final int GL_FRONT = RealOpenGLEnums.GL_FRONT;
	public static final int GL_COMPILE = RealOpenGLEnums.GL_COMPILE;
	public static final int GL_NEAREST = RealOpenGLEnums.GL_NEAREST;
	public static final int GL_CLAMP = RealOpenGLEnums.GL_CLAMP_TO_EDGE;
	public static final int GL_TEXTURE_WRAP_S = RealOpenGLEnums.GL_TEXTURE_WRAP_S;
	public static final int GL_TEXTURE_WRAP_T = RealOpenGLEnums.GL_TEXTURE_WRAP_T;
	public static final int GL_REPEAT = RealOpenGLEnums.GL_REPEAT;
	public static final int GL_BGRA = RealOpenGLEnums.GL_BGRA;
	public static final int GL_UNSIGNED_INT_8_8_8_8_REV = RealOpenGLEnums.GL_UNSIGNED_INT_8_8_8_8_REV;
	public static final int GL_DST_COLOR = RealOpenGLEnums.GL_DST_COLOR;
	public static final int GL_POLYGON_OFFSET_FILL = RealOpenGLEnums.GL_POLYGON_OFFSET_FILL;
	public static final int GL_NORMALIZE = RealOpenGLEnums.GL_NORMALIZE;
	public static final int GL_DST_ALPHA = RealOpenGLEnums.GL_DST_ALPHA;
	public static final int GL_FLOAT = RealOpenGLEnums.GL_FLOAT;
	public static final int GL_TEXTURE_COORD_ARRAY = RealOpenGLEnums.GL_TEXTURE_COORD_ARRAY;
	public static final int GL_SHORT = RealOpenGLEnums.GL_SHORT;
	public static final int GL_COLOR_ARRAY = RealOpenGLEnums.GL_COLOR_ARRAY;
	public static final int GL_VERTEX_ARRAY = RealOpenGLEnums.GL_VERTEX_ARRAY;
	public static final int GL_TRIANGLES = RealOpenGLEnums.GL_TRIANGLES;
	public static final int GL_NORMAL_ARRAY = RealOpenGLEnums.GL_NORMAL_ARRAY;
	public static final int GL_TEXTURE_3D = RealOpenGLEnums.GL_TEXTURE_3D;
	public static final int GL_FOG_MODE = RealOpenGLEnums.GL_FOG_MODE;
	public static final int GL_EXP = RealOpenGLEnums.GL_EXP;
	public static final int GL_FOG_DENSITY = RealOpenGLEnums.GL_FOG_DENSITY;
	public static final int GL_FOG_START = RealOpenGLEnums.GL_FOG_START;
	public static final int GL_FOG_END = RealOpenGLEnums.GL_FOG_END;
	public static final int GL_FOG_COLOR = RealOpenGLEnums.GL_FOG_COLOR;
	public static final int GL_TRIANGLE_STRIP = RealOpenGLEnums.GL_TRIANGLE_STRIP;
	public static final int GL_PACK_ALIGNMENT = RealOpenGLEnums.GL_PACK_ALIGNMENT;
	public static final int GL_UNPACK_ALIGNMENT = RealOpenGLEnums.GL_UNPACK_ALIGNMENT;
	public static final int GL_QUADS = RealOpenGLEnums.GL_QUADS;
	public static final int GL_INVALID_ENUM = RealOpenGLEnums.GL_INVALID_ENUM;
	public static final int GL_INVALID_VALUE = RealOpenGLEnums.GL_INVALID_VALUE;
	public static final int GL_INVALID_OPERATION = RealOpenGLEnums.GL_INVALID_OPERATION;
	public static final int GL_OUT_OF_MEMORY = RealOpenGLEnums.GL_OUT_OF_MEMORY;
	public static final int GL_CONTEXT_LOST_WEBGL = -144;
	public static final int GL_TRIANGLE_FAN = RealOpenGLEnums.GL_TRIANGLE_FAN;
	public static final int GL_LINE_STRIP = RealOpenGLEnums.GL_LINE_STRIP;
	public static final int GL_LINES = RealOpenGLEnums.GL_LINES;
	public static final int GL_NEAREST_MIPMAP_LINEAR = RealOpenGLEnums.GL_NEAREST_MIPMAP_LINEAR;
	public static final int GL_TEXTURE_MAX_ANISOTROPY = -150;
	public static final int GL_TEXTURE_MAX_LEVEL = RealOpenGLEnums.GL_TEXTURE_MAX_LEVEL;
	public static final int GL_LINEAR_MIPMAP_LINEAR = RealOpenGLEnums.GL_LINEAR_MIPMAP_LINEAR;
	public static final int GL_LINEAR_MIPMAP_NEAREST = RealOpenGLEnums.GL_LINEAR_MIPMAP_NEAREST;
	public static final int GL_NEAREST_MIPMAP_NEAREST = RealOpenGLEnums.GL_NEAREST_MIPMAP_NEAREST;

	public static final boolean isWebGL = _wisWebGL();

	private static final GLObjectMap<TextureGL> texObjects = new GLObjectMap(256);

	private static boolean enableTexture2D = false;
	private static boolean enableLighting = false;
	private static boolean enableAlphaTest = false;
	private static float alphaThresh = 0.1f;

	private static boolean isCompilingDisplayList = false;
	private static DisplayList compilingDisplayList = null;

	private static boolean enableColorArray = false;
	private static boolean enableNormalArray = false;
	private static boolean enableTex0Array = false;

	private static float colorR = 1.0f;
	private static float colorG = 1.0f;
	private static float colorB = 1.0f;
	private static float colorA = 1.0f;

	private static float normalX = 1.0f;
	private static float normalY = 0.0f;
	private static float normalZ = 0.0f;

	private static float tex0X = 0;
	private static float tex0Y = 0;

	private static boolean enableColorMaterial = false;

	private static float fogColorR = 1.0f;
	private static float fogColorG = 1.0f;
	private static float fogColorB = 1.0f;
	private static float fogColorA = 1.0f;
	private static int fogMode = 1;
	private static boolean fogEnabled = false;
	//private static boolean fogPremultiply = false;
	private static float fogStart = 1.0f;
	private static float fogEnd = 1.0f;
	private static float fogDensity = 1.0f;

	private static int bytesUploaded = 0;
	private static int vertexDrawn = 0;
	private static int triangleDrawn = 0;

	private static int matrixMode = GL_MODELVIEW;

	static Matrix4f[] matModelV = new Matrix4f[32];
	static int matModelPointer = 0;

	static Matrix4f[] matProjV = new Matrix4f[6];
	static int matProjPointer = 0;

	static Matrix4f[] matTexV = new Matrix4f[16];
	static int matTexPointer = 0;

	static {
		for (int i = 0; i < matModelV.length; ++i) {
			matModelV[i] = new Matrix4f();
		}
		for (int i = 0; i < matProjV.length; ++i) {
			matProjV[i] = new Matrix4f();
		}
		for (int i = 0; i < matTexV.length; ++i) {
			matTexV[i] = new Matrix4f();
		}
	}

	public static void glClearStack() {
		matModelV[0].load(matModelV[matModelPointer]);
		matModelPointer = 0;
		matProjV[0].load(matProjV[matProjPointer]);
		matProjPointer = 0;
		matTexV[0].load(matTexV[matTexPointer]);
		matTexPointer = 0;
	}

	private static BufferGL quadsToTrianglesBuffer = null;
	private static BufferArrayGL currentArray = null;

	private static class DisplayList {
		private final int id;
		private BufferArrayGL glarray;
		private BufferGL glbuffer;
		private int shaderMode;
		private int listLength;
		
		private List<Translate> translate = new ArrayList<Translate>();

		private DisplayList(int id) {
			this.id = id;
			this.glarray = null;
			this.glbuffer = null;
			this.shaderMode = -1;
			this.listLength = 0;
		}
	}
	
	private static class Translate {
		private float f, f1, f2;
		
		private Translate(float f, float f1, float f2) {
			this.f = f;
			this.f1 = f1;
			this.f2 = f2;
		}
	}

	private static final HashMap<Integer, DisplayList> displayLists = new HashMap();
	private static final HashMap<Integer, DisplayList> displayListsInitialized = new HashMap();

	public static final int getDisplayListCount() {
		return displayListsInitialized.size();
	}

	public static final void glEnable(int p1) {
		switch (p1) {
		case GL_DEPTH_TEST:
			_wglEnable(_wGL_DEPTH_TEST);
			break;
		case GL_CULL_FACE:
			_wglEnable(_wGL_CULL_FACE);
			break;
		case GL_BLEND:
			_wglEnable(_wGL_BLEND);
			break;
		case GL_RESCALE_NORMAL:
			break;
		case GL_TEXTURE_2D:
			enableTexture2D = true;
			break;
		case GL_LIGHTING:
			enableLighting = true;
			break;
		case GL_ALPHA_TEST:
			enableAlphaTest = true;
			break;
		case GL_FOG:
			fogEnabled = true;
			break;
		case GL_COLOR_MATERIAL:
			enableColorMaterial = true;
			break;
		case GL_POLYGON_OFFSET_FILL:
			_wglEnable(_wGL_POLYGON_OFFSET_FILL);
		default:
			break;
		}
	}

	static float clearDepth = -999.0f;
	public static final void glClearDepth(float p1) {
		p1 = 1.0f - p1;
		if(p1 != clearDepth) {
			_wglClearDepth(p1);
			clearDepth = p1;
		}
	}

	public static final int GL_GEQUAL = RealOpenGLEnums.GL_GEQUAL;
	public static final int GL_LESS = RealOpenGLEnums.GL_LESS;
	static int stateDepthFunc = -1;
	public static final void glDepthFunc(int p1) {
		int rev = p1;
		switch(p1) {
		case GL_GREATER:
			rev = _wGL_LESS;
			break;
		case GL_GEQUAL:
			rev = _wGL_LEQUAL;
			break;
		case GL_EQUAL:
			rev = _wGL_EQUAL;
			break;
		case GL_LEQUAL:
			rev = _wGL_GEQUAL;
			break;
		case GL_LESS:
			rev = _wGL_GREATER;
			break;
		}
		if(rev != stateDepthFunc) {
			_wglDepthFunc(rev);
			stateDepthFunc = rev;
		}
	}

	public static final void glAlphaFunc(int p1, float p2) {
		alphaThresh = p2;
	}

	private static int stateCullFace = -1;
	public static final void glCullFace(int p1) {
		if(p1 != stateCullFace) {
			stateCullFace = p1;
			_wglCullFace(p1);
		}
	}

	public static final void glMatrixMode(int p1) {
		matrixMode = p1;
	}

	private static final Matrix4f getMatrix() {
		switch (matrixMode) {
		case GL_MODELVIEW:
		default:
			return matModelV[matModelPointer];
		case GL_PROJECTION:
			return matProjV[matProjPointer];
		case GL_TEXTURE:
			return matTexV[matTexPointer];
		}
	}

	public static final void glLoadIdentity() {
		getMatrix().setIdentity();
	}

	public static final void glViewport(int p1, int p2, int p3, int p4) {
		_wglViewport(p1, p2, p3, p4);
	}

	public static final void glClear(int p1) {
		_wglClear(p1);
	}
	
	public static final void glOrtho(double left, double right, double bottom, double top, double zNear, double zFar) {
		Matrix4f res = getMatrix();
		res.m00 = (float) (2.0f / (right - left));
		res.m01 = 0.0f;
		res.m02 = 0.0f;
		res.m03 = 0.0f;
		res.m10 = 0.0f;
		res.m11 = (float) (2.0f / (top - bottom));
		res.m12 = 0.0f;
		res.m13 = 0.0f;
		res.m20 = 0.0f;
		res.m21 = 0.0f;
		res.m22 = (float) (2.0f / (zFar - zNear));
		res.m23 = 0.0f;
		res.m30 = (float) (-(right + left) / (right - left));
		res.m31 = (float) (-(top + bottom) / (top - bottom));
		res.m32 = (float) ((zFar + zNear) / (zFar - zNear));
		res.m33 = 1.0f;
	}

	public static final void glOrtho(float left, float right, float bottom, float top, float zNear, float zFar) {
		Matrix4f res = getMatrix();
		res.m00 = 2.0f / (right - left);
		res.m01 = 0.0f;
		res.m02 = 0.0f;
		res.m03 = 0.0f;
		res.m10 = 0.0f;
		res.m11 = 2.0f / (top - bottom);
		res.m12 = 0.0f;
		res.m13 = 0.0f;
		res.m20 = 0.0f;
		res.m21 = 0.0f;
		res.m22 = 2.0f / (zFar - zNear);
		res.m23 = 0.0f;
		res.m30 = -(right + left) / (right - left);
		res.m31 = -(top + bottom) / (top - bottom);
		res.m32 = (zFar + zNear) / (zFar - zNear);
		res.m33 = 1.0f;
	}

	private static final Vector3f deevis = new Vector3f();

	public static final void glTranslatef(float p1, float p2, float p3) {
		deevis.set(p1, p2, p3);
		getMatrix().translate(deevis);
		if (isCompilingDisplayList) {
			compilingDisplayList.translate.add(new Translate(p1, p2, p3));
			//throw new IllegalArgumentException("matrix is not supported while recording display list use tessellator class instead");
		}
	}

	public static final void glClearColor(float p1, float p2, float p3, float p4) {
		_wglClearColor(p1, p2, p3, p4);
	}

	public static final void glDisable(int p1) {
		switch (p1) {
		case GL_DEPTH_TEST:
			_wglDisable(_wGL_DEPTH_TEST);
			break;
		case GL_CULL_FACE:
			_wglDisable(_wGL_CULL_FACE);
			break;
		case GL_BLEND:
			_wglDisable(_wGL_BLEND);
			break;
		case GL_RESCALE_NORMAL:
			break;
		case GL_TEXTURE_2D:
			enableTexture2D = false;
			break;
		case GL_LIGHTING:
			enableLighting = false;
			break;
		case GL_ALPHA_TEST:
			enableAlphaTest = false;
			break;
		case GL_FOG:
			fogEnabled = false;
			break;
		case GL_COLOR_MATERIAL:
			enableColorMaterial = false;
			break;
		case GL_POLYGON_OFFSET_FILL:
			_wglDisable(_wGL_POLYGON_OFFSET_FILL);
		default:
			break;
		}
	}

	public static final void glColor4f(float p1, float p2, float p3, float p4) {
		colorR = p1;
		colorG = p2;
		colorB = p3;
		colorA = p4;
	}

	public static final int glGetError() {
		int err = _wglGetError();
		if (err == _wGL_CONTEXT_LOST_WEBGL)
			return GL_CONTEXT_LOST_WEBGL;
		return err;
	}

	public static final void glFlush() {
		_wglFlush();
	}

	public static final void glLineWidth(float p1) {
		webgl.lineWidth(p1);
	}

	public static final void glTexImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8,
			ByteBuffer p9) {
		_wglTexImage2D(_wGL_TEXTURE_2D, p2, _wGL_RGBA8, p4, p5, p6, _wGL_RGBA, _wGL_UNSIGNED_BYTE, p9);
	}

	private static Vector4f lightPos0vec0 = new Vector4f();
	private static Vector4f lightPos1vec0 = new Vector4f();
	private static Vector4f lightPos0vec = new Vector4f();
	private static Vector4f lightPos1vec = new Vector4f();
	
	public static final void copyModelToLightMatrix() {
		lightPos0vec0.set(lightPos0vec);
		lightPos1vec0.set(lightPos1vec);
		lightPos0vec.set(0.2f, 1.0f, -0.7f, 0.0f);
		lightPos0vec.normalise();
		lightPos1vec.set(-0.2f, 1.0f, 0.7f, 0.0f);
		lightPos1vec.normalise();
		Matrix4f.transform(matModelV[matModelPointer], lightPos0vec, lightPos0vec).normalise();
		Matrix4f.transform(matModelV[matModelPointer], lightPos1vec, lightPos1vec).normalise();
		lightPos0vec.x = -lightPos0vec.x;
		lightPos1vec.x = -lightPos1vec.x;
		lightPos0vec.y = -lightPos0vec.y;
		lightPos1vec.y = -lightPos1vec.y;
		lightPos0vec.z = -lightPos0vec.z;
		lightPos1vec.z = -lightPos1vec.z;
	}

	public static final void revertLightMatrix() {
		lightPos0vec.set(lightPos0vec0);
		lightPos1vec.set(lightPos1vec0);
	}

	public static final void glPushMatrix() {
		switch (matrixMode) {
		case GL_MODELVIEW:
		default:
			if (matModelPointer < matModelV.length - 1) {
				++matModelPointer;
				matModelV[matModelPointer].load(matModelV[matModelPointer - 1]);
			} else {
				System.err.println("modelview matrix stack overflow");
			}
			break;
		case GL_PROJECTION:
			if (matProjPointer < matProjV.length - 1) {
				++matProjPointer;
				matProjV[matProjPointer].load(matProjV[matProjPointer - 1]);
			} else {
				System.err.println("projection matrix stack overflow");
			}
			break;
		case GL_TEXTURE:
			if (matTexPointer < matTexV.length - 1) {
				++matTexPointer;
				matTexV[matTexPointer].load(matTexV[matTexPointer - 1]);
			} else {
				System.err.println("texture matrix stack overflow");
			}
			break;
		}
	}

	private static final float toRad = 0.0174532925f;

	public static final void glRotatef(float p1, float p2, float p3, float p4) {
		deevis.set(p2, p3, p4);
		getMatrix().rotate(p1 * toRad, deevis);
		if (isCompilingDisplayList) {
			throw new IllegalArgumentException("matrix is not supported while recording display list use tessellator class instead");
		}
	}

	public static final void glPopMatrix() {
		switch (matrixMode) {
		case GL_MODELVIEW:
		default:
			if (matModelPointer > 0) {
				--matModelPointer;
			} else {
				System.err.println("modelview matrix stack underflow");
			}
			break;
		case GL_PROJECTION:
			if (matProjPointer > 0) {
				--matProjPointer;
			} else {
				System.err.println("projection matrix stack underflow");
			}
			break;
		case GL_TEXTURE:
			if (matTexPointer > 0) {
				--matTexPointer;
			} else {
				System.err.println("texture matrix stack underflow");
			}
			break;
		}
	}

	public static final void glColorMaterial(int p1, int p2) {

	}

	public static final void glGetFloat(int p1, FloatBuffer p2) {
		switch (p1) {
		case GL_MODELVIEW_MATRIX:
		default:
			matModelV[matModelPointer].store(p2);
			break;
		case GL_PROJECTION_MATRIX:
			matProjV[matProjPointer].store(p2);
			break;
		}
	}

	public static final void glGetInteger(int p1, int[] p2) {
		if (p1 == GL_VIEWPORT) {
			_wglGetParameter(_wGL_VIEWPORT, 4, p2);
		}
	}

	public static final void glScalef(float p1, float p2, float p3) {
		deevis.set(p1, p2, p3);
		getMatrix().scale(deevis);
		if (isCompilingDisplayList) {
			throw new IllegalArgumentException("matrix is not supported while recording display list use tessellator class instead");
		}
	}
	
	public static final void glScaled(double p1, double p2, double p3) {
		deevis.set((float)p1, (float)p2, (float)p3);
		getMatrix().scale(deevis);
		if (isCompilingDisplayList) {
			throw new IllegalArgumentException("matrix is not supported while recording display list use tessellator class instead");
		}
	}

	static int stateBlendSRC = -1;
	static int stateBlendDST = -1;
	public static final void glBlendFunc(int p1, int p2) {
//		fogPremultiply = (p1 == GL_ONE && p2 == GL_ONE_MINUS_SRC_ALPHA);
//		if(overlayFBOBlending) {
//			_wglBlendFuncSeparate(p1, p2, GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
//		}else {
//			_wglBlendFunc(p1, p2);
//		}
		if(overlayFBOBlending) {
			glBlendFuncSeparate(p1, p2, 0, 1);
			return;
		}
		int srcBits = (p1 | (p1 << 16));
		int dstBits = (p2 | (p2 << 16));
		if(srcBits != stateBlendSRC || dstBits != stateBlendDST) {
			_wglBlendFunc(p1, p2);
			stateBlendSRC = srcBits;
			stateBlendDST = dstBits;
		}
	}
	
	private static boolean overlayFBOBlending = false;

	public static final void enableOverlayFramebufferBlending(boolean en) {
		overlayFBOBlending = en;
	}
	
	public static final void glBlendFuncSeparate(int p1, int p2, int p3, int p4) {
//		fogPremultiply = (p3 == GL_ONE && p4 == GL_ONE_MINUS_SRC_ALPHA);
//		_wglBlendFuncSeparate(p1, p2, p3, p4);
		if(overlayFBOBlending) { // game overlay framebuffer in EntityRenderer.java
			p3 = GL_ONE;
			p4 = GL_ONE_MINUS_SRC_ALPHA;
		}
		int srcBits = (p1 | (p3 << 16));
		int dstBits = (p2 | (p4 << 16));
		if(srcBits != stateBlendSRC || dstBits != stateBlendDST) {
			_wglBlendFuncSeparate(p1, p2, p3, p4);
			stateBlendSRC = srcBits;
			stateBlendDST = dstBits;
		}
	}

	private static boolean stateDepthMask = true;
	public static final void glDepthMask(boolean p1) {
		if(p1 != stateDepthMask) {
			stateDepthMask = p1;
			_wglDepthMask(p1);
		}
	}

	public static final void glColorMask(boolean p1, boolean p2, boolean p3, boolean p4) {
		_wglColorMask(p1, p2, p3, p4);
	}

	public static final void glBindTexture(int p1, int p2) {
		TextureGL t = texObjects.get(p2);
		_wglBindTexture(_wGL_TEXTURE_2D, t);
	}
	
	public static final void glBindTexture(int p2) {
		TextureGL t = texObjects.get(p2);
		_wglBindTexture(_wGL_TEXTURE_2D, t);
	}
	
	public static final void glBindTexture(int p1, TextureGL p2) {
		_wglBindTexture(_wGL_TEXTURE_2D, p2);
	}

	public static final void glCopyTexSubImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {
		_wglCopyTexSubImage2D(_wGL_TEXTURE_2D, p2, p3, p4, p5, p6, p7, p8);
	}

	public static final void glTexParameteri(int p1, int p2, int p3) {
		if(p3 == RealOpenGLEnums.GL_CLAMP_TO_EDGE || p3 == 10496) {
			p3 = _wGL_CLAMP;
		}
		_wglTexParameteri(p1, p2, p3);
	}

	public static final void glTexParameterf(int p1, int p2, float p3) {
		int pp1 = 0;
		switch (p1) {
		default:
		case GL_TEXTURE_2D:
			pp1 = _wGL_TEXTURE_2D;
			break;
		// case GL_TEXTURE_3D: pp1 = _wGL_TEXTURE_3D; break;
		}
		int pp2 = 0;
		switch (p2) {
		default:
		case GL_TEXTURE_MAX_ANISOTROPY:
			pp2 = _wGL_TEXTURE_MAX_ANISOTROPY;
			break;
		}
		_wglTexParameterf(pp1, pp2, p3);
	}

	public static final void glLogicOp(int p1) {

	}

	public static final void glNormal3f(float p1, float p2, float p3) {
		//float len = (float) Math.sqrt(p1 * p1 + p2 * p2 + p3 * p3);
		normalX = p1 /*/ len*/;
		normalY = p2 /*/ len*/;
		normalZ = p3 /*/ len*/;
	}

	public static final int glGenLists(int p1) {
		int base = displayListId + 1;
		for (int i = 0; i < p1; i++) {
			int id = ++displayListId;
			displayLists.put(id, new DisplayList(id));
		}
		return base;
	}

	public static final void _wglBindVertexArray0(BufferArrayGL p1) {
		currentArray = p1;
		_wglBindVertexArray(p1);
	}

	private static int displayListId = 0;

	public static final void glCallList(int p1) {
		if (!isCompilingDisplayList) {
			DisplayList d = displayListsInitialized.get(p1);
			if (d != null && d.listLength > 0) {
				bindTheShader(d.shaderMode | getShaderModeFlag1());
				_wglBindVertexArray0(d.glarray);
				
				//glTranslate support for display lists
				for(Translate t : d.translate) {
					deevis.set(t.f, t.f1, t.f2);
					getMatrix().translate(deevis);
				}
				
				_wglDrawQuadArrays(0, d.listLength);
				
				shader.unuseProgram();
				vertexDrawn += d.listLength * 6 / 4;
				triangleDrawn += d.listLength / 2;
			}
		}
	}

	public static final void glNewList(int p1, int p2) {
		if (!isCompilingDisplayList) {
			compilingDisplayList = displayLists.get(p1);
			if (compilingDisplayList != null) {
				compilingDisplayList.shaderMode = -1;
				compilingDisplayList.listLength = 0;
				isCompilingDisplayList = true;
			}
		}
	}

	public static final void glEndList() {
		if (isCompilingDisplayList) {
			isCompilingDisplayList = false;
			Object upload = _wGetLowLevelBuffersAppended();
			int l = _wArrayByteLength(upload);
			if (l > 0) {
				if (compilingDisplayList.glbuffer == null) {
					displayListsInitialized.put(compilingDisplayList.id, compilingDisplayList);
					compilingDisplayList.glarray = _wglCreateVertexArray();
					compilingDisplayList.glbuffer = _wglCreateBuffer();
					FixedFunctionShader f = FixedFunctionShader.instance(compilingDisplayList.shaderMode);
					_wglBindVertexArray0(compilingDisplayList.glarray);
					_wglBindBuffer(_wGL_ARRAY_BUFFER, compilingDisplayList.glbuffer);
					f.setupArrayForProgram();
				}
				_wglBindBuffer(_wGL_ARRAY_BUFFER, compilingDisplayList.glbuffer);
				_wglBufferData(_wGL_ARRAY_BUFFER, upload, _wGL_STATIC_DRAW);
				bytesUploaded += l;
			}
		}
	}
	
	public static final void flushDisplayList(int p1) {
		DisplayList d = displayListsInitialized.get(p1);
		if (d != null) {
			if (d.glbuffer != null) {
				_wglDeleteBuffer(d.glbuffer);
				_wglDeleteVertexArray(d.glarray);
				d.glbuffer = null;
				d.glarray = null;
			}
		}
	}

	public static final void glColor3f(float p1, float p2, float p3) {
		colorR = p1;
		colorG = p2;
		colorB = p3;
		colorA = 1.0f;
	}

	public static final void glTexImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8,
			IntBuffer p9) {
		/*
		 * int pp2 = 0; switch(p3) { default: case GL_RGBA: pp2 = _wGL_RGBA; break; case
		 * GL_BGRA: pp2 = _wGL_BGRA; break; } int pp3 = 0; switch(p7) { default: case
		 * GL_RGBA: pp3 = _wGL_RGBA; break; case GL_BGRA: pp3 = _wGL_BGRA; break; }
		 */
		bytesUploaded += p9.remaining() * 4;
		_wglTexImage2D(_wGL_TEXTURE_2D, p2, _wGL_RGBA8, p4, p5, p6, _wGL_RGBA, _wGL_UNSIGNED_BYTE, p9);
	}

	public static final void glTexImage2D_2(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8,
			IntBuffer p9) {
		bytesUploaded += p9.remaining() * 4;
		_wglTexImage2D(_wGL_TEXTURE_2D, p2, _wGL_RGB8, p4, p5, p6, _wGL_RGB, _wGL_UNSIGNED_BYTE, p9);
	}

	public static final void glTexSubImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8,
			IntBuffer p9) {
		int pp1 = 0;
		switch (p1) {
		default:
		case GL_TEXTURE_2D:
			pp1 = _wGL_TEXTURE_2D;
			break;
		// case GL_TEXTURE_3D: pp1 = _wGL_TEXTURE_3D; break;
		}
		/*
		 * int pp3 = 0; switch(p7) { default: case GL_RGBA: pp3 = _wGL_RGBA; break; case
		 * GL_BGRA: pp3 = _wGL_BGRA; break; }
		 */
		bytesUploaded += p9.remaining() * 4;
		_wglTexSubImage2D(pp1, p2, p3, p4, p5, p6, _wGL_RGBA, _wGL_UNSIGNED_BYTE, p9);
	}

	public static final void glDeleteTextures(int p1) {
		_wglDeleteTextures(texObjects.free(p1));
	}

	public static final void glPolygonOffset(float p1, float p2) {
		//if(p1 != 0) {
			p1 = -p1;
		//}
		//if(p2 != 0) {
			p2 = -p2;
		//}
		_wglPolygonOffset(-p1, -p2);
	}

	public static final void glCallLists(IntBuffer p1) {
		while (p1.hasRemaining()) {
			glCallList(p1.get());
		}
	}

	public static final void glEnableVertexAttrib(int p1) {
		switch (p1) {
		case GL_COLOR_ARRAY:
			enableColorArray = true;
			break;
		case GL_NORMAL_ARRAY:
			enableNormalArray = true;
			break;
		case GL_TEXTURE_COORD_ARRAY:
			enableTex0Array = true;
			break;
		default:
			break;
		}
	}

	public static final void glDisableVertexAttrib(int p1) {
		switch (p1) {
		case GL_COLOR_ARRAY:
			enableColorArray = false;
			break;
		case GL_NORMAL_ARRAY:
			enableNormalArray = false;
			break;
		case GL_TEXTURE_COORD_ARRAY:
			enableTex0Array = false;
			break;
		default:
			break;
		}
	}

	private static final int getShaderModeFlag0() {
		int mode = 0;
		mode = (mode | (enableColorArray ? FixedFunctionShader.COLOR : 0));
		mode = (mode | (enableNormalArray ? FixedFunctionShader.NORMAL : 0));
		mode = (mode | (enableTex0Array ? FixedFunctionShader.TEXTURE0 : 0));
		return mode;
	}

	private static final int getShaderModeFlag1() {
		int mode = 0;
		mode = (mode | ((enableColorMaterial && enableLighting) ? FixedFunctionShader.LIGHTING : 0));
		mode = (mode | (fogEnabled ? FixedFunctionShader.FOG : 0));
		mode = (mode | (enableAlphaTest ? FixedFunctionShader.ALPHATEST : 0));
		mode = (mode | (enableTexture2D ? FixedFunctionShader.UNIT0 : 0));
		return mode;
	}

	private static final int getShaderModeFlag() {
		int mode = 0;
		mode = (mode | (enableColorArray ? FixedFunctionShader.COLOR : 0));
		mode = (mode | (enableNormalArray ? FixedFunctionShader.NORMAL : 0));
		mode = (mode | (enableTex0Array ? FixedFunctionShader.TEXTURE0 : 0));
		mode = (mode | ((enableColorMaterial && enableLighting) ? FixedFunctionShader.LIGHTING : 0));
		mode = (mode | (fogEnabled ? FixedFunctionShader.FOG : 0));
		mode = (mode | (enableAlphaTest ? FixedFunctionShader.ALPHATEST : 0));
		mode = (mode | (enableTexture2D ? FixedFunctionShader.UNIT0 : 0));
		return mode;
	}

	private static FixedFunctionShader shader = null;

	private static final void bindTheShader() {
		bindTheShader(getShaderModeFlag());
	}

	private static final void bindTheShader(int mode) {
		FixedFunctionShader s = shader = FixedFunctionShader.instance(mode);
		s.useProgram();
		if (enableAlphaTest) {
			s.setAlphaTest(alphaThresh);
		}
		s.setColor(colorR, colorG, colorB, colorA);
		if (fogEnabled) {
			s.setFogMode(fogMode);
			s.setFogColor(fogColorR, fogColorG, fogColorB, fogColorA);
			s.setFogDensity(fogDensity);
			s.setFogStartEnd(fogStart, fogEnd);
		}
		s.setModelMatrix(matModelV[matModelPointer]);
		s.setProjectionMatrix(matProjV[matProjPointer]);
		s.setTextureMatrix(matTexV[matTexPointer]);
		if (enableColorMaterial && enableLighting) {
			s.setNormal(normalX, normalY, normalZ);
			s.setLightPositions(lightPos0vec, lightPos1vec);
		}
		s.setTex0Coords(tex0X, tex0Y);
	}

	private static Object blankUploadArray = _wCreateLowLevelIntBuffer(525000);

	public static final void glDrawArrays(int p1, int p2, int p3, Object buffer) {
		if (isCompilingDisplayList) {
			if (p1 == GL_QUADS) {
				if (compilingDisplayList.shaderMode == -1) {
					compilingDisplayList.shaderMode = getShaderModeFlag0();
				} else {
					if (compilingDisplayList.shaderMode != getShaderModeFlag0()) {
						System.err.println("vertex format inconsistent in display list");
					}
				}
				compilingDisplayList.listLength += p3;
				_wAppendLowLevelBuffer(buffer);
			} else {
				System.err.println("only GL_QUADS supported in a display list");
			}
		} else {
			int bl = _wArrayByteLength(buffer);
			bytesUploaded += bl;
			vertexDrawn += p3;

			bindTheShader();

			StreamBufferInstance sb = shader.streamBuffer.getBuffer(bl);
			_wglBindVertexArray0(sb.vertexArray);
			_wglBindBuffer(_wGL_ARRAY_BUFFER, sb.vertexBuffer);
			if (!shader.bufferIsInitialized) {
				shader.bufferIsInitialized = true;
				_wglBufferData(_wGL_ARRAY_BUFFER, blankUploadArray, _wGL_DYNAMIC_DRAW);
			}
			_wglBufferSubData(_wGL_ARRAY_BUFFER, 0, buffer);

			if (p1 == GL_QUADS) {
				_wglDrawQuadArrays(p2, p3);
				triangleDrawn += p3 / 2;
			} else {
				int drawMode = 0;
				switch (p1) {
				default:
				case GL_TRIANGLES:
					drawMode = _wGL_TRIANGLES;
					triangleDrawn += p3 / 3;
					break;
				case GL_TRIANGLE_STRIP:
					drawMode = _wGL_TRIANGLE_STRIP;
					triangleDrawn += p3 - 2;
					break;
				case GL_TRIANGLE_FAN:
					drawMode = _wGL_TRIANGLE_FAN;
					triangleDrawn += p3 - 2;
					break;
				case GL_LINE_STRIP:
					drawMode = _wGL_LINE_STRIP;
					triangleDrawn += p3 - 1;
					break;
				case GL_LINES:
					drawMode = _wGL_LINES;
					triangleDrawn += p3 / 2;
					break;
				}
				_wglDrawArrays(drawMode, p2, p3);
			}

			shader.unuseProgram();

		}
	}

	private static final void _wglDrawQuadArrays(int p2, int p3) {
		if (quadsToTrianglesBuffer == null) {
			IntBuffer upload = isWebGL ? IntBuffer.wrap(new int[98400 / 2])
					: ByteBuffer.allocateDirect(98400 * 2).order(ByteOrder.nativeOrder()).asIntBuffer();
			for (int i = 0; i < 16384; ++i) {
				int v1 = i * 4;
				int v2 = i * 4 + 1;
				int v3 = i * 4 + 2;
				int v4 = i * 4 + 3;
				upload.put(v1 | (v2 << 16));
				upload.put(v4 | (v2 << 16));
				upload.put(v3 | (v4 << 16));
			}
			upload.flip();
			quadsToTrianglesBuffer = _wglCreateBuffer();
			_wglBindBuffer(_wGL_ELEMENT_ARRAY_BUFFER, quadsToTrianglesBuffer);
			_wglBufferData0(_wGL_ELEMENT_ARRAY_BUFFER, upload, _wGL_STATIC_DRAW);
		}
		if (!currentArray.isQuadBufferBound) {
			currentArray.isQuadBufferBound = true;
			_wglBindBuffer(_wGL_ELEMENT_ARRAY_BUFFER, quadsToTrianglesBuffer);
		}
		_wglDrawElements(_wGL_TRIANGLES, p3 * 6 / 4, _wGL_UNSIGNED_SHORT, p2 * 6 / 4);
	}

	private static BufferArrayGL occlusion_vao = null;
	private static BufferGL occlusion_vbo = null;
	private static ProgramGL occlusion_program = null;
	private static UniformGL occlusion_matrix_m = null;
	private static UniformGL occlusion_matrix_p = null;

	private static final void initializeOcclusionObjects() {
		occlusion_vao = _wglCreateVertexArray();
		occlusion_vbo = _wglCreateBuffer();

		IntBuffer upload = (isWebGL ? IntBuffer.wrap(new int[108])
				: ByteBuffer.allocateDirect(108 << 2).order(ByteOrder.nativeOrder()).asIntBuffer());
		float[] verts = new float[] { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f,
				1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f,
				1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f };
		for (int i = 0; i < verts.length; i++) {
			upload.put(Float.floatToRawIntBits(verts[i]));
		}
		upload.flip();

		_wglBindVertexArray(occlusion_vao);
		_wglBindBuffer(_wGL_ARRAY_BUFFER, occlusion_vbo);
		_wglBufferData0(_wGL_ARRAY_BUFFER, upload, _wGL_STATIC_DRAW);
		_wglEnableVertexAttribArray(0);
		_wglVertexAttribPointer(0, 3, _wGL_FLOAT, false, 12, 0);

		ShaderGL vert = _wglCreateShader(_wGL_VERTEX_SHADER);
		ShaderGL frag = _wglCreateShader(_wGL_FRAGMENT_SHADER);

		String src = fileContents("/glsl/occl.glsl");
		_wglShaderSource(vert, _wgetShaderHeader() + "\n#define CC_VERT\n" + src);
		_wglShaderSource(frag, _wgetShaderHeader() + "\n#define CC_FRAG\n" + src);

		_wglCompileShader(vert);
		if (!_wglGetShaderCompiled(vert))
			System.err.println(("\n" + _wglGetShaderInfoLog(vert)).replace("\n", "\n[/glsl/occl.glsl][VERT] ") + "\n");

		_wglCompileShader(frag);
		if (!_wglGetShaderCompiled(frag))
			System.err.println(("\n" + _wglGetShaderInfoLog(frag)).replace("\n", "\n[/glsl/occl.glsl][FRAG] ") + "\n");

		occlusion_program = _wglCreateProgram();

		_wglAttachShader(occlusion_program, vert);
		_wglAttachShader(occlusion_program, frag);
		_wglLinkProgram(occlusion_program);
		_wglDetachShader(occlusion_program, vert);
		_wglDetachShader(occlusion_program, frag);
		_wglDeleteShader(vert);
		_wglDeleteShader(frag);

		if (!_wglGetProgramLinked(occlusion_program))
			System.err.println(
					("\n\n" + _wglGetProgramInfoLog(occlusion_program)).replace("\n", "\n[/glsl/occl.glsl][LINKER] "));

		_wglUseProgram(occlusion_program);
		occlusion_matrix_m = _wglGetUniformLocation(occlusion_program, "matrix_m");
		occlusion_matrix_p = _wglGetUniformLocation(occlusion_program, "matrix_p");

	}

	private static final GLObjectMap<QueryGL> queryObjs = new GLObjectMap(256);

	public static final int glCreateQuery() {
		return queryObjs.register(_wglCreateQuery());
	}

	public static final void glBeginQuery(int obj) {
		_wglBeginQuery(_wGL_ANY_SAMPLES_PASSED, queryObjs.get(obj));
	}

	public static final void glDeleteQuery(int obj) {
		_wglDeleteQuery(queryObjs.free(obj));
	}

	private static final Matrix4f cachedOcclusionP = (Matrix4f) (new Matrix4f()).setZero();
	private static float[] occlusionModel = new float[16];
	private static float[] occlusionProj = new float[16];

	public static final void glBindOcclusionBB() {
		if (occlusion_vao == null)
			initializeOcclusionObjects();
		_wglUseProgram(occlusion_program);
		_wglBindVertexArray(occlusion_vao);
		if (!cachedOcclusionP.equals(matProjV[matProjPointer])) {
			cachedOcclusionP.load(matProjV[matProjPointer]);
			cachedOcclusionP.store(occlusionProj);
			_wglUniformMat4fv(occlusion_matrix_p, occlusionProj);
		}
	}

	public static final void glEndOcclusionBB() {

	}

	public static final void glDrawOcclusionBB(float posX, float posY, float posZ, float sizeX, float sizeY,
			float sizeZ) {
		glPushMatrix();
		glTranslatef(posX - sizeX * 0.01f, posY - sizeY * 0.01f, posZ - sizeZ * 0.01f);
		glScalef(sizeX * 1.02f, sizeY * 1.02f, sizeZ * 1.02f);
		matModelV[matModelPointer].store(occlusionModel);
		_wglUniformMat4fv(occlusion_matrix_m, occlusionModel);
		_wglDrawArrays(_wGL_TRIANGLES, 0, 36);
		glPopMatrix();

	}

	public static final void glEndQuery() {
		_wglEndQuery(_wGL_ANY_SAMPLES_PASSED);
	}

	public static final int glGenTextures() {
		return texObjects.register(_wglGenTextures());
	}

	public static final void glTexSubImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8,
			ByteBuffer p9) {
		int pp1 = 0;
		switch (p1) {
		default:
		case GL_TEXTURE_2D:
			pp1 = _wGL_TEXTURE_2D;
			break;
		// case GL_TEXTURE_3D: pp1 = _wGL_TEXTURE_3D; break;
		}
		/*
		 * int pp3 = 0; switch(p7) { default: case GL_RGBA: pp3 = _wGL_RGBA; break; case
		 * GL_BGRA: pp3 = _wGL_BGRA; break; }
		 */
		bytesUploaded += p9.remaining();
		_wglTexSubImage2D(pp1, p2, p3, p4, p5, p6, _wGL_RGBA, _wGL_UNSIGNED_BYTE, p9);
	}

	public static final void glFogi(int p1, int p2) {
		if (p1 == GL_FOG_MODE) {
			switch (p2) {
			default:
			case GL_LINEAR:
				fogMode = 1;
				break;
			case GL_EXP:
				fogMode = 2;
				break;
			}
		}
	}

	public static final void glFogf(int p1, float p2) {
		switch (p1) {
		case GL_FOG_START:
			fogStart = p2;
			break;
		case GL_FOG_END:
			fogEnd = p2;
			break;
		case GL_FOG_DENSITY:
			fogDensity = p2;
			break;
		default:
			break;
		}
	}

	public static final void glFog(int p1, FloatBuffer p2) {
		if (p1 == GL_FOG_COLOR) {
			fogColorR = p2.get();
			fogColorG = p2.get();
			fogColorB = p2.get();
			fogColorA = p2.get();
		}
	}

	public static final void glDeleteLists(int p1, int p2) {
		for (int i = 0; i < p2; i++) {
			DisplayList d = displayListsInitialized.remove(p1 + i);
			if (d != null) {
				_wglDeleteVertexArray(d.glarray);
				_wglDeleteBuffer(d.glbuffer);
			}
			displayLists.remove(p1 + i);
		}
	}

	public static final void glMultiTexCoord2f(int p1, float p2, float p3) {
		tex0X = p2;
		tex0Y = p3;
	}

	private static Matrix4f unprojA = new Matrix4f();
	private static Matrix4f unprojB = new Matrix4f();
	private static Vector4f unprojC = new Vector4f();

	public static final void gluUnProject(float p1, float p2, float p3, FloatBuffer p4, FloatBuffer p5, int[] p6,
			FloatBuffer p7) {
		unprojA.load(p4);
		unprojB.load(p5);
		Matrix4f.mul(unprojA, unprojB, unprojB);
		unprojB.invert();
		unprojC.set(((p1 - (float) p6[0]) / (float) p6[2]) * 2f - 1f, ((p2 - (float) p6[1]) / (float) p6[3]) * 2f - 1f,
				p3, 1.0f);
		Matrix4f.transform(unprojB, unprojC, unprojC);
		p7.put(unprojC.x / unprojC.w);
		p7.put(unprojC.y / unprojC.w);
		p7.put(unprojC.z / unprojC.w);
	}

	public static final void gluPerspective(float fovy, float aspect, float zNear, float zFar) {
		Matrix4f res = getMatrix();
		float cotangent = (float) Math.cos(fovy * toRad * 0.5f) / (float) Math.sin(fovy * toRad * 0.5f);
		res.m00 = cotangent / aspect;
		res.m01 = 0.0f;
		res.m02 = 0.0f;
		res.m03 = 0.0f;
		res.m10 = 0.0f;
		res.m11 = cotangent;
		res.m12 = 0.0f;
		res.m13 = 0.0f;
		res.m20 = 0.0f;
		res.m21 = 0.0f;
		res.m22 = (zFar + zNear) / (zFar - zNear);
		res.m23 = -1.0f;
		res.m30 = 0.0f;
		res.m31 = 0.0f;
		res.m32 = 2.0f * zFar * zNear / (zFar - zNear);
		res.m33 = 0.0f;
	}

	public static final void gluPerspectiveFlat(float fovy, float aspect, float zNear, float zFar) {
		Matrix4f res = getMatrix();
		float cotangent = (float) Math.cos(fovy * toRad * 0.5f) / (float) Math.sin(fovy * toRad * 0.5f);
		res.m00 = cotangent / aspect;
		res.m01 = 0.0f;
		res.m02 = 0.0f;
		res.m03 = 0.0f;
		res.m10 = 0.0f;
		res.m11 = cotangent;
		res.m12 = 0.0f;
		res.m13 = 0.0f;
		res.m20 = 0.0f;
		res.m21 = 0.0f;
		res.m22 = ((zFar + zNear) / (zFar - zNear)) * 0.001f;
		res.m23 = -1.0f;
		res.m30 = 0.0f;
		res.m31 = 0.0f;
		res.m32 = 2.0f * zFar * zNear / (zFar - zNear);
		res.m33 = 0.0f;
	}

	public static final String gluErrorString(int p1) {
		switch (p1) {
		case GL_INVALID_ENUM:
			return "GL_INVALID_ENUM";
		case GL_INVALID_VALUE:
			return "GL_INVALID_VALUE";
		case GL_INVALID_OPERATION:
			return "GL_INVALID_OPERATION";
		case GL_OUT_OF_MEMORY:
			return "GL_OUT_OF_MEMORY";
		case GL_CONTEXT_LOST_WEBGL:
			return "CONTEXT_LOST_WEBGL";
		default:
			return "Unknown Error";
		}
	}
	
	public static final void optimize() {
		FixedFunctionShader.optimize();
	}

	private static long lastBandwidthReset = 0l;
	private static int lastBandwidth = 0;

	public static final int getBitsPerSecond() {
		if (System.currentTimeMillis() - lastBandwidthReset > 1000) {
			lastBandwidthReset = System.currentTimeMillis();
			lastBandwidth = bytesUploaded * 8;
			bytesUploaded = 0;
		}
		return lastBandwidth;
	}

	public static final int getVertexesPerSecond() {
		int ret = vertexDrawn;
		vertexDrawn = 0;
		return ret;
	}

	public static final int getTrianglesPerSecond() {
		int ret = triangleDrawn;
		triangleDrawn = 0;
		return ret;
	}

}