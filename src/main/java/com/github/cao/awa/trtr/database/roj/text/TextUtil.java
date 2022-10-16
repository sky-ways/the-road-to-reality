package com.github.cao.awa.trtr.database.roj.text;

import com.github.cao.awa.trtr.database.roj.collect.*;

/**
 * @author Roj234
 * @since 2021/6/19 0:14
 */
public class TextUtil {
	public static String toFixed(double d, int accurate) {
		String db = Double.toString(d);
		if (db.length() > accurate) {
			int dot = db.lastIndexOf('.');
			if (dot != -1) {
				db = db.substring(0, Math.min(dot + 1 + accurate, db.length()));
			}
		}
		return db;
	}

	// 8bits: ⣿ 每个点代表一位
	public static final int BRAILLN_CODE = 10240;

	public final static byte[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
										 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	public static final MyBitSet ASCII_CHARACTERS = MyBitSet.from(digits).addAll("*@-_+./");

	public static int lastMatches(CharSequence a, int aIndex, CharSequence b, int bIndex, int max) {
		int min = Math.min(Math.min(a.length() - aIndex, b.length() - bIndex), max);
		int i = 0;
		for (; i < min; i++) {
			if (a.charAt(aIndex++) != b.charAt(bIndex++)) break;
		}

		return i;
	}

	public static boolean regionMatches(CharSequence a, int aIndex, CharSequence b, int bIndex) {
		int min = Math.min(a.length() - aIndex, b.length() - bIndex);
		for (; min > 0; min--) {
			if (a.charAt(aIndex++) != b.charAt(bIndex++)) return false;
		}

		return true;
	}

}
