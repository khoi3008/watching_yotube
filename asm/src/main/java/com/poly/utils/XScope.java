package com.poly.utils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class XScope {
	public static HttpServletRequest request() {
		return RRSharer.request();
	}

	public static HttpSession session() {
		return request().getSession();
	}

	public static ServletContext application() {
		return request().getServletContext();
	}

	public static void setRequest(String name, Object value) {
		request().setAttribute(name, value);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getRequest(String name) {
		return (T) request().getAttribute(name);
	}

	public static void removeRequest(String name) {
		request().removeAttribute(name);
	}

	public static void setSession(String name, Object value) {
		session().setAttribute(name, value);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getSession(String name) {
		return (T) session().getAttribute(name);
	}

	public static void removeSession(String name) {
		session().removeAttribute(name);
	}

	public static void setApplication(String name, Object value) {
		application().setAttribute(name, value);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getApplication(String name) {
		return (T) application().getAttribute(name);
	}

	public static void removeApplication(String name) {
		application().removeAttribute(name);
	}

}

