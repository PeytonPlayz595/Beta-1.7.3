package net.PeytonPlayz585.opengl;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.util.glu.GLU;
import net.PeytonPlayz585.util.vector.Matrix4f;
import net.PeytonPlayz585.util.vector.Vector3f;
import net.PeytonPlayz585.util.vector.Vector4f;

import com.jcraft.jzlib.InflaterInputStream;

import de.cuina.fireandfuel.CodecJLayerMP3;
import net.PeytonPlayz585.GameWindowListener;
import net.PeytonPlayz585.awt.image.BufferedImage;
import net.PeytonPlayz585.awt.image.ImageIO;
import net.PeytonPlayz585.fileutils.FileChooserResult;
import net.PeytonPlayz585.glemu.FixedFunctionShader;
import net.PeytonPlayz585.glemu.StreamBuffer.StreamBufferInstance;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Tessellator;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

import org.lwjgl.opengl.ARBDebugOutput;
import org.lwjgl.opengl.ARBDebugOutputCallback;
import org.lwjgl.opengl.ARBOcclusionQuery2;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.EXTTextureFilterAnisotropic;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.PixelFormat;

import static net.PeytonPlayz585.opengl.GL11_1.EaglerAdapterImpl2.*;

public class GL11_1 {

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
	public static final int GL_S = RealOpenGLEnums.GL_S;
	public static final int GL_T = RealOpenGLEnums.GL_T;
	public static final int GL_R = RealOpenGLEnums.GL_R;
	public static final int GL_Q = RealOpenGLEnums.GL_Q;
	public static final int GL_TEXTURE_GEN_S = RealOpenGLEnums.GL_TEXTURE_GEN_S;
	public static final int GL_TEXTURE_GEN_T = RealOpenGLEnums.GL_TEXTURE_GEN_T;
	public static final int GL_TEXTURE_GEN_R = RealOpenGLEnums.GL_TEXTURE_GEN_R;
	public static final int GL_TEXTURE_GEN_Q = RealOpenGLEnums.GL_TEXTURE_GEN_Q;
	public static final int GL_TEXTURE_GEN_MODE = RealOpenGLEnums.GL_TEXTURE_GEN_MODE;
	public static final int GL_OBJECT_PLANE = RealOpenGLEnums.GL_OBJECT_PLANE;
	public static final int GL_EYE_PLANE = RealOpenGLEnums.GL_EYE_PLANE;
	public static final int GL_OBJECT_LINEAR = RealOpenGLEnums.GL_OBJECT_LINEAR;
	public static final int GL_EYE_LINEAR = RealOpenGLEnums.GL_EYE_LINEAR;
	public static final int GL_NEAREST = RealOpenGLEnums.GL_NEAREST;
	public static final int GL_CLAMP = RealOpenGLEnums.GL_CLAMP_TO_EDGE;
	public static final int GL_TEXTURE_WRAP_S = RealOpenGLEnums.GL_TEXTURE_WRAP_S;
	public static final int GL_TEXTURE_WRAP_T = RealOpenGLEnums.GL_TEXTURE_WRAP_T;
	public static final int GL_REPEAT = RealOpenGLEnums.GL_REPEAT;
	public static final int GL_BGRA = RealOpenGLEnums.GL_BGRA;
	public static final int GL_UNSIGNED_INT_8_8_8_8_REV = RealOpenGLEnums.GL_UNSIGNED_INT_8_8_8_8_REV;
	public static final int GL_DST_COLOR = RealOpenGLEnums.GL_DST_COLOR;
	public static final int GL_CONSTANT_COLOR = RealOpenGLEnums.GL_CONSTANT_COLOR;
	public static final int GL_ONE_MINUS_CONSTANT_COLOR = RealOpenGLEnums.GL_ONE_MINUS_CONSTANT_COLOR;
	public static final int GL_CONSTANT_ALPHA = RealOpenGLEnums.GL_CONSTANT_ALPHA;
	public static final int GL_ONE_MINUS_CONSTANT_ALPHA = RealOpenGLEnums.GL_ONE_MINUS_CONSTANT_ALPHA;
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
	public static final int GL_TEXTURE0 = RealOpenGLEnums.GL_TEXTURE0;
	public static final int GL_TEXTURE1 = RealOpenGLEnums.GL_TEXTURE1;
	public static final int GL_TEXTURE2 = RealOpenGLEnums.GL_TEXTURE2;
	public static final int GL_TEXTURE3 = RealOpenGLEnums.GL_TEXTURE3;
	public static final int GL_INVALID_ENUM = RealOpenGLEnums.GL_INVALID_ENUM;
	public static final int GL_INVALID_VALUE = RealOpenGLEnums.GL_INVALID_VALUE;
	public static final int GL_INVALID_OPERATION = RealOpenGLEnums.GL_INVALID_OPERATION;
	public static final int GL_OUT_OF_MEMORY = RealOpenGLEnums.GL_OUT_OF_MEMORY;
	public static final int GL_CONTEXT_LOST_WEBGL = -100;
	public static final int GL_TRIANGLE_FAN = RealOpenGLEnums.GL_TRIANGLE_FAN;
	public static final int GL_LINE_STRIP = RealOpenGLEnums.GL_LINE_STRIP;
	public static final int EAG_SWAP_RB = -101;
	public static final int GL_LINES = RealOpenGLEnums.GL_LINES;
	public static final int GL_NEAREST_MIPMAP_LINEAR = RealOpenGLEnums.GL_NEAREST_MIPMAP_LINEAR;
	public static final int GL_TEXTURE_MAX_ANISOTROPY = -103;
	public static final int GL_TEXTURE_MAX_LEVEL = RealOpenGLEnums.GL_TEXTURE_MAX_LEVEL;
	public static final int GL_LINEAR_MIPMAP_LINEAR = RealOpenGLEnums.GL_LINEAR_MIPMAP_LINEAR;
	public static final int GL_LINEAR_MIPMAP_NEAREST = RealOpenGLEnums.GL_LINEAR_MIPMAP_NEAREST;
	public static final int GL_NEAREST_MIPMAP_NEAREST = RealOpenGLEnums.GL_NEAREST_MIPMAP_NEAREST;
	
	//Just some extra ones :)
	public static final int GL_GEQUAL = RealOpenGLEnums.GL_GEQUAL;
	public static final int GL_LESS = RealOpenGLEnums.GL_LESS;
	public static final int GL_POINTS = RealOpenGLEnums.GL_POINTS;

	static final GLObjectMap<TextureGL> texObjects = new GLObjectMap(256);

	static boolean enableTexture2D = false;
	static boolean enableTexture2D_1 = false;
	static boolean enableLighting = false;
	static boolean enableAlphaTest = false;
	public static float alphaThresh = 0.1f;

	static boolean isDepthTest = false;
	static int currentDepthFunc = -99999;
	static boolean isCullFace = false;
	static int currentCullFace = -99999;
	static boolean isPolygonOffset = false;
	static float polygonOffset1 = -999.9f;
	static float polygonOffset2 = -999.9f;
	static boolean isBlend = false;
	static int blendSRC = 0;
	static int blendDST = 0;
	static int colorMask = 15;
	static boolean isDepthMask = true;

	static boolean isCompilingDisplayList = false;
	static DisplayList compilingDisplayList = null;

	static boolean enableColorArray = false;
	static boolean enableNormalArray = false;
	static boolean enableTex0Array = false;
	static boolean enableTex1Array = false;

	public static boolean enableAnisotropicFix = false;
	public static int anisotropicFixSerial = 0;
	public static float anisotropicFixX = 1024.0f;
	public static float anisotropicFixY = 1024.0f;

	public static int colorSerial = 0;
	public static float colorR = 1.0f;
	public static float colorG = 1.0f;
	public static float colorB = 1.0f;
	public static float colorA = 1.0f;

	public static int normalSerial = 0;
	public static float normalX = 1.0f;
	public static float normalY = 0.0f;
	public static float normalZ = 0.0f;

	static int selectedTex = 0;
	static int selectedClientTex = 0;
	static int[] boundTexI = new int[2];
	static TextureGL[] boundTex = new TextureGL[2];
	public static int tex0Serial = 0;
	public static float tex0X = 0;
	public static float tex0Y = 0;
	public static int tex1Serial = 0;
	public static float tex1X = 0;
	public static float tex1Y = 0;
	static TextureGL boundTexture0 = null;
	static boolean enableAnisotropicPatch = false;
	static boolean hintAnisotropicPatch = false;
	static boolean swapRB = true;

	public static final void anisotropicPatch(boolean e) {
		enableAnisotropicPatch = e;
	}

	static boolean enableTexGen = false;
	static boolean enableColorMaterial = false;

	public static int texPlaneSerial = 0;
	public static int texSSerial = 0;
	public static int texS_plane = 0;
	public static float texS_X = 0.0f;
	public static float texS_Y = 0.0f;
	public static float texS_Z = 0.0f;
	public static float texS_W = 0.0f;

	public static int texTSerial = 0;
	public static int texT_plane = 0;
	public static float texT_X = 0.0f;
	public static float texT_Y = 0.0f;
	public static float texT_Z = 0.0f;
	public static float texT_W = 0.0f;

	public static int texRSerial = 0;
	public static int texR_plane = 0;
	public static float texR_X = 0.0f;
	public static float texR_Y = 0.0f;
	public static float texR_Z = 0.0f;
	public static float texR_W = 0.0f;

	public static int texQSerial = 0;
	public static int texQ_plane = 0;
	public static float texQ_X = 0.0f;
	public static float texQ_Y = 0.0f;
	public static float texQ_Z = 0.0f;
	public static float texQ_W = 0.0f;

	public static int fogColorSerial = 0;
	public static float fogColorR = 1.0f;
	public static float fogColorG = 1.0f;
	public static float fogColorB = 1.0f;
	public static float fogColorA = 1.0f;
	public static int fogCfgSerial = 0;
	public static int fogMode = 1;
	static boolean fogEnabled = false;
	public static float fogStart = 1.0f;
	public static float fogEnd = 1.0f;
	public static float fogDensity = 1.0f;

	static int bytesUploaded = 0;
	static int vertexDrawn = 0;
	static int triangleDrawn = 0;

	static int matrixMode = GL_MODELVIEW;

	public static int matModelSerialCounter = 0;
	public static int[] matModelVSerial = new int[32];
	public static Matrix4f[] matModelV = new Matrix4f[32];
	public static int matModelPointer = 0;

	public static int matProjSerialCounter = 0;
	public static int[] matProjVSerial = new int[6];
	public static Matrix4f[] matProjV = new Matrix4f[6];
	public static int matProjPointer = 0;

	public static int matTexSerialCounter = 0;
	public static int[] matTexVSerial = new int[16];
	public static Matrix4f[] matTexV = new Matrix4f[16];
	public static int matTexPointer = 0;
	
	public static final boolean isWebGL = _wisWebGL();

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
			if(!isDepthTest) {
				_wglEnable(_wGL_DEPTH_TEST);
				isDepthTest = true;
			}
			break;
		case GL_CULL_FACE:
			if(!isCullFace) {
				_wglEnable(_wGL_CULL_FACE);
				isCullFace = true;
			}
			break;
		case GL_BLEND:
			if(!isBlend) {
				_wglEnable(_wGL_BLEND);
				isBlend = true;
			}
			break;
		case GL_RESCALE_NORMAL:
			break;
		case GL_TEXTURE_2D:
			if (selectedTex == 0) {
				enableTexture2D = true;
			}
			if (selectedTex == 1) {
				enableTexture2D_1 = true;
			}
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
		case GL_TEXTURE_GEN_S:
		case GL_TEXTURE_GEN_T:
		case GL_TEXTURE_GEN_R:
		case GL_TEXTURE_GEN_Q:
			enableTexGen = true;
			break;
		case GL_POLYGON_OFFSET_FILL:
			if(!isPolygonOffset) {
				_wglEnable(_wGL_POLYGON_OFFSET_FILL);
				isPolygonOffset = true;
			}
			break;
		case EAG_SWAP_RB:
			swapRB = true;
			break;
		default:
			break;
		}
	}

	public static final void glShadeModel(int p1) {

	}

	public static final void glClearDepth(float p1) {
		_wglClearDepth(1.0F-p1);
	}
	
	public static final void glClearDepth(double p1) {
		_wglClearDepth((float)(1.0F-p1));
	}

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
		if(rev != currentDepthFunc) {
			_wglDepthFunc(rev);
			currentDepthFunc = rev;
		}
	}

	public static final void glAlphaFunc(int p1, float p2) {
		alphaThresh = p2;
	}

	public static final void glCullFace(int p1) {
		if(p1 != currentCullFace) {
			_wglCullFace(p1);
			currentCullFace = p1;
		}
	}

	public static final void glMatrixMode(int p1) {
		matrixMode = p1;
	}

	private static final Matrix4f getMatrixIncrSerial() {
		switch (matrixMode) {
		case GL_MODELVIEW:
		default:
			matModelVSerial[matModelPointer] = ++matModelSerialCounter;
			return matModelV[matModelPointer];
		case GL_PROJECTION:
			matProjVSerial[matProjPointer] = ++matProjSerialCounter;
			return matProjV[matProjPointer];
		case GL_TEXTURE:
			matTexVSerial[matTexPointer] = ++matTexSerialCounter;
			return matTexV[matTexPointer];
		}
	}

	public static final void glLoadIdentity() {
		getMatrixIncrSerial().setIdentity();
	}

	public static final void glViewport(int p1, int p2, int p3, int p4) {
		_wglViewport(p1, p2, p3, p4);
	}

	public static final void glClear(int p1) {
		_wglClear(p1);
	}

	public static final void glOrtho(float left, float right, float bottom, float top, float zNear, float zFar) {
		Matrix4f res = getMatrixIncrSerial();
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
	
	public static final void glOrtho(double left, double right, double bottom, double top, double zNear, double zFar) {
		glOrtho((float) left, (float) right, (float) bottom, (float) top, (float) zNear, (float) zFar);
	}

	private static final Vector3f deevis = new Vector3f();

	public static final void glTranslatef(float p1, float p2, float p3) {
//		deevis.set(p1, p2, p3);
//		getMatrixIncrSerial().translate(deevis);
//		if (isCompilingDisplayList) {
//			System.err.println("matrix is not supported while recording display list use tessellator class instead");
//		}
		deevis.set(p1, p2, p3);
		getMatrixIncrSerial().translate(deevis);
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
			if(isDepthTest) {
				_wglDisable(_wGL_DEPTH_TEST);
				isDepthTest = false;
			}
			break;
		case GL_CULL_FACE:
			if(isCullFace) {
				_wglDisable(_wGL_CULL_FACE);
				isCullFace = false;
			}
			break;
		case GL_BLEND:
			if(isBlend) {
				_wglDisable(_wGL_BLEND);
				isBlend = false;
			}
			break;
		case GL_RESCALE_NORMAL:
			break;
		case GL_TEXTURE_2D:
			if (selectedTex == 0) {
				enableTexture2D = false;
			}
			if (selectedTex == 1) {
				enableTexture2D_1 = false;
			}
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
		case GL_TEXTURE_GEN_S:
		case GL_TEXTURE_GEN_T:
		case GL_TEXTURE_GEN_R:
		case GL_TEXTURE_GEN_Q:
			enableTexGen = false;
			break;
		case GL_POLYGON_OFFSET_FILL:
			if(isPolygonOffset) {
				_wglDisable(_wGL_POLYGON_OFFSET_FILL);
				isPolygonOffset = false;
			}
			break;
		case EAG_SWAP_RB:
			swapRB = false;
			break;
		default:
			break;
		}
	}

	public static final void glColor4f(float p1, float p2, float p3, float p4) {
		++colorSerial;
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

	}

	public static final void glTexImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8,
			ByteBuffer p9) {
		if (p2 == 0 && selectedTex == 0 && boundTexture0 != null) {
			boundTexture0.w = p4;
			boundTexture0.h = p5;
		}
		_wglTexImage2D(_wGL_TEXTURE_2D, p2, _wGL_RGBA8, p4, p5, p6, _wGL_RGBA, _wGL_UNSIGNED_BYTE, p9);
	}

	public static final void glLightModel(int p1, FloatBuffer p2) {

	}

	public static int lightPos0Serial = 0;
	public static int lightPos1Serial = 0;
	private static Vector4f lightPos0vec0 = new Vector4f();
	private static Vector4f lightPos1vec0 = new Vector4f();
	public static Vector4f lightPos0vec = new Vector4f();
	public static Vector4f lightPos1vec = new Vector4f();
	
	private static float[] light0 = new float[4];
	private static float[] light1 = new float[4];

	public static final void glLight(int light, int pname, FloatBuffer param) {
		if(pname == GL_POSITION) {
			switch(light) {
			case GL_LIGHT0:
				try {
					light0[0] = param.get(param.position());
					light0[1] = param.get(param.position() + 1);
					light0[2] = param.get(param.position() + 2);
					light0[3] = param.get(param.position() + 3);
				} catch(Exception e) {
					System.err.println("Failed to shade model (GL_LIGHT0)");
					light0[0] = 0.0F;
					light0[1] = 0.0F;
					light0[2] = 0.0F;
					light0[3] = 0.0F;
				}
			case GL_LIGHT1:
				try {
					light1[0] = param.get(param.position());
					light1[1] = param.get(param.position() + 1);
					light1[2] = param.get(param.position() + 2);
					light1[3] = param.get(param.position() + 3);
				} catch(Exception e) {
					System.err.println("Failed to shade model (GL_LIGHT1)");
					light1[0] = 0.0F;
					light1[1] = 0.0F;
					light1[2] = 0.0F;
					light1[3] = 0.0F;
				}
			}
		}
	}

	public static final void copyModelToLightMatrix() {
		++lightPos0Serial;
		++lightPos1Serial;
		lightPos0vec0.set(lightPos0vec);
		lightPos1vec0.set(lightPos1vec);
		lightPos0vec.set(light1[0], light1[1], light1[2], light1[3]);
		lightPos0vec.normalise();
		lightPos1vec.set(light0[0], light0[1], light0[2], light0[3]);
		lightPos1vec.normalise();
		Matrix4f.transform(matModelV[matModelPointer], lightPos0vec, lightPos0vec).normalise();
		Matrix4f.transform(matModelV[matModelPointer], lightPos1vec, lightPos1vec).normalise();
	}
	
	public static final void copyModelToLightMatrix2() {
		++lightPos0Serial;
		++lightPos1Serial;
		lightPos0vec0.set(lightPos0vec);
		lightPos1vec0.set(lightPos1vec);
		lightPos0vec.set(0.2f, 1.0f, -0.7f, 0.0f);
		lightPos0vec.normalise();
		lightPos1vec.set(-0.2f, 1.0f, 0.7f, 0.0f);
		lightPos1vec.normalise();
		Matrix4f.transform(matModelV[matModelPointer], lightPos0vec, lightPos0vec).normalise();
		Matrix4f.transform(matModelV[matModelPointer], lightPos1vec, lightPos1vec).normalise();
	}

	public static final void flipBothLightMatrix() {
		++lightPos0Serial;
		++lightPos1Serial;
		lightPos0vec.x = -lightPos0vec.x;
		lightPos1vec.x = -lightPos1vec.x;
		lightPos0vec.y = -lightPos0vec.y;
		lightPos1vec.y = -lightPos1vec.y;
		lightPos0vec.z = -lightPos0vec.z;
		lightPos1vec.z = -lightPos1vec.z;
	}
	
	public static final void flipFirstLightMatrix() {
		++lightPos0Serial;
		++lightPos1Serial;
		lightPos0vec.x = -lightPos0vec.x;
		lightPos0vec.y = -lightPos0vec.y;
		lightPos0vec.z = -lightPos0vec.z;
	}
	
	public static final void flipSecondLightMatrix() {
		++lightPos0Serial;
		++lightPos1Serial;
		lightPos1vec.x = -lightPos1vec.x;
		lightPos1vec.y = -lightPos1vec.y;
		lightPos1vec.z = -lightPos1vec.z;
	}

	public static final void revertBothLightMatrix() {
		++lightPos0Serial;
		++lightPos1Serial;
		lightPos0vec.set(lightPos0vec0);
		lightPos1vec.set(lightPos1vec0);
	}
	
	public static final void revertFirstLightMatrix() {
		++lightPos0Serial;
		++lightPos1Serial;
		lightPos0vec.set(lightPos0vec0);
	}
	
	public static final void revertSecondLightMatrix() {
		++lightPos0Serial;
		++lightPos1Serial;
		lightPos1vec.set(lightPos1vec0);
	}

	public static final void glPushMatrix() {
		switch (matrixMode) {
		case GL_MODELVIEW:
		default:
			if (matModelPointer < matModelV.length - 1) {
				++matModelPointer;
				matModelV[matModelPointer].load(matModelV[matModelPointer - 1]);
				matModelVSerial[matModelPointer] = matModelVSerial[matModelPointer - 1];
			} else {
				System.err.println("modelview matrix stack overflow");
			}
			break;
		case GL_PROJECTION:
			if (matProjPointer < matProjV.length - 1) {
				++matProjPointer;
				matProjV[matProjPointer].load(matProjV[matProjPointer - 1]);
				matProjVSerial[matProjPointer] = matProjVSerial[matProjPointer - 1];
			} else {
				System.err.println("projection matrix stack overflow");
			}
			break;
		case GL_TEXTURE:
			if (matTexPointer < matTexV.length - 1) {
				++matTexPointer;
				matTexV[matTexPointer].load(matTexV[matTexPointer - 1]);
				matTexVSerial[matTexPointer] = matTexVSerial[matTexPointer - 1];
			} else {
				System.err.println("texture matrix stack overflow");
			}
			break;
		}
	}

	private static final float toRad = 0.0174532925f;

	public static final void glRotatef(float p1, float p2, float p3, float p4) {
		deevis.set(p2, p3, p4);
		getMatrixIncrSerial().rotate(p1 * toRad, deevis);
		if (isCompilingDisplayList) {
			System.err.println("matrix is not supported while recording display list use tessellator class instead");
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
		getMatrixIncrSerial().scale(deevis);
		if (isCompilingDisplayList) {
			System.err.println("matrix is not supported while recording display list use tessellator class instead");
		}
	}
	
	public static final void glScaled(double p1, double p2, double p3) {
		glScalef((float)p1, (float)p2, (float)p3);
	}

	private static final Matrix4f tmpMat = new Matrix4f();

	public static final void glMultMatrixf(Matrix4f mat) {
		getMatrixIncrSerial().load(Matrix4f.mul(getMatrixIncrSerial(), mat, tmpMat));
	}

	public static final void glBlendFunc(int p1, int p2) {
		if(overlayFBOBlending) {
			int i = p1 | 0x10000;
			int j = p2 | 0x10000;
			if(blendSRC != i || blendDST != j) {
				_wglBlendFuncSeparate(p1, p2, GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
				blendSRC = i;
				blendDST = j;
			}
		}else {
			if(blendSRC != p1 || blendDST != p2) {
				_wglBlendFunc(p1, p2);
				blendSRC = p1;
				blendDST = p2;
			}
		}
	}

	private static boolean overlayFBOBlending = false;

	public static final void enableOverlayFramebufferBlending(boolean en) {
		overlayFBOBlending = en;
	}

	public static final void glDepthMask(boolean p1) {
		if(isDepthMask != p1) {
			_wglDepthMask(p1);
			isDepthMask = p1;
		}
	}

	public static final void glColorMask(boolean p1, boolean p2, boolean p3, boolean p4) {
		int hsh = (p1 ? 1 : 0) | (p2 ? 2 : 0) | (p3 ? 4 : 0) | (p4 ? 8 : 0);
		if(colorMask != hsh) {
			_wglColorMask(p1, p2, p3, p4);
			colorMask = hsh;
		}
	}

	private static final void updateAnisotropicPatch() {
		if (selectedTex == 0) {
			enableAnisotropicFix = false;
			if (enableAnisotropicPatch && boundTexture0 != null && boundTexture0.anisotropic && boundTexture0.nearest) {
				enableAnisotropicFix = true;
				++anisotropicFixSerial;
				anisotropicFixX = boundTexture0.w;
				anisotropicFixY = boundTexture0.h;
			}
		}
	}

	public static final void glBindTexture(int p1, int p2) {
		if(boundTexI[selectedTex] != p2) {
			TextureGL t = texObjects.get(p2);
			if(boundTex[selectedTex] != t) {
				_wglBindTexture(_wGL_TEXTURE_2D, t);
				if (selectedTex == 0) {
					boundTexture0 = t;
					updateAnisotropicPatch();
				}
				boundTex[selectedTex] = t;
			}
			boundTexI[selectedTex] = p2;
		}
	}

	public static final void glBindTexture(int p1, TextureGL p2) {
		boundTexI[selectedTex] = -1;
		if(boundTex[selectedTex] != p2) {
			_wglBindTexture(_wGL_TEXTURE_2D, p2);
			if (selectedTex == 0) {
				boundTexture0 = p2;
				updateAnisotropicPatch();
			}
			boundTex[selectedTex] = p2;
		}
	}

	public static final void glCopyTexSubImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {
		_wglCopyTexSubImage2D(_wGL_TEXTURE_2D, p2, p3, p4, p5, p6, p7, p8);
	}

	public static final void glTexParameteri(int p1, int p2, int p3) {
		if (selectedTex == 0 && boundTexture0 != null && p2 == GL_TEXTURE_MAG_FILTER) {
			boundTexture0.nearest = p3 == GL_NEAREST;
		}
		_wglTexParameteri(p1, p2, p3);
		updateAnisotropicPatch();
	}

	public static final void glTexParameterf(int p1, int p2, float p3) {
		if(p2 == GL_TEXTURE_MAX_ANISOTROPY && !anisotropicFilteringSupported()) {
			return;
		}
		if (selectedTex == 0 && boundTexture0 != null && p2 == GL_TEXTURE_MAX_ANISOTROPY) {
			boundTexture0.anisotropic = p3 > 1.0f;
		}
		_wglTexParameterf(p1, p2 == GL_TEXTURE_MAX_ANISOTROPY ? _wGL_TEXTURE_MAX_ANISOTROPY : p2, p3);
		updateAnisotropicPatch();
	}

	public static final void glLogicOp(int p1) {

	}

	public static final void glNormal3f(float p1, float p2, float p3) {
		++normalSerial;
		//float len = (float) Math.sqrt(p1 * p1 + p2 * p2 + p3 * p3);
		//normalX = p1 / len;
		//normalY = p2 / len;
		//normalZ = p3 / len;
		normalX = p1;
		normalY = p2;
		normalZ = p3;
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
		if(currentArray != p1) {
			currentArray = p1;
			_wglBindVertexArray(p1);
		}
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
					getMatrixIncrSerial().translate(deevis);
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
		++colorSerial;
		colorR = p1;
		colorG = p2;
		colorB = p3;
		colorA = 1.0f;
	}

	public static final void glTexGeni(int p1, int p2, int p3) {

	}
	
	private static final Vector4f tmpTexGenPlane = new Vector4f();

	public static final void glTexGen(int p1, int p2, FloatBuffer p3) {
		Vector4f vec = tmpTexGenPlane;
		vec.load(p3);
		if(p2 == GL_EYE_PLANE) {
			tmpMat.load(matModelV[matModelPointer]).invert().transpose();
			Matrix4f.transform(tmpMat, vec, vec);
		}
		switch (p1) {
		case GL_S:
			++texPlaneSerial;
			++texSSerial;
			texS_plane = (p2 == GL_EYE_PLANE ? 1 : 0);
			texS_X = vec.x;
			texS_Y = vec.y;
			texS_Z = vec.z;
			texS_W = vec.w;
			break;
		case GL_T:
			++texPlaneSerial;
			++texTSerial;
			texT_plane = (p2 == GL_EYE_PLANE ? 1 : 0);
			texT_X = vec.x;
			texT_Y = vec.y;
			texT_Z = vec.z;
			texT_W = vec.w;
			break;
		case GL_R:
			++texPlaneSerial;
			++texRSerial;
			texR_plane = (p2 == GL_EYE_PLANE ? 1 : 0);
			texR_X = vec.x;
			texR_Y = vec.y;
			texR_Z = vec.z;
			texR_W = vec.w;
			break;
		case GL_Q:
			++texPlaneSerial;
			++texQSerial;
			texQ_plane = (p2 == GL_EYE_PLANE ? 1 : 0);
			texQ_X = vec.x;
			texQ_Y = vec.y;
			texQ_Z = vec.z;
			texQ_W = vec.w;
			break;
		}
	}

	public static final void glTexImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8,
			IntBuffer p9) {
		/*
		 * int pp2 = 0; switch(p3) { default: case GL_RGBA: pp2 = _wGL_RGBA; break; case
		 * GL_BGRA: pp2 = _wGL_BGRA; break; } int pp3 = 0; switch(p7) { default: case
		 * GL_RGBA: pp3 = _wGL_RGBA; break; case GL_BGRA: pp3 = _wGL_BGRA; break; }
		 */
		if (p2 == 0 && selectedTex == 0 && boundTexture0 != null) {
			boundTexture0.w = p4;
			boundTexture0.h = p5;
		}
		bytesUploaded += p9.remaining() * 4;
		_wglTexImage2D(_wGL_TEXTURE_2D, p2, _wGL_RGBA8, p4, p5, p6, _wGL_RGBA, _wGL_UNSIGNED_BYTE, p9);
		updateAnisotropicPatch();
	}

	public static final void glTexImage2D_2(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8,
			IntBuffer p9) {
		if (p2 == 0 && selectedTex == 0 && boundTexture0 != null) {
			boundTexture0.w = p4;
			boundTexture0.h = p5;
		}
		bytesUploaded += p9.remaining() * 4;
		_wglTexImage2D(_wGL_TEXTURE_2D, p2, _wGL_RGB8, p4, p5, p6, _wGL_RGB, _wGL_UNSIGNED_BYTE, p9);
		updateAnisotropicPatch();
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
	
	public static final void glDeleteTextures(IntBuffer p1) {
		while(p1.hasRemaining()) {
			_wglDeleteTextures(texObjects.free(p1.get()));
		}
	}

	public static final void glPolygonOffset(float p1, float p2) {
		if(p1 != polygonOffset1 || p2 != polygonOffset2) {
			_wglPolygonOffset(p1, p2);
			polygonOffset1 = p1;
			polygonOffset2 = p2;
		}
	}

	public static final void glCallLists(IntBuffer p1) {
		while (p1.hasRemaining()) {
			glCallList(p1.get());
		}
	}

	public static final void glEnableClientState(int p1) {
		switch (p1) {
		case GL_COLOR_ARRAY:
			enableColorArray = true;
			break;
		case GL_NORMAL_ARRAY:
			enableNormalArray = true;
			break;
		case GL_TEXTURE_COORD_ARRAY:
			switch (selectedClientTex) {
			case 0:
				enableTex0Array = true;
				break;
			case 1:
				enableTex1Array = true;
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}

	public static final void glDisableClientState(int p1) {
		switch (p1) {
		case GL_COLOR_ARRAY:
			enableColorArray = false;
			break;
		case GL_NORMAL_ARRAY:
			enableNormalArray = false;
			break;
		case GL_TEXTURE_COORD_ARRAY:
			switch (selectedClientTex) {
			case 0:
				enableTex0Array = false;
				break;
			case 1:
				enableTex1Array = false;
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}

	public static final void hintAnisotropicFix(boolean hint) {
		hintAnisotropicPatch = hint;
	}

	private static final int getShaderModeFlag0() {
		int mode = 0;
		mode = (mode | (enableColorArray ? FixedFunctionShader.COLOR : 0));
		mode = (mode | (enableNormalArray ? FixedFunctionShader.NORMAL : 0));
		mode = (mode | (enableTex0Array ? FixedFunctionShader.TEXTURE0 : 0));
		mode = (mode | (enableTex1Array ? FixedFunctionShader.TEXTURE1 : 0));
		return mode;
	}

	private static final int getShaderModeFlag1() {
		int mode = 0;
		mode = (mode | (enableTexGen ? FixedFunctionShader.TEXGEN : 0));
		mode = (mode | ((enableColorMaterial && enableLighting) ? FixedFunctionShader.LIGHTING : 0));
		mode = (mode | (fogEnabled ? FixedFunctionShader.FOG : 0));
		mode = (mode | (enableAlphaTest ? FixedFunctionShader.ALPHATEST : 0));
		mode = (mode | (enableTexture2D ? FixedFunctionShader.UNIT0 : 0));
		mode = (mode | (enableTexture2D_1 ? FixedFunctionShader.UNIT1 : 0));
		mode = (mode | ((enableTexture2D && (enableAnisotropicFix || (hintAnisotropicPatch && enableAnisotropicPatch)))
				? FixedFunctionShader.FIX_ANISOTROPIC
				: 0));
		mode = (mode | (swapRB ? FixedFunctionShader.SWAP_RB : 0));
		return mode;
	}

	private static final int getShaderModeFlag() {
		int mode = 0;
		mode = (mode | (enableColorArray ? FixedFunctionShader.COLOR : 0));
		mode = (mode | (enableNormalArray ? FixedFunctionShader.NORMAL : 0));
		mode = (mode | (enableTex0Array ? FixedFunctionShader.TEXTURE0 : 0));
		mode = (mode | (enableTex1Array ? FixedFunctionShader.TEXTURE1 : 0));
		mode = (mode | (enableTexGen ? FixedFunctionShader.TEXGEN : 0));
		mode = (mode | ((enableColorMaterial && enableLighting) ? FixedFunctionShader.LIGHTING : 0));
		mode = (mode | (fogEnabled ? FixedFunctionShader.FOG : 0));
		mode = (mode | (enableAlphaTest ? FixedFunctionShader.ALPHATEST : 0));
		mode = (mode | (enableTexture2D ? FixedFunctionShader.UNIT0 : 0));
		mode = (mode | (enableTexture2D_1 ? FixedFunctionShader.UNIT1 : 0));
		mode = (mode | ((enableTexture2D && (enableAnisotropicFix || (hintAnisotropicPatch && enableAnisotropicPatch)))
				? FixedFunctionShader.FIX_ANISOTROPIC
				: 0));
		mode = (mode | (swapRB ? FixedFunctionShader.SWAP_RB : 0));
		return mode;
	}

	private static FixedFunctionShader shader = null;

	private static final void bindTheShader() {
		bindTheShader(getShaderModeFlag());
	}

	private static final void bindTheShader(int mode) {
		FixedFunctionShader s = shader = FixedFunctionShader.instance(mode);
		s.useProgram();
		s.update();
	}
	
	static Tessellator tess = Tessellator.instance;
	
	public static void glBegin(int mode) {
		tess.startDrawing(mode);
	}
	
	public static void glTexCoord2f(float x, float z) {
		tess.setTextureUV(x, z);
	}
	
	public static void glVertex3f(float x, float y, float z) {
		tess.addVertex(x, y, z);
	}
	
	public static void glEnd() {
		tess.draw();
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
			int bl =  _wArrayByteLength(buffer);
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
				switch (p1) {
				default:
				case GL_TRIANGLES:
					triangleDrawn += p3 / 3;
					break;
				case GL_TRIANGLE_STRIP:
					triangleDrawn += p3 - 2;
					break;
				case GL_TRIANGLE_FAN:
					triangleDrawn += p3 - 2;
					break;
				case GL_LINE_STRIP:
					triangleDrawn += p3 - 1;
					break;
				case GL_LINES:
					triangleDrawn += p3 / 2;
					break;
				}
				_wglDrawArrays(p1, p2, p3);
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
		_wglBindVertexArray0(occlusion_vao);
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

	public static final boolean glGetQueryResult(int obj) {
		QueryGL q = queryObjs.get(obj);
		return  _wglGetQueryObjecti(q, _wGL_QUERY_RESULT) > 0;
	}

	public static final boolean glGetQueryResultAvailable(int obj) {
		QueryGL q = queryObjs.get(obj);
		return _wglGetQueryObjecti(q, _wGL_QUERY_RESULT_AVAILABLE) > 0;
	}
	
	public static final void glGetQueryResult(int in, IntBuffer out) {
		QueryGL q = queryObjs.get(in);
		out.put(_wglGetQueryObjecti(q, _wGL_QUERY_RESULT_AVAILABLE));
	}
	
	public static final void glGetQueryResult1(int in, IntBuffer out) {
		QueryGL q = queryObjs.get(in);
		out.put(_wglGetQueryObjecti(q, _wGL_QUERY_RESULT));
	}

	public static final int glGenTextures() {
		return texObjects.register(_wglGenTextures());
	}
	
	public static final void glGenTextures(IntBuffer buf) {
		for(int i = buf.position(); i < buf.limit(); i++) {
			buf.put(i, glGenTextures());
		}
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
				++fogCfgSerial;
				fogMode = 1;
				break;
			case GL_EXP:
				++fogCfgSerial;
				fogMode = 2;
				break;
			}
		}
	}

	public static final void glFogf(int p1, float p2) {
		switch (p1) {
		case GL_FOG_START:
			++fogCfgSerial;
			fogStart = p2;
			break;
		case GL_FOG_END:
			++fogCfgSerial;
			fogEnd = p2;
			break;
		case GL_FOG_DENSITY:
			++fogCfgSerial;
			fogDensity = p2;
			break;
		default:
			break;
		}
	}

	public static final void glFog(int p1, FloatBuffer p2) {
		if (p1 == GL_FOG_COLOR) {
			++fogColorSerial;
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

	public static final void glActiveTexture(int p1) {
		switch (p1) {
		case GL_TEXTURE0:
			if(selectedTex != 0) {
				selectedTex = 0;
				_wglActiveTexture(_wGL_TEXTURE0);
			}
			break;
		case GL_TEXTURE1:
			if(selectedTex != 1) {
				selectedTex = 1;
				_wglActiveTexture(_wGL_TEXTURE1);
			}
			break;
		default:
			System.err.println("only two texture units implemented");
			break;
		}
	}

	public static final void glClientActiveTexture(int p1) {
		switch (p1) {
		case GL_TEXTURE0:
			selectedClientTex = 0;
			break;
		case GL_TEXTURE1:
			selectedClientTex = 1;
			break;
		default:
			System.err.println("only two texture units implemented");
			break;
		}
	}

	public static final void glMultiTexCoord2f(int p1, float p2, float p3) {
		switch (p1) {
		case GL_TEXTURE0:
			++tex0Serial;
			tex0X = p2;
			tex0Y = p3;
			break;
		case GL_TEXTURE1:
			++tex1Serial;
			tex1X = p2;
			tex1Y = p3;
			break;
		default:
			System.err.println("only two texture units implemented");
			break;
		}
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
		Matrix4f res = getMatrixIncrSerial();
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
		Matrix4f res = getMatrixIncrSerial();
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

	
	@SuppressWarnings("unused")
	private static class RealOpenGLEnums {

		// Field descriptor #544 I
		public static final int GL_ACCUM = 256;

		// Field descriptor #544 I
		public static final int GL_LOAD = 257;

		// Field descriptor #544 I
		public static final int GL_RETURN = 258;

		// Field descriptor #544 I
		public static final int GL_MULT = 259;

		// Field descriptor #544 I
		public static final int GL_ADD = 260;

		// Field descriptor #544 I
		public static final int GL_NEVER = 512;

		// Field descriptor #544 I
		public static final int GL_LESS = 513;

		// Field descriptor #544 I
		public static final int GL_EQUAL = 514;

		// Field descriptor #544 I
		public static final int GL_LEQUAL = 515;

		// Field descriptor #544 I
		public static final int GL_GREATER = 516;

		// Field descriptor #544 I
		public static final int GL_NOTEQUAL = 517;

		// Field descriptor #544 I
		public static final int GL_GEQUAL = 518;

		// Field descriptor #544 I
		public static final int GL_ALWAYS = 519;

		// Field descriptor #544 I
		public static final int GL_CURRENT_BIT = 1;

		// Field descriptor #544 I
		public static final int GL_POINT_BIT = 2;

		// Field descriptor #544 I
		public static final int GL_LINE_BIT = 4;

		// Field descriptor #544 I
		public static final int GL_POLYGON_BIT = 8;

		// Field descriptor #544 I
		public static final int GL_POLYGON_STIPPLE_BIT = 16;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MODE_BIT = 32;

		// Field descriptor #544 I
		public static final int GL_LIGHTING_BIT = 64;

		// Field descriptor #544 I
		public static final int GL_FOG_BIT = 128;

		// Field descriptor #544 I
		public static final int GL_DEPTH_BUFFER_BIT = 256;

		// Field descriptor #544 I
		public static final int GL_ACCUM_BUFFER_BIT = 512;

		// Field descriptor #544 I
		public static final int GL_STENCIL_BUFFER_BIT = 1024;

		// Field descriptor #544 I
		public static final int GL_VIEWPORT_BIT = 2048;

		// Field descriptor #544 I
		public static final int GL_TRANSFORM_BIT = 4096;

		// Field descriptor #544 I
		public static final int GL_ENABLE_BIT = 8192;

		// Field descriptor #544 I
		public static final int GL_COLOR_BUFFER_BIT = 16384;

		// Field descriptor #544 I
		public static final int GL_HINT_BIT = 32768;

		// Field descriptor #544 I
		public static final int GL_EVAL_BIT = 65536;

		// Field descriptor #544 I
		public static final int GL_LIST_BIT = 131072;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_BIT = 262144;

		// Field descriptor #544 I
		public static final int GL_SCISSOR_BIT = 524288;

		// Field descriptor #544 I
		public static final int GL_ALL_ATTRIB_BITS = 1048575;

		// Field descriptor #544 I
		public static final int GL_POINTS = 0;

		// Field descriptor #544 I
		public static final int GL_LINES = 1;

		// Field descriptor #544 I
		public static final int GL_LINE_LOOP = 2;

		// Field descriptor #544 I
		public static final int GL_LINE_STRIP = 3;

		// Field descriptor #544 I
		public static final int GL_TRIANGLES = 4;

		// Field descriptor #544 I
		public static final int GL_TRIANGLE_STRIP = 5;

		// Field descriptor #544 I
		public static final int GL_TRIANGLE_FAN = 6;

		// Field descriptor #544 I
		public static final int GL_QUADS = 7;

		// Field descriptor #544 I
		public static final int GL_QUAD_STRIP = 8;

		// Field descriptor #544 I
		public static final int GL_POLYGON = 9;

		// Field descriptor #544 I
		public static final int GL_ZERO = 0;

		// Field descriptor #544 I
		public static final int GL_ONE = 1;

		// Field descriptor #544 I
		public static final int GL_SRC_COLOR = 768;

		// Field descriptor #544 I
		public static final int GL_ONE_MINUS_SRC_COLOR = 769;

		// Field descriptor #544 I
		public static final int GL_SRC_ALPHA = 770;

		// Field descriptor #544 I
		public static final int GL_ONE_MINUS_SRC_ALPHA = 771;

		// Field descriptor #544 I
		public static final int GL_DST_ALPHA = 772;

		// Field descriptor #544 I
		public static final int GL_ONE_MINUS_DST_ALPHA = 773;

		// Field descriptor #544 I
		public static final int GL_DST_COLOR = 774;

		// Field descriptor #544 I
		public static final int GL_ONE_MINUS_DST_COLOR = 775;

		// Field descriptor #544 I
		public static final int GL_SRC_ALPHA_SATURATE = 776;

		// Field descriptor #544 I
		public static final int GL_CONSTANT_COLOR = 32769;

		// Field descriptor #544 I
		public static final int GL_ONE_MINUS_CONSTANT_COLOR = 32770;

		// Field descriptor #544 I
		public static final int GL_CONSTANT_ALPHA = 32771;

		// Field descriptor #544 I
		public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 32772;

		// Field descriptor #544 I
		public static final int GL_TRUE = 1;

		// Field descriptor #544 I
		public static final int GL_FALSE = 0;

		// Field descriptor #544 I
		public static final int GL_CLIP_PLANE0 = 12288;

		// Field descriptor #544 I
		public static final int GL_CLIP_PLANE1 = 12289;

		// Field descriptor #544 I
		public static final int GL_CLIP_PLANE2 = 12290;

		// Field descriptor #544 I
		public static final int GL_CLIP_PLANE3 = 12291;

		// Field descriptor #544 I
		public static final int GL_CLIP_PLANE4 = 12292;

		// Field descriptor #544 I
		public static final int GL_CLIP_PLANE5 = 12293;

		// Field descriptor #544 I
		public static final int GL_BYTE = 5120;

		// Field descriptor #544 I
		public static final int GL_UNSIGNED_BYTE = 5121;

		// Field descriptor #544 I
		public static final int GL_SHORT = 5122;

		// Field descriptor #544 I
		public static final int GL_UNSIGNED_SHORT = 5123;

		// Field descriptor #544 I
		public static final int GL_INT = 5124;

		// Field descriptor #544 I
		public static final int GL_UNSIGNED_INT = 5125;

		// Field descriptor #544 I
		public static final int GL_FLOAT = 5126;

		// Field descriptor #544 I
		public static final int GL_2_BYTES = 5127;

		// Field descriptor #544 I
		public static final int GL_3_BYTES = 5128;

		// Field descriptor #544 I
		public static final int GL_4_BYTES = 5129;

		// Field descriptor #544 I
		public static final int GL_DOUBLE = 5130;

		// Field descriptor #544 I
		public static final int GL_NONE = 0;

		// Field descriptor #544 I
		public static final int GL_FRONT_LEFT = 1024;

		// Field descriptor #544 I
		public static final int GL_FRONT_RIGHT = 1025;

		// Field descriptor #544 I
		public static final int GL_BACK_LEFT = 1026;

		// Field descriptor #544 I
		public static final int GL_BACK_RIGHT = 1027;

		// Field descriptor #544 I
		public static final int GL_FRONT = 1028;

		// Field descriptor #544 I
		public static final int GL_BACK = 1029;

		// Field descriptor #544 I
		public static final int GL_LEFT = 1030;

		// Field descriptor #544 I
		public static final int GL_RIGHT = 1031;

		// Field descriptor #544 I
		public static final int GL_FRONT_AND_BACK = 1032;

		// Field descriptor #544 I
		public static final int GL_AUX0 = 1033;

		// Field descriptor #544 I
		public static final int GL_AUX1 = 1034;

		// Field descriptor #544 I
		public static final int GL_AUX2 = 1035;

		// Field descriptor #544 I
		public static final int GL_AUX3 = 1036;

		// Field descriptor #544 I
		public static final int GL_NO_ERROR = 0;

		// Field descriptor #544 I
		public static final int GL_INVALID_ENUM = 1280;

		// Field descriptor #544 I
		public static final int GL_INVALID_VALUE = 1281;

		// Field descriptor #544 I
		public static final int GL_INVALID_OPERATION = 1282;

		// Field descriptor #544 I
		public static final int GL_STACK_OVERFLOW = 1283;

		// Field descriptor #544 I
		public static final int GL_STACK_UNDERFLOW = 1284;

		// Field descriptor #544 I
		public static final int GL_OUT_OF_MEMORY = 1285;

		// Field descriptor #544 I
		public static final int GL_2D = 1536;

		// Field descriptor #544 I
		public static final int GL_3D = 1537;

		// Field descriptor #544 I
		public static final int GL_3D_COLOR = 1538;

		// Field descriptor #544 I
		public static final int GL_3D_COLOR_TEXTURE = 1539;

		// Field descriptor #544 I
		public static final int GL_4D_COLOR_TEXTURE = 1540;

		// Field descriptor #544 I
		public static final int GL_PASS_THROUGH_TOKEN = 1792;

		// Field descriptor #544 I
		public static final int GL_POINT_TOKEN = 1793;

		// Field descriptor #544 I
		public static final int GL_LINE_TOKEN = 1794;

		// Field descriptor #544 I
		public static final int GL_POLYGON_TOKEN = 1795;

		// Field descriptor #544 I
		public static final int GL_BITMAP_TOKEN = 1796;

		// Field descriptor #544 I
		public static final int GL_DRAW_PIXEL_TOKEN = 1797;

		// Field descriptor #544 I
		public static final int GL_COPY_PIXEL_TOKEN = 1798;

		// Field descriptor #544 I
		public static final int GL_LINE_RESET_TOKEN = 1799;

		// Field descriptor #544 I
		public static final int GL_EXP = 2048;

		// Field descriptor #544 I
		public static final int GL_EXP2 = 2049;

		// Field descriptor #544 I
		public static final int GL_CW = 2304;

		// Field descriptor #544 I
		public static final int GL_CCW = 2305;

		// Field descriptor #544 I
		public static final int GL_COEFF = 2560;

		// Field descriptor #544 I
		public static final int GL_ORDER = 2561;

		// Field descriptor #544 I
		public static final int GL_DOMAIN = 2562;

		// Field descriptor #544 I
		public static final int GL_CURRENT_COLOR = 2816;

		// Field descriptor #544 I
		public static final int GL_CURRENT_INDEX = 2817;

		// Field descriptor #544 I
		public static final int GL_CURRENT_NORMAL = 2818;

		// Field descriptor #544 I
		public static final int GL_CURRENT_TEXTURE_COORDS = 2819;

		// Field descriptor #544 I
		public static final int GL_CURRENT_RASTER_COLOR = 2820;

		// Field descriptor #544 I
		public static final int GL_CURRENT_RASTER_INDEX = 2821;

		// Field descriptor #544 I
		public static final int GL_CURRENT_RASTER_TEXTURE_COORDS = 2822;

		// Field descriptor #544 I
		public static final int GL_CURRENT_RASTER_POSITION = 2823;

		// Field descriptor #544 I
		public static final int GL_CURRENT_RASTER_POSITION_VALID = 2824;

		// Field descriptor #544 I
		public static final int GL_CURRENT_RASTER_DISTANCE = 2825;

		// Field descriptor #544 I
		public static final int GL_POINT_SMOOTH = 2832;

		// Field descriptor #544 I
		public static final int GL_POINT_SIZE = 2833;

		// Field descriptor #544 I
		public static final int GL_POINT_SIZE_RANGE = 2834;

		// Field descriptor #544 I
		public static final int GL_POINT_SIZE_GRANULARITY = 2835;

		// Field descriptor #544 I
		public static final int GL_LINE_SMOOTH = 2848;

		// Field descriptor #544 I
		public static final int GL_LINE_WIDTH = 2849;

		// Field descriptor #544 I
		public static final int GL_LINE_WIDTH_RANGE = 2850;

		// Field descriptor #544 I
		public static final int GL_LINE_WIDTH_GRANULARITY = 2851;

		// Field descriptor #544 I
		public static final int GL_LINE_STIPPLE = 2852;

		// Field descriptor #544 I
		public static final int GL_LINE_STIPPLE_PATTERN = 2853;

		// Field descriptor #544 I
		public static final int GL_LINE_STIPPLE_REPEAT = 2854;

		// Field descriptor #544 I
		public static final int GL_LIST_MODE = 2864;

		// Field descriptor #544 I
		public static final int GL_MAX_LIST_NESTING = 2865;

		// Field descriptor #544 I
		public static final int GL_LIST_BASE = 2866;

		// Field descriptor #544 I
		public static final int GL_LIST_INDEX = 2867;

		// Field descriptor #544 I
		public static final int GL_POLYGON_MODE = 2880;

		// Field descriptor #544 I
		public static final int GL_POLYGON_SMOOTH = 2881;

		// Field descriptor #544 I
		public static final int GL_POLYGON_STIPPLE = 2882;

		// Field descriptor #544 I
		public static final int GL_EDGE_FLAG = 2883;

		// Field descriptor #544 I
		public static final int GL_CULL_FACE = 2884;

		// Field descriptor #544 I
		public static final int GL_CULL_FACE_MODE = 2885;

		// Field descriptor #544 I
		public static final int GL_FRONT_FACE = 2886;

		// Field descriptor #544 I
		public static final int GL_LIGHTING = 2896;

		// Field descriptor #544 I
		public static final int GL_LIGHT_MODEL_LOCAL_VIEWER = 2897;

		// Field descriptor #544 I
		public static final int GL_LIGHT_MODEL_TWO_SIDE = 2898;

		// Field descriptor #544 I
		public static final int GL_LIGHT_MODEL_AMBIENT = 2899;

		// Field descriptor #544 I
		public static final int GL_SHADE_MODEL = 2900;

		// Field descriptor #544 I
		public static final int GL_COLOR_MATERIAL_FACE = 2901;

		// Field descriptor #544 I
		public static final int GL_COLOR_MATERIAL_PARAMETER = 2902;

		// Field descriptor #544 I
		public static final int GL_COLOR_MATERIAL = 2903;

		// Field descriptor #544 I
		public static final int GL_FOG = 2912;

		// Field descriptor #544 I
		public static final int GL_FOG_INDEX = 2913;

		// Field descriptor #544 I
		public static final int GL_FOG_DENSITY = 2914;

		// Field descriptor #544 I
		public static final int GL_FOG_START = 2915;

		// Field descriptor #544 I
		public static final int GL_FOG_END = 2916;

		// Field descriptor #544 I
		public static final int GL_FOG_MODE = 2917;

		// Field descriptor #544 I
		public static final int GL_FOG_COLOR = 2918;

		// Field descriptor #544 I
		public static final int GL_DEPTH_RANGE = 2928;

		// Field descriptor #544 I
		public static final int GL_DEPTH_TEST = 2929;

		// Field descriptor #544 I
		public static final int GL_DEPTH_WRITEMASK = 2930;

		// Field descriptor #544 I
		public static final int GL_DEPTH_CLEAR_VALUE = 2931;

		// Field descriptor #544 I
		public static final int GL_DEPTH_FUNC = 2932;

		// Field descriptor #544 I
		public static final int GL_ACCUM_CLEAR_VALUE = 2944;

		// Field descriptor #544 I
		public static final int GL_STENCIL_TEST = 2960;

		// Field descriptor #544 I
		public static final int GL_STENCIL_CLEAR_VALUE = 2961;

		// Field descriptor #544 I
		public static final int GL_STENCIL_FUNC = 2962;

		// Field descriptor #544 I
		public static final int GL_STENCIL_VALUE_MASK = 2963;

		// Field descriptor #544 I
		public static final int GL_STENCIL_FAIL = 2964;

		// Field descriptor #544 I
		public static final int GL_STENCIL_PASS_DEPTH_FAIL = 2965;

		// Field descriptor #544 I
		public static final int GL_STENCIL_PASS_DEPTH_PASS = 2966;

		// Field descriptor #544 I
		public static final int GL_STENCIL_REF = 2967;

		// Field descriptor #544 I
		public static final int GL_STENCIL_WRITEMASK = 2968;

		// Field descriptor #544 I
		public static final int GL_MATRIX_MODE = 2976;

		// Field descriptor #544 I
		public static final int GL_NORMALIZE = 2977;

		// Field descriptor #544 I
		public static final int GL_VIEWPORT = 2978;

		// Field descriptor #544 I
		public static final int GL_MODELVIEW_STACK_DEPTH = 2979;

		// Field descriptor #544 I
		public static final int GL_PROJECTION_STACK_DEPTH = 2980;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_STACK_DEPTH = 2981;

		// Field descriptor #544 I
		public static final int GL_MODELVIEW_MATRIX = 2982;

		// Field descriptor #544 I
		public static final int GL_PROJECTION_MATRIX = 2983;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_MATRIX = 2984;

		// Field descriptor #544 I
		public static final int GL_ATTRIB_STACK_DEPTH = 2992;

		// Field descriptor #544 I
		public static final int GL_CLIENT_ATTRIB_STACK_DEPTH = 2993;

		// Field descriptor #544 I
		public static final int GL_ALPHA_TEST = 3008;

		// Field descriptor #544 I
		public static final int GL_ALPHA_TEST_FUNC = 3009;

		// Field descriptor #544 I
		public static final int GL_ALPHA_TEST_REF = 3010;

		// Field descriptor #544 I
		public static final int GL_DITHER = 3024;

		// Field descriptor #544 I
		public static final int GL_BLEND_DST = 3040;

		// Field descriptor #544 I
		public static final int GL_BLEND_SRC = 3041;

		// Field descriptor #544 I
		public static final int GL_BLEND = 3042;

		// Field descriptor #544 I
		public static final int GL_LOGIC_OP_MODE = 3056;

		// Field descriptor #544 I
		public static final int GL_INDEX_LOGIC_OP = 3057;

		// Field descriptor #544 I
		public static final int GL_COLOR_LOGIC_OP = 3058;

		// Field descriptor #544 I
		public static final int GL_AUX_BUFFERS = 3072;

		// Field descriptor #544 I
		public static final int GL_DRAW_BUFFER = 3073;

		// Field descriptor #544 I
		public static final int GL_READ_BUFFER = 3074;

		// Field descriptor #544 I
		public static final int GL_SCISSOR_BOX = 3088;

		// Field descriptor #544 I
		public static final int GL_SCISSOR_TEST = 3089;

		// Field descriptor #544 I
		public static final int GL_INDEX_CLEAR_VALUE = 3104;

		// Field descriptor #544 I
		public static final int GL_INDEX_WRITEMASK = 3105;

		// Field descriptor #544 I
		public static final int GL_COLOR_CLEAR_VALUE = 3106;

		// Field descriptor #544 I
		public static final int GL_COLOR_WRITEMASK = 3107;

		// Field descriptor #544 I
		public static final int GL_INDEX_MODE = 3120;

		// Field descriptor #544 I
		public static final int GL_RGBA_MODE = 3121;

		// Field descriptor #544 I
		public static final int GL_DOUBLEBUFFER = 3122;

		// Field descriptor #544 I
		public static final int GL_STEREO = 3123;

		// Field descriptor #544 I
		public static final int GL_RENDER_MODE = 3136;

		// Field descriptor #544 I
		public static final int GL_PERSPECTIVE_CORRECTION_HINT = 3152;

		// Field descriptor #544 I
		public static final int GL_POINT_SMOOTH_HINT = 3153;

		// Field descriptor #544 I
		public static final int GL_LINE_SMOOTH_HINT = 3154;

		// Field descriptor #544 I
		public static final int GL_POLYGON_SMOOTH_HINT = 3155;

		// Field descriptor #544 I
		public static final int GL_FOG_HINT = 3156;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_GEN_S = 3168;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_GEN_T = 3169;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_GEN_R = 3170;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_GEN_Q = 3171;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_I_TO_I = 3184;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_S_TO_S = 3185;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_I_TO_R = 3186;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_I_TO_G = 3187;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_I_TO_B = 3188;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_I_TO_A = 3189;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_R_TO_R = 3190;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_G_TO_G = 3191;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_B_TO_B = 3192;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_A_TO_A = 3193;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_I_TO_I_SIZE = 3248;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_S_TO_S_SIZE = 3249;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_I_TO_R_SIZE = 3250;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_I_TO_G_SIZE = 3251;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_I_TO_B_SIZE = 3252;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_I_TO_A_SIZE = 3253;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_R_TO_R_SIZE = 3254;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_G_TO_G_SIZE = 3255;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_B_TO_B_SIZE = 3256;

		// Field descriptor #544 I
		public static final int GL_PIXEL_MAP_A_TO_A_SIZE = 3257;

		// Field descriptor #544 I
		public static final int GL_UNPACK_SWAP_BYTES = 3312;

		// Field descriptor #544 I
		public static final int GL_UNPACK_LSB_FIRST = 3313;

		// Field descriptor #544 I
		public static final int GL_UNPACK_ROW_LENGTH = 3314;

		// Field descriptor #544 I
		public static final int GL_UNPACK_SKIP_ROWS = 3315;

		// Field descriptor #544 I
		public static final int GL_UNPACK_SKIP_PIXELS = 3316;

		// Field descriptor #544 I
		public static final int GL_UNPACK_ALIGNMENT = 3317;

		// Field descriptor #544 I
		public static final int GL_PACK_SWAP_BYTES = 3328;

		// Field descriptor #544 I
		public static final int GL_PACK_LSB_FIRST = 3329;

		// Field descriptor #544 I
		public static final int GL_PACK_ROW_LENGTH = 3330;

		// Field descriptor #544 I
		public static final int GL_PACK_SKIP_ROWS = 3331;

		// Field descriptor #544 I
		public static final int GL_PACK_SKIP_PIXELS = 3332;

		// Field descriptor #544 I
		public static final int GL_PACK_ALIGNMENT = 3333;

		// Field descriptor #544 I
		public static final int GL_MAP_COLOR = 3344;

		// Field descriptor #544 I
		public static final int GL_MAP_STENCIL = 3345;

		// Field descriptor #544 I
		public static final int GL_INDEX_SHIFT = 3346;

		// Field descriptor #544 I
		public static final int GL_INDEX_OFFSET = 3347;

		// Field descriptor #544 I
		public static final int GL_RED_SCALE = 3348;

		// Field descriptor #544 I
		public static final int GL_RED_BIAS = 3349;

		// Field descriptor #544 I
		public static final int GL_ZOOM_X = 3350;

		// Field descriptor #544 I
		public static final int GL_ZOOM_Y = 3351;

		// Field descriptor #544 I
		public static final int GL_GREEN_SCALE = 3352;

		// Field descriptor #544 I
		public static final int GL_GREEN_BIAS = 3353;

		// Field descriptor #544 I
		public static final int GL_BLUE_SCALE = 3354;

		// Field descriptor #544 I
		public static final int GL_BLUE_BIAS = 3355;

		// Field descriptor #544 I
		public static final int GL_ALPHA_SCALE = 3356;

		// Field descriptor #544 I
		public static final int GL_ALPHA_BIAS = 3357;

		// Field descriptor #544 I
		public static final int GL_DEPTH_SCALE = 3358;

		// Field descriptor #544 I
		public static final int GL_DEPTH_BIAS = 3359;

		// Field descriptor #544 I
		public static final int GL_MAX_EVAL_ORDER = 3376;

		// Field descriptor #544 I
		public static final int GL_MAX_LIGHTS = 3377;

		// Field descriptor #544 I
		public static final int GL_MAX_CLIP_PLANES = 3378;

		// Field descriptor #544 I
		public static final int GL_MAX_TEXTURE_SIZE = 3379;

		// Field descriptor #544 I
		public static final int GL_MAX_PIXEL_MAP_TABLE = 3380;

		// Field descriptor #544 I
		public static final int GL_MAX_ATTRIB_STACK_DEPTH = 3381;

		// Field descriptor #544 I
		public static final int GL_MAX_MODELVIEW_STACK_DEPTH = 3382;

		// Field descriptor #544 I
		public static final int GL_MAX_NAME_STACK_DEPTH = 3383;

		// Field descriptor #544 I
		public static final int GL_MAX_PROJECTION_STACK_DEPTH = 3384;

		// Field descriptor #544 I
		public static final int GL_MAX_TEXTURE_STACK_DEPTH = 3385;

		// Field descriptor #544 I
		public static final int GL_MAX_VIEWPORT_DIMS = 3386;

		// Field descriptor #544 I
		public static final int GL_MAX_CLIENT_ATTRIB_STACK_DEPTH = 3387;

		// Field descriptor #544 I
		public static final int GL_SUBPIXEL_BITS = 3408;

		// Field descriptor #544 I
		public static final int GL_INDEX_BITS = 3409;

		// Field descriptor #544 I
		public static final int GL_RED_BITS = 3410;

		// Field descriptor #544 I
		public static final int GL_GREEN_BITS = 3411;

		// Field descriptor #544 I
		public static final int GL_BLUE_BITS = 3412;

		// Field descriptor #544 I
		public static final int GL_ALPHA_BITS = 3413;

		// Field descriptor #544 I
		public static final int GL_DEPTH_BITS = 3414;

		// Field descriptor #544 I
		public static final int GL_STENCIL_BITS = 3415;

		// Field descriptor #544 I
		public static final int GL_ACCUM_RED_BITS = 3416;

		// Field descriptor #544 I
		public static final int GL_ACCUM_GREEN_BITS = 3417;

		// Field descriptor #544 I
		public static final int GL_ACCUM_BLUE_BITS = 3418;

		// Field descriptor #544 I
		public static final int GL_ACCUM_ALPHA_BITS = 3419;

		// Field descriptor #544 I
		public static final int GL_NAME_STACK_DEPTH = 3440;

		// Field descriptor #544 I
		public static final int GL_AUTO_NORMAL = 3456;

		// Field descriptor #544 I
		public static final int GL_MAP1_COLOR_4 = 3472;

		// Field descriptor #544 I
		public static final int GL_MAP1_INDEX = 3473;

		// Field descriptor #544 I
		public static final int GL_MAP1_NORMAL = 3474;

		// Field descriptor #544 I
		public static final int GL_MAP1_TEXTURE_COORD_1 = 3475;

		// Field descriptor #544 I
		public static final int GL_MAP1_TEXTURE_COORD_2 = 3476;

		// Field descriptor #544 I
		public static final int GL_MAP1_TEXTURE_COORD_3 = 3477;

		// Field descriptor #544 I
		public static final int GL_MAP1_TEXTURE_COORD_4 = 3478;

		// Field descriptor #544 I
		public static final int GL_MAP1_VERTEX_3 = 3479;

		// Field descriptor #544 I
		public static final int GL_MAP1_VERTEX_4 = 3480;

		// Field descriptor #544 I
		public static final int GL_MAP2_COLOR_4 = 3504;

		// Field descriptor #544 I
		public static final int GL_MAP2_INDEX = 3505;

		// Field descriptor #544 I
		public static final int GL_MAP2_NORMAL = 3506;

		// Field descriptor #544 I
		public static final int GL_MAP2_TEXTURE_COORD_1 = 3507;

		// Field descriptor #544 I
		public static final int GL_MAP2_TEXTURE_COORD_2 = 3508;

		// Field descriptor #544 I
		public static final int GL_MAP2_TEXTURE_COORD_3 = 3509;

		// Field descriptor #544 I
		public static final int GL_MAP2_TEXTURE_COORD_4 = 3510;

		// Field descriptor #544 I
		public static final int GL_MAP2_VERTEX_3 = 3511;

		// Field descriptor #544 I
		public static final int GL_MAP2_VERTEX_4 = 3512;

		// Field descriptor #544 I
		public static final int GL_MAP1_GRID_DOMAIN = 3536;

		// Field descriptor #544 I
		public static final int GL_MAP1_GRID_SEGMENTS = 3537;

		// Field descriptor #544 I
		public static final int GL_MAP2_GRID_DOMAIN = 3538;

		// Field descriptor #544 I
		public static final int GL_MAP2_GRID_SEGMENTS = 3539;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_1D = 3552;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_2D = 3553;

		// Field descriptor #544 I
		public static final int GL_FEEDBACK_BUFFER_POINTER = 3568;

		// Field descriptor #544 I
		public static final int GL_FEEDBACK_BUFFER_SIZE = 3569;

		// Field descriptor #544 I
		public static final int GL_FEEDBACK_BUFFER_TYPE = 3570;

		// Field descriptor #544 I
		public static final int GL_SELECTION_BUFFER_POINTER = 3571;

		// Field descriptor #544 I
		public static final int GL_SELECTION_BUFFER_SIZE = 3572;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_WIDTH = 4096;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_HEIGHT = 4097;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_INTERNAL_FORMAT = 4099;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_BORDER_COLOR = 4100;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_BORDER = 4101;

		// Field descriptor #544 I
		public static final int GL_DONT_CARE = 4352;

		// Field descriptor #544 I
		public static final int GL_FASTEST = 4353;

		// Field descriptor #544 I
		public static final int GL_NICEST = 4354;

		// Field descriptor #544 I
		public static final int GL_LIGHT0 = 16384;

		// Field descriptor #544 I
		public static final int GL_LIGHT1 = 16385;

		// Field descriptor #544 I
		public static final int GL_LIGHT2 = 16386;

		// Field descriptor #544 I
		public static final int GL_LIGHT3 = 16387;

		// Field descriptor #544 I
		public static final int GL_LIGHT4 = 16388;

		// Field descriptor #544 I
		public static final int GL_LIGHT5 = 16389;

		// Field descriptor #544 I
		public static final int GL_LIGHT6 = 16390;

		// Field descriptor #544 I
		public static final int GL_LIGHT7 = 16391;

		// Field descriptor #544 I
		public static final int GL_AMBIENT = 4608;

		// Field descriptor #544 I
		public static final int GL_DIFFUSE = 4609;

		// Field descriptor #544 I
		public static final int GL_SPECULAR = 4610;

		// Field descriptor #544 I
		public static final int GL_POSITION = 4611;

		// Field descriptor #544 I
		public static final int GL_SPOT_DIRECTION = 4612;

		// Field descriptor #544 I
		public static final int GL_SPOT_EXPONENT = 4613;

		// Field descriptor #544 I
		public static final int GL_SPOT_CUTOFF = 4614;

		// Field descriptor #544 I
		public static final int GL_CONSTANT_ATTENUATION = 4615;

		// Field descriptor #544 I
		public static final int GL_LINEAR_ATTENUATION = 4616;

		// Field descriptor #544 I
		public static final int GL_QUADRATIC_ATTENUATION = 4617;

		// Field descriptor #544 I
		public static final int GL_COMPILE = 4864;

		// Field descriptor #544 I
		public static final int GL_COMPILE_AND_EXECUTE = 4865;

		// Field descriptor #544 I
		public static final int GL_CLEAR = 5376;

		// Field descriptor #544 I
		public static final int GL_AND = 5377;

		// Field descriptor #544 I
		public static final int GL_AND_REVERSE = 5378;

		// Field descriptor #544 I
		public static final int GL_COPY = 5379;

		// Field descriptor #544 I
		public static final int GL_AND_INVERTED = 5380;

		// Field descriptor #544 I
		public static final int GL_NOOP = 5381;

		// Field descriptor #544 I
		public static final int GL_XOR = 5382;

		// Field descriptor #544 I
		public static final int GL_OR = 5383;

		// Field descriptor #544 I
		public static final int GL_NOR = 5384;

		// Field descriptor #544 I
		public static final int GL_EQUIV = 5385;

		// Field descriptor #544 I
		public static final int GL_INVERT = 5386;

		// Field descriptor #544 I
		public static final int GL_OR_REVERSE = 5387;

		// Field descriptor #544 I
		public static final int GL_COPY_INVERTED = 5388;

		// Field descriptor #544 I
		public static final int GL_OR_INVERTED = 5389;

		// Field descriptor #544 I
		public static final int GL_NAND = 5390;

		// Field descriptor #544 I
		public static final int GL_SET = 5391;

		// Field descriptor #544 I
		public static final int GL_EMISSION = 5632;

		// Field descriptor #544 I
		public static final int GL_SHININESS = 5633;

		// Field descriptor #544 I
		public static final int GL_AMBIENT_AND_DIFFUSE = 5634;

		// Field descriptor #544 I
		public static final int GL_COLOR_INDEXES = 5635;

		// Field descriptor #544 I
		public static final int GL_MODELVIEW = 5888;

		// Field descriptor #544 I
		public static final int GL_PROJECTION = 5889;

		// Field descriptor #544 I
		public static final int GL_TEXTURE = 5890;

		// Field descriptor #544 I
		public static final int GL_COLOR = 6144;

		// Field descriptor #544 I
		public static final int GL_DEPTH = 6145;

		// Field descriptor #544 I
		public static final int GL_STENCIL = 6146;

		// Field descriptor #544 I
		public static final int GL_COLOR_INDEX = 6400;

		// Field descriptor #544 I
		public static final int GL_STENCIL_INDEX = 6401;

		// Field descriptor #544 I
		public static final int GL_DEPTH_COMPONENT = 6402;

		// Field descriptor #544 I
		public static final int GL_RED = 6403;

		// Field descriptor #544 I
		public static final int GL_GREEN = 6404;

		// Field descriptor #544 I
		public static final int GL_BLUE = 6405;

		// Field descriptor #544 I
		public static final int GL_ALPHA = 6406;

		// Field descriptor #544 I
		public static final int GL_RGB = 6407;

		// Field descriptor #544 I
		public static final int GL_RGBA = 6408;

		// Field descriptor #544 I
		public static final int GL_LUMINANCE = 6409;

		// Field descriptor #544 I
		public static final int GL_LUMINANCE_ALPHA = 6410;

		// Field descriptor #544 I
		public static final int GL_BITMAP = 6656;

		// Field descriptor #544 I
		public static final int GL_POINT = 6912;

		// Field descriptor #544 I
		public static final int GL_LINE = 6913;

		// Field descriptor #544 I
		public static final int GL_FILL = 6914;

		// Field descriptor #544 I
		public static final int GL_RENDER = 7168;

		// Field descriptor #544 I
		public static final int GL_FEEDBACK = 7169;

		// Field descriptor #544 I
		public static final int GL_SELECT = 7170;

		// Field descriptor #544 I
		public static final int GL_FLAT = 7424;

		// Field descriptor #544 I
		public static final int GL_SMOOTH = 7425;

		// Field descriptor #544 I
		public static final int GL_KEEP = 7680;

		// Field descriptor #544 I
		public static final int GL_REPLACE = 7681;

		// Field descriptor #544 I
		public static final int GL_INCR = 7682;

		// Field descriptor #544 I
		public static final int GL_DECR = 7683;

		// Field descriptor #544 I
		public static final int GL_VENDOR = 7936;

		// Field descriptor #544 I
		public static final int GL_RENDERER = 7937;

		// Field descriptor #544 I
		public static final int GL_VERSION = 7938;

		// Field descriptor #544 I
		public static final int GL_EXTENSIONS = 7939;

		// Field descriptor #544 I
		public static final int GL_S = 8192;

		// Field descriptor #544 I
		public static final int GL_T = 8193;

		// Field descriptor #544 I
		public static final int GL_R = 8194;

		// Field descriptor #544 I
		public static final int GL_Q = 8195;

		// Field descriptor #544 I
		public static final int GL_MODULATE = 8448;

		// Field descriptor #544 I
		public static final int GL_DECAL = 8449;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_ENV_MODE = 8704;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_ENV_COLOR = 8705;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_ENV = 8960;

		// Field descriptor #544 I
		public static final int GL_EYE_LINEAR = 9216;

		// Field descriptor #544 I
		public static final int GL_OBJECT_LINEAR = 9217;

		// Field descriptor #544 I
		public static final int GL_SPHERE_MAP = 9218;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_GEN_MODE = 9472;

		// Field descriptor #544 I
		public static final int GL_OBJECT_PLANE = 9473;

		// Field descriptor #544 I
		public static final int GL_EYE_PLANE = 9474;

		// Field descriptor #544 I
		public static final int GL_NEAREST = 9728;

		// Field descriptor #544 I
		public static final int GL_LINEAR = 9729;

		// Field descriptor #544 I
		public static final int GL_NEAREST_MIPMAP_NEAREST = 9984;

		// Field descriptor #544 I
		public static final int GL_LINEAR_MIPMAP_NEAREST = 9985;

		// Field descriptor #544 I
		public static final int GL_NEAREST_MIPMAP_LINEAR = 9986;

		// Field descriptor #544 I
		public static final int GL_LINEAR_MIPMAP_LINEAR = 9987;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_MAG_FILTER = 10240;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_MIN_FILTER = 10241;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_WRAP_S = 10242;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_WRAP_T = 10243;

		// Field descriptor #544 I
		public static final int GL_CLAMP = 10496;

		// Field descriptor #544 I
		public static final int GL_REPEAT = 10497;

		// Field descriptor #544 I
		public static final int GL_CLIENT_PIXEL_STORE_BIT = 1;

		// Field descriptor #544 I
		public static final int GL_CLIENT_VERTEX_ARRAY_BIT = 2;

		// Field descriptor #544 I
		public static final int GL_ALL_CLIENT_ATTRIB_BITS = -1;

		// Field descriptor #544 I
		public static final int GL_POLYGON_OFFSET_FACTOR = 32824;

		// Field descriptor #544 I
		public static final int GL_POLYGON_OFFSET_UNITS = 10752;

		// Field descriptor #544 I
		public static final int GL_POLYGON_OFFSET_POINT = 10753;

		// Field descriptor #544 I
		public static final int GL_POLYGON_OFFSET_LINE = 10754;

		// Field descriptor #544 I
		public static final int GL_POLYGON_OFFSET_FILL = 32823;

		// Field descriptor #544 I
		public static final int GL_ALPHA4 = 32827;

		// Field descriptor #544 I
		public static final int GL_ALPHA8 = 32828;

		// Field descriptor #544 I
		public static final int GL_ALPHA12 = 32829;

		// Field descriptor #544 I
		public static final int GL_ALPHA16 = 32830;

		// Field descriptor #544 I
		public static final int GL_LUMINANCE4 = 32831;

		// Field descriptor #544 I
		public static final int GL_LUMINANCE8 = 32832;

		// Field descriptor #544 I
		public static final int GL_LUMINANCE12 = 32833;

		// Field descriptor #544 I
		public static final int GL_LUMINANCE16 = 32834;

		// Field descriptor #544 I
		public static final int GL_LUMINANCE4_ALPHA4 = 32835;

		// Field descriptor #544 I
		public static final int GL_LUMINANCE6_ALPHA2 = 32836;

		// Field descriptor #544 I
		public static final int GL_LUMINANCE8_ALPHA8 = 32837;

		// Field descriptor #544 I
		public static final int GL_LUMINANCE12_ALPHA4 = 32838;

		// Field descriptor #544 I
		public static final int GL_LUMINANCE12_ALPHA12 = 32839;

		// Field descriptor #544 I
		public static final int GL_LUMINANCE16_ALPHA16 = 32840;

		// Field descriptor #544 I
		public static final int GL_INTENSITY = 32841;

		// Field descriptor #544 I
		public static final int GL_INTENSITY4 = 32842;

		// Field descriptor #544 I
		public static final int GL_INTENSITY8 = 32843;

		// Field descriptor #544 I
		public static final int GL_INTENSITY12 = 32844;

		// Field descriptor #544 I
		public static final int GL_INTENSITY16 = 32845;

		// Field descriptor #544 I
		public static final int GL_R3_G3_B2 = 10768;

		// Field descriptor #544 I
		public static final int GL_RGB4 = 32847;

		// Field descriptor #544 I
		public static final int GL_RGB5 = 32848;

		// Field descriptor #544 I
		public static final int GL_RGB8 = 32849;

		// Field descriptor #544 I
		public static final int GL_RGB10 = 32850;

		// Field descriptor #544 I
		public static final int GL_RGB12 = 32851;

		// Field descriptor #544 I
		public static final int GL_RGB16 = 32852;

		// Field descriptor #544 I
		public static final int GL_RGBA2 = 32853;

		// Field descriptor #544 I
		public static final int GL_RGBA4 = 32854;

		// Field descriptor #544 I
		public static final int GL_RGB5_A1 = 32855;

		// Field descriptor #544 I
		public static final int GL_RGBA8 = 32856;

		// Field descriptor #544 I
		public static final int GL_RGB10_A2 = 32857;

		// Field descriptor #544 I
		public static final int GL_RGBA12 = 32858;

		// Field descriptor #544 I
		public static final int GL_RGBA16 = 32859;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_RED_SIZE = 32860;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_GREEN_SIZE = 32861;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_BLUE_SIZE = 32862;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_ALPHA_SIZE = 32863;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_LUMINANCE_SIZE = 32864;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_INTENSITY_SIZE = 32865;

		// Field descriptor #544 I
		public static final int GL_PROXY_TEXTURE_1D = 32867;

		// Field descriptor #544 I
		public static final int GL_PROXY_TEXTURE_2D = 32868;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_PRIORITY = 32870;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_RESIDENT = 32871;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_BINDING_1D = 32872;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_BINDING_2D = 32873;

		// Field descriptor #544 I
		public static final int GL_VERTEX_ARRAY = 32884;

		// Field descriptor #544 I
		public static final int GL_NORMAL_ARRAY = 32885;

		// Field descriptor #544 I
		public static final int GL_COLOR_ARRAY = 32886;

		// Field descriptor #544 I
		public static final int GL_INDEX_ARRAY = 32887;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_COORD_ARRAY = 32888;

		// Field descriptor #544 I
		public static final int GL_EDGE_FLAG_ARRAY = 32889;

		// Field descriptor #544 I
		public static final int GL_VERTEX_ARRAY_SIZE = 32890;

		// Field descriptor #544 I
		public static final int GL_VERTEX_ARRAY_TYPE = 32891;

		// Field descriptor #544 I
		public static final int GL_VERTEX_ARRAY_STRIDE = 32892;

		// Field descriptor #544 I
		public static final int GL_NORMAL_ARRAY_TYPE = 32894;

		// Field descriptor #544 I
		public static final int GL_NORMAL_ARRAY_STRIDE = 32895;

		// Field descriptor #544 I
		public static final int GL_COLOR_ARRAY_SIZE = 32897;

		// Field descriptor #544 I
		public static final int GL_COLOR_ARRAY_TYPE = 32898;

		// Field descriptor #544 I
		public static final int GL_COLOR_ARRAY_STRIDE = 32899;

		// Field descriptor #544 I
		public static final int GL_INDEX_ARRAY_TYPE = 32901;

		// Field descriptor #544 I
		public static final int GL_INDEX_ARRAY_STRIDE = 32902;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_COORD_ARRAY_SIZE = 32904;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_COORD_ARRAY_TYPE = 32905;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_COORD_ARRAY_STRIDE = 32906;

		// Field descriptor #544 I
		public static final int GL_EDGE_FLAG_ARRAY_STRIDE = 32908;

		// Field descriptor #544 I
		public static final int GL_VERTEX_ARRAY_POINTER = 32910;

		// Field descriptor #544 I
		public static final int GL_NORMAL_ARRAY_POINTER = 32911;

		// Field descriptor #544 I
		public static final int GL_COLOR_ARRAY_POINTER = 32912;

		// Field descriptor #544 I
		public static final int GL_INDEX_ARRAY_POINTER = 32913;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_COORD_ARRAY_POINTER = 32914;

		// Field descriptor #544 I
		public static final int GL_EDGE_FLAG_ARRAY_POINTER = 32915;

		// Field descriptor #544 I
		public static final int GL_V2F = 10784;

		// Field descriptor #544 I
		public static final int GL_V3F = 10785;

		// Field descriptor #544 I
		public static final int GL_C4UB_V2F = 10786;

		// Field descriptor #544 I
		public static final int GL_C4UB_V3F = 10787;

		// Field descriptor #544 I
		public static final int GL_C3F_V3F = 10788;

		// Field descriptor #544 I
		public static final int GL_N3F_V3F = 10789;

		// Field descriptor #544 I
		public static final int GL_C4F_N3F_V3F = 10790;

		// Field descriptor #544 I
		public static final int GL_T2F_V3F = 10791;

		// Field descriptor #544 I
		public static final int GL_T4F_V4F = 10792;

		// Field descriptor #544 I
		public static final int GL_T2F_C4UB_V3F = 10793;

		// Field descriptor #544 I
		public static final int GL_T2F_C3F_V3F = 10794;

		// Field descriptor #544 I
		public static final int GL_T2F_N3F_V3F = 10795;

		// Field descriptor #544 I
		public static final int GL_T2F_C4F_N3F_V3F = 10796;

		// Field descriptor #544 I
		public static final int GL_T4F_C4F_N3F_V4F = 10797;

		// Field descriptor #544 I
		public static final int GL_LOGIC_OP = 3057;

		// Field descriptor #544 I
		public static final int GL_TEXTURE_COMPONENTS = 4099;

		// Field descriptor #45 I
		public static final int GL_TEXTURE_BINDING_3D = 32874;

		// Field descriptor #45 I
		public static final int GL_PACK_SKIP_IMAGES = 32875;

		// Field descriptor #45 I
		public static final int GL_PACK_IMAGE_HEIGHT = 32876;

		// Field descriptor #45 I
		public static final int GL_UNPACK_SKIP_IMAGES = 32877;

		// Field descriptor #45 I
		public static final int GL_UNPACK_IMAGE_HEIGHT = 32878;

		// Field descriptor #45 I
		public static final int GL_TEXTURE_3D = 32879;

		// Field descriptor #45 I
		public static final int GL_PROXY_TEXTURE_3D = 32880;

		// Field descriptor #45 I
		public static final int GL_TEXTURE_DEPTH = 32881;

		// Field descriptor #45 I
		public static final int GL_TEXTURE_WRAP_R = 32882;

		// Field descriptor #45 I
		public static final int GL_MAX_3D_TEXTURE_SIZE = 32883;

		// Field descriptor #45 I
		public static final int GL_BGR = 32992;

		// Field descriptor #45 I
		public static final int GL_BGRA = 32993;

		// Field descriptor #45 I
		public static final int GL_UNSIGNED_BYTE_3_3_2 = 32818;

		// Field descriptor #45 I
		public static final int GL_UNSIGNED_BYTE_2_3_3_REV = 33634;

		// Field descriptor #45 I
		public static final int GL_UNSIGNED_SHORT_5_6_5 = 33635;

		// Field descriptor #45 I
		public static final int GL_UNSIGNED_SHORT_5_6_5_REV = 33636;

		// Field descriptor #45 I
		public static final int GL_UNSIGNED_SHORT_4_4_4_4 = 32819;

		// Field descriptor #45 I
		public static final int GL_UNSIGNED_SHORT_4_4_4_4_REV = 33637;

		// Field descriptor #45 I
		public static final int GL_UNSIGNED_SHORT_5_5_5_1 = 32820;

		// Field descriptor #45 I
		public static final int GL_UNSIGNED_SHORT_1_5_5_5_REV = 33638;

		// Field descriptor #45 I
		public static final int GL_UNSIGNED_INT_8_8_8_8 = 32821;

		// Field descriptor #45 I
		public static final int GL_UNSIGNED_INT_8_8_8_8_REV = 33639;

		// Field descriptor #45 I
		public static final int GL_UNSIGNED_INT_10_10_10_2 = 32822;

		// Field descriptor #45 I
		public static final int GL_UNSIGNED_INT_2_10_10_10_REV = 33640;

		// Field descriptor #45 I
		public static final int GL_RESCALE_NORMAL = 32826;

		// Field descriptor #45 I
		public static final int GL_LIGHT_MODEL_COLOR_CONTROL = 33272;

		// Field descriptor #45 I
		public static final int GL_SINGLE_COLOR = 33273;

		// Field descriptor #45 I
		public static final int GL_SEPARATE_SPECULAR_COLOR = 33274;

		// Field descriptor #45 I
		public static final int GL_CLAMP_TO_EDGE = 33071;

		// Field descriptor #45 I
		public static final int GL_TEXTURE_MIN_LOD = 33082;

		// Field descriptor #45 I
		public static final int GL_TEXTURE_MAX_LOD = 33083;

		// Field descriptor #45 I
		public static final int GL_TEXTURE_BASE_LEVEL = 33084;

		// Field descriptor #45 I
		public static final int GL_TEXTURE_MAX_LEVEL = 33085;

		// Field descriptor #45 I
		public static final int GL_MAX_ELEMENTS_VERTICES = 33000;

		// Field descriptor #45 I
		public static final int GL_MAX_ELEMENTS_INDICES = 33001;

		// Field descriptor #45 I
		public static final int GL_ALIASED_POINT_SIZE_RANGE = 33901;

		// Field descriptor #45 I
		public static final int GL_ALIASED_LINE_WIDTH_RANGE = 33902;

		// Field descriptor #45 I
		public static final int GL_SMOOTH_POINT_SIZE_RANGE = 2834;

		// Field descriptor #45 I
		public static final int GL_SMOOTH_POINT_SIZE_GRANULARITY = 2835;

		// Field descriptor #45 I
		public static final int GL_SMOOTH_LINE_WIDTH_RANGE = 2850;

		// Field descriptor #45 I
		public static final int GL_SMOOTH_LINE_WIDTH_GRANULARITY = 2851;

		// Field descriptor #76 I
		public static final int GL_TEXTURE0 = 33984;

		// Field descriptor #76 I
		public static final int GL_TEXTURE1 = 33985;

		// Field descriptor #76 I
		public static final int GL_TEXTURE2 = 33986;

		// Field descriptor #76 I
		public static final int GL_TEXTURE3 = 33987;

		// Field descriptor #76 I
		public static final int GL_TEXTURE4 = 33988;

		// Field descriptor #76 I
		public static final int GL_TEXTURE5 = 33989;

		// Field descriptor #76 I
		public static final int GL_TEXTURE6 = 33990;

		// Field descriptor #76 I
		public static final int GL_TEXTURE7 = 33991;

		// Field descriptor #76 I
		public static final int GL_TEXTURE8 = 33992;

		// Field descriptor #76 I
		public static final int GL_TEXTURE9 = 33993;

		// Field descriptor #76 I
		public static final int GL_TEXTURE10 = 33994;

		// Field descriptor #76 I
		public static final int GL_TEXTURE11 = 33995;

		// Field descriptor #76 I
		public static final int GL_TEXTURE12 = 33996;

		// Field descriptor #76 I
		public static final int GL_TEXTURE13 = 33997;

		// Field descriptor #76 I
		public static final int GL_TEXTURE14 = 33998;

		// Field descriptor #76 I
		public static final int GL_TEXTURE15 = 33999;

		// Field descriptor #76 I
		public static final int GL_TEXTURE16 = 34000;

		// Field descriptor #76 I
		public static final int GL_TEXTURE17 = 34001;

		// Field descriptor #76 I
		public static final int GL_TEXTURE18 = 34002;

		// Field descriptor #76 I
		public static final int GL_TEXTURE19 = 34003;

		// Field descriptor #76 I
		public static final int GL_TEXTURE20 = 34004;

		// Field descriptor #76 I
		public static final int GL_TEXTURE21 = 34005;

		// Field descriptor #76 I
		public static final int GL_TEXTURE22 = 34006;

		// Field descriptor #76 I
		public static final int GL_TEXTURE23 = 34007;

		// Field descriptor #76 I
		public static final int GL_TEXTURE24 = 34008;

		// Field descriptor #76 I
		public static final int GL_TEXTURE25 = 34009;

		// Field descriptor #76 I
		public static final int GL_TEXTURE26 = 34010;

		// Field descriptor #76 I
		public static final int GL_TEXTURE27 = 34011;

		// Field descriptor #76 I
		public static final int GL_TEXTURE28 = 34012;

		// Field descriptor #76 I
		public static final int GL_TEXTURE29 = 34013;

		// Field descriptor #76 I
		public static final int GL_TEXTURE30 = 34014;

		// Field descriptor #76 I
		public static final int GL_TEXTURE31 = 34015;

		// Field descriptor #76 I
		public static final int GL_ACTIVE_TEXTURE = 34016;

		// Field descriptor #76 I
		public static final int GL_CLIENT_ACTIVE_TEXTURE = 34017;

		// Field descriptor #76 I
		public static final int GL_MAX_TEXTURE_UNITS = 34018;

		// Field descriptor #76 I
		public static final int GL_NORMAL_MAP = 34065;

		// Field descriptor #76 I
		public static final int GL_REFLECTION_MAP = 34066;

		// Field descriptor #76 I
		public static final int GL_TEXTURE_CUBE_MAP = 34067;

		// Field descriptor #76 I
		public static final int GL_TEXTURE_BINDING_CUBE_MAP = 34068;

		// Field descriptor #76 I
		public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_X = 34069;

		// Field descriptor #76 I
		public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 34070;

		// Field descriptor #76 I
		public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 34071;

		// Field descriptor #76 I
		public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 34072;

		// Field descriptor #76 I
		public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 34073;

		// Field descriptor #76 I
		public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 34074;

		// Field descriptor #76 I
		public static final int GL_PROXY_TEXTURE_CUBE_MAP = 34075;

		// Field descriptor #76 I
		public static final int GL_MAX_CUBE_MAP_TEXTURE_SIZE = 34076;

		// Field descriptor #76 I
		public static final int GL_COMPRESSED_ALPHA = 34025;

		// Field descriptor #76 I
		public static final int GL_COMPRESSED_LUMINANCE = 34026;

		// Field descriptor #76 I
		public static final int GL_COMPRESSED_LUMINANCE_ALPHA = 34027;

		// Field descriptor #76 I
		public static final int GL_COMPRESSED_INTENSITY = 34028;

		// Field descriptor #76 I
		public static final int GL_COMPRESSED_RGB = 34029;

		// Field descriptor #76 I
		public static final int GL_COMPRESSED_RGBA = 34030;

		// Field descriptor #76 I
		public static final int GL_TEXTURE_COMPRESSION_HINT = 34031;

		// Field descriptor #76 I
		public static final int GL_TEXTURE_COMPRESSED_IMAGE_SIZE = 34464;

		// Field descriptor #76 I
		public static final int GL_TEXTURE_COMPRESSED = 34465;

		// Field descriptor #76 I
		public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS = 34466;

		// Field descriptor #76 I
		public static final int GL_COMPRESSED_TEXTURE_FORMATS = 34467;

		// Field descriptor #76 I
		public static final int GL_MULTISAMPLE = 32925;

		// Field descriptor #76 I
		public static final int GL_SAMPLE_ALPHA_TO_COVERAGE = 32926;

		// Field descriptor #76 I
		public static final int GL_SAMPLE_ALPHA_TO_ONE = 32927;

		// Field descriptor #76 I
		public static final int GL_SAMPLE_COVERAGE = 32928;

		// Field descriptor #76 I
		public static final int GL_SAMPLE_BUFFERS = 32936;

		// Field descriptor #76 I
		public static final int GL_SAMPLES = 32937;

		// Field descriptor #76 I
		public static final int GL_SAMPLE_COVERAGE_VALUE = 32938;

		// Field descriptor #76 I
		public static final int GL_SAMPLE_COVERAGE_INVERT = 32939;

		// Field descriptor #76 I
		public static final int GL_MULTISAMPLE_BIT = 536870912;

		// Field descriptor #76 I
		public static final int GL_TRANSPOSE_MODELVIEW_MATRIX = 34019;

		// Field descriptor #76 I
		public static final int GL_TRANSPOSE_PROJECTION_MATRIX = 34020;

		// Field descriptor #76 I
		public static final int GL_TRANSPOSE_TEXTURE_MATRIX = 34021;

		// Field descriptor #76 I
		public static final int GL_TRANSPOSE_COLOR_MATRIX = 34022;

		// Field descriptor #76 I
		public static final int GL_COMBINE = 34160;

		// Field descriptor #76 I
		public static final int GL_COMBINE_RGB = 34161;

		// Field descriptor #76 I
		public static final int GL_COMBINE_ALPHA = 34162;

		// Field descriptor #76 I
		public static final int GL_SOURCE0_RGB = 34176;

		// Field descriptor #76 I
		public static final int GL_SOURCE1_RGB = 34177;

		// Field descriptor #76 I
		public static final int GL_SOURCE2_RGB = 34178;

		// Field descriptor #76 I
		public static final int GL_SOURCE0_ALPHA = 34184;

		// Field descriptor #76 I
		public static final int GL_SOURCE1_ALPHA = 34185;

		// Field descriptor #76 I
		public static final int GL_SOURCE2_ALPHA = 34186;

		// Field descriptor #76 I
		public static final int GL_OPERAND0_RGB = 34192;

		// Field descriptor #76 I
		public static final int GL_OPERAND1_RGB = 34193;

		// Field descriptor #76 I
		public static final int GL_OPERAND2_RGB = 34194;

		// Field descriptor #76 I
		public static final int GL_OPERAND0_ALPHA = 34200;

		// Field descriptor #76 I
		public static final int GL_OPERAND1_ALPHA = 34201;

		// Field descriptor #76 I
		public static final int GL_OPERAND2_ALPHA = 34202;

		// Field descriptor #76 I
		public static final int GL_RGB_SCALE = 34163;

		// Field descriptor #76 I
		public static final int GL_ADD_SIGNED = 34164;

		// Field descriptor #76 I
		public static final int GL_INTERPOLATE = 34165;

		// Field descriptor #76 I
		public static final int GL_SUBTRACT = 34023;

		// Field descriptor #76 I
		public static final int GL_CONSTANT = 34166;

		// Field descriptor #76 I
		public static final int GL_PRIMARY_COLOR = 34167;

		// Field descriptor #76 I
		public static final int GL_PREVIOUS = 34168;

		// Field descriptor #76 I
		public static final int GL_DOT3_RGB = 34478;

		// Field descriptor #76 I
		public static final int GL_DOT3_RGBA = 34479;

		// Field descriptor #76 I
		public static final int GL_CLAMP_TO_BORDER = 33069;

		// Field descriptor #71 I
		public static final int GL_ARRAY_BUFFER = 34962;

		// Field descriptor #71 I
		public static final int GL_ELEMENT_ARRAY_BUFFER = 34963;

		// Field descriptor #71 I
		public static final int GL_ARRAY_BUFFER_BINDING = 34964;

		// Field descriptor #71 I
		public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING = 34965;

		// Field descriptor #71 I
		public static final int GL_VERTEX_ARRAY_BUFFER_BINDING = 34966;

		// Field descriptor #71 I
		public static final int GL_NORMAL_ARRAY_BUFFER_BINDING = 34967;

		// Field descriptor #71 I
		public static final int GL_COLOR_ARRAY_BUFFER_BINDING = 34968;

		// Field descriptor #71 I
		public static final int GL_INDEX_ARRAY_BUFFER_BINDING = 34969;

		// Field descriptor #71 I
		public static final int GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING = 34970;

		// Field descriptor #71 I
		public static final int GL_EDGE_FLAG_ARRAY_BUFFER_BINDING = 34971;

		// Field descriptor #71 I
		public static final int GL_SECONDARY_COLOR_ARRAY_BUFFER_BINDING = 34972;

		// Field descriptor #71 I
		public static final int GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING = 34973;

		// Field descriptor #71 I
		public static final int GL_WEIGHT_ARRAY_BUFFER_BINDING = 34974;

		// Field descriptor #71 I
		public static final int GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 34975;

		// Field descriptor #71 I
		public static final int GL_STREAM_DRAW = 35040;

		// Field descriptor #71 I
		public static final int GL_STREAM_READ = 35041;

		// Field descriptor #71 I
		public static final int GL_STREAM_COPY = 35042;

		// Field descriptor #71 I
		public static final int GL_STATIC_DRAW = 35044;

		// Field descriptor #71 I
		public static final int GL_STATIC_READ = 35045;

		// Field descriptor #71 I
		public static final int GL_STATIC_COPY = 35046;

		// Field descriptor #71 I
		public static final int GL_DYNAMIC_DRAW = 35048;

		// Field descriptor #71 I
		public static final int GL_DYNAMIC_READ = 35049;

		// Field descriptor #71 I
		public static final int GL_DYNAMIC_COPY = 35050;

		// Field descriptor #71 I
		public static final int GL_READ_ONLY = 35000;

		// Field descriptor #71 I
		public static final int GL_WRITE_ONLY = 35001;

		// Field descriptor #71 I
		public static final int GL_READ_WRITE = 35002;

		// Field descriptor #71 I
		public static final int GL_BUFFER_SIZE = 34660;

		// Field descriptor #71 I
		public static final int GL_BUFFER_USAGE = 34661;

		// Field descriptor #71 I
		public static final int GL_BUFFER_ACCESS = 35003;

		// Field descriptor #71 I
		public static final int GL_BUFFER_MAPPED = 35004;

		// Field descriptor #71 I
		public static final int GL_BUFFER_MAP_POINTER = 35005;

		// Field descriptor #71 I
		public static final int GL_FOG_COORD_SRC = 33872;

		// Field descriptor #71 I
		public static final int GL_FOG_COORD = 33873;

		// Field descriptor #71 I
		public static final int GL_CURRENT_FOG_COORD = 33875;

		// Field descriptor #71 I
		public static final int GL_FOG_COORD_ARRAY_TYPE = 33876;

		// Field descriptor #71 I
		public static final int GL_FOG_COORD_ARRAY_STRIDE = 33877;

		// Field descriptor #71 I
		public static final int GL_FOG_COORD_ARRAY_POINTER = 33878;

		// Field descriptor #71 I
		public static final int GL_FOG_COORD_ARRAY = 33879;

		// Field descriptor #71 I
		public static final int GL_FOG_COORD_ARRAY_BUFFER_BINDING = 34973;

		// Field descriptor #71 I
		public static final int GL_SRC0_RGB = 34176;

		// Field descriptor #71 I
		public static final int GL_SRC1_RGB = 34177;

		// Field descriptor #71 I
		public static final int GL_SRC2_RGB = 34178;

		// Field descriptor #71 I
		public static final int GL_SRC0_ALPHA = 34184;

		// Field descriptor #71 I
		public static final int GL_SRC1_ALPHA = 34185;

		// Field descriptor #71 I
		public static final int GL_SRC2_ALPHA = 34186;

		// Field descriptor #71 I
		public static final int GL_SAMPLES_PASSED = 35092;

		// Field descriptor #71 I
		public static final int GL_QUERY_COUNTER_BITS = 34916;

		// Field descriptor #71 I
		public static final int GL_CURRENT_QUERY = 34917;

		// Field descriptor #71 I
		public static final int GL_QUERY_RESULT = 34918;

		// Field descriptor #71 I
		public static final int GL_QUERY_RESULT_AVAILABLE = 34919;

		// Field descriptor #194 I
		public static final int GL_SHADING_LANGUAGE_VERSION = 35724;

		// Field descriptor #194 I
		public static final int GL_CURRENT_PROGRAM = 35725;

		// Field descriptor #194 I
		public static final int GL_SHADER_TYPE = 35663;

		// Field descriptor #194 I
		public static final int GL_DELETE_STATUS = 35712;

		// Field descriptor #194 I
		public static final int GL_COMPILE_STATUS = 35713;

		// Field descriptor #194 I
		public static final int GL_LINK_STATUS = 35714;

		// Field descriptor #194 I
		public static final int GL_VALIDATE_STATUS = 35715;

		// Field descriptor #194 I
		public static final int GL_INFO_LOG_LENGTH = 35716;

		// Field descriptor #194 I
		public static final int GL_ATTACHED_SHADERS = 35717;

		// Field descriptor #194 I
		public static final int GL_ACTIVE_UNIFORMS = 35718;

		// Field descriptor #194 I
		public static final int GL_ACTIVE_UNIFORM_MAX_LENGTH = 35719;

		// Field descriptor #194 I
		public static final int GL_ACTIVE_ATTRIBUTES = 35721;

		// Field descriptor #194 I
		public static final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 35722;

		// Field descriptor #194 I
		public static final int GL_SHADER_SOURCE_LENGTH = 35720;

		// Field descriptor #194 I
		public static final int GL_SHADER_OBJECT = 35656;

		// Field descriptor #194 I
		public static final int GL_FLOAT_VEC2 = 35664;

		// Field descriptor #194 I
		public static final int GL_FLOAT_VEC3 = 35665;

		// Field descriptor #194 I
		public static final int GL_FLOAT_VEC4 = 35666;

		// Field descriptor #194 I
		public static final int GL_INT_VEC2 = 35667;

		// Field descriptor #194 I
		public static final int GL_INT_VEC3 = 35668;

		// Field descriptor #194 I
		public static final int GL_INT_VEC4 = 35669;

		// Field descriptor #194 I
		public static final int GL_BOOL = 35670;

		// Field descriptor #194 I
		public static final int GL_BOOL_VEC2 = 35671;

		// Field descriptor #194 I
		public static final int GL_BOOL_VEC3 = 35672;

		// Field descriptor #194 I
		public static final int GL_BOOL_VEC4 = 35673;

		// Field descriptor #194 I
		public static final int GL_FLOAT_MAT2 = 35674;

		// Field descriptor #194 I
		public static final int GL_FLOAT_MAT3 = 35675;

		// Field descriptor #194 I
		public static final int GL_FLOAT_MAT4 = 35676;

		// Field descriptor #194 I
		public static final int GL_SAMPLER_1D = 35677;

		// Field descriptor #194 I
		public static final int GL_SAMPLER_2D = 35678;

		// Field descriptor #194 I
		public static final int GL_SAMPLER_3D = 35679;

		// Field descriptor #194 I
		public static final int GL_SAMPLER_CUBE = 35680;

		// Field descriptor #194 I
		public static final int GL_SAMPLER_1D_SHADOW = 35681;

		// Field descriptor #194 I
		public static final int GL_SAMPLER_2D_SHADOW = 35682;

		// Field descriptor #194 I
		public static final int GL_VERTEX_SHADER = 35633;

		// Field descriptor #194 I
		public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS = 35658;

		// Field descriptor #194 I
		public static final int GL_MAX_VARYING_FLOATS = 35659;

		// Field descriptor #194 I
		public static final int GL_MAX_VERTEX_ATTRIBS = 34921;

		// Field descriptor #194 I
		public static final int GL_MAX_TEXTURE_IMAGE_UNITS = 34930;

		// Field descriptor #194 I
		public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS = 35660;

		// Field descriptor #194 I
		public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 35661;

		// Field descriptor #194 I
		public static final int GL_MAX_TEXTURE_COORDS = 34929;

		// Field descriptor #194 I
		public static final int GL_VERTEX_PROGRAM_POINT_SIZE = 34370;

		// Field descriptor #194 I
		public static final int GL_VERTEX_PROGRAM_TWO_SIDE = 34371;

		// Field descriptor #194 I
		public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED = 34338;

		// Field descriptor #194 I
		public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE = 34339;

		// Field descriptor #194 I
		public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE = 34340;

		// Field descriptor #194 I
		public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE = 34341;

		// Field descriptor #194 I
		public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED = 34922;

		// Field descriptor #194 I
		public static final int GL_CURRENT_VERTEX_ATTRIB = 34342;

		// Field descriptor #194 I
		public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER = 34373;

		// Field descriptor #194 I
		public static final int GL_FRAGMENT_SHADER = 35632;

		// Field descriptor #194 I
		public static final int GL_MAX_FRAGMENT_UNIFORM_COMPONENTS = 35657;

		// Field descriptor #194 I
		public static final int GL_FRAGMENT_SHADER_DERIVATIVE_HINT = 35723;

		// Field descriptor #194 I
		public static final int GL_MAX_DRAW_BUFFERS = 34852;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER0 = 34853;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER1 = 34854;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER2 = 34855;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER3 = 34856;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER4 = 34857;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER5 = 34858;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER6 = 34859;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER7 = 34860;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER8 = 34861;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER9 = 34862;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER10 = 34863;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER11 = 34864;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER12 = 34865;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER13 = 34866;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER14 = 34867;

		// Field descriptor #194 I
		public static final int GL_DRAW_BUFFER15 = 34868;

		// Field descriptor #194 I
		public static final int GL_POINT_SPRITE = 34913;

		// Field descriptor #194 I
		public static final int GL_COORD_REPLACE = 34914;

		// Field descriptor #194 I
		public static final int GL_POINT_SPRITE_COORD_ORIGIN = 36000;

		// Field descriptor #194 I
		public static final int GL_LOWER_LEFT = 36001;

		// Field descriptor #194 I
		public static final int GL_UPPER_LEFT = 36002;

		// Field descriptor #194 I
		public static final int GL_STENCIL_BACK_FUNC = 34816;

		// Field descriptor #194 I
		public static final int GL_STENCIL_BACK_FAIL = 34817;

		// Field descriptor #194 I
		public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL = 34818;

		// Field descriptor #194 I
		public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS = 34819;

		// Field descriptor #194 I
		public static final int GL_STENCIL_BACK_REF = 36003;

		// Field descriptor #194 I
		public static final int GL_STENCIL_BACK_VALUE_MASK = 36004;

		// Field descriptor #194 I
		public static final int GL_STENCIL_BACK_WRITEMASK = 36005;

		// Field descriptor #194 I
		public static final int GL_BLEND_EQUATION_RGB = 32777;

		// Field descriptor #194 I
		public static final int GL_BLEND_EQUATION_ALPHA = 34877;

	}
	
	public static class EaglerAdapterImpl2 {
		public static final boolean _wisWebGL() {
			return false;
		}
		public static final boolean _wisAnisotropicPatched() {
			return true;
		}
		public static final String _wgetShaderHeader() {
			return "#version 150";
		}
		
		public static final InputStream loadResource(String path) {
			byte[] file = loadResourceBytes(path);
			if (file != null) {
				return new ByteArrayInputStream(file);
			} else {
				return null;
			}
		}

		public static final boolean isSSLPage() {
			return true;
		}
		
		public static final String[] getIdentifier() {
			return new String[0];
		}
		
		private static final boolean useEPKTest = false;

		public static final byte[] loadResourceBytes(String path) {
			if(useEPKTest) {
				return AssetRepository.getResource(path);
			}else {
				try {
					InputStream stream;
					try {
						stream = new FileInputStream(new File("resources", path));
					} catch (FileNotFoundException e) {
						return null;
					}
					byte[] targetArray = new byte[stream.available()];
					stream.read(targetArray);
					stream.close();
					return targetArray;
				} catch (IOException e) {
					return null;
				}
			}
		}
		
		public static final String fileContents(String path) {
			byte[] contents = loadResourceBytes(path);
			if(contents == null) {
				return null;
			}else {
				return new String(contents, Charset.forName("UTF-8"));
			}
		}
		
		public static final String[] fileContentsLines(String path) {
			String contents = fileContents(path);
			if(contents == null) {
				return null;
			}else {
				return contents.replace("\r\n", "\n").split("[\r\n]");
			}
		}
		
		public static final void setDebugVar(String v, String s) {
			
		}
		
		public static final int _wGL_TEXTURE_2D = GL11.GL_TEXTURE_2D;
		public static final int _wGL_DEPTH_TEST = GL11.GL_DEPTH_TEST;
		public static final int _wGL_LEQUAL = GL11.GL_LEQUAL;
		public static final int _wGL_GEQUAL = GL11.GL_GEQUAL;
		public static final int _wGL_GREATER = GL11.GL_GREATER;
		public static final int _wGL_LESS = GL11.GL_LESS;
		public static final int _wGL_BACK = GL11.GL_BACK;
		public static final int _wGL_FRONT = GL11.GL_FRONT;
		public static final int _wGL_FRONT_AND_BACK = GL11.GL_FRONT_AND_BACK;
		public static final int _wGL_COLOR_BUFFER_BIT = GL11.GL_COLOR_BUFFER_BIT;
		public static final int _wGL_DEPTH_BUFFER_BIT = GL11.GL_DEPTH_BUFFER_BIT;
		public static final int _wGL_BLEND = GL11.GL_BLEND;
		public static final int _wGL_RGBA = GL11.GL_RGBA;
		public static final int _wGL_RGB = GL11.GL_RGB;
		public static final int _wGL_RGB8 = GL11.GL_RGB8;
		public static final int _wGL_RGBA8 = GL11.GL_RGBA8;
		public static final int _wGL_UNSIGNED_BYTE = GL11.GL_UNSIGNED_BYTE;
		public static final int _wGL_UNSIGNED_SHORT = GL11.GL_UNSIGNED_SHORT;
		public static final int _wGL_TEXTURE_WIDTH = GL11.GL_TEXTURE_WIDTH;
		public static final int _wGL_SRC_ALPHA = GL11.GL_SRC_ALPHA;
		public static final int _wGL_ONE_MINUS_SRC_ALPHA = GL11.GL_ONE_MINUS_SRC_ALPHA;
		public static final int _wGL_ONE_MINUS_DST_COLOR = GL11.GL_ONE_MINUS_DST_COLOR;
		public static final int _wGL_ONE_MINUS_SRC_COLOR = GL11.GL_ONE_MINUS_SRC_COLOR;
		public static final int _wGL_ZERO = GL11.GL_ZERO;
		public static final int _wGL_CULL_FACE = GL11.GL_CULL_FACE;
		public static final int _wGL_TEXTURE_MIN_FILTER = GL11.GL_TEXTURE_MIN_FILTER;
		public static final int _wGL_TEXTURE_MAG_FILTER = GL11.GL_TEXTURE_MAG_FILTER;
		public static final int _wGL_LINEAR = GL11.GL_LINEAR;
		public static final int _wGL_NEAREST_MIPMAP_LINEAR = GL11.GL_NEAREST_MIPMAP_LINEAR;
		public static final int _wGL_LINEAR_MIPMAP_LINEAR = GL11.GL_LINEAR_MIPMAP_LINEAR;
		public static final int _wGL_LINEAR_MIPMAP_NEAREST = GL11.GL_LINEAR_MIPMAP_NEAREST;
		public static final int _wGL_NEAREST_MIPMAP_NEAREST = GL11.GL_NEAREST_MIPMAP_NEAREST;
		public static final int _wGL_EQUAL = GL11.GL_EQUAL;
		public static final int _wGL_SRC_COLOR = GL11.GL_SRC_COLOR;
		public static final int _wGL_ONE = GL11.GL_ONE;
		public static final int _wGL_NEAREST = GL11.GL_NEAREST;
		public static final int _wGL_CLAMP = GL12.GL_CLAMP_TO_EDGE;
		public static final int _wGL_TEXTURE_WRAP_S = GL11.GL_TEXTURE_WRAP_S;
		public static final int _wGL_TEXTURE_WRAP_T = GL11.GL_TEXTURE_WRAP_T;
		public static final int _wGL_TEXTURE_MAX_LEVEL = GL12.GL_TEXTURE_MAX_LEVEL;
		public static final int _wGL_REPEAT = GL11.GL_REPEAT;
		public static final int _wGL_DST_COLOR = GL11.GL_DST_COLOR;
		public static final int _wGL_DST_ALPHA = GL11.GL_DST_ALPHA;
		public static final int _wGL_FLOAT = GL11.GL_FLOAT;
		public static final int _wGL_SHORT = GL11.GL_SHORT;
		public static final int _wGL_TRIANGLES = GL11.GL_TRIANGLES;
		public static final int _wGL_TRIANGLE_STRIP = GL11.GL_TRIANGLE_STRIP;
		public static final int _wGL_TRIANGLE_FAN = GL11.GL_TRIANGLE_FAN;
		public static final int _wGL_LINE_STRIP = GL11.GL_LINE_STRIP;
		public static final int _wGL_LINES = GL11.GL_LINES;
		public static final int _wGL_PACK_ALIGNMENT = GL11.GL_PACK_ALIGNMENT;
		public static final int _wGL_UNPACK_ALIGNMENT = GL11.GL_UNPACK_ALIGNMENT;
		public static final int _wGL_TEXTURE0 = GL13.GL_TEXTURE0;
		public static final int _wGL_TEXTURE1 = GL13.GL_TEXTURE1;
		public static final int _wGL_TEXTURE2 = GL13.GL_TEXTURE2;
		public static final int _wGL_TEXTURE3 = GL13.GL_TEXTURE3;
		public static final int _wGL_VIEWPORT = GL11.GL_VIEWPORT;
		public static final int _wGL_VERTEX_SHADER = GL20.GL_VERTEX_SHADER;
		public static final int _wGL_FRAGMENT_SHADER = GL20.GL_FRAGMENT_SHADER;
		public static final int _wGL_ARRAY_BUFFER = GL15.GL_ARRAY_BUFFER;
		public static final int _wGL_ELEMENT_ARRAY_BUFFER = GL15.GL_ELEMENT_ARRAY_BUFFER;
		public static final int _wGL_STATIC_DRAW = GL15.GL_STATIC_DRAW;
		public static final int _wGL_DYNAMIC_DRAW = GL15.GL_DYNAMIC_DRAW;
		public static final int _wGL_STREAM_DRAW = GL15.GL_STREAM_DRAW;
		public static final int _wGL_INVALID_ENUM = GL11.GL_INVALID_ENUM;
		public static final int _wGL_INVALID_VALUE= GL11.GL_INVALID_VALUE;
		public static final int _wGL_INVALID_OPERATION = GL11.GL_INVALID_OPERATION;
		public static final int _wGL_OUT_OF_MEMORY = GL11.GL_OUT_OF_MEMORY;
		public static final int _wGL_CONTEXT_LOST_WEBGL = -1;
		public static final int _wGL_FRAMEBUFFER_COMPLETE = GL30.GL_FRAMEBUFFER_COMPLETE;
		public static final int _wGL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = GL30.GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT;
		public static final int _wGL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = GL30.GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT;
		public static final int _wGL_COLOR_ATTACHMENT0 = GL30.GL_COLOR_ATTACHMENT0;
		public static final int _wGL_DEPTH_STENCIL_ATTACHMENT = GL30.GL_DEPTH_STENCIL_ATTACHMENT; 
		public static final int _wGL_DEPTH_ATTACHMENT = GL30.GL_DEPTH_ATTACHMENT;
		public static final int _wGL_DEPTH_COMPONENT32F = GL30.GL_DEPTH_COMPONENT32F;
		public static final int _wGL_DEPTH_STENCIL = GL30.GL_DEPTH_STENCIL;
		public static final int _wGL_DEPTH24_STENCIL8 = GL30.GL_DEPTH24_STENCIL8; 
		public static final int _wGL_UNSIGNED_INT_24_8 = GL30.GL_UNSIGNED_INT_24_8;
		public static final int _wGL_UNSIGNED_INT = GL11.GL_UNSIGNED_INT;
		public static int _wGL_ANY_SAMPLES_PASSED = -1;
		public static final int _wGL_QUERY_RESULT = GL15.GL_QUERY_RESULT; 
		public static final int _wGL_QUERY_RESULT_AVAILABLE = GL15.GL_QUERY_RESULT_AVAILABLE; 
		public static int _wGL_TEXTURE_MAX_ANISOTROPY = -1;
		public static final int _wGL_R8 = GL30.GL_R8;
		public static final int _wGL_R32UI = GL30.GL_R32UI;
		public static final int _wGL_RED = GL11.GL_RED;
		public static final int _wGL_RENDERBUFFER = GL30.GL_RENDERBUFFER;
		public static final int _wGL_MULTISAMPLE = GL13.GL_MULTISAMPLE;
		public static final int _wGL_LINE_SMOOTH = GL11.GL_LINE_SMOOTH;
		public static final int _wGL_DRAW_FRAMEBUFFER = GL30.GL_DRAW_FRAMEBUFFER;
		public static final int _wGL_READ_FRAMEBUFFER = GL30.GL_READ_FRAMEBUFFER;
		public static final int _wGL_FRAMEBUFFER = GL30.GL_FRAMEBUFFER;
		public static final int _wGL_POLYGON_OFFSET_FILL = GL11.GL_POLYGON_OFFSET_FILL;

		public static final class TextureGL {
			protected final int obj;
			public int w = -1;
			public int h = -1;
			public boolean nearest = true;
			public boolean anisotropic = false;
			protected TextureGL(int obj) {
				this.obj = obj;
			}
		}
		public static final class BufferGL {
			protected final int obj;
			protected BufferGL(int obj) {
				this.obj = obj;
			}
		}
		public static final class ShaderGL {
			protected final int obj;
			protected ShaderGL(int obj) {
				this.obj = obj;
			}
		}
		public static final class ProgramGL {
			protected final int obj;
			protected ProgramGL(int obj) {
				this.obj = obj;
			}
		}
		public static final class UniformGL {
			protected final int obj;
			protected UniformGL(int obj) {
				this.obj = obj;
			}
		}
		public static final class BufferArrayGL {
			protected final int obj;
			public boolean isQuadBufferBound;
			protected BufferArrayGL(int obj) {
				this.obj = obj;
				this.isQuadBufferBound = false;
			}
		}
		public static final class FramebufferGL {
			protected final int obj;
			protected FramebufferGL(int obj) {
				this.obj = obj;
			}
		}
		public static final class RenderbufferGL {
			protected final int obj;
			protected RenderbufferGL(int obj) {
				this.obj = obj;
			}
		}
		public static final class QueryGL {
			protected final int obj;
			protected QueryGL(int obj) {
				this.obj = obj;
			}
		}
		
		public static final void _wglEnable(int p1) {
			GL11.glEnable(p1);
		}
		public static final void _wglClearDepth(float p1) {
			GL11.glClearDepth(p1);
		}
		public static final void _wglDepthFunc(int p1) {
			GL11.glDepthFunc(p1);
		}
		public static final void _wglCullFace(int p1) {
			GL11.glCullFace(p1);
		}
		private static final int[] viewport = new int[4];
		public static final void _wglViewport(int p1, int p2, int p3, int p4) {
			viewport[0] = p1; viewport[1] = p2;
			viewport[2] = p3; viewport[3] = p4;
			GL11.glViewport(p1, p2, p3, p4);
		}
		public static final void _wglClear(int p1) {
			GL11.glClear(p1);
		}
		public static final void _wglClearColor(float p1, float p2, float p3, float p4) {
			GL11.glClearColor(p1, p2, p3, p4);
		}
		public static final void _wglDisable(int p1) {
			GL11.glDisable(p1);
		}
		public static final int _wglGetError() {
			return GL11.glGetError();
		}
		public static final void _wglFlush() {
			GL11.glFlush();
		}
		public static final void _wglTexImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, ByteBuffer p9) {
			GL11.glTexImage2D(p1, p2, p3, p4, p5, p6, p7, p8, p9);
		}
		public static final void _wglBlendFunc(int p1, int p2) {
			GL11.glBlendFunc(p1, p2);
		}
		public static final void _wglBlendFuncSeparate(int p1, int p2, int p3, int p4) {
			GL14.glBlendFuncSeparate(p1, p2, p3, p4);
		}
		public static final void _wglBlendColor(float r, float g, float b, float a) {
			GL14.glBlendColor(r, g, b, a);
		}
		public static final void _wglDepthMask(boolean p1) {
			GL11.glDepthMask(p1);
		}
		public static final void _wglColorMask(boolean p1, boolean p2, boolean p3, boolean p4) {
			GL11.glColorMask(p1, p2, p3, p4);
		}
		public static final void _wglBindTexture(int p1, TextureGL p2) {
			GL11.glBindTexture(p1, p2 == null ? 0 : p2.obj);
		}
		public static final void _wglCopyTexSubImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {
			GL11.glCopyTexSubImage2D(p1, p2, p3, p4, p5, p6, p7, p8);
		}
		public static final void _wglTexParameteri(int p1, int p2, int p3) {
			GL11.glTexParameteri(p1, p2, p3);
		}
		public static final void _wglTexParameterf(int p1, int p2, int p3) {
			GL11.glTexParameterf(p1, p2, p3);
		}
		public static final void _wglTexImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, IntBuffer p9) {
			GL11.glTexImage2D(p1, p2, p3, p4, p5, p6, p7, p8, p9);
		}
		public static final void _wglTexSubImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, IntBuffer p9) {
			GL11.glTexSubImage2D(p1, p2, p3, p4, p5, p6, p7, p8, p9);
		}
		public static final void _wglDeleteTextures(TextureGL p1) {
			GL11.glDeleteTextures(p1.obj);
		}
		public static final void _wglDrawArrays(int p1, int p2, int p3) {
			GL11.glDrawArrays(p1, p2, p3);
		}
		public static final void _wglDrawElements(int p1, int p2, int p3, int p4) {
			GL11.glDrawElements(p1, p2, p3, p4);
		}
		public static final TextureGL _wglGenTextures() {
			return new TextureGL(GL11.glGenTextures());
		}
		public static final void _wglTexSubImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, ByteBuffer p9) {
			GL11.glTexSubImage2D(p1, p2, p3, p4, p5, p6, p7, p8, p9);
		}
		public static final void _wglTexImage3D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, int p9, ByteBuffer p10) {
			GL12.glTexImage3D(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
		}
		public static final void _wglTexParameterf(int p1, int p2, float p3) {
			GL11.glTexParameterf(p1, p2, p3);
		}
		public static final void _wglActiveTexture(int p1) {
			GL13.glActiveTexture(p1);
		}
		public static final String _wgluErrorString(int p1) {
			return GLU.gluErrorString(p1);
		}
		public static final ProgramGL _wglCreateProgram() {
			return new ProgramGL(GL20.glCreateProgram());
		}
		public static final ShaderGL _wglCreateShader(int p1) {
			return new ShaderGL(GL20.glCreateShader(p1));
		}
		public static final void _wglAttachShader(ProgramGL p1, ShaderGL p2) {
			GL20.glAttachShader(p1.obj, p2.obj);
		}
		public static final void _wglDetachShader(ProgramGL p1, ShaderGL p2) {
			GL20.glDetachShader(p1.obj, p2.obj);
		}
		public static final void _wglCompileShader(ShaderGL p1) {
			GL20.glCompileShader(p1.obj);
		}
		public static final void _wglLinkProgram(ProgramGL p1) {
			GL20.glLinkProgram(p1.obj);
		}
		public static final void _wglShaderSource(ShaderGL p1, String p2) {
			GL20.glShaderSource(p1.obj, p2);
		}
		public static final String _wglGetShaderInfoLog(ShaderGL p1) {
			return GL20.glGetShaderInfoLog(p1.obj, 8192);
		}
		public static final String _wglGetProgramInfoLog(ProgramGL p1) {
			return GL20.glGetProgramInfoLog(p1.obj, 8192);
		}
		public static final boolean _wglGetShaderCompiled(ShaderGL p1) {
			return GL20.glGetShaderi(p1.obj, GL20.GL_COMPILE_STATUS) == GL11.GL_TRUE;
		}
		public static final boolean _wglGetProgramLinked(ProgramGL p1) {
			return GL20.glGetProgrami(p1.obj, GL20.GL_LINK_STATUS) == GL11.GL_TRUE;
		}
		public static final void _wglDeleteShader(ShaderGL p1) {
			GL20.glDeleteShader(p1.obj);
		}
		public static final void _wglDeleteProgram(ProgramGL p1) {
			GL20.glDeleteProgram(p1.obj);
		}
		public static final BufferArrayGL _wglCreateVertexArray() {
			return new BufferArrayGL(GL30.glGenVertexArrays());
		}
		public static final void _wglDeleteVertexArray(BufferArrayGL p1) {
			GL30.glDeleteVertexArrays(p1.obj);
		}
		public static final void _wglBindVertexArray(BufferArrayGL p1) {
			GL30.glBindVertexArray(p1 == null ? 0 : p1.obj);
		}
		public static final BufferGL _wglCreateBuffer() {
			return new BufferGL(GL15.glGenBuffers());
		}
		public static final void _wglDeleteBuffer(BufferGL p1) {
			GL15.glDeleteBuffers(p1.obj);
		}
		public static final void _wglBindBuffer(int p1, BufferGL p2) {
			GL15.glBindBuffer(p1, p2 == null ? 0 : p2.obj);
		}
		public static final void _wglBufferData(int p1, Object p2, int p3) {
			GL15.glBufferData(p1, (IntBuffer)p2, p3);
		}
		public static final void _wglBufferSubData(int p1, int p2, Object p3) {
			GL15.glBufferSubData(p1, p2, (IntBuffer)p3);
		}
		public static final void _wglBufferData0(int p1, IntBuffer p2, int p3) {
			GL15.glBufferData(p1, p2, p3);
		}
		public static final void _wglBufferData00(int p1, long len, int p3) {
			GL15.glBufferData(p1, len, p3);
		}
		public static final void _wglBufferSubData0(int p1, int p2, IntBuffer p3) {
			GL15.glBufferSubData(p1, p2, p3);
		}
		public static final void _wglBindAttribLocation(int p1, int p2, String p3) {
			GL20.glBindAttribLocation(p1, p2, p3);
		}
		public static final void _wglEnableVertexAttribArray(int p1) {
			GL20.glEnableVertexAttribArray(p1);
		}
		public static final void _wglDisableVertexAttribArray(int p1) {
			GL20.glDisableVertexAttribArray(p1);
		}
		public static final UniformGL _wglGetUniformLocation(ProgramGL p1, String p2) {
			int u = GL20.glGetUniformLocation(p1.obj, p2);
			return u == -1 ? null : new UniformGL(u);
		}
		public static final void _wglBindAttributeLocation(ProgramGL p1, int p2, String p3) {
			GL20.glBindAttribLocation(p1.obj, p2, p3);
		}
		public static final void _wglUniform1f(UniformGL p1, float p2) {
			if(p1 != null) GL20.glUniform1f(p1.obj, p2);
		}
		public static final void _wglUniform2f(UniformGL p1, float p2, float p3) {
			if(p1 != null) GL20.glUniform2f(p1.obj, p2, p3);
		}
		public static final void _wglUniform3f(UniformGL p1, float p2, float p3, float p4) {
			if(p1 != null) GL20.glUniform3f(p1.obj, p2, p3, p4);
		}
		public static final void _wglUniform4f(UniformGL p1, float p2, float p3, float p4, float p5) {
			if(p1 != null) GL20.glUniform4f(p1.obj, p2, p3, p4, p5);
		}
		public static final void _wglUniform1i(UniformGL p1, int p2) {
			if(p1 != null) GL20.glUniform1i(p1.obj, p2);
		}
		public static final void _wglUniform2i(UniformGL p1, int p2, int p3) {
			if(p1 != null) GL20.glUniform2i(p1.obj, p2, p3);
		}
		public static final void _wglUniform3i(UniformGL p1, int p2, int p3, int p4) {
			if(p1 != null) GL20.glUniform3i(p1.obj, p2, p3, p4);
		}
		public static final void _wglUniform4i(UniformGL p1, int p2, int p3, int p4, int p5) {
			if(p1 != null) GL20.glUniform4i(p1.obj, p2, p3, p4, p5);
		}
		private static final FloatBuffer matUpload = ByteBuffer.allocateDirect(16 << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
		public static final void _wglUniformMat2fv(UniformGL p1, float[] mat) {
			matUpload.clear();
			matUpload.put(mat);
			matUpload.flip();
			if(p1 != null) GL20.glUniformMatrix2(p1.obj, false, matUpload);
		}
		public static final void _wglUniformMat3fv(UniformGL p1, float[] mat) {
			matUpload.clear();
			matUpload.put(mat);
			matUpload.flip();
			if(p1 != null) GL20.glUniformMatrix3(p1.obj, false, matUpload);
		}
		public static final void _wglUniformMat4fv(UniformGL p1, float[] mat) {
			matUpload.clear();
			matUpload.put(mat);
			matUpload.flip();
			if(p1 != null) GL20.glUniformMatrix4(p1.obj, false, matUpload);
		}
		private static int currentProgram = 0;
		public static final void _wglUseProgram(ProgramGL p1) {
			int i = p1 == null ? 0 : p1.obj;
			if(i != currentProgram) {
				currentProgram = i;
				GL20.glUseProgram(i);
			}
		}
		public static final void _wglGetParameter(int p1, int size, int[] p3) {
			if(p1 == _wGL_VIEWPORT) {
				p3[0] = viewport[0]; p3[1] = viewport[1];
				p3[2] = viewport[2]; p3[3] = viewport[3];
			}
		}
		public static final void _wglPolygonOffset(float p1, float p2) {
			GL11.glPolygonOffset(p1, p2);
		}
		public static final void _wglVertexAttribPointer(int p1, int p2, int p3, boolean p4, int p5, int p6) {
			GL20.glVertexAttribPointer(p1, p2, p3, p4, p5, p6);
		}
		public static final void _wglBindFramebuffer(int p1, FramebufferGL p2) {
			GL30.glBindFramebuffer(p1, p2 == null ? 0 : p2.obj);
		}
		public static final void _wglReadBuffer(int p1) {
			GL11.glReadBuffer(p1);
		}
		public static final void _wglDrawBuffer(int p1) {
			GL11.glDrawBuffer(p1);
		}
		public static final void _wglBlitFramebuffer(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, int p9, int p10) {
			GL30.glBlitFramebuffer(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
		}
		public static final FramebufferGL _wglCreateFramebuffer() {
			return new FramebufferGL(GL30.glGenFramebuffers());
		}
		public static final void _wglDeleteFramebuffer(FramebufferGL p1) {
			GL30.glDeleteFramebuffers(p1.obj);
		}
		public static final void _wglFramebufferTexture2D(int p1, TextureGL p2) {
			GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, p1, GL11.GL_TEXTURE_2D, p2.obj, 0);
		}
		public static final void _wglFramebufferTexture2D(int p1, TextureGL p2, int lvl) {
			GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, p1, GL11.GL_TEXTURE_2D, p2.obj, lvl);
		}
		public static final RenderbufferGL _wglCreateRenderBuffer() {
			return new RenderbufferGL(GL30.glGenRenderbuffers());
		}
		public static final void _wglDeleteRenderbuffer(RenderbufferGL p1) {
			GL30.glDeleteRenderbuffers(p1.obj);
		}
		public static final void _wglBindRenderbuffer(RenderbufferGL p1) {
			GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, p1 == null ? 0 : p1.obj);
		}
		public static final void _wglRenderbufferStorage(int p1, int p2, int p3) {
			GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, p1, p2, p3);
		}
		public static final void _wglRenderbufferStorageMultisample(int p1, int p2, int p3, int p4) {
			GL30.glRenderbufferStorageMultisample(GL30.GL_RENDERBUFFER, p1, p2, p3, p4);
		}
		public static final void _wglFramebufferRenderbuffer(int p1, RenderbufferGL p2) {
			GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, p1, GL30.GL_RENDERBUFFER, p2.obj);
		}
		public static final QueryGL _wglCreateQuery() {
			return new QueryGL(GL15.glGenQueries());
		}
		public static final void _wglBeginQuery(int p1, QueryGL p2) {
			GL15.glBeginQuery(p1, p2.obj);
		}
		public static final void _wglEndQuery(int p1) {
			GL15.glEndQuery(p1);
		}
		public static final void _wglDeleteQuery(QueryGL p1) {
			GL15.glDeleteQueries(p1.obj);
		}
		public static final int _wglGetQueryObjecti(QueryGL p1, int p2) {
			return GL15.glGetQueryObjecti(p1.obj, p2);
		}
		public static final void _wglLineWidth(float p1) {
			GL11.glLineWidth(p1);
		}
		public static final int _wglGetTexParameteri(int p1) {
			return GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, p1);
		}
		public static final float _wglGetTexParameterf(int p1) {
			return GL11.glGetTexParameterf(GL11.GL_TEXTURE_2D, p1);
		}
		public static final int _wglGetAttribLocation(ProgramGL p1, String p2) {
			return GL20.glGetAttribLocation(p1.obj, p2);
		}
		
		public static final BufferedImage loadPNG(byte[] data) {
			try {
				java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(new ByteArrayInputStream(data));
				int[] pxls = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
				return new BufferedImage(pxls, img.getWidth(), img.getHeight(), true);
			} catch (IOException e) {
				System.err.println("Could not load PNG file:");
				e.printStackTrace();
				return null;
			}
		}
		
		public static final boolean isVideoSupported() {
			return false;
		}
		public static final void loadVideo(String src, boolean autoplay) {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final void loadVideo(String src, boolean autoplay, String setJavascriptPointer) {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final void loadVideo(String src, boolean autoplay, String setJavascriptPointer, String javascriptOnloadFunction) {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final void bufferVideo(String src, int ttl) {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final void unloadVideo() {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final boolean isVideoLoaded() {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final boolean isVideoPaused() {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final void setVideoPaused(boolean pause) {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final void setVideoLoop(boolean pause) {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final void setVideoVolume(float x, float y, float z, float v) {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final void updateVideoTexture() {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final void bindVideoTexture() {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final int getVideoWidth() {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final int getVideoHeight() {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final float getVideoCurrentTime() {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final void setVideoCurrentTime(float seconds) {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final float getVideoDuration() {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}
		public static final void setVideoFrameRate(float seconds) {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}

		public static final int VIDEO_ERR_NONE = -1;
		public static final int VIDEO_ERR_ABORTED = 1;
		public static final int VIDEO_ERR_NETWORK = 2;
		public static final int VIDEO_ERR_DECODE = 3;
		public static final int VIDEO_ERR_SRC_NOT_SUPPORTED = 4;

		public static final int getVideoError() {
			throw new UnsupportedOperationException("Video is not supported in LWJGL runtime");
		}

		public static final boolean isImageSupported() {
			return false;
		}
		public static final void loadImage(String src) {
			throw new UnsupportedOperationException("Image is not supported in LWJGL runtime");
		}
		public static final void loadImage(String src, String setJavascriptPointer) {
			throw new UnsupportedOperationException("Image is not supported in LWJGL runtime");
		}
		public static final void loadImage(String src, String setJavascriptPointer, String javascriptOnloadFunction) {
			throw new UnsupportedOperationException("Image is not supported in LWJGL runtime");
		}
		public static final void bufferImage(String src, int ttl) {
			throw new UnsupportedOperationException("Image is not supported in LWJGL runtime");
		}
		public static final void unloadImage() {
			throw new UnsupportedOperationException("Image is not supported in LWJGL runtime");
		}
		public static final boolean isImageLoaded() {
			throw new UnsupportedOperationException("Image is not supported in LWJGL runtime");
		}
		public static final void updateImageTexture() {
			throw new UnsupportedOperationException("Image is not supported in LWJGL runtime");
		}
		public static final void bindImageTexture() {
			throw new UnsupportedOperationException("Image is not supported in LWJGL runtime");
		}
		public static final int getImageWidth() {
			throw new UnsupportedOperationException("Image is not supported in LWJGL runtime");
		}
		public static final int getImageHeight() {
			throw new UnsupportedOperationException("Image is not supported in LWJGL runtime");
		}
		public static final void setImageFrameRate(float seconds) {
			throw new UnsupportedOperationException("Image is not supported in LWJGL runtime");
		}

		// =======================================================================================
		// =======================================================================================
		// =======================================================================================
		// =======================================================================================
		// =======================================================================================
		
		private static Canvas daCanvas = null;
		private static Frame eagler = null;
		private static SoundSystem ss = null;
		public static final void initializeContext() {
			daCanvas = new Canvas();
			eagler = new Frame();
			eagler.setTitle("Eaglercraft Beta 1.7.3");
			eagler.setBackground(Color.BLACK);
			JPanel var16 = new JPanel();
			eagler.setLayout(new BorderLayout());
			var16.setPreferredSize(new Dimension(854, 480));
			eagler.add(var16, "Center");
			eagler.pack();
			eagler.setLocationRelativeTo((Component) null);
			eagler.setVisible(true);
			eagler.addWindowListener(new GameWindowListener());
			eagler.removeAll();
			eagler.setLayout(new BorderLayout());
			eagler.add(daCanvas, "Center");
			eagler.validate();
			eagler.setVisible(true);

			try {
				ContextAttribs contextAtrributes = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true).withDebug(true);
				Display.setParent(daCanvas);
				Display.create((new PixelFormat()).withDepthBits(24), contextAtrributes);
			} catch (LWJGLException var5) {
				var5.printStackTrace();

				try {
					Thread.sleep(1000L);
				} catch (InterruptedException var4) {
					;
				}

				try {
					Display.create();
				} catch (LWJGLException e) {
					e.printStackTrace();
				}
			}
			
			//if(!_wisWebGL()) {
			//	GL30.glBindVertexArray(GL30.glGenVertexArrays());
			//}

			//EarlyLoadScreen.paintScreen();

			try {
				Mouse.create();
				Keyboard.create();
			} catch (LWJGLException var5) {
				var5.printStackTrace();
			}
			
			if(useEPKTest) {
				try {
					InputStream stream = new FileInputStream(new File("out.epk"));
					byte[] targetArray = new byte[stream.available()];
					stream.read(targetArray);
					stream.close();
					AssetRepository.install(targetArray);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}

			Display.setTitle("eaglercraft desktop runtime");
			System.out.println("LWJGL Version: " + Sys.getVersion());
			
			_wGL_ANY_SAMPLES_PASSED = ARBOcclusionQuery2.GL_ANY_SAMPLES_PASSED;
			_wGL_TEXTURE_MAX_ANISOTROPY = EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT;
			
			GL11.glEnable(ARBDebugOutput.GL_DEBUG_OUTPUT_SYNCHRONOUS_ARB);
			ARBDebugOutput.glDebugMessageCallbackARB(new ARBDebugOutputCallback(new ARBDebugOutputCallback.Handler() {
				
				@Override
				public void handleMessage(int arg0, int arg1, int arg2, int arg3, String arg4) {
					if(arg3 == ARBDebugOutput.GL_DEBUG_SEVERITY_MEDIUM_ARB || arg3 == ARBDebugOutput.GL_DEBUG_SEVERITY_HIGH_ARB) {
						StringBuilder b = new StringBuilder();
						b.append("[KHR DEBUG #"); b.append(arg2); b.append("] ");
						switch(arg0) {
						case ARBDebugOutput.GL_DEBUG_SOURCE_API_ARB: b.append("[API - "); break;
						case ARBDebugOutput.GL_DEBUG_SOURCE_APPLICATION_ARB: b.append("[APPLICATION - "); break;
						default:
						case ARBDebugOutput.GL_DEBUG_SOURCE_OTHER_ARB: b.append("[OTHER - "); break;
						case ARBDebugOutput.GL_DEBUG_SOURCE_SHADER_COMPILER_ARB: b.append("[SHADER COMPILER - "); break;
						case ARBDebugOutput.GL_DEBUG_SOURCE_THIRD_PARTY_ARB: b.append("[THIRD PARTY - "); break;
						}
						switch(arg1) {
						case ARBDebugOutput.GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR_ARB: b.append("DEPRECATED BEHAVIOR] "); break;
						case ARBDebugOutput.GL_DEBUG_TYPE_ERROR_ARB: b.append("ERROR] "); break;
						default:
						case ARBDebugOutput.GL_DEBUG_TYPE_OTHER_ARB: b.append("OTHER] "); break;
						case ARBDebugOutput.GL_DEBUG_TYPE_PERFORMANCE_ARB: b.append("PERFORMANCE] "); break;
						case ARBDebugOutput.GL_DEBUG_TYPE_PORTABILITY_ARB: b.append("PORTABILITY] "); break;
						case ARBDebugOutput.GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR_ARB: b.append("UNDEFINED BEHAVIOR] "); break;
						}
						switch(arg3) {
						default:
						case ARBDebugOutput.GL_DEBUG_SEVERITY_LOW_ARB: b.append("[LOW Severity] "); break;
						case ARBDebugOutput.GL_DEBUG_SEVERITY_MEDIUM_ARB: b.append("[MEDIUM Severity] "); break;
						case ARBDebugOutput.GL_DEBUG_SEVERITY_HIGH_ARB: b.append("[SEVERE] "); break;
						}
						b.append(arg4);
						System.err.println(b.toString());
						if(arg3 == ARBDebugOutput.GL_DEBUG_SEVERITY_HIGH_ARB) {
							throw new RuntimeException("GL_DEBUG_SEVERITY_HIGH_ARB was thrown");
						}
					}
				}
				
			}));
			
			try {
				SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
				SoundSystemConfig.setCodec("mp3", CodecJLayerMP3.class);
				ss = new SoundSystem();
			}catch(Throwable t) {
				t.printStackTrace();
			}
		}
		public static final void destroyContext() {
			Display.destroy();
			Keyboard.destroy();
			Mouse.destroy();
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					eagler.dispose();
				}
			});
			if(ss != null) {
				ss.cleanup();
			}
			AL.destroy();
		}
		public static final boolean isWindows() {
			return System.getProperty("os.name").toLowerCase().contains("windows");
		}
		public static final boolean mouseNext() {
			return Mouse.next();
		}
		public static final int mouseGetEventButton() {
			return Mouse.getEventButton();
		}
		public static final boolean mouseGetEventButtonState() {
			return Mouse.getEventButtonState();
		}
		public static final boolean mouseIsButtonDown(int p1) {
			return Mouse.isButtonDown(p1);
		}
		public static final int mouseGetEventDWheel() {
			return Mouse.getDWheel();
		}
		public static final void mouseSetCursorPosition(int x, int y) {
			Mouse.setCursorPosition(x, y);
		}
		public static final void mouseSetGrabbed(boolean grabbed) {
			Mouse.setGrabbed(grabbed);
		}
		public static final boolean isPointerLocked() {
			return Mouse.isGrabbed();
		}
		public static final int mouseGetDX() {
			return Mouse.getDX();
		}
		public static final int mouseGetDY() {
			return Mouse.getDY();
		}
		public static final int mouseGetX() {
			return Mouse.getX();
		}
		public static final int mouseGetY() {
			return Mouse.getY();
		}
		public static final int mouseGetEventX() {
			return Mouse.getEventX();
		}
		public static final int mouseGetEventY() {
			return Mouse.getEventY();
		}
		public static final boolean keysNext() {
			return Keyboard.next();
		}
		public static final int getEventKey() {
			return Keyboard.getEventKey();
		}
		public static final char getEventChar() {
			return Keyboard.getEventCharacter();
		}
		public static final boolean getEventKeyState() {
			return Keyboard.getEventKeyState();
		}
		public static final boolean isKeyDown(int p1) {
			return Keyboard.isKeyDown(p1);
		}
		public static final String getKeyName(int p1) {
			return Keyboard.getKeyName(p1);
		}
		public static final void setFullscreen(boolean p1) {
			try {
				Display.setFullscreen(p1);
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}
		public static final boolean shouldShutdown() {
			return Display.isCloseRequested();
		}
		public static final void updateDisplay() {
			Display.update();
		}
		public static final void setVSyncEnabled(boolean p1) {
			Display.setVSyncEnabled(p1);
		} 
		public static final void enableRepeatEvents(boolean b) {
			Keyboard.enableRepeatEvents(b);
		}
		public static final boolean isFocused() {
			return Display.isActive();
		}
		public static final int getScreenWidth() {
			return Display.getDisplayMode().getWidth();
		}
		public static final int getScreenHeight() {
			return Display.getDisplayMode().getHeight();
		}
		public static final int getCanvasWidth() {
			return daCanvas.getWidth();
		}
		public static final int getCanvasHeight() {
			return daCanvas.getHeight();
		}
		public static final float getContentScaling() {
			return 1.0f;
		}
		public static final void setDisplaySize(int x, int y) {
			try {
				Display.setDisplayMode(new DisplayMode(x, y));
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}
		public static final void syncDisplay(int performanceToFps) {
			Display.sync(performanceToFps);
		}

		private static final Set<String> rateLimitedAddresses = new HashSet();
		private static final Set<String> blockedAddresses = new HashSet();
		
		private static WebSocketClient clientSocket = null;
		private static final Object socketSync = new Object();
		
		private static LinkedList<byte[]> readPackets = new LinkedList();
		
		private static class EaglerSocketClient extends WebSocketClient {
			
			private Exception currentException = null;
			private boolean wasAbleToConnect = false;
			private String serverUriString;
			private boolean socketIsAlive = false;
			
			public EaglerSocketClient(URI serverUri, String str) throws IOException, InterruptedException {
				super(serverUri);
				this.setTcpNoDelay(true);
				this.setConnectionLostTimeout(5);
				System.out.println("[ws] connecting to "+serverUri.toString());
				rateLimitStatus = null;
				if(!this.connectBlocking(5, TimeUnit.SECONDS)) {
					synchronized(socketSync) {
						if(rateLimitStatus == null) {
							if(blockedAddresses.contains(str)) {
								rateLimitStatus = RateLimit.BLOCKED;
							}else if(rateLimitedAddresses.contains(str)) {
								rateLimitStatus = RateLimit.FAILED_POSSIBLY_LOCKED;
							}else {
								rateLimitStatus = RateLimit.FAILED;
							}
						}
					}
					throw new IOException("could not connect socket", currentException);
				}
				serverUriString = str;
			}

			@Override
			public void onClose(int arg0, String arg1, boolean arg2) {
				synchronized(socketSync) {
					readPackets.clear();
					System.out.println("[ws] disconnecting - " + currentException);
					currentException = null;
					if(!wasAbleToConnect && rateLimitStatus == null) {
						if(blockedAddresses.contains(serverUriString)) {
							rateLimitStatus = RateLimit.LOCKED;
						}else if(rateLimitedAddresses.contains(serverUriString)) {
							rateLimitStatus = RateLimit.FAILED_POSSIBLY_LOCKED;
						}else {
							rateLimitStatus = RateLimit.FAILED;
						}
					}else if(!socketIsAlive && (blockedAddresses.contains(serverUriString) || rateLimitedAddresses.contains(serverUriString))) {
						rateLimitStatus = RateLimit.LOCKED;
					}
				}
			}

			@Override
			public void onError(Exception arg0) {
				currentException = arg0;
			}

			@Override
			public void onMessage(String arg0) {
				wasAbleToConnect = true;
				synchronized(socketSync) {
					if(arg0.equalsIgnoreCase("BLOCKED")) {
						rateLimitedAddresses.add(serverUriString);
						if(rateLimitStatus == null) {
							rateLimitStatus = RateLimit.BLOCKED;
						}
					}else if(arg0.equalsIgnoreCase("LOCKED")) {
						blockedAddresses.add(serverUriString);
						rateLimitedAddresses.add(serverUriString);
						if(rateLimitStatus == null) {
							rateLimitStatus = RateLimit.NOW_LOCKED;
						}
					}
				}
				this.close();
				currentException = null;
			}

			@Override
			public void onMessage(ByteBuffer arg0) {
				wasAbleToConnect = true;
				synchronized(socketSync) {
					readPackets.add(arg0.array());
				}
				currentException = null;
			}

			@Override
			public void onOpen(ServerHandshake arg0) {
				System.out.println("[ws] connected.");
			}
			
		}
		
		public static final boolean startConnection(String uri) {
			if(clientSocket != null) {
				clientSocket.close();
			}
			rateLimitStatus = null;
			try {
				clientSocket = new EaglerSocketClient(new URI(uri), uri);
				return true;
			}catch(InterruptedException e) {
				clientSocket = null;
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		public static final void endConnection() {
			synchronized(socketSync) {
				if(clientSocket.isOpen()) {
					clientSocket.close();
				}
				clientSocket = null;
				readPackets.clear();
			}
		}
		public static final boolean connectionOpen() {
			return clientSocket != null && clientSocket.isOpen();
		}
		public static final void writePacket(byte[] packet) {
			if(clientSocket != null && clientSocket.isOpen()) {
				clientSocket.send(ByteBuffer.wrap(packet));
			}
		}
		public static final byte[] readPacket() {
			synchronized(socketSync) {
				if(!readPackets.isEmpty()) {
					return readPackets.remove(0);
				}
			}
			return null;
		}
		private static RateLimit rateLimitStatus = null;
		public static enum RateLimit {
			NONE, FAILED, BLOCKED, FAILED_POSSIBLY_LOCKED, LOCKED, NOW_LOCKED;
		}
		public static final RateLimit getRateLimitStatus() {
			RateLimit l = rateLimitStatus;
			rateLimitStatus = null;
			return l;
		}
		public static final void logRateLimit(String addr, RateLimit l) {
			synchronized(socketSync) {
				if(l == RateLimit.LOCKED) {
					blockedAddresses.add(addr);
				}else {
					rateLimitedAddresses.add(addr);
				}
			}
		}
		public static final RateLimit checkRateLimitHistory(String addr) {
			synchronized(socketSync) {
				if(blockedAddresses.contains(addr)) {
					return RateLimit.LOCKED;
				}else if(rateLimitedAddresses.contains(addr)) {
					return RateLimit.BLOCKED;
				}else {
					return RateLimit.NONE;
				}
			}
		}
		public static final byte[] loadLocalStorage(String key) {
			try {
				File f = new File("filesystem/_eaglercraft_beta."+key+".dat");
				byte[] b = new byte[(int)f.length()];
				FileInputStream s = new FileInputStream(f);
				s.read(b);
				s.close();
				return b;
			} catch (IOException e) {
				return null;
			}
		}
		public static final void saveLocalStorage(String key, byte[] data) {
			try {
				FileOutputStream f = new FileOutputStream(new File("filesystem/_eaglercraft_beta."+key+".dat"));
				f.write(data);
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public static final void openLink(String url) {
			try {
				Class var3 = Class.forName("java.awt.Desktop");
				Object var4 = var3.getMethod("getDesktop", new Class[0]).invoke((Object) null, new Object[0]);
				var3.getMethod("browse", new Class[] { URI.class }).invoke(var4, new Object[] { new URI(url) });
			} catch (Throwable var5) {
				var5.printStackTrace();
			}
		}
		
		private static volatile boolean fileChooserOpen = false;
		private static volatile boolean fileChooserHasResult = false;
		private static volatile FileChooserResult fileChooserResultObject = null;
		
		public static void displayFileChooser(final String mime, final String ext) {
			if(!fileChooserOpen) {
				fileChooserOpen = true;
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						runDisplayFileChooser(mime, ext);
					}
				});
			}
		}

		private static void runDisplayFileChooser(String ext, String mime) {
			try {
				JFileChooser fc = new FileChooserAlwaysOnTop((new File(".")).getAbsoluteFile());
				fc.setDialogTitle("select a file");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setMultiSelectionEnabled(false);
				fc.setFileFilter(new FileFilterExt(ext));
				if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					if(f != null) {
						String name = f.getName();
						byte[] bytes = new byte[(int)f.length()];
						try(FileInputStream is = new FileInputStream(f)) {
							is.read(bytes);
						}
						fileChooserResultObject = new FileChooserResult(name, bytes);
					}else {
						fileChooserResultObject = null;
					}
				}
			}catch(Throwable t) {
				fileChooserResultObject = null;
			}
			fileChooserOpen = false;
			fileChooserHasResult = true;
		}
		
		private static class FileChooserAlwaysOnTop extends JFileChooser {
			
			private FileChooserAlwaysOnTop(File file) {
				super(file);
			}
			
			protected JDialog createDialog(Component parent) throws HeadlessException {
				JDialog dialog = super.createDialog(parent);
				dialog.setAlwaysOnTop(true);
				return dialog;
			}
			
		}

		private static class FileFilterExt extends FileFilter {

			private final String extension;

			private FileFilterExt(String ext) {
				extension = ext;
			}

			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().endsWith("." + extension);
			}

			@Override
			public String getDescription() {
				return extension + " files";
			}

		}
		
		public static boolean fileChooserHasResult() {
			return fileChooserHasResult;
		}

		public static FileChooserResult getFileChooserResult() {
			fileChooserHasResult = false;
			FileChooserResult res = fileChooserResultObject;
			fileChooserResultObject = null;
			return res;
		}
		
		public static final void setListenerPos(float x, float y, float z, float vx, float vy, float vz, float pitch, float yaw) {
			float var2 = MathHelper.cos(-yaw * 0.017453292F);
			float var3 = MathHelper.sin(-yaw * 0.017453292F);
			float var4 = -MathHelper.cos(pitch * 0.017453292F);
			float var5 = MathHelper.sin(pitch * 0.017453292F);
			ss.setListenerPosition(x, y, z);
			ss.setListenerOrientation(-var3 * var4, -var5, -var2 * var4, 0.0f, 1.0f, 0.0f);
			ss.setListenerVelocity(vx, vy, vz);
		}
		public static final void setPlaybackOffsetDelay(float f) {
			// nah
		}
		public static final void setMasterVolume(float v) {
			ss.setMasterVolume(v);
		}
		public static final void setMusicVolume(float v) {
			// used in browser
		}
		private static int playbackId = 0;
		public static final int beginPlayback(String fileName, float x, float y, float z, float volume, float pitch) {
			return beginPlayback(fileName, x, y, z, volume, pitch, false);
		}
		public static final int beginPlayback(String fileName, float x, float y, float z, float volume, float pitch, boolean ayunamiAddedThisBoolean) {
			int id = ++playbackId;
			URL loc = null;
			if((loc = getResourceURL(fileName)) != null) {
				String name = "sound_"+id;
				float var8 = 16.0F;
				if (volume > 1.0F) {
					var8 *= volume;
				}
				ss.newSource(false, name, loc, fileName, false, x, y, z, 2, var8);
				ss.setTemporary(name, true);
				ss.setPitch(name, pitch);
				ss.setVolume(name, volume);
				ss.play(name);
			}else {
				System.err.println("unknown sound event "+fileName);
			}
			return id;
		}
		public static final int beginPlaybackStatic(String fileName, float volume, float pitch) {
			return beginPlaybackStatic(fileName, volume, pitch, false);
		}
		public static final int beginPlaybackStatic(String fileName, float volume, float pitch, boolean ayunamiAddedThisBoolean) {
			int id = ++playbackId;
			URL loc = null;
			if((loc = getResourceURL(fileName)) != null) {
				String name = "sound_"+id;
				ss.newSource(false, name, loc, fileName, false, 0f, 0f, 0f, 0, 0f);
				ss.setTemporary(name, true);
				ss.setPitch(name, pitch);
				ss.setVolume(name, volume);
				ss.play(name);
			}else {
				System.err.println("unknown sound event "+fileName);
			}
			return id;
		}
		private static URL getResourceURL(String path) {
			try {
				File f = new File("resources", path);
				if(f.exists()) {
					return f.toURI().toURL();
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return null;
		}
		public static final void setPitch(int id, float pitch) {
			String name = "sound_"+id;
			if(ss.playing(name)) {
				ss.setPitch(name, pitch);
			}
		}
		public static final void setVolume(int id, float volume) {
			String name = "sound_"+id;
			if(ss.playing(name)) {
				ss.setVolume(name, volume);
			}
		}
		public static final void moveSound(int id, float x, float y, float z, float vx, float vy, float vz) {
			String name = "sound_"+id;
			if(ss.playing(name)) {
				ss.setPosition(name, x, y, z);
				ss.setVelocity(name, vx, vy, vz);
			}
		}
		public static final void endSound(int id) {
			String name = "sound_"+id;
			if(ss.playing(name)) {
				ss.stop(name);
			}
		}
		public static final boolean isPlaying(int id) {
			return ss.playing("sound_"+id);
		}
		public static final long maxMemory() {
			return Runtime.getRuntime().maxMemory();
		}
		public static final long totalMemory() {
			return Runtime.getRuntime().totalMemory();
		}
		public static final long freeMemory() {
			return Runtime.getRuntime().freeMemory();
		}
		public static final void exit() {
			Runtime.getRuntime().halt(0);
		}
		public static final int _wArrayByteLength(Object obj) {
			return ((IntBuffer)obj).remaining() * 4;
		}
		public static final Object _wCreateLowLevelIntBuffer(int len) {
			return ByteBuffer.allocateDirect(len*4).order(ByteOrder.nativeOrder()).asIntBuffer();
		}
		
		private static final IntBuffer appendbuffer = (IntBuffer) _wCreateLowLevelIntBuffer(525000);
		
		public static final void _wAppendLowLevelBuffer(Object arr) {
			if(appendbuffer.limit() != appendbuffer.capacity()) appendbuffer.clear();
			IntBuffer a = (IntBuffer)arr;
			if(appendbuffer.remaining() >= a.remaining()) {
				appendbuffer.put(a);
			}
		}
		
		public static final Object _wGetLowLevelBuffersAppended() {
			appendbuffer.flip();
			return appendbuffer;
		}
		
		public static final String getUserAgent() {
			return "Desktop/" + System.getProperty("os.name");
		}
		
		public static final String getClipboard() {
			try {
				return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
			} catch (HeadlessException | UnsupportedFlavorException | IOException e) {
				return null;
			}
		}
		
		public static final void setClipboard(String str) {
			StringSelection selection = new StringSelection(str);
		    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		    clipboard.setContents(selection, selection);
		}
		
		public static final String saveScreenshot() {
			return "null";
		}
		
		private static final File downloadsDirectory = new File("downloads");
		
		public static final void downloadFile(String str, byte[] dat) {
			if(!downloadsDirectory.isDirectory() && !downloadsDirectory.mkdirs()) {
				throw new RuntimeException("Could not create directory: " + downloadsDirectory.getAbsolutePath());
			}

			File f = new File(downloadsDirectory, str);
			if(f.exists()) { 
				String name = str;
				String ext = "";
				int i = str.lastIndexOf('.');
				if(i != -1) {
					name = str.substring(0, i);
					ext = str.substring(i);
				}

				i = 0;
				do {
					f = new File(downloadsDirectory, name + " (" + (++i) + ")" + ext);
				}while(f.exists());
			}

			try(FileOutputStream fos = new FileOutputStream(f)) {
				fos.write(dat);
			}catch(IOException ex) {
				throw new RuntimeException("Could not save file: " + f.getAbsolutePath());
			}
			
			System.out.println("Saved " + dat.length + " byte file to: " + f.getAbsolutePath());

			try {
				Desktop.getDesktop().open(downloadsDirectory);
			}catch(Throwable t) {
			}
		}
		
		/**
		 * I'm pretty sure my IntBuffers address this problem but if byte order glitches (such as corrupted textures) appear then change to true
		 */
		public static final boolean isBigEndian() {
			return false;
		}
		
		public static final byte[] downloadURL(String url) {
			return null;
		}
		
		public static boolean anisotropicFilteringSupported() {
			return true;
		}
		public static boolean glNeedsAnisotropicFix() {
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static class GLObjectMap<T> {
		private Object[] values;
		private int size;
		private int insertIndex;
		public int allocatedObjects;
		
		public GLObjectMap(int initialSize) {
			this.values = new Object[initialSize];
			this.size = initialSize;
			this.insertIndex = 0;
			this.allocatedObjects = 0;
		}

		public int register(T obj) {
			int start = insertIndex;
			do {
				++insertIndex;
				if(insertIndex >= size) {
					insertIndex = 0;
				}
				if(insertIndex == start) {
					resize();
					return register(obj);
				}
			}while(values[insertIndex] != null);
			values[insertIndex] = obj;
			++allocatedObjects;
			return insertIndex;
		}
		
		@SuppressWarnings("unchecked")
		public T free(int obj) {
			if(obj >= size || obj < 0) return null;
			Object ret = values[obj];
			values[obj] = null;
			--allocatedObjects;
			return (T) ret;
		}
		
		@SuppressWarnings("unchecked")
		public T get(int obj) {
			if(obj >= size || obj < 0) return null;
			return (T) values[obj];
		}
		
		private void resize() {
			int oldSize = size;
			size += size / 2;
			Object[] oldValues = values;
			values = new Object[size];
			System.arraycopy(oldValues, 0, values, 0, oldSize);
		}
	}
	
	
	
	public static class AssetRepository {
		
		private static final HashMap<String, byte[]> filePool = new HashMap();

		public static final void install(byte[] pkg) throws IOException {
			ByteArrayInputStream in2 = new ByteArrayInputStream(pkg);
			DataInputStream in = new DataInputStream(in2);
			byte[] header = new byte[8];
			in.read(header);
			if (!"EAGPKG!!".equals(new String(header, Charset.forName("UTF-8"))))
				throw new IOException("invalid epk file");
			in.readUTF();
			in = new DataInputStream(new InflaterInputStream(in2));
			String s = null;
			SHA1Digest dg = new SHA1Digest();
			while ("<file>".equals(s = in.readUTF())) {
				String path = in.readUTF();
				byte[] digest = new byte[20];
				byte[] digest2 = new byte[20];
				in.read(digest);
				int len = in.readInt();
				byte[] file = new byte[len];
				in.read(file);
				if (filePool.containsKey(path))
					continue;
				dg.update(file, 0, len);
				dg.doFinal(digest2, 0);
				if (!Arrays.equals(digest, digest2))
					throw new IOException("invalid file hash for " + path);
				filePool.put(path, file);
				if (!"</file>".equals(in.readUTF()))
					throw new IOException("invalid epk file");
			}
			if (in.available() > 0 || !" end".equals(s))
				throw new IOException("invalid epk file");
		}

		public static final byte[] getResource(String path) {
			if (path.startsWith("/"))
				path = path.substring(1);
			return filePool.get(path);
		}
	}
	
	@SuppressWarnings("unused")
	public static class SHA1Digest extends GeneralDigest {
	    private static final int DIGEST_LENGTH = 20;

	    private int H1, H2, H3, H4, H5;

	    private int[] X = new int[80];
	    private int xOff;

	    public SHA1Digest() {
	        reset();
	    }

		public SHA1Digest(SHA1Digest t) {
	        super(t);

	        H1 = t.H1;
	        H2 = t.H2;
	        H3 = t.H3;
	        H4 = t.H4;
	        H5 = t.H5;

	        System.arraycopy(t.X, 0, X, 0, t.X.length);
	        xOff = t.xOff;
	    }

	    public String getAlgorithmName() {
	        return "SHA-1";
	    }

	    public int getDigestSize() {
	        return DIGEST_LENGTH;
	    }

	    protected void processWord(
	        byte[] in,
	        int inOff) {
	        X[xOff++] = ((in [inOff] & 0xff) << 24) | ((in [inOff + 1] & 0xff) << 16) |
	            ((in [inOff + 2] & 0xff) << 8) | ((in [inOff + 3] & 0xff));

	        if (xOff == 16) {
	            processBlock();
	        }
	    }

	    private void unpackWord(
	        int word,
	        byte[] out,
	        int outOff) {
	        out[outOff] = (byte)(word >>> 24);
	        out[outOff + 1] = (byte)(word >>> 16);
	        out[outOff + 2] = (byte)(word >>> 8);
	        out[outOff + 3] = (byte) word;
	    }

	    protected void processLength(
	        long bitLength) {
	        if (xOff > 14) {
	            processBlock();
	        }

	        X[14] = (int)(bitLength >>> 32);
	        X[15] = (int)(bitLength & 0xffffffff);
	    }

	    public int doFinal(
	        byte[] out,
	        int outOff) {
	        finish();

	        unpackWord(H1, out, outOff);
	        unpackWord(H2, out, outOff + 4);
	        unpackWord(H3, out, outOff + 8);
	        unpackWord(H4, out, outOff + 12);
	        unpackWord(H5, out, outOff + 16);

	        reset();

	        return DIGEST_LENGTH;
	    }

	    public void reset() {
	        super.reset();

	        H1 = 0x67452301;
	        H2 = 0xefcdab89;
	        H3 = 0x98badcfe;
	        H4 = 0x10325476;
	        H5 = 0xc3d2e1f0;

	        xOff = 0;
	        for (int i = 0; i != X.length; i++) {
	            X[i] = 0;
	        }
	    }

	    private static final int Y1 = 0x5a827999;
	    private static final int Y2 = 0x6ed9eba1;
	    private static final int Y3 = 0x8f1bbcdc;
	    private static final int Y4 = 0xca62c1d6;

	    private int f(
	        int u,
	        int v,
	        int w) {
	        return ((u & v) | ((~u) & w));
	    }

	    private int h(
	        int u,
	        int v,
	        int w) {
	        return (u ^ v ^ w);
	    }

	    private int g(
	        int u,
	        int v,
	        int w) {
	        return ((u & v) | (u & w) | (v & w));
	    }

	    private int rotateLeft(
	        int x,
	        int n) {
	        return (x << n) | (x >>> (32 - n));
	    }

	    protected void processBlock() {
	        for (int i = 16; i <= 79; i++) {
	            X[i] = rotateLeft((X[i - 3] ^ X[i - 8] ^ X[i - 14] ^ X[i - 16]), 1);
	        }

	        int A = H1;
	        int B = H2;
	        int C = H3;
	        int D = H4;
	        int E = H5;

	        for (int j = 0; j <= 19; j++) {
	            int t = rotateLeft(A, 5) + f(B, C, D) + E + X[j] + Y1;

	            E = D;
	            D = C;
	            C = rotateLeft(B, 30);
	            B = A;
	            A = t;
	        }

	        for (int j = 20; j <= 39; j++) {
	            int t = rotateLeft(A, 5) + h(B, C, D) + E + X[j] + Y2;

	            E = D;
	            D = C;
	            C = rotateLeft(B, 30);
	            B = A;
	            A = t;
	        }

	        for (int j = 40; j <= 59; j++) {
	            int t = rotateLeft(A, 5) + g(B, C, D) + E + X[j] + Y3;

	            E = D;
	            D = C;
	            C = rotateLeft(B, 30);
	            B = A;
	            A = t;
	        }

	        for (int j = 60; j <= 79; j++) {
	            int t = rotateLeft(A, 5) + h(B, C, D) + E + X[j] + Y4;

	            E = D;
	            D = C;
	            C = rotateLeft(B, 30);
	            B = A;
	            A = t;
	        }

	        H1 += A;
	        H2 += B;
	        H3 += C;
	        H4 += D;
	        H5 += E;

	        xOff = 0;
	        for (int i = 0; i != X.length; i++) {
	            X[i] = 0;
	        }
	    }
	}
	
	private static abstract class GeneralDigest {
	    private byte[] xBuf;
	    private int xBufOff;

	    private long byteCount;

	    protected GeneralDigest() {
	        xBuf = new byte[4];
	        xBufOff = 0;
	    }

	    protected GeneralDigest(GeneralDigest t) {
	        xBuf = new byte[t.xBuf.length];
	        System.arraycopy(t.xBuf, 0, xBuf, 0, t.xBuf.length);

	        xBufOff = t.xBufOff;
	        byteCount = t.byteCount;
	    }

	    public void update(
	        byte in ) {
	        xBuf[xBufOff++] = in;

	        if (xBufOff == xBuf.length) {
	            processWord(xBuf, 0);
	            xBufOff = 0;
	        }

	        byteCount++;
	    }

	    public void update(
	        byte[] in,
	        int inOff,
	        int len) {
	    	
	        while ((xBufOff != 0) && (len > 0)) {
	            update(in [inOff]);

	            inOff++;
	            len--;
	        }

	        while (len > xBuf.length) {
	            processWord(in, inOff);

	            inOff += xBuf.length;
	            len -= xBuf.length;
	            byteCount += xBuf.length;
	        }

	        while (len > 0) {
	            update(in [inOff]);

	            inOff++;
	            len--;
	        }
	    }

	    public void finish() {
	        long bitLength = (byteCount << 3);

	        update((byte) 128);

	        while (xBufOff != 0) {
	            update((byte) 0);
	        }

	        processLength(bitLength);

	        processBlock();
	    }

	    public void reset() {
	        byteCount = 0;

	        xBufOff = 0;
	        for (int i = 0; i < xBuf.length; i++) {
	            xBuf[i] = 0;
	        }
	    }

	    protected abstract void processWord(byte[] in, int inOff);

	    protected abstract void processLength(long bitLength);

	    protected abstract void processBlock();
	}
	
	@SuppressWarnings("unused")
	private static class Base64 extends BaseNCodec {

	    /**
	     * BASE32 characters are 6 bits in length.
	     * They are formed by taking a block of 3 octets to form a 24-bit string,
	     * which is converted into 4 BASE64 characters.
	     */
	    private static final int BITS_PER_ENCODED_BYTE = 6;
	    private static final int BYTES_PER_UNENCODED_BLOCK = 3;
	    private static final int BYTES_PER_ENCODED_BLOCK = 4;

	    /**
	     * This array is a lookup table that translates 6-bit positive integer index values into their "Base64 Alphabet"
	     * equivalents as specified in Table 1 of RFC 2045.
	     *
	     * Thanks to "commons" project in ws.apache.org for this code.
	     * http://svn.apache.org/repos/asf/webservices/commons/trunk/modules/util/
	     */
	    private static final byte[] STANDARD_ENCODE_TABLE = {
	            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
	            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
	            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
	            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
	    };

	    /**
	     * This is a copy of the STANDARD_ENCODE_TABLE above, but with + and /
	     * changed to - and _ to make the encoded Base64 results more URL-SAFE.
	     * This table is only used when the Base64's mode is set to URL-SAFE.
	     */
	    private static final byte[] URL_SAFE_ENCODE_TABLE = {
	            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
	            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
	            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
	            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'
	    };

	    /**
	     * This array is a lookup table that translates Unicode characters drawn from the "Base64 Alphabet" (as specified
	     * in Table 1 of RFC 2045) into their 6-bit positive integer equivalents. Characters that are not in the Base64
	     * alphabet but fall within the bounds of the array are translated to -1.
	     *
	     * Note: '+' and '-' both decode to 62. '/' and '_' both decode to 63. This means decoder seamlessly handles both
	     * URL_SAFE and STANDARD base64. (The encoder, on the other hand, needs to know ahead of time what to emit).
	     *
	     * Thanks to "commons" project in ws.apache.org for this code.
	     * http://svn.apache.org/repos/asf/webservices/commons/trunk/modules/util/
	     */
	    private static final byte[] DECODE_TABLE = {
	        //   0   1   2   3   4   5   6   7   8   9   A   B   C   D   E   F
	            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 00-0f
	            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 10-1f
	            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, // 20-2f + - /
	            52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, // 30-3f 0-9
	            -1,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, // 40-4f A-O
	            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, // 50-5f P-Z _
	            -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, // 60-6f a-o
	            41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51                      // 70-7a p-z
	    };

	    /**
	     * Base64 uses 6-bit fields.
	     */
	    /** Mask used to extract 6 bits, used when encoding */
	    private static final int MASK_6BITS = 0x3f;
	    /** Mask used to extract 4 bits, used when decoding final trailing character. */
	    private static final int MASK_4BITS = 0xf;
	    /** Mask used to extract 2 bits, used when decoding final trailing character. */
	    private static final int MASK_2BITS = 0x3;

	    // The static final fields above are used for the original static byte[] methods on Base64.
	    // The private member fields below are used with the new streaming approach, which requires
	    // some state be preserved between calls of encode() and decode().

	    /**
	     * Decodes Base64 data into octets.
	     * <p>
	     * <b>Note:</b> this method seamlessly handles data encoded in URL-safe or normal mode.
	     * </p>
	     *
	     * @param base64Data
	     *            Byte array containing Base64 data
	     * @return Array containing decoded data.
	     */
	    public static byte[] decodeBase64(final byte[] base64Data) {
	        return new Base64().decode(base64Data);
	    }

	    /**
	     * Decodes a Base64 String into octets.
	     * <p>
	     * <b>Note:</b> this method seamlessly handles data encoded in URL-safe or normal mode.
	     * </p>
	     *
	     * @param base64String
	     *            String containing Base64 data
	     * @return Array containing decoded data.
	     * @since 1.4
	     */
	    public static byte[] decodeBase64(final String base64String) {
	        return new Base64().decode(base64String);
	    }

	    // Implementation of integer encoding used for crypto
	    /**
	     * Decodes a byte64-encoded integer according to crypto standards such as W3C's XML-Signature.
	     *
	     * @param pArray
	     *            a byte array containing base64 character data
	     * @return A BigInteger
	     * @since 1.4
	     */
	    public static BigInteger decodeInteger(final byte[] pArray) {
	        return new BigInteger(1, decodeBase64(pArray));
	    }

	    /**
	     * Encodes binary data using the base64 algorithm but does not chunk the output.
	     *
	     * @param binaryData
	     *            binary data to encode
	     * @return byte[] containing Base64 characters in their UTF-8 representation.
	     */
	    public static byte[] encodeBase64(final byte[] binaryData) {
	        return encodeBase64(binaryData, false);
	    }

	    /**
	     * Encodes binary data using the base64 algorithm, optionally chunking the output into 76 character blocks.
	     *
	     * @param binaryData
	     *            Array containing binary data to encode.
	     * @param isChunked
	     *            if {@code true} this encoder will chunk the base64 output into 76 character blocks
	     * @return Base64-encoded data.
	     * @throws IllegalArgumentException
	     *             Thrown when the input array needs an output array bigger than {@link Integer#MAX_VALUE}
	     */
	    public static byte[] encodeBase64(final byte[] binaryData, final boolean isChunked) {
	        return encodeBase64(binaryData, isChunked, false);
	    }

	    /**
	     * Encodes binary data using the base64 algorithm, optionally chunking the output into 76 character blocks.
	     *
	     * @param binaryData
	     *            Array containing binary data to encode.
	     * @param isChunked
	     *            if {@code true} this encoder will chunk the base64 output into 76 character blocks
	     * @param urlSafe
	     *            if {@code true} this encoder will emit - and _ instead of the usual + and / characters.
	     *            <b>Note: no padding is added when encoding using the URL-safe alphabet.</b>
	     * @return Base64-encoded data.
	     * @throws IllegalArgumentException
	     *             Thrown when the input array needs an output array bigger than {@link Integer#MAX_VALUE}
	     * @since 1.4
	     */
	    public static byte[] encodeBase64(final byte[] binaryData, final boolean isChunked, final boolean urlSafe) {
	        return encodeBase64(binaryData, isChunked, urlSafe, Integer.MAX_VALUE);
	    }

	    /**
	     * Encodes binary data using the base64 algorithm, optionally chunking the output into 76 character blocks.
	     *
	     * @param binaryData
	     *            Array containing binary data to encode.
	     * @param isChunked
	     *            if {@code true} this encoder will chunk the base64 output into 76 character blocks
	     * @param urlSafe
	     *            if {@code true} this encoder will emit - and _ instead of the usual + and / characters.
	     *            <b>Note: no padding is added when encoding using the URL-safe alphabet.</b>
	     * @param maxResultSize
	     *            The maximum result size to accept.
	     * @return Base64-encoded data.
	     * @throws IllegalArgumentException
	     *             Thrown when the input array needs an output array bigger than maxResultSize
	     * @since 1.4
	     */
	    public static byte[] encodeBase64(final byte[] binaryData, final boolean isChunked,
	                                      final boolean urlSafe, final int maxResultSize) {
	        if (binaryData == null || binaryData.length == 0) {
	            return binaryData;
	        }

	        // Create this so can use the super-class method
	        // Also ensures that the same roundings are performed by the ctor and the code
	        final Base64 b64 = isChunked ? new Base64(urlSafe) : new Base64(0, CHUNK_SEPARATOR, urlSafe);
	        final long len = b64.getEncodedLength(binaryData);
	        if (len > maxResultSize) {
	            throw new IllegalArgumentException("Input array too big, the output array would be bigger (" +
	                len +
	                ") than the specified maximum size of " +
	                maxResultSize);
	        }

	        return b64.encode(binaryData);
	    }

	    /**
	     * Encodes binary data using the base64 algorithm and chunks the encoded output into 76 character blocks
	     *
	     * @param binaryData
	     *            binary data to encode
	     * @return Base64 characters chunked in 76 character blocks
	     */
	    public static byte[] encodeBase64Chunked(final byte[] binaryData) {
	        return encodeBase64(binaryData, true);
	    }

	    /**
	     * Encodes binary data using the base64 algorithm but does not chunk the output.
	     *
	     * NOTE:  We changed the behavior of this method from multi-line chunking (commons-codec-1.4) to
	     * single-line non-chunking (commons-codec-1.5).
	     *
	     * @param binaryData
	     *            binary data to encode
	     * @return String containing Base64 characters.
	     * @since 1.4 (NOTE:  1.4 chunked the output, whereas 1.5 does not).
	     */
	    public static String encodeBase64String(final byte[] binaryData) {
	        return new String(encodeBase64(binaryData, false), Charset.forName("UTF-8"));
	    }

	    /**
	     * Encodes binary data using a URL-safe variation of the base64 algorithm but does not chunk the output. The
	     * url-safe variation emits - and _ instead of + and / characters.
	     * <b>Note: no padding is added.</b>
	     * @param binaryData
	     *            binary data to encode
	     * @return byte[] containing Base64 characters in their UTF-8 representation.
	     * @since 1.4
	     */
	    public static byte[] encodeBase64URLSafe(final byte[] binaryData) {
	        return encodeBase64(binaryData, false, true);
	    }

	    /**
	     * Encodes binary data using a URL-safe variation of the base64 algorithm but does not chunk the output. The
	     * url-safe variation emits - and _ instead of + and / characters.
	     * <b>Note: no padding is added.</b>
	     * @param binaryData
	     *            binary data to encode
	     * @return String containing Base64 characters
	     * @since 1.4
	     */
	    public static String encodeBase64URLSafeString(final byte[] binaryData) {
	        return new String(encodeBase64(binaryData, false, true), Charset.forName("UTF-8"));
	    }

	    /**
	     * Encodes to a byte64-encoded integer according to crypto standards such as W3C's XML-Signature.
	     *
	     * @param bigInteger
	     *            a BigInteger
	     * @return A byte array containing base64 character data
	     * @throws NullPointerException
	     *             if null is passed in
	     * @since 1.4
	     */
	    public static byte[] encodeInteger(final BigInteger bigInteger) {
	        return encodeBase64(toIntegerBytes(bigInteger), false);
	    }

	    /**
	     * Tests a given byte array to see if it contains only valid characters within the Base64 alphabet. Currently the
	     * method treats whitespace as valid.
	     *
	     * @param arrayOctet
	     *            byte array to test
	     * @return {@code true} if all bytes are valid characters in the Base64 alphabet or if the byte array is empty;
	     *         {@code false}, otherwise
	     * @deprecated 1.5 Use {@link #isBase64(byte[])}, will be removed in 2.0.
	     */
	    @Deprecated
	    public static boolean isArrayByteBase64(final byte[] arrayOctet) {
	        return isBase64(arrayOctet);
	    }

	    /**
	     * Returns whether or not the {@code octet} is in the base 64 alphabet.
	     *
	     * @param octet
	     *            The value to test
	     * @return {@code true} if the value is defined in the the base 64 alphabet, {@code false} otherwise.
	     * @since 1.4
	     */
	    public static boolean isBase64(final byte octet) {
	        return octet == PAD_DEFAULT || (octet >= 0 && octet < DECODE_TABLE.length && DECODE_TABLE[octet] != -1);
	    }

	    /**
	     * Tests a given byte array to see if it contains only valid characters within the Base64 alphabet. Currently the
	     * method treats whitespace as valid.
	     *
	     * @param arrayOctet
	     *            byte array to test
	     * @return {@code true} if all bytes are valid characters in the Base64 alphabet or if the byte array is empty;
	     *         {@code false}, otherwise
	     * @since 1.5
	     */
	    public static boolean isBase64(final byte[] arrayOctet) {
	        for (int i = 0; i < arrayOctet.length; i++) {
	            if (!isBase64(arrayOctet[i]) && !isWhiteSpace(arrayOctet[i])) {
	                return false;
	            }
	        }
	        return true;
	    }

	    /**
	     * Tests a given String to see if it contains only valid characters within the Base64 alphabet. Currently the
	     * method treats whitespace as valid.
	     *
	     * @param base64
	     *            String to test
	     * @return {@code true} if all characters in the String are valid characters in the Base64 alphabet or if
	     *         the String is empty; {@code false}, otherwise
	     *  @since 1.5
	     */
	    public static boolean isBase64(final String base64) {
	        return isBase64(base64.getBytes(Charset.forName("UTF-8")));
	    }

	    /**
	     * Returns a byte-array representation of a {@code BigInteger} without sign bit.
	     *
	     * @param bigInt
	     *            {@code BigInteger} to be converted
	     * @return a byte array representation of the BigInteger parameter
	     */
	    static byte[] toIntegerBytes(final BigInteger bigInt) {
	        int bitlen = bigInt.bitLength();
	        // round bitlen
	        bitlen = ((bitlen + 7) >> 3) << 3;
	        final byte[] bigBytes = bigInt.toByteArray();

	        if (((bigInt.bitLength() % 8) != 0) && (((bigInt.bitLength() / 8) + 1) == (bitlen / 8))) {
	            return bigBytes;
	        }
	        // set up params for copying everything but sign bit
	        int startSrc = 0;
	        int len = bigBytes.length;

	        // if bigInt is exactly byte-aligned, just skip signbit in copy
	        if ((bigInt.bitLength() % 8) == 0) {
	            startSrc = 1;
	            len--;
	        }
	        final int startDst = bitlen / 8 - len; // to pad w/ nulls as per spec
	        final byte[] resizedBytes = new byte[bitlen / 8];
	        System.arraycopy(bigBytes, startSrc, resizedBytes, startDst, len);
	        return resizedBytes;
	    }

	    /**
	     * Encode table to use: either STANDARD or URL_SAFE. Note: the DECODE_TABLE above remains static because it is able
	     * to decode both STANDARD and URL_SAFE streams, but the encodeTable must be a member variable so we can switch
	     * between the two modes.
	     */
	    private final byte[] encodeTable;

	    // Only one decode table currently; keep for consistency with Base32 code
	    private final byte[] decodeTable = DECODE_TABLE;

	    /**
	     * Line separator for encoding. Not used when decoding. Only used if lineLength &gt; 0.
	     */
	    private final byte[] lineSeparator;

	    /**
	     * Convenience variable to help us determine when our buffer is going to run out of room and needs resizing.
	     * {@code decodeSize = 3 + lineSeparator.length;}
	     */
	    private final int decodeSize;

	    /**
	     * Convenience variable to help us determine when our buffer is going to run out of room and needs resizing.
	     * {@code encodeSize = 4 + lineSeparator.length;}
	     */
	    private final int encodeSize;

	    /**
	     * Creates a Base64 codec used for decoding (all modes) and encoding in URL-unsafe mode.
	     * <p>
	     * When encoding the line length is 0 (no chunking), and the encoding table is STANDARD_ENCODE_TABLE.
	     * </p>
	     *
	     * <p>
	     * When decoding all variants are supported.
	     * </p>
	     */
	    public Base64() {
	        this(0);
	    }

	    /**
	     * Creates a Base64 codec used for decoding (all modes) and encoding in the given URL-safe mode.
	     * <p>
	     * When encoding the line length is 76, the line separator is CRLF, and the encoding table is STANDARD_ENCODE_TABLE.
	     * </p>
	     *
	     * <p>
	     * When decoding all variants are supported.
	     * </p>
	     *
	     * @param urlSafe
	     *            if {@code true}, URL-safe encoding is used. In most cases this should be set to
	     *            {@code false}.
	     * @since 1.4
	     */
	    public Base64(final boolean urlSafe) {
	        this(MIME_CHUNK_SIZE, CHUNK_SEPARATOR, urlSafe);
	    }

	    /**
	     * Creates a Base64 codec used for decoding (all modes) and encoding in URL-unsafe mode.
	     * <p>
	     * When encoding the line length is given in the constructor, the line separator is CRLF, and the encoding table is
	     * STANDARD_ENCODE_TABLE.
	     * </p>
	     * <p>
	     * Line lengths that aren't multiples of 4 will still essentially end up being multiples of 4 in the encoded data.
	     * </p>
	     * <p>
	     * When decoding all variants are supported.
	     * </p>
	     *
	     * @param lineLength
	     *            Each line of encoded data will be at most of the given length (rounded down to nearest multiple of
	     *            4). If lineLength &lt;= 0, then the output will not be divided into lines (chunks). Ignored when
	     *            decoding.
	     * @since 1.4
	     */
	    public Base64(final int lineLength) {
	        this(lineLength, CHUNK_SEPARATOR);
	    }

	    /**
	     * Creates a Base64 codec used for decoding (all modes) and encoding in URL-unsafe mode.
	     * <p>
	     * When encoding the line length and line separator are given in the constructor, and the encoding table is
	     * STANDARD_ENCODE_TABLE.
	     * </p>
	     * <p>
	     * Line lengths that aren't multiples of 4 will still essentially end up being multiples of 4 in the encoded data.
	     * </p>
	     * <p>
	     * When decoding all variants are supported.
	     * </p>
	     *
	     * @param lineLength
	     *            Each line of encoded data will be at most of the given length (rounded down to nearest multiple of
	     *            4). If lineLength &lt;= 0, then the output will not be divided into lines (chunks). Ignored when
	     *            decoding.
	     * @param lineSeparator
	     *            Each line of encoded data will end with this sequence of bytes.
	     * @throws IllegalArgumentException
	     *             Thrown when the provided lineSeparator included some base64 characters.
	     * @since 1.4
	     */
	    public Base64(final int lineLength, final byte[] lineSeparator) {
	        this(lineLength, lineSeparator, false);
	    }

	    /**
	     * Creates a Base64 codec used for decoding (all modes) and encoding in URL-unsafe mode.
	     * <p>
	     * When encoding the line length and line separator are given in the constructor, and the encoding table is
	     * STANDARD_ENCODE_TABLE.
	     * </p>
	     * <p>
	     * Line lengths that aren't multiples of 4 will still essentially end up being multiples of 4 in the encoded data.
	     * </p>
	     * <p>
	     * When decoding all variants are supported.
	     * </p>
	     *
	     * @param lineLength
	     *            Each line of encoded data will be at most of the given length (rounded down to nearest multiple of
	     *            4). If lineLength &lt;= 0, then the output will not be divided into lines (chunks). Ignored when
	     *            decoding.
	     * @param lineSeparator
	     *            Each line of encoded data will end with this sequence of bytes.
	     * @param urlSafe
	     *            Instead of emitting '+' and '/' we emit '-' and '_' respectively. urlSafe is only applied to encode
	     *            operations. Decoding seamlessly handles both modes.
	     *            <b>Note: no padding is added when using the URL-safe alphabet.</b>
	     * @throws IllegalArgumentException
	     *             Thrown when the {@code lineSeparator} contains Base64 characters.
	     * @since 1.4
	     */
	    public Base64(final int lineLength, final byte[] lineSeparator, final boolean urlSafe) {
	        this(lineLength, lineSeparator, urlSafe, CodecPolicy.LENIANT);
	    }

	    /**
	     * Creates a Base64 codec used for decoding (all modes) and encoding in URL-unsafe mode.
	     * <p>
	     * When encoding the line length and line separator are given in the constructor, and the encoding table is
	     * STANDARD_ENCODE_TABLE.
	     * </p>
	     * <p>
	     * Line lengths that aren't multiples of 4 will still essentially end up being multiples of 4 in the encoded data.
	     * </p>
	     * <p>
	     * When decoding all variants are supported.
	     * </p>
	     *
	     * @param lineLength
	     *            Each line of encoded data will be at most of the given length (rounded down to nearest multiple of
	     *            4). If lineLength &lt;= 0, then the output will not be divided into lines (chunks). Ignored when
	     *            decoding.
	     * @param lineSeparator
	     *            Each line of encoded data will end with this sequence of bytes.
	     * @param urlSafe
	     *            Instead of emitting '+' and '/' we emit '-' and '_' respectively. urlSafe is only applied to encode
	     *            operations. Decoding seamlessly handles both modes.
	     *            <b>Note: no padding is added when using the URL-safe alphabet.</b>
	     * @param decodingPolicy The decoding policy.
	     * @throws IllegalArgumentException
	     *             Thrown when the {@code lineSeparator} contains Base64 characters.
	     * @since 1.15
	     */
	    public Base64(final int lineLength, final byte[] lineSeparator, final boolean urlSafe, final CodecPolicy decodingPolicy) {
	        super(BYTES_PER_UNENCODED_BLOCK, BYTES_PER_ENCODED_BLOCK,
	                lineLength,
	                lineSeparator == null ? 0 : lineSeparator.length,
	                PAD_DEFAULT,
	                decodingPolicy);
	        // TODO could be simplified if there is no requirement to reject invalid line sep when length <=0
	        // @see test case Base64Test.testConstructors()
	        if (lineSeparator != null) {
	            if (containsAlphabetOrPad(lineSeparator)) {
	                final String sep = new String(lineSeparator, Charset.forName("UTF-8"));
	                throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + sep + "]");
	            }
	            if (lineLength > 0){ // null line-sep forces no chunking rather than throwing IAE
	                this.encodeSize = BYTES_PER_ENCODED_BLOCK + lineSeparator.length;
	                this.lineSeparator = new byte[lineSeparator.length];
	                System.arraycopy(lineSeparator, 0, this.lineSeparator, 0, lineSeparator.length);
	            } else {
	                this.encodeSize = BYTES_PER_ENCODED_BLOCK;
	                this.lineSeparator = null;
	            }
	        } else {
	            this.encodeSize = BYTES_PER_ENCODED_BLOCK;
	            this.lineSeparator = null;
	        }
	        this.decodeSize = this.encodeSize - 1;
	        this.encodeTable = urlSafe ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
	    }

	    // Implementation of the Encoder Interface

	    /**
	     * <p>
	     * Decodes all of the provided data, starting at inPos, for inAvail bytes. Should be called at least twice: once
	     * with the data to decode, and once with inAvail set to "-1" to alert decoder that EOF has been reached. The "-1"
	     * call is not necessary when decoding, but it doesn't hurt, either.
	     * </p>
	     * <p>
	     * Ignores all non-base64 characters. This is how chunked (e.g. 76 character) data is handled, since CR and LF are
	     * silently ignored, but has implications for other bytes, too. This method subscribes to the garbage-in,
	     * garbage-out philosophy: it will not check the provided data for validity.
	     * </p>
	     * <p>
	     * Thanks to "commons" project in ws.apache.org for the bitwise operations, and general approach.
	     * http://svn.apache.org/repos/asf/webservices/commons/trunk/modules/util/
	     * </p>
	     *
	     * @param in
	     *            byte[] array of ascii data to base64 decode.
	     * @param inPos
	     *            Position to start reading data from.
	     * @param inAvail
	     *            Amount of bytes available from input for decoding.
	     * @param context
	     *            the context to be used
	     */
	    @Override
	    void decode(final byte[] in, int inPos, final int inAvail, final Context context) {
	        if (context.eof) {
	            return;
	        }
	        if (inAvail < 0) {
	            context.eof = true;
	        }
	        for (int i = 0; i < inAvail; i++) {
	            final byte[] buffer = ensureBufferSize(decodeSize, context);
	            final byte b = in[inPos++];
	            if (b == pad) {
	                // We're done.
	                context.eof = true;
	                break;
	            }
	            if (b >= 0 && b < DECODE_TABLE.length) {
	                final int result = DECODE_TABLE[b];
	                if (result >= 0) {
	                    context.modulus = (context.modulus+1) % BYTES_PER_ENCODED_BLOCK;
	                    context.ibitWorkArea = (context.ibitWorkArea << BITS_PER_ENCODED_BYTE) + result;
	                    if (context.modulus == 0) {
	                        buffer[context.pos++] = (byte) ((context.ibitWorkArea >> 16) & MASK_8BITS);
	                        buffer[context.pos++] = (byte) ((context.ibitWorkArea >> 8) & MASK_8BITS);
	                        buffer[context.pos++] = (byte) (context.ibitWorkArea & MASK_8BITS);
	                    }
	                }
	            }
	        }

	        // Two forms of EOF as far as base64 decoder is concerned: actual
	        // EOF (-1) and first time '=' character is encountered in stream.
	        // This approach makes the '=' padding characters completely optional.
	        if (context.eof && context.modulus != 0) {
	            final byte[] buffer = ensureBufferSize(decodeSize, context);

	            // We have some spare bits remaining
	            // Output all whole multiples of 8 bits and ignore the rest
	            switch (context.modulus) {
//	              case 0 : // impossible, as excluded above
	                case 1 : // 6 bits - either ignore entirely, or raise an exception
	                    validateTrailingCharacter();
	                    break;
	                case 2 : // 12 bits = 8 + 4
	                    validateCharacter(MASK_4BITS, context);
	                    context.ibitWorkArea = context.ibitWorkArea >> 4; // dump the extra 4 bits
	                    buffer[context.pos++] = (byte) ((context.ibitWorkArea) & MASK_8BITS);
	                    break;
	                case 3 : // 18 bits = 8 + 8 + 2
	                    validateCharacter(MASK_2BITS, context);
	                    context.ibitWorkArea = context.ibitWorkArea >> 2; // dump 2 bits
	                    buffer[context.pos++] = (byte) ((context.ibitWorkArea >> 8) & MASK_8BITS);
	                    buffer[context.pos++] = (byte) ((context.ibitWorkArea) & MASK_8BITS);
	                    break;
	                default:
	                    throw new IllegalStateException("Impossible modulus " + context.modulus);
	            }
	        }
	    }

	    /**
	     * <p>
	     * Encodes all of the provided data, starting at inPos, for inAvail bytes. Must be called at least twice: once with
	     * the data to encode, and once with inAvail set to "-1" to alert encoder that EOF has been reached, to flush last
	     * remaining bytes (if not multiple of 3).
	     * </p>
	     * <p><b>Note: no padding is added when encoding using the URL-safe alphabet.</b></p>
	     * <p>
	     * Thanks to "commons" project in ws.apache.org for the bitwise operations, and general approach.
	     * http://svn.apache.org/repos/asf/webservices/commons/trunk/modules/util/
	     * </p>
	     *
	     * @param in
	     *            byte[] array of binary data to base64 encode.
	     * @param inPos
	     *            Position to start reading data from.
	     * @param inAvail
	     *            Amount of bytes available from input for encoding.
	     * @param context
	     *            the context to be used
	     */
	    @Override
	    void encode(final byte[] in, int inPos, final int inAvail, final Context context) {
	        if (context.eof) {
	            return;
	        }
	        // inAvail < 0 is how we're informed of EOF in the underlying data we're
	        // encoding.
	        if (inAvail < 0) {
	            context.eof = true;
	            if (0 == context.modulus && lineLength == 0) {
	                return; // no leftovers to process and not using chunking
	            }
	            final byte[] buffer = ensureBufferSize(encodeSize, context);
	            final int savedPos = context.pos;
	            switch (context.modulus) { // 0-2
	                case 0 : // nothing to do here
	                    break;
	                case 1 : // 8 bits = 6 + 2
	                    // top 6 bits:
	                    buffer[context.pos++] = encodeTable[(context.ibitWorkArea >> 2) & MASK_6BITS];
	                    // remaining 2:
	                    buffer[context.pos++] = encodeTable[(context.ibitWorkArea << 4) & MASK_6BITS];
	                    // URL-SAFE skips the padding to further reduce size.
	                    if (encodeTable == STANDARD_ENCODE_TABLE) {
	                        buffer[context.pos++] = pad;
	                        buffer[context.pos++] = pad;
	                    }
	                    break;

	                case 2 : // 16 bits = 6 + 6 + 4
	                    buffer[context.pos++] = encodeTable[(context.ibitWorkArea >> 10) & MASK_6BITS];
	                    buffer[context.pos++] = encodeTable[(context.ibitWorkArea >> 4) & MASK_6BITS];
	                    buffer[context.pos++] = encodeTable[(context.ibitWorkArea << 2) & MASK_6BITS];
	                    // URL-SAFE skips the padding to further reduce size.
	                    if (encodeTable == STANDARD_ENCODE_TABLE) {
	                        buffer[context.pos++] = pad;
	                    }
	                    break;
	                default:
	                    throw new IllegalStateException("Impossible modulus " + context.modulus);
	            }
	            context.currentLinePos += context.pos - savedPos; // keep track of current line position
	            // if currentPos == 0 we are at the start of a line, so don't add CRLF
	            if (lineLength > 0 && context.currentLinePos > 0) {
	                System.arraycopy(lineSeparator, 0, buffer, context.pos, lineSeparator.length);
	                context.pos += lineSeparator.length;
	            }
	        } else {
	            for (int i = 0; i < inAvail; i++) {
	                final byte[] buffer = ensureBufferSize(encodeSize, context);
	                context.modulus = (context.modulus+1) % BYTES_PER_UNENCODED_BLOCK;
	                int b = in[inPos++];
	                if (b < 0) {
	                    b += 256;
	                }
	                context.ibitWorkArea = (context.ibitWorkArea << 8) + b; //  BITS_PER_BYTE
	                if (0 == context.modulus) { // 3 bytes = 24 bits = 4 * 6 bits to extract
	                    buffer[context.pos++] = encodeTable[(context.ibitWorkArea >> 18) & MASK_6BITS];
	                    buffer[context.pos++] = encodeTable[(context.ibitWorkArea >> 12) & MASK_6BITS];
	                    buffer[context.pos++] = encodeTable[(context.ibitWorkArea >> 6) & MASK_6BITS];
	                    buffer[context.pos++] = encodeTable[context.ibitWorkArea & MASK_6BITS];
	                    context.currentLinePos += BYTES_PER_ENCODED_BLOCK;
	                    if (lineLength > 0 && lineLength <= context.currentLinePos) {
	                        System.arraycopy(lineSeparator, 0, buffer, context.pos, lineSeparator.length);
	                        context.pos += lineSeparator.length;
	                        context.currentLinePos = 0;
	                    }
	                }
	            }
	        }
	    }

	    /**
	     * Returns whether or not the {@code octet} is in the Base64 alphabet.
	     *
	     * @param octet
	     *            The value to test
	     * @return {@code true} if the value is defined in the the Base64 alphabet {@code false} otherwise.
	     */
	    @Override
	    protected boolean isInAlphabet(final byte octet) {
	        return octet >= 0 && octet < decodeTable.length && decodeTable[octet] != -1;
	    }

	    /**
	     * Returns our current encode mode. True if we're URL-SAFE, false otherwise.
	     *
	     * @return true if we're in URL-SAFE mode, false otherwise.
	     * @since 1.4
	     */
	    public boolean isUrlSafe() {
	        return this.encodeTable == URL_SAFE_ENCODE_TABLE;
	    }

	    /**
	     * Validates whether decoding the final trailing character is possible in the context
	     * of the set of possible base 64 values.
	     *
	     * <p>The character is valid if the lower bits within the provided mask are zero. This
	     * is used to test the final trailing base-64 digit is zero in the bits that will be discarded.
	     *
	     * @param emptyBitsMask The mask of the lower bits that should be empty
	     * @param context the context to be used
	     *
	     * @throws IllegalArgumentException if the bits being checked contain any non-zero value
	     */
	    private void validateCharacter(final int emptyBitsMask, final Context context) {
	        if (isStrictDecoding() && (context.ibitWorkArea & emptyBitsMask) != 0) {
	            throw new IllegalArgumentException(
	                "Strict decoding: Last encoded character (before the paddings if any) is a valid base 64 alphabet but not a possible encoding. " +
	                "Expected the discarded bits from the character to be zero.");
	        }
	    }

	    /**
	     * Validates whether decoding allows an entire final trailing character that cannot be
	     * used for a complete byte.
	     *
	     * @throws IllegalArgumentException if strict decoding is enabled
	     */
	    private void validateTrailingCharacter() {
	        if (isStrictDecoding()) {
	            throw new IllegalArgumentException(
	                "Strict decoding: Last encoded character (before the paddings if any) is a valid base 64 alphabet but not a possible encoding. " +
	                "Decoding requires at least two trailing 6-bit characters to create bytes.");
	        }
	    }

	}
	
	@SuppressWarnings("unused")
	private static abstract class BaseNCodec {
		
	    static enum CodecPolicy {
	    	STRICT,LENIANT;
		}

		/**
	     * Holds thread context so classes can be thread-safe.
	     *
	     * This class is not itself thread-safe; each thread must allocate its own copy.
	     *
	     * @since 1.7
	     */
	    static class Context {

	        /**
	         * Place holder for the bytes we're dealing with for our based logic.
	         * Bitwise operations store and extract the encoding or decoding from this variable.
	         */
	        int ibitWorkArea;

	        /**
	         * Place holder for the bytes we're dealing with for our based logic.
	         * Bitwise operations store and extract the encoding or decoding from this variable.
	         */
	        long lbitWorkArea;

	        /**
	         * Buffer for streaming.
	         */
	        byte[] buffer;

	        /**
	         * Position where next character should be written in the buffer.
	         */
	        int pos;

	        /**
	         * Position where next character should be read from the buffer.
	         */
	        int readPos;

	        /**
	         * Boolean flag to indicate the EOF has been reached. Once EOF has been reached, this object becomes useless,
	         * and must be thrown away.
	         */
	        boolean eof;

	        /**
	         * Variable tracks how many characters have been written to the current line. Only used when encoding. We use
	         * it to make sure each encoded line never goes beyond lineLength (if lineLength &gt; 0).
	         */
	        int currentLinePos;

	        /**
	         * Writes to the buffer only occur after every 3/5 reads when encoding, and every 4/8 reads when decoding. This
	         * variable helps track that.
	         */
	        int modulus;

	        Context() {
	        }

	        /**
	         * Returns a String useful for debugging (especially within a debugger.)
	         *
	         * @return a String useful for debugging.
	         */
	        @SuppressWarnings("boxing") // OK to ignore boxing here
	        @Override
	        public String toString() {
	            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, " +
	                    "modulus=%s, pos=%s, readPos=%s]", this.getClass().getSimpleName(), Arrays.toString(buffer),
	                    currentLinePos, eof, ibitWorkArea, lbitWorkArea, modulus, pos, readPos);
	        }
	    }

	    /**
	     * EOF
	     *
	     * @since 1.7
	     */
	    static final int EOF = -1;

	    /**
	     *  MIME chunk size per RFC 2045 section 6.8.
	     *
	     * <p>
	     * The {@value} character limit does not count the trailing CRLF, but counts all other characters, including any
	     * equal signs.
	     * </p>
	     *
	     * @see <a href="http://www.ietf.org/rfc/rfc2045.txt">RFC 2045 section 6.8</a>
	     */
	    public static final int MIME_CHUNK_SIZE = 76;

	    /**
	     * PEM chunk size per RFC 1421 section 4.3.2.4.
	     *
	     * <p>
	     * The {@value} character limit does not count the trailing CRLF, but counts all other characters, including any
	     * equal signs.
	     * </p>
	     *
	     * @see <a href="http://tools.ietf.org/html/rfc1421">RFC 1421 section 4.3.2.4</a>
	     */
	    public static final int PEM_CHUNK_SIZE = 64;

	    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;

	    /**
	     * Defines the default buffer size - currently {@value}
	     * - must be large enough for at least one encoded block+separator
	     */
	    private static final int DEFAULT_BUFFER_SIZE = 8192;

	    /**
	     * The maximum size buffer to allocate.
	     *
	     * <p>This is set to the same size used in the JDK {@code java.util.ArrayList}:</p>
	     * <blockquote>
	     * Some VMs reserve some header words in an array.
	     * Attempts to allocate larger arrays may result in
	     * OutOfMemoryError: Requested array size exceeds VM limit.
	     * </blockquote>
	     */
	    private static final int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;

	    /** Mask used to extract 8 bits, used in decoding bytes */
	    protected static final int MASK_8BITS = 0xff;

	    /**
	     * Byte used to pad output.
	     */
	    protected static final byte PAD_DEFAULT = '='; // Allow static access to default

	    /**
	     * Chunk separator per RFC 2045 section 2.1.
	     *
	     * @see <a href="http://www.ietf.org/rfc/rfc2045.txt">RFC 2045 section 2.1</a>
	     */
	    static final byte[] CHUNK_SEPARATOR = {'\r', '\n'};

	    /**
	     * Compares two {@code int} values numerically treating the values
	     * as unsigned. Taken from JDK 1.8.
	     *
	     * <p>TODO: Replace with JDK 1.8 Integer::compareUnsigned(int, int).</p>
	     *
	     * @param  x the first {@code int} to compare
	     * @param  y the second {@code int} to compare
	     * @return the value {@code 0} if {@code x == y}; a value less
	     *         than {@code 0} if {@code x < y} as unsigned values; and
	     *         a value greater than {@code 0} if {@code x > y} as
	     *         unsigned values
	     */
	    private static int compareUnsigned(final int xx, final int yy) {
	    	int x = xx + Integer.MIN_VALUE;
	    	int y = yy + Integer.MIN_VALUE;
	        return (x < y) ? -1 : ((x == y) ? 0 : 1);
	    }

	    /**
	     * Create a positive capacity at least as large the minimum required capacity.
	     * If the minimum capacity is negative then this throws an OutOfMemoryError as no array
	     * can be allocated.
	     *
	     * @param minCapacity the minimum capacity
	     * @return the capacity
	     * @throws OutOfMemoryError if the {@code minCapacity} is negative
	     */
	    private static int createPositiveCapacity(final int minCapacity) {
	        if (minCapacity < 0) {
	            // overflow
	            throw new OutOfMemoryError("Unable to allocate array size: " + (minCapacity & 0xffffffffL));
	        }
	        // This is called when we require buffer expansion to a very big array.
	        // Use the conservative maximum buffer size if possible, otherwise the biggest required.
	        //
	        // Note: In this situation JDK 1.8 java.util.ArrayList returns Integer.MAX_VALUE.
	        // This excludes some VMs that can exceed MAX_BUFFER_SIZE but not allocate a full
	        // Integer.MAX_VALUE length array.
	        // The result is that we may have to allocate an array of this size more than once if
	        // the capacity must be expanded again.
	        return (minCapacity > MAX_BUFFER_SIZE) ?
	            minCapacity :
	            MAX_BUFFER_SIZE;
	    }

	    /**
	     * Gets a copy of the chunk separator per RFC 2045 section 2.1.
	     *
	     * @return the chunk separator
	     * @see <a href="http://www.ietf.org/rfc/rfc2045.txt">RFC 2045 section 2.1</a>
	     * @since 1.15
	     */
	    public static byte[] getChunkSeparator() {
	        return CHUNK_SEPARATOR.clone();
	    }

	    /**
	     * Checks if a byte value is whitespace or not.
	     * Whitespace is taken to mean: space, tab, CR, LF
	     * @param byteToCheck
	     *            the byte to check
	     * @return true if byte is whitespace, false otherwise
	     */
	    protected static boolean isWhiteSpace(final byte byteToCheck) {
	        switch (byteToCheck) {
	            case ' ' :
	            case '\n' :
	            case '\r' :
	            case '\t' :
	                return true;
	            default :
	                return false;
	        }
	    }

	    /**
	     * Increases our buffer by the {@link #DEFAULT_BUFFER_RESIZE_FACTOR}.
	     * @param context the context to be used
	     * @param minCapacity the minimum required capacity
	     * @return the resized byte[] buffer
	     * @throws OutOfMemoryError if the {@code minCapacity} is negative
	     */
	    private static byte[] resizeBuffer(final Context context, final int minCapacity) {
	        // Overflow-conscious code treats the min and new capacity as unsigned.
	        final int oldCapacity = context.buffer.length;
	        int newCapacity = oldCapacity * DEFAULT_BUFFER_RESIZE_FACTOR;
	        if (compareUnsigned(newCapacity, minCapacity) < 0) {
	            newCapacity = minCapacity;
	        }
	        if (compareUnsigned(newCapacity, MAX_BUFFER_SIZE) > 0) {
	            newCapacity = createPositiveCapacity(minCapacity);
	        }

	        final byte[] b = new byte[newCapacity];
	        System.arraycopy(context.buffer, 0, b, 0, context.buffer.length);
	        context.buffer = b;
	        return b;
	    }

	    /**
	     * @deprecated Use {@link #pad}. Will be removed in 2.0.
	     */
	    @Deprecated
	    protected final byte PAD = PAD_DEFAULT; // instance variable just in case it needs to vary later

	    protected final byte pad; // instance variable just in case it needs to vary later

	    /** Number of bytes in each full block of unencoded data, e.g. 4 for Base64 and 5 for Base32 */
	    private final int unencodedBlockSize;

	    /** Number of bytes in each full block of encoded data, e.g. 3 for Base64 and 8 for Base32 */
	    private final int encodedBlockSize;

	    /**
	     * Chunksize for encoding. Not used when decoding.
	     * A value of zero or less implies no chunking of the encoded data.
	     * Rounded down to nearest multiple of encodedBlockSize.
	     */
	    protected final int lineLength;

	    /**
	     * Size of chunk separator. Not used unless {@link #lineLength} &gt; 0.
	     */
	    private final int chunkSeparatorLength;

	    /**
	     * Defines the decoding behavior when the input bytes contain leftover trailing bits that
	     * cannot be created by a valid encoding. These can be bits that are unused from the final
	     * character or entire characters. The default mode is lenient decoding. Set this to
	     * {@code true} to enable strict decoding.
	     * <ul>
	     * <li>Lenient: Any trailing bits are composed into 8-bit bytes where possible.
	     *     The remainder are discarded.
	     * <li>Strict: The decoding will raise an {@link IllegalArgumentException} if trailing bits
	     *     are not part of a valid encoding. Any unused bits from the final character must
	     *     be zero. Impossible counts of entire final characters are not allowed.
	     * </ul>
	     *
	     * <p>When strict decoding is enabled it is expected that the decoded bytes will be re-encoded
	     * to a byte array that matches the original, i.e. no changes occur on the final
	     * character. This requires that the input bytes use the same padding and alphabet
	     * as the encoder.
	     */
	    private final CodecPolicy decodingPolicy;

	    /**
	     * Note {@code lineLength} is rounded down to the nearest multiple of the encoded block size.
	     * If {@code chunkSeparatorLength} is zero, then chunking is disabled.
	     * @param unencodedBlockSize the size of an unencoded block (e.g. Base64 = 3)
	     * @param encodedBlockSize the size of an encoded block (e.g. Base64 = 4)
	     * @param lineLength if &gt; 0, use chunking with a length {@code lineLength}
	     * @param chunkSeparatorLength the chunk separator length, if relevant
	     */
	    protected BaseNCodec(final int unencodedBlockSize, final int encodedBlockSize,
	                         final int lineLength, final int chunkSeparatorLength) {
	        this(unencodedBlockSize, encodedBlockSize, lineLength, chunkSeparatorLength, PAD_DEFAULT);
	    }

	    /**
	     * Note {@code lineLength} is rounded down to the nearest multiple of the encoded block size.
	     * If {@code chunkSeparatorLength} is zero, then chunking is disabled.
	     * @param unencodedBlockSize the size of an unencoded block (e.g. Base64 = 3)
	     * @param encodedBlockSize the size of an encoded block (e.g. Base64 = 4)
	     * @param lineLength if &gt; 0, use chunking with a length {@code lineLength}
	     * @param chunkSeparatorLength the chunk separator length, if relevant
	     * @param pad byte used as padding byte.
	     */
	    protected BaseNCodec(final int unencodedBlockSize, final int encodedBlockSize,
	                         final int lineLength, final int chunkSeparatorLength, final byte pad) {
	        this(unencodedBlockSize, encodedBlockSize, lineLength, chunkSeparatorLength, pad, CodecPolicy.LENIANT);
	    }

	    /**
	     * Note {@code lineLength} is rounded down to the nearest multiple of the encoded block size.
	     * If {@code chunkSeparatorLength} is zero, then chunking is disabled.
	     * @param unencodedBlockSize the size of an unencoded block (e.g. Base64 = 3)
	     * @param encodedBlockSize the size of an encoded block (e.g. Base64 = 4)
	     * @param lineLength if &gt; 0, use chunking with a length {@code lineLength}
	     * @param chunkSeparatorLength the chunk separator length, if relevant
	     * @param pad byte used as padding byte.
	     * @param decodingPolicy Decoding policy.
	     * @since 1.15
	     */
	    protected BaseNCodec(final int unencodedBlockSize, final int encodedBlockSize,
	                         final int lineLength, final int chunkSeparatorLength, final byte pad, final CodecPolicy decodingPolicy) {
	        this.unencodedBlockSize = unencodedBlockSize;
	        this.encodedBlockSize = encodedBlockSize;
	        final boolean useChunking = lineLength > 0 && chunkSeparatorLength > 0;
	        this.lineLength = useChunking ? (lineLength / encodedBlockSize) * encodedBlockSize : 0;
	        this.chunkSeparatorLength = chunkSeparatorLength;
	        this.pad = pad;
	        this.decodingPolicy = decodingPolicy;
	    }

	    /**
	     * Returns the amount of buffered data available for reading.
	     *
	     * @param context the context to be used
	     * @return The amount of buffered data available for reading.
	     */
	    int available(final Context context) {  // package protected for access from I/O streams
	        return context.buffer != null ? context.pos - context.readPos : 0;
	    }

	    /**
	     * Tests a given byte array to see if it contains any characters within the alphabet or PAD.
	     *
	     * Intended for use in checking line-ending arrays
	     *
	     * @param arrayOctet
	     *            byte array to test
	     * @return {@code true} if any byte is a valid character in the alphabet or PAD; {@code false} otherwise
	     */
	    protected boolean containsAlphabetOrPad(final byte[] arrayOctet) {
	        if (arrayOctet == null) {
	            return false;
	        }
	        for (final byte element : arrayOctet) {
	            if (pad == element || isInAlphabet(element)) {
	                return true;
	            }
	        }
	        return false;
	    }

	    /**
	     * Decodes a byte[] containing characters in the Base-N alphabet.
	     *
	     * @param pArray
	     *            A byte array containing Base-N character data
	     * @return a byte array containing binary data
	     */
	    public byte[] decode(final byte[] pArray) {
	        if (pArray == null || pArray.length == 0) {
	            return pArray;
	        }
	        final Context context = new Context();
	        decode(pArray, 0, pArray.length, context);
	        decode(pArray, 0, EOF, context); // Notify decoder of EOF.
	        final byte[] result = new byte[context.pos];
	        readResults(result, 0, result.length, context);
	        return result;
	    }

	    // package protected for access from I/O streams
	    abstract void decode(byte[] pArray, int i, int length, Context context);

	    /**
	     * Decodes an Object using the Base-N algorithm. This method is provided in order to satisfy the requirements of
	     * the Decoder interface, and will throw a DecoderException if the supplied object is not of type byte[] or String.
	     *
	     * @param obj
	     *            Object to decode
	     * @return An object (of type byte[]) containing the binary data which corresponds to the byte[] or String
	     *         supplied.
	     * @throws DecoderException
	     *             if the parameter supplied is not of type byte[]
	     */
	    public Object decode(final Object obj) {
	        if (obj instanceof byte[]) {
	            return decode((byte[]) obj);
	        } else if (obj instanceof String) {
	            return decode((String) obj);
	        } else {
	            return null;
	        }
	    }

	    /**
	     * Decodes a String containing characters in the Base-N alphabet.
	     *
	     * @param pArray
	     *            A String containing Base-N character data
	     * @return a byte array containing binary data
	     */
	    public byte[] decode(final String pArray) {
	        return decode(pArray.getBytes(Charset.forName("UTF-8")));
	    }

	    /**
	     * Encodes a byte[] containing binary data, into a byte[] containing characters in the alphabet.
	     *
	     * @param pArray
	     *            a byte array containing binary data
	     * @return A byte array containing only the base N alphabetic character data
	     */
	    public byte[] encode(final byte[] pArray) {
	        if (pArray == null || pArray.length == 0) {
	            return pArray;
	        }
	        return encode(pArray, 0, pArray.length);
	    }

	    /**
	     * Encodes a byte[] containing binary data, into a byte[] containing
	     * characters in the alphabet.
	     *
	     * @param pArray
	     *            a byte array containing binary data
	     * @param offset
	     *            initial offset of the subarray.
	     * @param length
	     *            length of the subarray.
	     * @return A byte array containing only the base N alphabetic character data
	     * @since 1.11
	     */
	    public byte[] encode(final byte[] pArray, final int offset, final int length) {
	        if (pArray == null || pArray.length == 0) {
	            return pArray;
	        }
	        final Context context = new Context();
	        encode(pArray, offset, length, context);
	        encode(pArray, offset, EOF, context); // Notify encoder of EOF.
	        final byte[] buf = new byte[context.pos - context.readPos];
	        readResults(buf, 0, buf.length, context);
	        return buf;
	    }

	    // package protected for access from I/O streams
	    abstract void encode(byte[] pArray, int i, int length, Context context);

	    /**
	     * Encodes an Object using the Base-N algorithm. This method is provided in order to satisfy the requirements of
	     * the Encoder interface, and will throw an EncoderException if the supplied object is not of type byte[].
	     *
	     * @param obj
	     *            Object to encode
	     * @return An object (of type byte[]) containing the Base-N encoded data which corresponds to the byte[] supplied.
	     * @throws EncoderException
	     *             if the parameter supplied is not of type byte[]
	     */
	    public Object encode(final Object obj) {
	        return encode((byte[]) obj);
	    }

	    /**
	     * Encodes a byte[] containing binary data, into a String containing characters in the appropriate alphabet.
	     * Uses UTF8 encoding.
	     *
	     * @param pArray a byte array containing binary data
	     * @return String containing only character data in the appropriate alphabet.
	     * @since 1.5
	     * This is a duplicate of {@link #encodeToString(byte[])}; it was merged during refactoring.
	    */
	    public String encodeAsString(final byte[] pArray){
	        return new String(encode(pArray), Charset.forName("UTF-8"));
	    }

	    /**
	     * Encodes a byte[] containing binary data, into a String containing characters in the Base-N alphabet.
	     * Uses UTF8 encoding.
	     *
	     * @param pArray
	     *            a byte array containing binary data
	     * @return A String containing only Base-N character data
	     */
	    public String encodeToString(final byte[] pArray) {
	        return new String(encode(pArray), Charset.forName("UTF-8"));
	    }

	    /**
	     * Ensure that the buffer has room for {@code size} bytes
	     *
	     * @param size minimum spare space required
	     * @param context the context to be used
	     * @return the buffer
	     */
	    protected byte[] ensureBufferSize(final int size, final Context context){
	        if (context.buffer == null) {
	            context.buffer = new byte[Math.max(size, getDefaultBufferSize())];
	            context.pos = 0;
	            context.readPos = 0;

	            // Overflow-conscious:
	            // x + y > z  ==  x + y - z > 0
	        } else if (context.pos + size - context.buffer.length > 0) {
	            return resizeBuffer(context, context.pos + size);
	        }
	        return context.buffer;
	    }

	    /**
	     * Returns the decoding behavior policy.
	     * 
	     * <p>
	     * The default is lenient. If the decoding policy is strict, then decoding will raise an
	     * {@link IllegalArgumentException} if trailing bits are not part of a valid encoding. Decoding will compose
	     * trailing bits into 8-bit bytes and discard the remainder.
	     * </p>
	     *
	     * @return true if using strict decoding
	     * @since 1.15
	     */
	    public CodecPolicy getCodecPolicy() {
	        return decodingPolicy;
	    }

	    /**
	     * Get the default buffer size. Can be overridden.
	     *
	     * @return the default buffer size.
	     */
	    protected int getDefaultBufferSize() {
	        return DEFAULT_BUFFER_SIZE;
	    }

	    /**
	     * Calculates the amount of space needed to encode the supplied array.
	     *
	     * @param pArray byte[] array which will later be encoded
	     *
	     * @return amount of space needed to encoded the supplied array.
	     * Returns a long since a max-len array will require &gt; Integer.MAX_VALUE
	     */
	    public long getEncodedLength(final byte[] pArray) {
	        // Calculate non-chunked size - rounded up to allow for padding
	        // cast to long is needed to avoid possibility of overflow
	        long len = ((pArray.length + unencodedBlockSize-1)  / unencodedBlockSize) * (long) encodedBlockSize;
	        if (lineLength > 0) { // We're using chunking
	            // Round up to nearest multiple
	            len += ((len + lineLength-1) / lineLength) * chunkSeparatorLength;
	        }
	        return len;
	    }

	    /**
	     * Returns true if this object has buffered data for reading.
	     *
	     * @param context the context to be used
	     * @return true if there is data still available for reading.
	     */
	    boolean hasData(final Context context) {  // package protected for access from I/O streams
	        return context.buffer != null;
	    }

	    /**
	     * Returns whether or not the {@code octet} is in the current alphabet.
	     * Does not allow whitespace or pad.
	     *
	     * @param value The value to test
	     *
	     * @return {@code true} if the value is defined in the current alphabet, {@code false} otherwise.
	     */
	    protected abstract boolean isInAlphabet(byte value);

	    /**
	     * Tests a given byte array to see if it contains only valid characters within the alphabet.
	     * The method optionally treats whitespace and pad as valid.
	     *
	     * @param arrayOctet byte array to test
	     * @param allowWSPad if {@code true}, then whitespace and PAD are also allowed
	     *
	     * @return {@code true} if all bytes are valid characters in the alphabet or if the byte array is empty;
	     *         {@code false}, otherwise
	     */
	    public boolean isInAlphabet(final byte[] arrayOctet, final boolean allowWSPad) {
	        for (final byte octet : arrayOctet) {
	            if (!isInAlphabet(octet) &&
	                    (!allowWSPad || (octet != pad) && !isWhiteSpace(octet))) {
	                return false;
	            }
	        }
	        return true;
	    }

	    /**
	     * Tests a given String to see if it contains only valid characters within the alphabet.
	     * The method treats whitespace and PAD as valid.
	     *
	     * @param basen String to test
	     * @return {@code true} if all characters in the String are valid characters in the alphabet or if
	     *         the String is empty; {@code false}, otherwise
	     * @see #isInAlphabet(byte[], boolean)
	     */
	    public boolean isInAlphabet(final String basen) {
	        return isInAlphabet(basen.getBytes(Charset.forName("UTF-8")), true);
	    }

	    /**
	     * Returns true if decoding behavior is strict. Decoding will raise an {@link IllegalArgumentException} if trailing
	     * bits are not part of a valid encoding.
	     *
	     * <p>
	     * The default is false for lenient decoding. Decoding will compose trailing bits into 8-bit bytes and discard the
	     * remainder.
	     * </p>
	     *
	     * @return true if using strict decoding
	     * @since 1.15
	     */
	    public boolean isStrictDecoding() {
	        return decodingPolicy == CodecPolicy.STRICT;
	    }

	    /**
	     * Extracts buffered data into the provided byte[] array, starting at position bPos, up to a maximum of bAvail
	     * bytes. Returns how many bytes were actually extracted.
	     * <p>
	     * Package protected for access from I/O streams.
	     *
	     * @param b
	     *            byte[] array to extract the buffered data into.
	     * @param bPos
	     *            position in byte[] array to start extraction at.
	     * @param bAvail
	     *            amount of bytes we're allowed to extract. We may extract fewer (if fewer are available).
	     * @param context
	     *            the context to be used
	     * @return The number of bytes successfully extracted into the provided byte[] array.
	     */
	    int readResults(final byte[] b, final int bPos, final int bAvail, final Context context) {
	        if (context.buffer != null) {
	            final int len = Math.min(available(context), bAvail);
	            System.arraycopy(context.buffer, context.readPos, b, bPos, len);
	            context.readPos += len;
	            if (context.readPos >= context.pos) {
	                context.buffer = null; // so hasData() will return false, and this method can return -1
	            }
	            return len;
	        }
	        return context.eof ? EOF : 0;
	    }
	}

	public static InputStream getResourceAsStream(String string) {
		return ImageIO.getResourceAsStream(string);
	}

	public static void glBindTexture(int texture) {
		glBindTexture(GL_TEXTURE_2D, texture);
	}

	public static BufferedImage getResource(String string) {
		return ImageIO.getResource(string);
	}
}