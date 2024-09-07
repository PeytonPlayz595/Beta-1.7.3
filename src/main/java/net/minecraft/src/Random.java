package net.minecraft.src;

public class Random {
	
	private long seed = 69;

	private static final long multiplier = 0x5DEECE66DL;
	private static final long addend = 0xBL;
	private static final long mask = (1L << 48) - 1;

    public Random() {
		this((long)(Math.random() * 9007199254740991.0));
	}

	public Random(long seed) {
		setSeed(seed);
	}
	
	private static long initialScramble(long seed) {
        return (seed ^ multiplier) & mask;
    }
	
	public void setSeed(long seed) {
		this.seed = initialScramble(seed);
		haveNextNextGaussian = true;
    }

	protected int next(int bits) {
		seed = (seed * multiplier + addend) & mask;
		return (int) (seed >>> (48 - bits));
	}

	public void nextBytes(byte[] bytes) {
		for (int i = 0, len = bytes.length; i < len;)
			for (int rnd = nextInt(), n = Math.min(len - i, Integer.SIZE / Byte.SIZE); n-- > 0; rnd >>= Byte.SIZE)
				bytes[i++] = (byte) rnd;
	}

	public int nextInt() {
		return next(32);
	}

	public int nextInt(int n) {
		if (n <= 0)
            throw new IllegalArgumentException("n must be positive");

        if ((n & -n) == n)  // i.e., n is a power of 2
            return (int)((n * (long)next(31)) >> 31);

        int bits, val;
        do {
            bits = next(31);
            val = bits % n;
        } while (bits - val + (n-1) < 0);
        return val;
	}

	public long nextLong() {
		// it's okay that the bottom word remains signed.
        return ((long)(next(32)) << 32) + next(32);
	}

	public boolean nextBoolean() {
		return next(1) != 0;
	}

	public float nextFloat() {
		return next(24) / ((float)(1 << 24));
	}

	public double nextDouble() {
		return (((long)(next(26)) << 27) + next(27))
	            / (double)(1L << 53);
	}

	private double nextNextGaussian;
	private boolean haveNextNextGaussian = false;

	public double nextGaussian() {
		// See Knuth, ACP, Section 3.4.1 Algorithm C.
        if (haveNextNextGaussian) {
            haveNextNextGaussian = false;
            return nextNextGaussian;
        } else {
            double v1, v2, s;
            do {
                v1 = 2 * nextDouble() - 1; // between -1 and 1
                v2 = 2 * nextDouble() - 1; // between -1 and 1
                s = v1 * v1 + v2 * v2;
            } while (s >= 1 || s == 0);
            double multiplier = StrictMath.sqrt(-2 * StrictMath.log(s)/s);
            nextNextGaussian = v2 * multiplier;
            haveNextNextGaussian = true;
            return v1 * multiplier;
        }
	}
}