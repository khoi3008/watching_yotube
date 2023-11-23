package com.poly.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.common.BaseResponse;
import com.poly.constatnt.Constant;
import com.poly.entity.User;
import com.poly.utils.XScope;

@WebFilter(filterName = "auth", urlPatterns = { "/api/admin/*", "/admin/*", "/api/share/*",
		"/api/account/change-password", "/api/favorite/*", "/api/update-user", "/api/user-data", "/favorite",
		"/profile" })
public class AuthFilter implements HttpFilter {
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		User us = XScope.getSession(Constant.USER);
		String uri = req.getRequestURI();
		if (us != null) {
			if (uri.startsWith("/asm/api/admin") && !us.isAdmin()) {
				ObjectMapper objectMapper = new ObjectMapper();
				BaseResponse baseResponse = new BaseResponse("You don't have permission.Please login with admin accout",
						false);
				objectMapper.writeValue(resp.getOutputStream(), baseResponse);
			} else if (uri.startsWith("/asm/admin") && !us.isAdmin()) {
				resp.sendRedirect("/asm/sign-in");
			}else {
				chain.doFilter(req, resp);
			}
		} else {
			if (uri.startsWith("/asm/api")) {
				ObjectMapper objectMapper = new ObjectMapper();
				BaseResponse baseResponse = new BaseResponse("Please login before using this feature", false);
				objectMapper.writeValue(resp.getOutputStream(), baseResponse);
			}else {
				resp.sendRedirect("/asm/sign-in");
			}
		}

	}

}
