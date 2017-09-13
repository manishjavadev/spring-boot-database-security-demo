package com.manish.javadev.app.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * This class will be used to send response when credential is no longer
 * authorized. If the authentication event was successful, or authentication was
 * not attempted because the HTTP header did not contain a supported
 * Authentication request, the filter chain will continue as normal. this class
 * call will be called only and only when authentication fails.
 * 
 * 
 * @author Manish
 *
 */

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	/**
	 * 
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("Security Stack");
	}
}
