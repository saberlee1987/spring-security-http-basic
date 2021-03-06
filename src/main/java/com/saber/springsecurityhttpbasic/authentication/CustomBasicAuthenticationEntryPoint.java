package com.saber.springsecurityhttpbasic.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saber.springsecurityhttpbasic.dto.ServiceErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
	private final ObjectMapper mapper;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		ServiceErrorResponse errorResponse = new ServiceErrorResponse();
		errorResponse.setCode(7);
		errorResponse.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		errorResponse.setOriginalMessage(String.format("{\"code\":%d,\"text\":\"%s\"}", HttpStatus.UNAUTHORIZED.value(), authException.getLocalizedMessage()));
		try {
			writer.println(mapper.writeValueAsString(errorResponse));
		} catch (Exception ex) {
			writer.println(errorResponse);
		}
	}
	
	@Override
	public void afterPropertiesSet() {
		setRealmName("saber");
		super.afterPropertiesSet();
	}
}
