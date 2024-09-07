package net.minecraft.src;

public class MathHelper {
	private static float[] SIN_TABLE = new float[4096];

	public static final float sin(float var0) {
		return SIN_TABLE[(int)(var0 * 651.8986F) & 4095];
	}

	public static final float cos(float var0) {
		return SIN_TABLE[(int)((var0 + ((float)Math.PI / 2F)) * 651.8986F) & 4095]; 
	}

	public static final float sqrt_float(float var0) {
		return (float)Math.sqrt((double)var0);
	}

	public static final float sqrt_double(double var0) {
		return (float)Math.sqrt(var0);
	}

	public static int floor_float(float var0) {
		int var1 = (int)var0;
		return var0 < (float)var1 ? var1 - 1 : var1;
	}

	public static int floor_double(double var0) {
		int var2 = (int)var0;
		return var0 < (double)var2 ? var2 - 1 : var2;
	}

	public static float abs(float var0) {
		return var0 >= 0.0F ? var0 : -var0;
	}

	public static double abs_max(double var0, double var2) {
		if(var0 < 0.0D) {
			var0 = -var0;
		}

		if(var2 < 0.0D) {
			var2 = -var2;
		}

		return var0 > var2 ? var0 : var2;
	}

	public static int bucketInt(int var0, int var1) {
		return var0 < 0 ? -((-var0 - 1) / var1) - 1 : var0 / var1;
	}

	public static boolean stringNullOrLengthZero(String var0) {
		return var0 == null || var0.length() == 0;
	}

	static {
		for (int j = 0; j < 4096; ++j) {
			SIN_TABLE[j] = (float)Math.sin((double)(((float)j + 0.5F) / 4096.0F * ((float)Math.PI * 2F)));
		}
		
		for (int l = 0; l < 360; l += 90) {
			SIN_TABLE[(int)((float)l * 11.377778F) & 4095] = (float)Math.sin((double)((float)l * 0.017453292F));
		}
	}
}
