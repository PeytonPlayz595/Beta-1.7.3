package net.lax1dude.eaglercraft;

import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.PeytonPlayz585.awt.image.BufferedImage;
import net.minecraft.src.GLAllocation;

public class SpriteSheetTexture {

	public final String name;
	public final int iconIndex;
	public final int iconTileSize;
	private IntBuffer buffer = null;
	private int dataFrameCount = 0;
	private int[] frameSet = null;
	public int ticks = 0;

	private int[] mipmapOffsets = new int[5];

	public SpriteSheetTexture(String name, int iconIndex, int iconTileSize) {
		this.name = name;
		this.iconIndex = iconIndex;
		this.iconTileSize = iconTileSize;
		reloadData();
	}

	public void update() {
		ticks = (ticks + 1) % frameSet.length;
	}

	public IntBuffer grabFrame(int lvl) {
		int offWidth = 16 >> lvl;
		int step = offWidth * offWidth * iconTileSize * iconTileSize;
		int startIndex = mipmapOffsets[lvl] + step * frameSet[ticks];
		buffer.limit(startIndex + step);
		buffer.position(startIndex);
		return buffer;
	}

	public void reloadData() {
		ticks = 0;
		buffer = null;
		frameSet = null;

		byte[] imgBytes = GL11.EaglerAdapterImpl2.loadResourceBytes("/sprite_sheet/" + name + ".png");
		if(imgBytes == null) {
			return;
		}

		int dim = iconTileSize * iconTileSize * 256;
		BufferedImage img = GL11.EaglerAdapterImpl2.loadPNG(imgBytes);
		dataFrameCount = img.getData().length / dim;

		ArrayList<Integer> loadedFrameSet = new ArrayList();
		byte[] metaBytes = GL11.EaglerAdapterImpl2.loadResourceBytes("/sprite_sheet/" + name + ".txt");

		if(metaBytes == null) {
			for(int i = 0; i < dataFrameCount; ++i) {
				loadedFrameSet.add(i);
			}
		}else {
			String str = new String(metaBytes, StandardCharsets.UTF_8);
			String[] splitted = str.split("\\s*,\\s*");
			for(int i = 0; i < splitted.length; ++i) {
				String idxStr = splitted[i];
				int z = idxStr.indexOf('*');
				if(z != -1) {
					int idx = Integer.parseInt(idxStr.substring(0, z));
					int c = Integer.parseInt(idxStr.substring(z + 1));
					for(int j = 0; j < c; ++j) {
						loadedFrameSet.add(idx);
					}
				}else {
					loadedFrameSet.add(Integer.parseInt(idxStr));
				}
			}
		}

		frameSet = new int[loadedFrameSet.size()];
		for(int i = 0; i < frameSet.length; ++i) {
			frameSet[i] = loadedFrameSet.get(i).intValue();
		}

		buffer = GLAllocation.createDirectIntBuffer(
			16 * 16 * dataFrameCount * iconTileSize * iconTileSize +
			8 * 8 * dataFrameCount * iconTileSize * iconTileSize +
			4 * 4 * dataFrameCount * iconTileSize * iconTileSize +
			2 * 2 * dataFrameCount * iconTileSize * iconTileSize +
			dataFrameCount * iconTileSize * iconTileSize
		);

		int[] texData = swapRB(img.getData());
		mipmapOffsets[0] = 0;
		buffer.put(texData);
		mipmapOffsets[1] = buffer.position();
		texData = downscale(texData, 16 * iconTileSize, 16 * iconTileSize * dataFrameCount);
		buffer.put(texData);
		mipmapOffsets[2] = buffer.position();
		texData = downscale(texData, 8 * iconTileSize, 8 * iconTileSize * dataFrameCount);
		buffer.put(texData);
		mipmapOffsets[3] = buffer.position();
		texData = downscale(texData, 4 * iconTileSize, 4 * iconTileSize * dataFrameCount);
		buffer.put(texData);
		mipmapOffsets[4] = buffer.position();
		texData = downscale(texData, 2 * iconTileSize, 2 * iconTileSize * dataFrameCount);
		buffer.put(texData);

	}

	private static int[] downscale(int[] in, int sw, int sh) {
		int dw = sw / 2;
		int dh = sh / 2;
		int[] ret = new int[dw * dh];
		for(int y = 0; y < dh; ++y) {
			for(int x = 0; x < dw; ++x) {
				int a = in[((x * 2) + (y * 2) * sw)];
				int b = in[((x * 2 + 1) + (y * 2) * sw)];
				int c = in[((x * 2 + 1) + (y * 2 + 1) * sw)];
				int d = in[((x * 2) + (y * 2 + 1) * sw)];
				int b1= (((a >> 26) & 0x3F)) + 
						(((b >> 26) & 0x3F)) + 
						(((c >> 26) & 0x3F)) + 
						(((d >> 26) & 0x3F));
				int b2= (((a >> 18) & 0x3F)) + 
						(((b >> 18) & 0x3F)) + 
						(((c >> 18) & 0x3F)) + 
						(((d >> 18) & 0x3F));
				int b3= (((a >> 10) & 0x3F)) + 
						(((b >> 10) & 0x3F)) + 
						(((c >> 10) & 0x3F)) + 
						(((d >> 10) & 0x3F));
				int b4= (((a >> 2) & 0x3F)) + 
						(((b >> 2) & 0x3F)) + 
						(((c >> 2) & 0x3F)) + 
						(((d >> 2) & 0x3F));
				ret[y * dw + x] = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
			}
		}
		return ret;
	}

	private static int[] swapRB(int[] in) {
		int[] out = new int[in.length];
		for(int i = 0; i < in.length; ++i) {
			int r = (in[i] >> 16) & 0xFF;
			int b = in[i] & 0xFF;
			out[i] = (in[i] & 0xFF00FF00) | (b << 16) | r;
		}
		return out;
	}

}