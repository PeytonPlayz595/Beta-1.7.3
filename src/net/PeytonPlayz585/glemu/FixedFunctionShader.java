package net.PeytonPlayz585.glemu;

import static org.lwjgl.opengl.GL11.EaglerAdapterImpl2.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11.EaglerAdapterImpl2.ProgramGL;
import org.lwjgl.opengl.GL11.EaglerAdapterImpl2.ShaderGL;
import org.lwjgl.opengl.GL11.EaglerAdapterImpl2.UniformGL;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

public class FixedFunctionShader {
	
	private static final FixedFunctionShader[] instances = new FixedFunctionShader[4096]; //lol
	private static final List<FixedFunctionShader> instanceList = new ArrayList();
	
	public static void refreshCoreGL() {
		for(int i = 0; i < instances.length; ++i) {
			if(instances[i] != null) {
				_wglDeleteProgram(instances[i].globject);
				instances[i] = null;
			}
		}
		instanceList.clear();
		shaderSource = null;
	}

	public static final int COLOR = 1;
	public static final int NORMAL = 2;
	public static final int TEXTURE0 = 4;
	public static final int TEXTURE1 = 8;
	public static final int TEXGEN = 16;
	public static final int LIGHTING = 32;
	public static final int FOG = 64;
	public static final int ALPHATEST = 128;
	public static final int UNIT0 = 256;
	public static final int UNIT1 = 512;
	public static final int FIX_ANISOTROPIC = 1024;
	public static final int SWAP_RB = 2048;
	
	public static FixedFunctionShader instance(int i) {
		FixedFunctionShader s = instances[i];
		if(s == null) {
			boolean CC_a_color = false;
			boolean CC_a_normal = false;
			boolean CC_a_texture0 = false;
			boolean CC_a_texture1 = false;
			boolean CC_TEX_GEN_STRQ = false;
			boolean CC_lighting = false;
			boolean CC_fog = false;
			boolean CC_alphatest = false;
			boolean CC_unit0 = false;
			boolean CC_unit1 = false;
			boolean CC_anisotropic = false;
			boolean CC_swap_rb = false;
			if((i & COLOR) == COLOR) {
				CC_a_color = true;
			}
			if((i & NORMAL) == NORMAL) {
				CC_a_normal = true;
			}
			if((i & TEXTURE0) == TEXTURE0) {
				CC_a_texture0 = true;
			}
			if((i & TEXTURE1) == TEXTURE1) {
				CC_a_texture1 = true;
			}
			if((i & TEXGEN) == TEXGEN) {
				CC_TEX_GEN_STRQ = true;
			}
			if((i & LIGHTING) == LIGHTING) {
				CC_lighting = true;
			}
			if((i & FOG) == FOG) {
				CC_fog = true;
			}
			if((i & ALPHATEST) == ALPHATEST) {
				CC_alphatest = true;
			}
			if((i & UNIT0) == UNIT0) {
				CC_unit0 = true;
			}
			if((i & UNIT1) == UNIT1) {
				CC_unit1 = true;
			}
			if((i & FIX_ANISOTROPIC) == FIX_ANISOTROPIC) {
				CC_anisotropic = true;
			}
			if((i & SWAP_RB) == SWAP_RB) {
				CC_swap_rb = true;
			}
			s = new FixedFunctionShader(i, CC_a_color, CC_a_normal, CC_a_texture0, CC_a_texture1, CC_TEX_GEN_STRQ, CC_lighting,
					CC_fog, CC_alphatest, CC_unit0, CC_unit1, CC_anisotropic, CC_swap_rb);
			instances[i] = s;
			instanceList.add(s);
		}
		return s;
	}

	private static String shaderSource = null;
	
	private final boolean enable_color;
	private final boolean enable_normal;
	private final boolean enable_texture0;
	private final boolean enable_texture1;
	private final boolean enable_TEX_GEN_STRQ;
	private final boolean enable_lighting;
	private final boolean enable_fog;
	private final boolean enable_alphatest;
	private final boolean enable_unit0;
	private final boolean enable_unit1;
	private final boolean enable_anisotropic_fix;
	private final boolean enable_swap_rb;
	private final ProgramGL globject;

	private UniformGL u_matrix_m = null;
	private UniformGL u_matrix_p = null;
	private UniformGL u_matrix_t = null;
	
	private UniformGL u_fogColor = null;
	//private UniformGL u_fogMode = null;
	//private UniformGL u_fogStart = null;
	//private UniformGL u_fogEnd = null;
	//private UniformGL u_fogDensity = null;
	private UniformGL u_fogParam = null;

	private UniformGL u_colorUniform = null;
	private UniformGL u_normalUniform = null;
	
	private UniformGL u_alphaTestF = null;

	//private UniformGL u_textureGenS_M = null;
	//private UniformGL u_textureGenT_M = null;
	//private UniformGL u_textureGenR_M = null;
	//private UniformGL u_textureGenQ_M = null;
	private UniformGL u_textureGen_M = null;
	private UniformGL u_textureGenS_V = null;
	private UniformGL u_textureGenT_V = null;
	private UniformGL u_textureGenR_V = null;
	private UniformGL u_textureGenQ_V = null;
	
	private UniformGL u_texCoordV0 = null;
	private UniformGL u_texCoordV1 = null;
	
	private UniformGL u_light0Pos = null;
	private UniformGL u_light1Pos = null;
	//private UniformGL u_invertNormals = null;
	
	private UniformGL u_anisotropic_fix = null;

	private final int a_position;
	private final int a_texture0;
	private final int a_color;
	private final int a_normal;
	private final int a_texture1;
	
	private final int attributeIndexesToEnable;

	public final StreamBuffer streamBuffer;
	public boolean bufferIsInitialized = false;
	
	private FixedFunctionShader(int j, boolean CC_a_color, boolean CC_a_normal, boolean CC_a_texture0, boolean CC_a_texture1, boolean CC_TEX_GEN_STRQ, boolean CC_lighting, 
			boolean CC_fog, boolean CC_alphatest, boolean CC_unit0, boolean CC_unit1, boolean CC_anisotropic_fix, boolean CC_swap_rb) {
		enable_color = CC_a_color;
		enable_normal = CC_a_normal;
		enable_texture0 = CC_a_texture0;
		enable_texture1 = CC_a_texture1;
		enable_TEX_GEN_STRQ = CC_TEX_GEN_STRQ;
		enable_lighting = CC_lighting;
		enable_fog = CC_fog;
		enable_alphatest = CC_alphatest;
		enable_unit0 = CC_unit0;
		enable_unit1 = CC_unit1;
		enable_anisotropic_fix = CC_anisotropic_fix;
		enable_swap_rb = CC_swap_rb;
		
		if(shaderSource == null) {
			shaderSource = fileContents("/glsl/core.glsl");
		}
		
		String source = "";
		if(enable_color) source += "\n#define CC_a_color\n";
		if(enable_normal) source += "#define CC_a_normal\n";
		if(enable_texture0) source += "#define CC_a_texture0\n";
		if(enable_texture1) source += "#define CC_a_texture1\n";
		if(enable_TEX_GEN_STRQ) source += "#define CC_TEX_GEN_STRQ\n";
		if(enable_lighting) source += "#define CC_lighting\n";
		if(enable_fog) source += "#define CC_fog\n";
		if(enable_alphatest) source += "#define CC_alphatest\n";
		if(enable_unit0) source += "#define CC_unit0\n";
		if(enable_unit1) source += "#define CC_unit1\n";
		if(enable_anisotropic_fix) source += "#define CC_patch_anisotropic\n";
		if(enable_swap_rb) source += "#define CC_swap_rb\n";
		source += shaderSource;
		
		ShaderGL v = _wglCreateShader(_wGL_VERTEX_SHADER);
		_wglShaderSource(v, _wgetShaderHeader()+"\n#define CC_VERT\n"+source);
		_wglCompileShader(v);
		
		if(!_wglGetShaderCompiled(v)) {
			System.err.println(("\n\n"+_wglGetShaderInfoLog(v)).replace("\n", "\n[/glsl/core.glsl][CC_VERT] "));
			throw new RuntimeException("broken shader file");
		}
		
		ShaderGL f = _wglCreateShader(_wGL_FRAGMENT_SHADER);
		_wglShaderSource(f, _wgetShaderHeader()+"\n#define CC_FRAG\n"+source);
		_wglCompileShader(f);
		
		if(!_wglGetShaderCompiled(f)) {
			System.err.println(("\n\n"+_wglGetShaderInfoLog(f)).replace("\n", "\n[/glsl/core.glsl][CC_FRAG] "));
			throw new RuntimeException("broken shader file");
		}
		
		globject = _wglCreateProgram();
		_wglAttachShader(globject, v);
		_wglAttachShader(globject, f);
		
		int i = 0;
		a_position = i++;
		_wglBindAttributeLocation(globject, a_position, "a_position");
		
		if(enable_texture0) {
			a_texture0 = i++;
			_wglBindAttributeLocation(globject, a_texture0, "a_texture0");
		}else {
			a_texture0 = -1;
		}
		if(enable_color) {
			a_color = i++;
			_wglBindAttributeLocation(globject, a_color, "a_color");
		}else {
			a_color = -1;
		}
		if(enable_normal) {
			a_normal = i++;
			_wglBindAttributeLocation(globject, a_normal, "a_normal");
		}else {
			a_normal = -1;
		}
		if(enable_texture1) {
			a_texture1 = i++;
			_wglBindAttributeLocation(globject, a_texture1, "a_texture1");
		}else {
			a_texture1 = -1;
		}
		
		attributeIndexesToEnable = i;
		
		_wglLinkProgram(globject);
		
		_wglDetachShader(globject, v);
		_wglDetachShader(globject, f);
		_wglDeleteShader(v);
		_wglDeleteShader(f);
		
		if(!_wglGetProgramLinked(globject)) {
			System.err.println(("\n\n"+_wglGetProgramInfoLog(globject)).replace("\n", "\n[LINKER] "));
			throw new RuntimeException("broken shader file");
		}
		
		_wglUseProgram(globject);

		u_matrix_m = _wglGetUniformLocation(globject, "matrix_m");
		u_matrix_p = _wglGetUniformLocation(globject, "matrix_p");
		u_matrix_t = _wglGetUniformLocation(globject, "matrix_t");

		u_colorUniform = _wglGetUniformLocation(globject, "colorUniform");
		
		if(enable_lighting) {
			u_normalUniform = _wglGetUniformLocation(globject, "normalUniform");
			//u_invertNormals = _wglGetUniformLocation(globject, "invertNormals");
			u_light0Pos = _wglGetUniformLocation(globject, "light0Pos");
			u_light1Pos = _wglGetUniformLocation(globject, "light1Pos");
		}
		
		if(enable_fog) {
			u_fogColor = _wglGetUniformLocation(globject, "fogColor");
			//u_fogMode = _wglGetUniformLocation(globject, "fogMode");
			//u_fogStart = _wglGetUniformLocation(globject, "fogStart");
			//u_fogEnd = _wglGetUniformLocation(globject, "fogEnd");
			//u_fogDensity = _wglGetUniformLocation(globject, "fogDensity");
			u_fogParam = _wglGetUniformLocation(globject, "fogParam");
		}
		
		if(enable_alphatest) {
			u_alphaTestF = _wglGetUniformLocation(globject, "alphaTestF");
		}
		
		if(enable_TEX_GEN_STRQ) {
			//u_textureGenS_M = _wglGetUniformLocation(globject, "textureGenS_M");
			//u_textureGenT_M = _wglGetUniformLocation(globject, "textureGenT_M");
			//u_textureGenR_M = _wglGetUniformLocation(globject, "textureGenR_M");
			//u_textureGenQ_M = _wglGetUniformLocation(globject, "textureGenQ_M");
			u_textureGen_M = _wglGetUniformLocation(globject, "textureGen_M");
			u_textureGenS_V = _wglGetUniformLocation(globject, "textureGenS_V");
			u_textureGenT_V = _wglGetUniformLocation(globject, "textureGenT_V");
			u_textureGenR_V = _wglGetUniformLocation(globject, "textureGenR_V");
			u_textureGenQ_V = _wglGetUniformLocation(globject, "textureGenQ_V");
		}
		
		if(enable_anisotropic_fix) {
			u_anisotropic_fix =  _wglGetUniformLocation(globject, "anisotropic_fix");
			_wglUniform2f(u_anisotropic_fix, 1024.0f * 63.0f / 64.0f, 1024.0f * 63.0f / 64.0f);
		}
		
		_wglUniform1i(_wglGetUniformLocation(globject, "tex0"), 0);
		_wglUniform1i(_wglGetUniformLocation(globject, "tex1"), 1);
		
		u_texCoordV0 = _wglGetUniformLocation(globject, "texCoordV0");
		u_texCoordV1 = _wglGetUniformLocation(globject, "texCoordV1");
	
		streamBuffer = new StreamBuffer(0x8000, 3, 8, (vertexArray, vertexBuffer) -> {
			_wglBindVertexArray0(vertexArray);
			_wglBindBuffer(_wGL_ARRAY_BUFFER, vertexBuffer);
			setupArrayForProgram();
		});
		
	}

	public void setupArrayForProgram() {
		_wglEnableVertexAttribArray(a_position);
		_wglVertexAttribPointer(a_position, 3, _wGL_FLOAT, false, 32, 0);
		if(enable_texture0) {
			_wglEnableVertexAttribArray(a_texture0);
			_wglVertexAttribPointer(a_texture0, 2, _wGL_FLOAT, false, 32, 12);
		}
		if(enable_color) {
			_wglEnableVertexAttribArray(a_color);
			_wglVertexAttribPointer(a_color, 4, _wGL_UNSIGNED_BYTE, true, 32, 20);
		}
		if(enable_normal) {
			_wglEnableVertexAttribArray(a_normal);
			_wglVertexAttribPointer(a_normal, 4, _wGL_UNSIGNED_BYTE, true, 32, 24);
		}
		if(enable_texture1) {
			_wglEnableVertexAttribArray(a_texture1);
			_wglVertexAttribPointer(a_texture1, 2, _wGL_SHORT, false, 32, 28);
		}
	}

	public void useProgram() {
		_wglUseProgram(globject);
	}

	public void unuseProgram() {
		
	}

	public static void optimize() {
		FixedFunctionShader pp;
		for(int i = 0, l = instanceList.size(); i < l; ++i) {
			instanceList.get(i).streamBuffer.optimize();
		}
	}

	private float[] matBuffer = new float[16];

	private Vector4f light0Pos = new Vector4f();
	private Vector4f light1Pos = new Vector4f();
	private Vector2f anisotropicFix = new Vector2f(0.0f, 0.0f);

	private int fogMode = 0;

	private float fogColorR = 0.0f;
	private float fogColorG = 0.0f;
	private float fogColorB = 0.0f;
	private float fogColorA = 0.0f;

	private float fogStart = 0.0f;
	private float fogEnd = 0.0f;

	private float fogDensity = 0.0f;

	private float alphaTestValue = 0.0f;

	private float tex0x = 0.0f;
	private float tex0y = 0.0f;
	
	private float tex1x = 0.0f;
	private float tex1y = 0.0f;

	private float colorUniformR = 0.0f;
	private float colorUniformG = 0.0f;
	private float colorUniformB = 0.0f;
	private float colorUniformA = 0.0f;

	private float normalUniformX = 0.0f;
	private float normalUniformY = 0.0f;
	private float normalUniformZ = 0.0f;

	private int anisotropicFixSerial = -1;
	private int colorSerial = -1;
	private int normalSerial = -1;
	private int tex0Serial = -1;
	private int tex1Serial = -1;
	private int texPlaneSerial = -1;
	private int texSSerial = -1;
	private int texTSerial = -1;
	private int texRSerial = -1;
	private int texQSerial = -1;
	private int fogColorSerial = -1;
	private int fogCfgSerial = -1;
	private int matModelSerialCounter = -1;
	private int matProjSerialCounter = -1;
	private int matTexSerialCounter = -1;
	private int lightPos0Serial = -1;
	private int lightPos1Serial = -1;

	private int texS_plane = -1;
	private float texS_X = -999.0f;
	private float texS_Y = -999.0f;
	private float texS_Z = -999.0f;
	private float texS_W = -999.0f;

	private int texT_plane = -1;
	private float texT_X = -999.0f;
	private float texT_Y = -999.0f;
	private float texT_Z = -999.0f;
	private float texT_W = -999.0f;

	private int texR_plane = -1;
	private float texR_X = -999.0f;
	private float texR_Y = -999.0f;
	private float texR_Z = -999.0f;
	private float texR_W = -999.0f;

	private int texQ_plane = -1;
	private float texQ_X = -999.0f;
	private float texQ_Y = -999.0f;
	private float texQ_Z = -999.0f;
	private float texQ_W = -999.0f;

	public void update() {
		if(anisotropicFixSerial != GL11.anisotropicFixSerial) {
			float x = GL11.anisotropicFixX;
			float y = GL11.anisotropicFixY;
			anisotropicFixSerial = GL11.anisotropicFixSerial;
			if(anisotropicFix.x != x || anisotropicFix.y != y) {
				anisotropicFix.x = x;
				anisotropicFix.y = y;
				_wglUniform2f(u_anisotropic_fix, x, y);
			}
		}
		if(colorSerial != GL11.colorSerial) {
			float r = GL11.colorR;
			float g = GL11.colorG;
			float b = GL11.colorB;
			float a = GL11.colorA;
			colorSerial = GL11.colorSerial;
			if(colorUniformR != r || colorUniformG != g || colorUniformB != b || colorUniformA != a) {
				colorUniformR = r;
				colorUniformG = g;
				colorUniformB = b;
				colorUniformA = a;
				_wglUniform4f(u_colorUniform, r, g, b, a);
			}
		}
		if(normalSerial != GL11.normalSerial) {
			float x = GL11.normalX;
			float y = GL11.normalY;
			float z = GL11.normalZ;
			normalSerial = GL11.normalSerial;
			if(normalUniformX != x || normalUniformY != y || normalUniformZ != z) {
				normalUniformX = x;
				normalUniformY = y;
				normalUniformZ = z;
				_wglUniform3f(u_normalUniform, x, y, z);
			}
		}
		if(tex0Serial != GL11.tex0Serial) {
			float x = GL11.tex0X;
			float y = GL11.tex0Y;
			tex0Serial = GL11.tex0Serial;
			if(tex0x != x || tex0y != y) {
				tex0x = x;
				tex0y = y;
				_wglUniform2f(u_texCoordV0, x, y);
			}
		}
		if(tex1Serial != GL11.tex1Serial) {
			float x = GL11.tex1X;
			float y = GL11.tex1Y;
			tex1Serial = GL11.tex1Serial;
			if(tex1x != x || tex1y != y) {
				tex1x = x;
				tex1y = y;
				_wglUniform2f(u_texCoordV1, x, y);
			}
		}
		if(texPlaneSerial != GL11.texPlaneSerial) {
			int s = GL11.texS_plane;
			int t = GL11.texT_plane;
			int r = GL11.texR_plane;
			int q = GL11.texQ_plane;
			texPlaneSerial = GL11.texPlaneSerial;
			if(texS_plane != s || texT_plane != t || texR_plane != r || texQ_plane != q) {
				texS_plane = s;
				texT_plane = t;
				texR_plane = r;
				texQ_plane = q;
				_wglUniform4i(u_textureGen_M, s, t, r, q);
			}
		}
		if(texSSerial != GL11.texSSerial) {
			float x = GL11.texS_X;
			float y = GL11.texS_Y;
			float z = GL11.texS_Z;
			float w = GL11.texS_W;
			texSSerial = GL11.texSSerial;
			if(texS_X != x || texS_Y != y || texS_Z != z || texS_W != w) {
				texS_X = x;
				texS_Y = y;
				texS_Z = z;
				texS_W = w;
				_wglUniform4f(u_textureGenS_V, x, y, z, w);
			}
		}
		if(texTSerial != GL11.texTSerial) {
			float x = GL11.texT_X;
			float y = GL11.texT_Y;
			float z = GL11.texT_Z;
			float w = GL11.texT_W;
			texTSerial = GL11.texTSerial;
			if(texT_X != x || texT_Y != y || texT_Z != z || texT_W != w) {
				texT_X = x;
				texT_Y = y;
				texT_Z = z;
				texT_W = w;
				_wglUniform4f(u_textureGenT_V, x, y, z, w);
			}
		}
		if(texRSerial != GL11.texRSerial) {
			float x = GL11.texR_X;
			float y = GL11.texR_Y;
			float z = GL11.texR_Z;
			float w = GL11.texR_W;
			texRSerial = GL11.texRSerial;
			if(texR_X != x || texR_Y != y || texR_Z != z || texR_W != w) {
				texR_X = x;
				texR_Y = y;
				texR_Z = z;
				texR_W = w;
				_wglUniform4f(u_textureGenR_V, x, y, z, w);
			}
		}
		if(texQSerial != GL11.texQSerial) {
			float x = GL11.texQ_X;
			float y = GL11.texQ_Y;
			float z = GL11.texQ_Z;
			float w = GL11.texQ_W;
			texQSerial = GL11.texQSerial;
			if(texQ_X != x || texQ_Y != y || texQ_Z != z || texQ_W != w) {
				texQ_X = x;
				texQ_Y = y;
				texQ_Z = z;
				texQ_W = w;
				_wglUniform4f(u_textureGenQ_V, x, y, z, w);
			}
		}
		if(fogColorSerial != GL11.fogColorSerial) {
			float r = GL11.fogColorR;
			float g = GL11.fogColorG;
			float b = GL11.fogColorB;
			float a = GL11.fogColorA;
			fogColorSerial = GL11.fogColorSerial;
			if(fogColorR != r || fogColorG != g || fogColorB != b || fogColorA != a) {
				fogColorR = r;
				fogColorG = g;
				fogColorB = b;
				fogColorA = a;
				_wglUniform4f(u_fogColor, r, g, b, a);
			}
		}
		if(fogCfgSerial != GL11.fogCfgSerial) {
			int fogModex = GL11.fogMode;
			float fogStarty = GL11.fogStart;
			float fogEndz = GL11.fogEnd - fogStarty;
			float fogDensityw = GL11.fogDensity;
			fogCfgSerial = GL11.fogCfgSerial;
			if(fogMode != fogModex || fogStart != fogStarty ||
					fogEnd != fogEndz || fogDensity != fogDensityw) {
				fogMode = fogModex;
				fogStart = fogStarty;
				fogEnd = fogEndz;
				fogDensity = fogDensityw;
				_wglUniform4f(u_fogParam, fogModex, fogStarty, fogEndz, fogDensityw);
			}
		}
		float limit = GL11.alphaThresh;
		if(alphaTestValue != limit) {
			alphaTestValue = limit;
			_wglUniform1f(u_alphaTestF, limit);
		}
		float[] matCopyBuffer = matBuffer;
		int i = GL11.matModelPointer;
		int j = GL11.matModelVSerial[i];
		if(matModelSerialCounter != j) {
			matModelSerialCounter = j;
			GL11.matModelV[i].store(matCopyBuffer);
			_wglUniformMat4fv(u_matrix_m, matCopyBuffer);
		}
		i = GL11.matProjPointer;
		j = GL11.matProjVSerial[i];
		if(matProjSerialCounter != j) {
			matProjSerialCounter = j;
			GL11.matProjV[i].store(matCopyBuffer);
			_wglUniformMat4fv(u_matrix_p, matCopyBuffer);
		}
		i = GL11.matTexPointer;
		j = GL11.matTexVSerial[i];
		if(matTexSerialCounter != j) {
			matTexSerialCounter = j;
			GL11.matTexV[i].store(matCopyBuffer);
			_wglUniformMat4fv(u_matrix_t, matCopyBuffer);
		}
		if(lightPos0Serial != GL11.lightPos0Serial) {
			lightPos0Serial = GL11.lightPos0Serial;
			Vector4f pos = GL11.lightPos0vec;
			if(!pos.equals(light0Pos)) {
				light0Pos.set(pos);
				_wglUniform3f(u_light0Pos, pos.x, pos.y, pos.z);
			}
		}
		if(lightPos1Serial != GL11.lightPos1Serial) {
			lightPos1Serial = GL11.lightPos1Serial;
			Vector4f pos = GL11.lightPos1vec;
			if(!pos.equals(light1Pos)) {
				light1Pos.set(pos);
				_wglUniform3f(u_light1Pos, pos.x, pos.y, pos.z);
			}
		}
	}

}
