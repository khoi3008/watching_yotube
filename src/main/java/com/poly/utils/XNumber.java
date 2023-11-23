package com.poly.utils;

public class XNumber {
	public static int roundUp(double x) {
		double y = Math.ceil(x);
		return y >= x ? (int) y : (int) (y + 1);
	}

}
