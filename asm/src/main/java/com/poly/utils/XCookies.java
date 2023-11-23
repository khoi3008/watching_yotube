package com.poly.utils;

import jakarta.servlet.http.Cookie;

public class XCookies {
	public static void add(String name, String value, int hours) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(hours * 60 * 60);
		cookie.setPath("/");
		RRSharer.response().addCookie(cookie);
	}

	public static String get(String name, String defaultValue) {
		Cookie[] cookies = RRSharer.request().getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase(name)) {
					return cookie.getValue();
				}
			}
		}
		return defaultValue;
	}
}
