package com.poly.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "cors", urlPatterns = "/*")
public class CorsFilter implements HttpFilter {
    private static final String[] allowedOrigins = {"http://localhost:3000", "http://localhost:5500",
            "http://localhost:5501", "http://127.0.0.1:3000", "http://127.0.0.1:5500", "http://127.0.0.1:5501"};

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        String requestOrigin = req.getHeader("Origin");
//		 if(isAllowedOrigin(requestOrigin)) {
//	            // Authorize the origin, all headers, and all methods
//			 resp.addHeader("Access-Control-Allow-Origin", requestOrigin);
//	            resp.addHeader("Access-Control-Allow-Headers", "*");
//	            resp.addHeader("Access-Control-Allow-Methods",
//	                    "GET, OPTIONS, HEAD, PUT, POST, DELETE");
//
//	            
//	            // CORS handshake (pre-flight request)
//	            if (req.getMethod().equals("OPTIONS")) {
//	                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
//	                return;
//	            }
//	        }
        resp.addHeader("Access-Control-Allow-Origin", requestOrigin);
        resp.addHeader("Access-Control-Allow-Headers", "*");
        resp.addHeader("Access-Control-Allow-Methods",
                "GET, OPTIONS, HEAD, PUT, POST, DELETE");
        // pass the request along the filter chain
        chain.doFilter(req, resp);
    }

    private boolean isAllowedOrigin(String origin) {
        for (int i = 0; i < allowedOrigins.length; i++) {
            if (allowedOrigins[i].equalsIgnoreCase(origin)) {
                return true;
            }
        }
        return false;
    }

}
