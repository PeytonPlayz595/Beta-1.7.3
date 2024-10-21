package net.minecraft.src;

import java.nio.ByteOrder;

import net.PeytonPlayz585.opengl.GL11;
import net.lax1dude.eaglercraft.internal.buffer.ByteBuffer;
import net.lax1dude.eaglercraft.internal.buffer.IntBuffer;

public class Tessellator {
	private ByteBuffer byteBuffer;
	private IntBuffer intBuffer;
	private int[] rawBuffer;
	private int vertexCount = 0;
	private double textureU;
	private double textureV;
	private int color;
	private boolean hasColor = false;
	private boolean hasTexture = false;
	private boolean hasNormals = false;
	private int rawBufferIndex = 0;
	private int addedVertices = 0;
	private boolean isColorDisabled = false;
	private int drawMode;
	private double xOffset;
	private double yOffset;
	private double zOffset;
	private int normal;
	public static final Tessellator instance = new Tessellator(2097152);
	private boolean isDrawing = false;
	private IntBuffer vertexBuffers;
	private int vboIndex = 0;
	private int vboCount = 10;
	private int bufferSize;

	private Tessellator(int var1) {
		this.bufferSize = var1;
		this.byteBuffer = GLAllocation.createDirectByteBuffer(var1 * 4);
		this.intBuffer = this.byteBuffer.asIntBuffer();
		this.rawBuffer = new int[var1];
	}

	public void draw() {
		if(!this.isDrawing) {
			throw new IllegalStateException("Not tesselating!");
		} else {
			this.isDrawing = false;
			if(this.vertexCount > 0) {
				IntBuffer upload = null;
				this.intBuffer.clear();
				this.intBuffer.put(rawBuffer, 0, this.rawBufferIndex);
				this.intBuffer.flip();
				upload = this.intBuffer;
				
				if(this.hasTexture) {
					GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
				}

				if(this.hasColor) {
					GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
				}

				if(this.hasNormals) {
					GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
				}

				GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
				GL11.glDrawArrays(this.drawMode, GL11.GL_POINTS, this.vertexCount, upload);

				GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
				if(this.hasTexture) {
					GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
				}

				if(this.hasColor) {
					GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
				}

				if(this.hasNormals) {
					GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
				}
			}

			this.reset();
		}
	}

	private void reset() {
		this.vertexCount = 0;
		this.rawBufferIndex = 0;
		this.addedVertices = 0;
	}

	public void startDrawingQuads() {
		this.startDrawing(7);
	}

	public void startDrawing(int var1) {
		if(this.isDrawing) {
			throw new IllegalStateException("Already tesselating!");
		} else {
			this.isDrawing = true;
			this.reset();
			this.drawMode = var1;
			this.hasNormals = false;
			this.hasColor = false;
			this.hasTexture = false;
			this.isColorDisabled = false;
		}
	}

	public void setTextureUV(double var1, double var3) {
		this.hasTexture = true;
		this.textureU = (float) var1;
		this.textureV = (float) var3;
	}

	public void setColorOpaque_F(float var1, float var2, float var3) {
		this.setColorOpaque((int)(var1 * 255.0F), (int)(var2 * 255.0F), (int)(var3 * 255.0F));
	}

	public void setColorRGBA_F(float var1, float var2, float var3, float var4) {
		this.setColorRGBA((int)(var1 * 255.0F), (int)(var2 * 255.0F), (int)(var3 * 255.0F), (int)(var4 * 255.0F));
	}

	public void setColorOpaque(int var1, int var2, int var3) {
		this.setColorRGBA(var1, var2, var3, 255);
	}

	public void setColorRGBA(int var1, int var2, int var3, int var4) {
		if(!this.isColorDisabled) {
			if(var1 > 255) {
				var1 = 255;
			}

			if(var2 > 255) {
				var2 = 255;
			}

			if(var3 > 255) {
				var3 = 255;
			}

			if(var4 > 255) {
				var4 = 255;
			}

			if(var1 < 0) {
				var1 = 0;
			}

			if(var2 < 0) {
				var2 = 0;
			}

			if(var3 < 0) {
				var3 = 0;
			}

			if(var4 < 0) {
				var4 = 0;
			}

			this.hasColor = true;
			if(ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
				this.color = var4 << 24 | var3 << 16 | var2 << 8 | var1;
			} else {
				this.color = var1 << 24 | var2 << 16 | var3 << 8 | var4;
			}

		}
	}

	public void addVertexWithUV(double var1, double var3, double var5, double var7, double var9) {
		this.setTextureUV(var7, var9);
		this.addVertex(var1, var3, var5);
	}

	public void addVertex(double var1, double var3, double var5) {
		if(this.addedVertices > 65534) return;
		++this.addedVertices;
		
		this.rawBuffer[this.rawBufferIndex + 0] = Float.floatToRawIntBits((float) (var1 + this.xOffset));
		this.rawBuffer[this.rawBufferIndex + 1] = Float.floatToRawIntBits((float) (var3 + this.yOffset));
		this.rawBuffer[this.rawBufferIndex + 2] = Float.floatToRawIntBits((float) (var5 + this.zOffset));

		if (this.hasTexture) {
			this.rawBuffer[this.rawBufferIndex + 3] = Float.floatToRawIntBits((float) this.textureU);
			this.rawBuffer[this.rawBufferIndex + 4] = Float.floatToRawIntBits((float) this.textureV);
		}

		if (this.hasColor) {
			this.rawBuffer[this.rawBufferIndex + 5] = this.color;
		}

		if (this.hasNormals) {
			this.rawBuffer[this.rawBufferIndex + 6] = this.normal;
		}

		this.rawBufferIndex += 8;
		++this.vertexCount;
		if(this.vertexCount % 4 == 0 && this.rawBufferIndex >= this.bufferSize - 32) {
			this.draw();
			this.isDrawing = true;
		}
	}

	public void setColorOpaque_I(int var1) {
		int var2 = var1 >>> 16 & 255;
		int var3 = var1 >>> 8 & 255;
		int var4 = var1 & 255;
		this.setColorOpaque(var2, var3, var4);
	}

	public void setColorRGBA_I(int var1, int var2) {
		int var3 = var1 >>> 16 & 255;
		int var4 = var1 >>> 8 & 255;
		int var5 = var1 & 255;
		this.setColorRGBA(var3, var4, var5, var2);
	}

	public void disableColor() {
		this.isColorDisabled = true;
	}

	public void setNormal(float var1, float var2, float var3) {
		if(!this.isDrawing) {
			System.out.println("But..");
		}

		this.hasNormals = true;
		int var4 = (int)((var1) * 127.0F) + 127;
		int var5 = (int)((var2) * 127.0F) + 127;
		int var6 = (int)((var3) * 127.0F) + 127;
		this.normal = var4 & 255 | (var5 & 255) << 8 | (var6 & 255) << 16;
	}

	public void setTranslationD(double var1, double var3, double var5) {
		this.xOffset = var1;
		this.yOffset = var3;
		this.zOffset = var5;
	}

	public void setTranslationF(float var1, float var2, float var3) {
		this.xOffset += (double)var1;
		this.yOffset += (double)var2;
		this.zOffset += (double)var3;
	}
}