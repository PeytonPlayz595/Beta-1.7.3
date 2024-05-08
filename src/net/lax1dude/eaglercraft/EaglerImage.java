package net.lax1dude.eaglercraft;

public class EaglerImage {

	public final int[] data;
	public final int w;
	public final int h;
	public final boolean alpha;

	public EaglerImage(int width, int height, int[] pixels, boolean alpha) {
		this.w = width;
		this.h = height;
		this.data = pixels;
		this.alpha = alpha;
	}
	
	public EaglerImage(int width, int height, boolean alpha) {
		this.w = width;
		this.h = height;
		this.data = new int[width * height];
		this.alpha = alpha;
	}
	
	public EaglerImage getSubImage(int x, int y, int pw, int ph) {
		int[] img = new int[pw * ph];
		for(int i = 0; i < ph; ++i) {
			System.arraycopy(data, (i + y) * this.w + x, img, i * pw, pw);
		}
		return new EaglerImage(pw, ph, img, alpha);
	}
	
	public int[] data() {
		return this.data;
	}
	
	public int[] getRGB(int startX, int startY, int w, int h,  int[] rgbArray, int offset, int scansize) {
		if (startX < 0 || startY < 0 || w <= 0 || h <= 0 ||
				startX + w > this.w || startY + h > this.h ||
				rgbArray.length < offset + w * h) {
				throw new IllegalArgumentException("Suck my dick nigga");
		}

		for (int y = startY; y < startY + h; y++) {
			for (int x = startX; x < startX + w; x++) {
				int imageDataIndex = y * this.w + x;
				int argb = data[imageDataIndex];
				int alpha = (argb >> 24) & 0xff;
				int red = (argb >> 16) & 0xff;
				int green = (argb >> 8) & 0xff;
				int blue = argb & 0xff;
				int rgb = (alpha << 24) | (red << 16) | (green << 8) | blue;
      
				rgbArray[offset + (y - startY) * scansize + (x - startX)] = rgb;
			}
		}

		return rgbArray;
	}
}