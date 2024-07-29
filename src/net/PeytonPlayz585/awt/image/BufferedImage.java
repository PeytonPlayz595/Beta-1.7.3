package net.PeytonPlayz585.awt.image;

public class BufferedImage {

	private final int[] pixels;
	private final int width;
	private final int height;
	private final boolean isAlphaPremultiplied;
	
	public BufferedImage(int[] pixels, int width, int height, boolean alpha) {
		if(pixels.length != width*height) {
			throw new IllegalArgumentException("array size does not equal image size");
		}
		
		this.width = width;
		this.height = height;
		this.pixels = pixels;
		this.isAlphaPremultiplied = alpha;
	}
	
	public BufferedImage(int width, int height, int[] pixels, boolean alpha) {
		if(pixels.length != width*height) {
			throw new IllegalArgumentException("array size does not equal image size");
		}
		
		this.width = width;
		this.height = height;
		this.pixels = pixels;
		this.isAlphaPremultiplied = alpha;
	}
	
	public BufferedImage(int width, int height, boolean alpha) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		this.isAlphaPremultiplied = alpha;
	}
	
	public BufferedImage(int w, int h, int k) {
		this.width = w;
		this.height = h;
		this.isAlphaPremultiplied = true;
		this.pixels = new int[w * h];
	}

	public BufferedImage getSubImage(int x, int y, int pw, int ph) {
		int[] img = new int[pw * ph];
		for(int i = 0; i < ph; ++i) {
			System.arraycopy(pixels, (i + y) * this.width + x, img, i * pw, pw);
		}
		return new BufferedImage(pw, ph, img, isAlphaPremultiplied);
	}
	
	public void getRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize) {
        for (int y = 0; y < h; ++y) {
            System.arraycopy(pixels, offset + (y + startY) * scansize + startX, rgbArray, y * w, w);
        }
    }

	public void setRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize) {
		for (int y = 0; y < h; ++y) {
            System.arraycopy(rgbArray, offset + (y + startY) * scansize + startX, pixels, y * w, w);
        }
	}
	
	public boolean isAlphaPremultiplied() {
		return isAlphaPremultiplied;
	}
	
	public int[] getData() {
		return pixels;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}