package de.jrk.nevosim2.util;

import java.util.Random;

public class Util {
	public static Random random = new Random();
	
	
	/**
	 * Stores the values of a in b.
	 * The arrays must be the same length.
	 * @param a
	 * @param b
	 */
	public static void storeIn(double[] a, double[] b) {
		if (a.length != b.length) {
			throw new IllegalArgumentException("the arrays must be the same length!");
		}
		for (int i = 0; i < a.length; i++) {
			b[i] = a[i];
		}
	}
}
