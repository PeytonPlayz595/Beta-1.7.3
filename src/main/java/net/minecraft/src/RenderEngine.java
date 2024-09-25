package net.minecraft.src;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.PeytonPlayz585.awt.image.BufferedImage;
import net.PeytonPlayz585.opengl.GL11;
import net.PeytonPlayz585.profile.Profile;
import net.PeytonPlayz585.textures.TextureLocation;
import net.lax1dude.eaglercraft.SpriteSheetTexture;
import net.minecraft.client.Minecraft;

public class RenderEngine {
	public static boolean useMipmaps = false;
	private HashMap textureMap = new HashMap();
	private HashMap field_28151_c = new HashMap();
	private HashMap textureNameToImageMap = new HashMap();
	private IntBuffer singleIntBuffer = GLAllocation.createDirectIntBuffer(1);
	private List textureList = new ArrayList();
	private GameSettings options;
	private boolean clampTexture = false;
	private boolean blurTexture = false;
	private List<SpriteSheetTexture> textureSpriteList = new ArrayList();
	private TexturePackList texturePack;
	private BufferedImage missingTextureImage;
	private ByteBuffer imageDataB1 = GLAllocation.createDirectByteBuffer(4194304 * 2);
	private ByteBuffer imageDataB2 = GLAllocation.createDirectByteBuffer(4194304 * 2);
	private int textureWidth;

	public RenderEngine(TexturePackList var1, GameSettings var2) {
		this.options = var2;
		this.texturePack = var1;
		int[] missingTexture = new int[256];
		for(int i = 0; i < 256; ++i) {
			missingTexture[i] = ((i / 16 + (i % 16)) % 2 == 0) ? 0xffff00ff : 0xff000000;
		}
		this.missingTextureImage = new BufferedImage(16, 16, missingTexture, true);
		
		try {
			BufferedImage img = this.readTextureImage(this.texturePack.selectedTexturePack.getResourceAsStream("/terrain.png"));
			int width = img.getWidth();
			int textureWidth = width / 16;
			this.textureWidth = textureWidth;
		} catch (IOException e) {
			System.err.println("Unable to read terrain.png, using default 16x16 texture animations");
			textureWidth = 16;
		}
	}
	
	public int getTexture(String var1) {
		TexturePackBase var2 = this.texturePack.selectedTexturePack;
		Integer var3 = (Integer)this.textureMap.get(var1);
		if(var3 != null) {
			return var3.intValue();
		} else {
			try {
				this.singleIntBuffer.clear();
				GLAllocation.generateTextureNames(this.singleIntBuffer);
				int var6 = this.singleIntBuffer.get(0);
				if(var1.startsWith("%clamp%")) {
					this.clampTexture = true;
					this.setupTexture(this.readTextureImage(var2.getResourceAsStream(var1.substring(7))), var6);
					this.clampTexture = false;
				} else if(var1.startsWith("%blur%")) {
					this.blurTexture = true;
					this.setupTexture(this.readTextureImage(var2.getResourceAsStream(var1.substring(6))), var6);
					this.blurTexture = false;
				} else {
					if(var1.equals("/terrain.png")) {
						useMipmaps = true;
					}
					InputStream var7 = var2.getResourceAsStream(var1);
					if(var7 == null) {
						this.setupTexture(this.missingTextureImage, var6);
					} else {
						this.setupTexture(this.readTextureImage(var7), var6);
					}
					useMipmaps = false;
				}

				this.textureMap.put(var1, Integer.valueOf(var6));
				return var6;
			} catch (IOException var5) {
				var5.printStackTrace();
				GLAllocation.generateTextureNames(this.singleIntBuffer);
				int var4 = this.singleIntBuffer.get(0);
				this.setupTexture(this.missingTextureImage, var4);
				this.textureMap.put(var1, Integer.valueOf(var4));
				return var4;
			}
		}
	}
	
	public int allocateAndSetupTexture(BufferedImage var1) {
		this.singleIntBuffer.clear();
		GLAllocation.generateTextureNames(this.singleIntBuffer);
		int var2 = this.singleIntBuffer.get(0);
		this.setupTexture(var1, var2);
		this.textureNameToImageMap.put(Integer.valueOf(var2), var1);
		return var2;
	}
	
	public int allocateAndSetupTexture(byte[] data, int w, int h) {
		int i = GL11.glGenTextures();
		bindTexture(i);
		GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, 10241 /* GL_TEXTURE_MIN_FILTER */, 9729 /* GL_LINEAR */);
		GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, 10240 /* GL_TEXTURE_MAG_FILTER */, 9728 /* GL_NEAREST */);
		GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, 10242 /* GL_TEXTURE_WRAP_S */, 10497 /* GL_REPEAT */);
		GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, 10243 /* GL_TEXTURE_WRAP_T */, 10497 /* GL_REPEAT */);
		imageDataB1.clear();
		imageDataB1.put(data);
		imageDataB1.position(0).limit(data.length);
		GL11.glTexImage2D(3553 /* GL_TEXTURE_2D */, 0, 6408 /* GL_RGBA */, w, h, 0, 6408 /* GL_RGBA */,
						5121 /* GL_UNSIGNED_BYTE */, imageDataB1);
		return i;
	}

	public void setupTexture(BufferedImage var1, int var2) {
		bindTexture(var2);
		if (useMipmaps) {
			GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, 10241 /* GL_TEXTURE_MIN_FILTER */, GL11.GL_NEAREST_MIPMAP_LINEAR);
			GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, 10240 /* GL_TEXTURE_MAG_FILTER */, GL11.GL_NEAREST /* GL_LINEAR */);
			GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, GL11.GL_TEXTURE_MAX_LEVEL, 4);
		} else {
			GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, 10241 /* GL_TEXTURE_MIN_FILTER */, 9728 /* GL_NEAREST */);
			GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, 10240 /* GL_TEXTURE_MAG_FILTER */, 9728 /* GL_NEAREST */);
		}
		if (blurTexture) {
			GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, 10241 /* GL_TEXTURE_MIN_FILTER */, 9729 /* GL_LINEAR */);
			GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, 10240 /* GL_TEXTURE_MAG_FILTER */, 9729 /* GL_LINEAR */);
		}
		if (clampTexture) {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
		} else {
			GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, 10242 /* GL_TEXTURE_WRAP_S */, 10497 /* GL_REPEAT */);
			GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, 10243 /* GL_TEXTURE_WRAP_T */, 10497 /* GL_REPEAT */);
		}
		int j = var1.getWidth();
		int k = var1.getHeight();
		int ai[] = var1.getData();
		byte abyte0[] = new byte[j * k * 4];
		for (int l = 0; l < ai.length; l++) {
			int j1 = ai[l] >> 24 & 0xff;
			int l1 = ai[l] >> 16 & 0xff;
			int j2 = ai[l] >> 8 & 0xff;
			int l2 = ai[l] >> 0 & 0xff;
			if (options != null && options.anaglyph) {
				int j3 = (l1 * 30 + j2 * 59 + l2 * 11) / 100;
				int l3 = (l1 * 30 + j2 * 70) / 100;
				int j4 = (l1 * 30 + l2 * 70) / 100;
				l1 = j3;
				j2 = l3;
				l2 = j4;
			}
			abyte0[l * 4 + 0] = (byte) l1;
			abyte0[l * 4 + 1] = (byte) j2;
			abyte0[l * 4 + 2] = (byte) l2;
			abyte0[l * 4 + 3] = (byte) j1;
		}
		imageDataB1.clear();
		imageDataB1.put(abyte0);
		imageDataB1.position(0).limit(abyte0.length);
		GL11.glTexImage2D(3553 /* GL_TEXTURE_2D */, 0, 6408 /* GL_RGBA */, j, k, 0, 6408 /* GL_RGBA */,
				5121 /* GL_UNSIGNED_BYTE */, imageDataB1);
		if (useMipmaps) {
			for (int i1 = 1; i1 <= 4; i1++) {
				int k1 = j >> i1 - 1;
				int i2 = j >> i1;
				int k2 = k >> i1;
				imageDataB2.clear();
				for (int i3 = 0; i3 < i2; i3++) {
					for (int k3 = 0; k3 < k2; k3++) {
						int i4 = imageDataB1.getInt((i3 * 2 + 0 + (k3 * 2 + 0) * k1) * 4);
						int k4 = imageDataB1.getInt((i3 * 2 + 1 + (k3 * 2 + 0) * k1) * 4);
						int l4 = imageDataB1.getInt((i3 * 2 + 1 + (k3 * 2 + 1) * k1) * 4);
						int i5 = imageDataB1.getInt((i3 * 2 + 0 + (k3 * 2 + 1) * k1) * 4);
						int j5 = averageColor(averageColor(i4, k4), averageColor(l4, i5));
						imageDataB2.putInt((i3 + k3 * i2) * 4, j5);
					}

				}
				
				GL11.glTexImage2D(3553 /* GL_TEXTURE_2D */, i1, 6408 /* GL_RGBA */, i2, k2, 0, 6408 /* GL_RGBA */,
						5121 /* GL_UNSIGNED_BYTE */, imageDataB2);
				ByteBuffer tmp = imageDataB1;
				imageDataB1 = imageDataB2;
				imageDataB2 = tmp;
			}

		}
	}
	
	public void func_28150_a(int[] var1, int var2, int var3, int var4) {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, var4);
		if(useMipmaps) {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST_MIPMAP_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		} else {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		}

		if(this.blurTexture) {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		}

		if(this.clampTexture) {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
		} else {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		}

		byte[] var5 = new byte[var2 * var3 * 4];

		for(int var6 = 0; var6 < var1.length; ++var6) {
			int var7 = var1[var6] >> 24 & 255;
			int var8 = var1[var6] >> 16 & 255;
			int var9 = var1[var6] >> 8 & 255;
			int var10 = var1[var6] & 255;
			if(this.options != null && this.options.anaglyph) {
				int var11 = (var8 * 30 + var9 * 59 + var10 * 11) / 100;
				int var12 = (var8 * 30 + var9 * 70) / 100;
				int var13 = (var8 * 30 + var10 * 70) / 100;
				var8 = var11;
				var9 = var12;
				var10 = var13;
			}

			var5[var6 * 4 + 0] = (byte)var8;
			var5[var6 * 4 + 1] = (byte)var9;
			var5[var6 * 4 + 2] = (byte)var10;
			var5[var6 * 4 + 3] = (byte)var7;
		}

		this.imageDataB1.clear();
		this.imageDataB1.put(var5);
		this.imageDataB1.position(0).limit(var5.length);
		GL11.glTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, var2, var3, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer)this.imageDataB1);
	}
	
	public int[] func_28149_a(String var1) {
		TexturePackBase var2 = this.texturePack.selectedTexturePack;
		int[] var3 = (int[])this.field_28151_c.get(var1);
		if(var3 != null) {
			return var3;
		} else {
			try {
				Object var6 = null;
				if(var1.startsWith("%clamp%")) {
					this.clampTexture = true;
					var3 = this.func_28148_b(this.readTextureImage(var2.getResourceAsStream(var1.substring(7))));
					this.clampTexture = false;
				} else if(var1.startsWith("%blur%")) {
					this.blurTexture = true;
					var3 = this.func_28148_b(this.readTextureImage(var2.getResourceAsStream(var1.substring(6))));
					this.blurTexture = false;
				} else {
					InputStream var7 = var2.getResourceAsStream(var1);
					if(var7 == null) {
						var3 = this.func_28148_b(this.missingTextureImage);
					} else {
						var3 = this.func_28148_b(this.readTextureImage(var7));
					}
				}

				this.field_28151_c.put(var1, var3);
				return var3;
			} catch (IOException var5) {
				var5.printStackTrace();
				int[] var4 = this.func_28148_b(this.missingTextureImage);
				this.field_28151_c.put(var1, var4);
				return var4;
			}
		}
	}
	
	private int[] func_28148_b(BufferedImage var1) {
		int var2 = var1.getWidth();
		int var3 = var1.getHeight();
		int[] var4 = new int[var2 * var3];
		var1.getRGB(0, 0, var2, var3, var4, 0, var2);
		return var4;
	}

	public void deleteTexture(int var1) {
		GL11.glDeleteTextures(var1);
	}
	
	public int getTextureForDownloadableImage(String s, String s1) {
		if(Minecraft.getMinecraft().theWorld.multiplayerWorld) {
			return getTexture("/mob/char.png");
		} else {
			return Profile.defaultOptionsTextures[Profile.presetSkinId].getTexturePointer();
		}
	}

	public void registerTextureFX(TextureFX var1) {
		this.textureList.add(var1);
		var1.onTick();
	}

	public void updateDynamicTextures() {
		for (int i = 0; i < textureList.size(); i++) {
			TextureFX texturefx = (TextureFX) textureList.get(i);
			texturefx.anaglyphEnabled = options.anaglyph;
			texturefx.onTick();
			texturefx.bindImage(this);
			int tileSize = 16 * 16 * 4;
			imageDataB1.clear();
			imageDataB1.put(texturefx.imageData);
			imageDataB1.position(0).limit(tileSize);
			GL11.glTexSubImage2D(3553 /* GL_TEXTURE_2D */, 0, (texturefx.iconIndex % this.textureWidth) * 16, (texturefx.iconIndex / this.textureWidth) * 16, 16, 16,
					6408 /* GL_RGBA */, 5121 /* GL_UNSIGNED_BYTE */, imageDataB1);
		}
		
		TextureFX.terrainTexture.bindTexture();
		for(int i = 0, l = textureSpriteList.size(); i < l; ++i) {
			SpriteSheetTexture sp = textureSpriteList.get(i);
			sp.update();
			int w = 16;
			for(int j = 0; j < 5; ++j) {
				GL11.glTexSubImage2D(3553 /* GL_TEXTURE_2D */, j, (sp.iconIndex % this.textureWidth) * w, (sp.iconIndex / this.textureWidth) * w, w * sp.iconTileSize, w * sp.iconTileSize,
						6408 /* GL_RGBA */, 5121 /* GL_UNSIGNED_BYTE */, sp.grabFrame(j));
				w /= 2;
			}
		}
	}
	
	public void registerSpriteSheet(String name, int iconIndex, int iconTileSize) {
		textureSpriteList.add(new SpriteSheetTexture(name, iconIndex, iconTileSize));
	}

	private int averageColor(int var1, int var2) {
		int var3 = (var1 & -16777216) >> 24 & 255;
		int var4 = (var2 & -16777216) >> 24 & 255;
		return (var3 + var4 >> 1 << 24) + ((var1 & 16711422) + (var2 & 16711422) >> 1);
	}

	public void refreshTextures() {
		TextureLocation.freeTextures();
		TexturePackBase var1 = this.texturePack.selectedTexturePack;
		int i;
		BufferedImage bufferedimage;
		for (Iterator iterator = textureNameToImageMap.keySet().iterator(); iterator
				.hasNext(); setupTexture(bufferedimage, i)) {
			i = ((Integer) iterator.next()).intValue();
			bufferedimage = (BufferedImage) textureNameToImageMap.get(Integer.valueOf(i));
		}

		for (Iterator iterator2 = textureMap.keySet().iterator(); iterator2.hasNext();) {
			String s = (String) iterator2.next();
			try {
				BufferedImage bufferedimage1;
				if (s.startsWith("%clamp%")) {
					clampTexture = true;
					bufferedimage1 = readTextureImage(var1.getResourceAsStream(s.substring(7)));
				} else if (s.startsWith("%blur%")) {
					blurTexture = true;
					bufferedimage1 = readTextureImage(var1.getResourceAsStream(s.substring(6)));
				} else {
					bufferedimage1 = readTextureImage(var1.getResourceAsStream(s));
				}
				int j = ((Integer) textureMap.get(s)).intValue();
				setupTexture(bufferedimage1, j);
				blurTexture = false;
				clampTexture = false;
			} catch (IOException ioexception) {
				ioexception.printStackTrace();
			}
		}
		
		for(int j = 0, l = textureSpriteList.size(); j < l; ++j) {
			textureSpriteList.get(j).reloadData();
		}
		
		try {
			BufferedImage img = this.readTextureImage(this.texturePack.selectedTexturePack.getResourceAsStream("/terrain.png"));
			int width = img.getWidth();
			int textureWidth = width / 16;
			this.textureWidth = textureWidth;
		} catch (IOException e) {
			System.err.println("Unable to read terrain.png, using default 16x16 texture animations");
			textureWidth = 16;
		}
		
	}

	private BufferedImage readTextureImage(InputStream var1) throws IOException {
		return GL11.EaglerAdapterImpl2.loadPNG(((ByteArrayInputStream)var1).readAllBytes());
	}

	public void bindTexture(int i) {
		if (i < 0) {
			return;
		} else {
			GL11.glBindTexture(3553, i);
			return;
		}
	}
}
