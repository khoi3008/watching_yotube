package com.poly.filter;

import com.poly.constatnt.Constant;
import com.poly.entity.User;
import com.poly.utils.RRSharer;
import com.poly.utils.XScope;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "app", urlPatterns = "/*", dispatcherTypes = DispatcherType.REQUEST)
public class AppFilter implements HttpFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		RRSharer.add(req, resp);
		User us = XScope.getSession(Constant.USER);
		if(us!=null) {
			req.setAttribute("isAdmin", us.isAdmin());
			req.setAttribute("isLogin", true);
		}else {
			req.setAttribute("isAdmin", false);
			req.setAttribute("isLogin", false);
		}
		chain.doFilter(req, resp);
	}

}
