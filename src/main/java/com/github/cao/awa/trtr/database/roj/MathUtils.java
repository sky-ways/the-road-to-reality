package com.github.cao.awa.trtr.database.roj;

/**
 * Math utilities.
 */
public abstract class MathUtils {
	public static int getMin2PowerOf(int x) {
		if (x >= 1073741824) return 1073741824;
		x--;
		x |= x >>> 1;
		x |= x >>> 2;
		x |= x >>> 4;
		x |= x >>> 8;
		x |= x >>> 16;
		return (x < 0) ? 1 : x + 1;
	}
}