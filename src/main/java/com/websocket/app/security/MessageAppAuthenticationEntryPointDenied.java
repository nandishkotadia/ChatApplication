package com.websocket.app.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.websocket.app.util.CookieUtility;

@Component
public class MessageAppAuthenticationEntryPointDenied implements AuthenticationEntryPoint {

	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.addCookie(CookieUtility.addCookie("", true));
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Exception e = (Exception) request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		out.println(createdErrorJsonObject(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage()));
	}
	
	private String createdErrorJsonObject(int statusCode, String errorMessage) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("status", statusCode);
		node.put("message", errorMessage);
		node.put("hasError", Boolean.TRUE);
		return node.toString();
	}
}
